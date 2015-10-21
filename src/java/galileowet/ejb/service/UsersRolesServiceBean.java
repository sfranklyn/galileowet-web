/*
 * UsersRolesServiceBean.java
 * 
 * Created on Sep 7, 2009, 10:19:59 AM
 */
package galileowet.ejb.service;

import galileowet.ejb.dao.RolesDaoRemote;
import galileowet.ejb.dao.UsersDaoRemote;
import galileowet.ejb.dao.UsersRolesDaoRemote;
import galileowet.jpa.Roles;
import galileowet.jpa.Users;
import galileowet.jpa.UsersRoles;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Samuel Franklyn
 */
@Stateless
public class UsersRolesServiceBean implements UsersRolesServiceRemote {

    private static final String MESSAGES = "messages";
    @EJB
    private UsersDaoRemote usersDaoRemote = null;
    @EJB
    private RolesDaoRemote rolesDaoRemote = null;
    @EJB
    private UsersRolesDaoRemote usersRolesDaoRemote = null;

    public List<String> saveCreate(UsersRoles usersRoles, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if ("".equals(usersRoles.getUsers().getUserName())) {
            errorList.add(messageSource.getString("user_name_required"));
        }
        if ("".equals(usersRoles.getRoles().getRoleName())) {
            errorList.add(messageSource.getString("role_name_required"));
        }
        Users users = usersDaoRemote.selectByUserName(usersRoles.getUsers().getUserName());
        if (users == null) {
            errorList.add(messageSource.getString("user_name_not_registered"));
        }
        if (errorList.isEmpty()) {
            try {
                Roles roles = rolesDaoRemote.selectByRoleName(usersRoles.getRoles().getRoleName());
                usersRoles.setUsers(users);
                usersRoles.setRoles(roles);
                usersRoles.getUsersRolesPK().setRoleId(roles.getRoleId());
                usersRoles.getUsersRolesPK().setUserId(users.getUserId());
                usersRoles.setUsersRolesDesc(users.getUserName() + " " + roles.getRoleName());
                usersRolesDaoRemote.insert(usersRoles);
            } catch (Exception ex) {
                Throwable cause = ex.getCause();
                boolean duplicate = false;
                while (cause != null) {
                    if (cause.toString().contains("javax.persistence.EntityExistsException")) {
                        errorList.add(messageSource.getString("users_roles_already_registered"));
                        duplicate = true;
                    }
                    cause = cause.getCause();
                }
                if (errorList.isEmpty()) {
                    errorList.add(ex.toString());
                }
                if (!duplicate) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
                }
            }
        }
        return errorList;
    }

    public List<String> saveDelete(UsersRoles usersRoles, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if ("".equals(usersRoles.getUsers().getUserName())) {
            errorList.add(messageSource.getString("user_name_required"));
        }
        if ("".equals(usersRoles.getRoles().getRoleName())) {
            errorList.add(messageSource.getString("role_name_required"));
        }
        if (errorList.isEmpty()) {
            try {
                usersRolesDaoRemote.delete(usersRoles.getUsersRolesPK());
            } catch (Exception ex) {
                errorList.add(ex.toString());
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
            }
        }
        return errorList;
    }
}
