/*
 * VisitBean.java
 * 
 * Created on Aug 10, 2009, 10:38:40 AM
 */
package galileowet.jsf.beans;

import com.google.inject.servlet.SessionScoped;
import galileowet.ejb.service.XmlSelect;
import galileowet.jpa.Users;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.faces.context.FacesContext;
import org.primefaces.optimus.config.Scope;
import org.primefaces.optimus.config.annotations.Controller;

/**
 *
 * @author Samuel Franklyn
 */
@Controller(name = "visit", scope = Scope.SESSION)
@SessionScoped
public class VisitBean {

    private static final Locale localeId = new Locale("in", "ID");
    private static final Locale localeUs = new Locale("en", "US");
    private static final String RESTRICTED = "RESTRICTED COMMAND";
    private Users users;
    private String securePath;
    private List menuList;
    private Boolean isAdmin;
    private Locale locale;
    private String terminalText;
    private String terminalCommand;
    private Boolean isSignOn = Boolean.FALSE;
    private static final Pattern pattern = Pattern.compile("\\r", Pattern.DOTALL);
    private XmlSelect xs;
    private String sutaKey;
    private List restrictedCmds;
    private String queueNbr = "";

    public String getQueueNbr() {
        return queueNbr;
    }

    public void setQueueNbr(String queueNbr) {
        this.queueNbr = queueNbr;
    }

    public List getRestrictedCmds() {
        return restrictedCmds;
    }

    public void setRestrictedCmds(List restrictedCmds) {
        this.restrictedCmds = restrictedCmds;
    }

    public Locale getLocale() {
        if (locale == null) {
            locale = localeId;
        }
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        return locale;
    }

    public ResourceBundle getMessageSource() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        return FacesContext.getCurrentInstance().getApplication().getResourceBundle(ctx, "messages");
    }

    public String chooseEnglish() {
        locale = localeUs;
        return null;
    }

    public String chooseIndonesia() {
        locale = localeId;
        return null;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(final Users users) {
        this.users = users;
    }

    public String getSecurePath() {
        return securePath;
    }

    public void setSecurePath(final String securePath) {
        this.securePath = securePath;
    }

    public List getMenuList() {
        return menuList;
    }

    public void setMenuList(final List menuList) {
        this.menuList = menuList;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getTerminalText() {
        return terminalText;
    }

    public void setTerminalText(String terminalText) {
        this.terminalText = terminalText;
    }

    private void execTerminalCmd() {
        ExecuteTerminalCommand execTermCommand = new ExecuteTerminalCommand(terminalCommand);
        ExecutorService es = Executors.newSingleThreadExecutor();
        try {
            this.terminalText = "";
            this.terminalText = es.submit(execTermCommand).get(60, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            Logger.getLogger(VisitBean.class.getName()).log(Level.SEVERE, "GWET0001:" + ex.getMessage(), ex);
            this.terminalText = ex.getMessage();
        }
        es.shutdown();
    }

    public void execTerminalCommand() {
        if (isRestrictedCommand(terminalCommand)) {
            terminalCommand = "";
            this.terminalText = RESTRICTED;
            return;
        }

        execTerminalCmd();

        // After an end transaction, put BF to a configured queue for that PCC
        if (!queueNbr.isEmpty()) {
            if (terminalCommand.equals("E") || terminalCommand.equals("ER") || terminalCommand.equals("ET")) {
                if (this.terminalText.startsWith("EOK") || this.terminalText.startsWith("OK")) {
                    String response = this.terminalText;
                    String recloc = getRecLoc(response);
                    terminalCommand = "*" + recloc;
                    execTerminalCmd();
                    terminalCommand = "QEB/" + queueNbr;
                    execTerminalCmd();
                    this.terminalText = response;
                }
            }
        }

        terminalCommand = "";
    }

    private String getRecLoc(String response) {
        String[] str = response.split(" ");
        return str[2].substring(0, 6);
    }

    private boolean isRestrictedCommand(String cmd) {
        if (restrictedCmds == null) {
            return false;
        }
        Iterator it = restrictedCmds.iterator();
        while (it.hasNext()) {
            String s = (String) it.next();
            if (cmd.startsWith(s.trim().toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public String execute() {
        if (terminalCommand.startsWith("STD")) {
            terminalCommand = "";
            return "redirect:secure/change_signon_password";
        }
        execTerminalCommand();
        if (this.terminalText.contains("]STD")) {
            return "redirect:secure/change_signon_password";
        }
        return null;
    }

    public String getTerminalCommand() {
        return terminalCommand;
    }

    public void setTerminalCommand(String terminalCommand) {
        this.terminalCommand = terminalCommand.toUpperCase();
    }

    public String getSutaKey() {
        return sutaKey;
    }

    public void setSutaKey(String sutaKey) {
        this.sutaKey = sutaKey;
    }

    class ExecuteTerminalCommand implements Callable<String> {

        private String termCommand = null;

        public ExecuteTerminalCommand(String termCommand) {
            this.termCommand = termCommand;
        }

        public String call() throws Exception {
            String termText = "";

            if (xs != null) {
                Logger.getLogger(VisitBean.class.getName()).log(Level.INFO, "GWET0001:entry={0}", termCommand);
                String response = xs.terminalSubmit(termCommand, sutaKey);
                String[] terminalLines = pattern.split(response);
                termText = "";
                int pos = 0, lineMax = 64;
                for (String terminalLine : terminalLines) {
                    if (terminalLine.length() > 64) {
                        while (pos < terminalLine.length()) {
                            if (pos + lineMax < terminalLine.length()) {
                                termText += terminalLine.substring(pos, pos + lineMax) + "\n";
                            } else {
                                termText += terminalLine.substring(pos) + "\n";
                            }
                            pos += lineMax;
                        }
                    } else {
                        termText += terminalLine + "\n";
                    }
                }
                Logger.getLogger(VisitBean.class.getName()).log(Level.INFO, "GWET0001:response=\n{0}", termText);
                if (termCommand.startsWith("SOF")) {
                    isSignOn = Boolean.FALSE;
                    xs.endSession(Integer.MIN_VALUE + 1, sutaKey);
                    xs.destroySuta(sutaKey);
                    xs = null;
                }
            }
            return termText;
        }
    }

    public Boolean getIsSignOn() {
        return isSignOn;
    }

    public void setIsSignOn(Boolean isSignOn) {
        this.isSignOn = isSignOn;
    }

    public String getFocusScript() {
        if (isSignOn) {
            return "document.terminal.elements['terminal:terminalCommand'].focus();";
        } else {
            return "document.signOn.elements['signOn:signOn'].focus();";
        }
    }

    public XmlSelect getXs() {
        return xs;
    }

    public void setXs(XmlSelect xs) {
        this.xs = xs;
    }
}
