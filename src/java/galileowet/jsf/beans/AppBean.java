/*
 * AppBean.java
 * 
 * Created on Aug 10, 2009, 11:02:38 AM
 */
package galileowet.jsf.beans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.optimus.config.Scope;
import org.primefaces.optimus.config.annotations.Controller;

/**
 *
 * @author Samuel Franklyn
 */
@Controller(name = "app", scope = Scope.APPLICATION)
public class AppBean {

    public String getBaseURL() {
        final HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        final String baseURL = "http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/faces/index.xhtml";
        return baseURL;
    }
}
