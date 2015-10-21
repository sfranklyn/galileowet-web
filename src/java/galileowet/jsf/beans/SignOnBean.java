/*
 * SignOnBean.java
 * 
 * Created on Sep 2, 2009, 10:08:38 AM
 */
package galileowet.jsf.beans;

import com.galileoindonesia.schema.session.ChangePassword;
import com.galileoindonesia.schema.session.SessionChangePassword10;
import com.galileoindonesia.schema.session.SessionMods;
import com.google.inject.Inject;
import galileowet.ejb.service.XmlSelect;
import galileowet.ejb.service.XsFilter;
import galileowet.ejb.service.XsIdentity;
import java.io.StringWriter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.primefaces.optimus.config.Scope;
import org.primefaces.optimus.config.annotations.Controller;

/**
 *
 * @author Samuel Franklyn
 */
@Controller(name = "signOn", scope = Scope.SESSION)
public class SignOnBean {

    private static final String MESSAGES = "webmessages";
    private static final String ERR = "ERR:";
    private ResourceBundle messageSource;
    private String signOn = " ";
    private String userPassword1 = "";
    private String userPassword2 = "";
    private String password = "";
    @Inject
    private VisitBean visit;
    @Inject
    private XmlSelect xmlSelect;

    public String doSignOn() {
        messageSource = ResourceBundle.getBundle(MESSAGES, visit.getLocale());
        try {
            visit.setSutaKey(xmlSelect.createSuta());
            xmlSelect.setHcmName(visit.getUsers().getUserHcm(), visit.getSutaKey());
            xmlSelect.setTerminalTimeout(30, visit.getSutaKey());
            xmlSelect.beginSession(Integer.MIN_VALUE, visit.getSutaKey());
            visit.setXs(xmlSelect);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), ""));
            Logger.getLogger(LogInBean.class.getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
            if (xmlSelect != null) {
                xmlSelect.endSession(Integer.MIN_VALUE + 1, visit.getSutaKey());
                xmlSelect.destroySuta(visit.getSutaKey());
                visit.setXs(null);
            }
            return null;
        }

        signOn = signOn.trim().toUpperCase();
        if (signOn.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            messageSource.getString("signon_required"), ""));
            signOn = " ";
            xmlSelect.endSession(Integer.MIN_VALUE + 1, visit.getSutaKey());
            xmlSelect.destroySuta(visit.getSutaKey());
            visit.setXs(null);
            return null;
        }
        password = password.trim().toUpperCase();
        if (password.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            messageSource.getString("password_required"), ""));
            xmlSelect.endSession(Integer.MIN_VALUE + 1, visit.getSutaKey());
            xmlSelect.destroySuta(visit.getSutaKey());
            visit.setXs(null);
            return null;
        }
        visit.setTerminalCommand("SON/" + signOn);
        visit.execTerminalCommand();
        if (visit.getTerminalText().contains(ERR)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            visit.getTerminalText(), ""));
            xmlSelect.endSession(Integer.MIN_VALUE + 1, visit.getSutaKey());
            xmlSelect.destroySuta(visit.getSutaKey());
            visit.setXs(null);
            return null;
        }
        visit.setTerminalCommand("PASSWORD? " + password);
        visit.execTerminalCommand();
        if (visit.getTerminalText().contains(ERR)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            visit.getTerminalText(), ""));
            xmlSelect.endSession(Integer.MIN_VALUE + 1, visit.getSutaKey());
            xmlSelect.destroySuta(visit.getSutaKey());
            visit.setXs(null);
            return null;
        }
        visit.setIsSignOn(Boolean.TRUE);
        return null;
    }

    public String changeSignOnPassword() {
        String result = "secure/index";
        visit.setTerminalText("");

        try {
            if (userPassword1.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                messageSource.getString("password_required"), ""));
                return null;
            }
            if (userPassword2.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                messageSource.getString("reconfirm_user_password"), ""));
                return null;
            }
            if (!userPassword1.equals(userPassword2)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                messageSource.getString("user_password_reconfirm_not_match"), ""));
                return null;

            }

            SessionChangePassword10 scp = new SessionChangePassword10();
            SessionMods sm = new SessionMods();
            scp.setSessionMods(sm);
            ChangePassword cp = new ChangePassword();
            sm.setChangePassword(cp);
            cp.setNewPwd(userPassword1.toUpperCase());
            cp.setNewKeyword("ABCDEF");
            JAXBContext jc = JAXBContext.newInstance("com.galileoindonesia.schema.session");
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            m.marshal(scp, sw);
            String strRequest = sw.toString();
            Logger.getLogger(SignOnBean.class.getName()).log(Level.INFO, strRequest);
            String identity = XsIdentity.identity(signOn,
                    visit.getUsers().getPccId().getPccPcc());
            Logger.getLogger(SignOnBean.class.getName()).log(Level.INFO,
                    xmlSelect.syncSubmit(identity,
                            strRequest, XsFilter.ALL, visit.getSutaKey()));            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(), ""));
            Logger.getLogger(SignOnBean.class.getName()).log(Level.SEVERE, null, ex);
            result = "redirect:secure/change_signon_password";
        }

        return result;
    }

    public String getSignOn() {
        return signOn;
    }

    public void setSignOn(String signOn) {
        this.signOn = signOn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userPassword1
     */
    public String getUserPassword1() {
        return userPassword1;
    }

    /**
     * @param userPassword1 the userPassword1 to set
     */
    public void setUserPassword1(String userPassword1) {
        this.userPassword1 = userPassword1;
    }

    /**
     * @return the userPassword2
     */
    public String getUserPassword2() {
        return userPassword2;
    }

    /**
     * @param userPassword2 the userPassword2 to set
     */
    public void setUserPassword2(String userPassword2) {
        this.userPassword2 = userPassword2;
    }
}
