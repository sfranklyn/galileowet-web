/*
 * UrlsRolesServiceBean.java
 * 
 * Created on Sep 7, 2009, 1:50:59 PM
 */
package galileowet.ejb.service;

import galileowet.ejb.dao.RolesDaoRemote;
import galileowet.ejb.dao.UrlsRolesDaoRemote;
import galileowet.jpa.Roles;
import galileowet.jpa.UrlsRoles;
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
public class UrlsRolesServiceBean implements UrlsRolesServiceRemote {

    private static final String MESSAGES = "messages";
    @EJB
    private RolesDaoRemote rolesDaoRemote = null;
    @EJB
    private UrlsRolesDaoRemote urlsRolesDaoRemote = null;

    public List<String> saveCreate(UrlsRoles urlsRoles, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if ("".equals(urlsRoles.getUrlsRolesPK().getUrlRole())) {
            errorList.add(messageSource.getString("url_role_required"));
        }
        if ("".equals(urlsRoles.getRoles().getRoleName())) {
            errorList.add(messageSource.getString("role_name_required"));
        }
        if (errorList.size() == 0) {
            try {
                Roles roles = rolesDaoRemote.selectByRoleName(urlsRoles.getRoles().getRoleName());
                urlsRoles.setRoles(roles);
                urlsRoles.getUrlsRolesPK().setRoleId(roles.getRoleId());
                urlsRolesDaoRemote.insert(urlsRoles);
            } catch (Exception ex) {
                Throwable cause = ex.getCause();
                boolean duplicate = false;
                while (cause != null) {
                    if (cause.toString().contains("Duplicate entry")) {
                        errorList.add(messageSource.getString("urls_roles_already_registered"));
                        duplicate = true;
                    }
                    cause = cause.getCause();
                }
                if (errorList.size() == 0) {
                    errorList.add(ex.toString());
                }
                if (!duplicate) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
                }
            }
        }
        return errorList;
    }

    public List<String> saveDelete(UrlsRoles urlsRoles, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if ("".equals(urlsRoles.getUrlsRolesPK().getUrlRole())) {
            errorList.add(messageSource.getString("url_role_required"));
        }
        if ("".equals(urlsRoles.getRoles().getRoleName())) {
            errorList.add(messageSource.getString("role_name_required"));
        }
        if (errorList.size() == 0) {
            try {
                urlsRolesDaoRemote.delete(urlsRoles.getUrlsRolesPK());
            } catch (Exception ex) {
                errorList.add(ex.toString());
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
            }
        }
        return errorList;
    }
}
