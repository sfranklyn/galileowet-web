/*
 * LogOutBean.java
 * 
 * Created on Aug 12, 2009, 10:27:36 AM
 */
package galileowet.jsf.beans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.optimus.config.Scope;
import org.primefaces.optimus.config.annotations.Controller;

/**
 *
 * @author Samuel Franklyn
 */
@Controller(name = "logOut", scope = Scope.REQUEST)
public class LogOutBean {

    public String logOut() {
        final HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:index";
    }
}
