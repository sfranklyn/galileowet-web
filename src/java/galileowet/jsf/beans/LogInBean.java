/*
 * LogInBean.java
 * 
 * Created on Aug 12, 2009, 10:27:23 AM
 */
package galileowet.jsf.beans;

import com.google.inject.Inject;
import galileowet.ejb.dao.RolesDaoRemote;
import galileowet.ejb.dao.UsersDaoRemote;
import galileowet.ejb.service.ConfigsServiceRemote;
import galileowet.ejb.service.UsersServiceRemote;
import galileowet.jpa.Configs;
import galileowet.jpa.Roles;
import galileowet.jpa.Users;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.primefaces.optimus.config.Scope;
import org.primefaces.optimus.config.annotations.Controller;

/**
 *
 * @author Samuel Franklyn
 */
@Controller(name = "logIn", scope = Scope.REQUEST)
public class LogInBean {

    private static final Logger log = Logger.getLogger(LogInBean.class.getName());
    private static final String prefix = "RestrictCMD-";
    private static final String qprefix = "Q-";
    private static final String ERR_MSG = "user_does_not_have_role";
    private String userName;
    private String userPassword;
    @Inject
    private AppBean app;
    @Inject
    private VisitBean visit;
    @Inject
    private UsersDaoRemote usersDaoRemote;
    @Inject
    private RolesDaoRemote rolesDaoRemote;
    @Inject
    private UsersServiceRemote usersServiceRemote;
    @Inject
    private ConfigsServiceRemote configsServiceRemote;

    public String logIn() {
        List<String> errorList = usersServiceRemote.logIn(userName, userPassword, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
            return null;
        }

        Users users = usersDaoRemote.selectByUserName(userName);
        visit.setUsers(users);
        visit.setIsAdmin(rolesDaoRemote.isAdminByUserName(userName));
        final List<String> menuList = rolesDaoRemote.getMenuList(userName);
        visit.setMenuList(menuList);

        Configs configs = configsServiceRemote.getByKey(qprefix + visit.getUsers().getPccId().getPccPcc());
        if (configs != null && !configs.getConfigValue().trim().isEmpty()) {
            String queue = configs.getConfigValue().trim();
            if (isValidQueue(queue)) {
                visit.setQueueNbr(queue);
            }
        }

        if (!visit.getIsAdmin()) {
            Roles roles = rolesDaoRemote.selectByUserName(userName);
            if (roles == null) {
                String msg = usersServiceRemote.getMsgString(ERR_MSG, visit.getLocale());
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        msg, ""));
                visit.setUsers(null);
                return null;
            }
            String rolename = roles.getRoleName();
            configs = configsServiceRemote.getByKey(prefix + rolename);
            if (configs != null && !configs.getConfigValue().trim().isEmpty()) {
                String[] arr = configs.getConfigValue().trim().split(",");
                visit.setRestrictedCmds(Arrays.asList(arr));
            }
        }

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(visit.getSecurePath());
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), ""));
        }

        return "redirect:secure/index";
    }

    private boolean isValidQueue(String queue) {
        if ((queue != null) && (!queue.isEmpty())) {
            try {
                int i = Integer.parseInt(queue);
                if ((i > 0) && (i <= 99))
                    return true;
            }
            catch (NumberFormatException e) {
                return false;
            }
            return false;
        }
        return false;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(final String userPassword) {
        this.userPassword = userPassword;
    }

    public RolesDaoRemote getRolesDaoRemote() {
        return rolesDaoRemote;
    }

    public void setRolesDaoRemote(RolesDaoRemote rolesDaoRemote) {
        this.rolesDaoRemote = rolesDaoRemote;
    }

    public AppBean getApp() {
        return app;
    }

    public void setApp(AppBean app) {
        this.app = app;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
