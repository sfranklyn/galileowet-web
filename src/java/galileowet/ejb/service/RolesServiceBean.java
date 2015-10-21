/*
 * RolesServiceBean.java
 * 
 * Created on Sep 4, 2009, 3:09:32 PM
 */
package galileowet.ejb.service;

import galileowet.ejb.dao.RolesDaoRemote;
import galileowet.jpa.Roles;
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
public class RolesServiceBean implements RolesServiceRemote {

    private static final String MESSAGES = "messages";
    @EJB
    private RolesDaoRemote rolesDaoRemote = null;

    public List<String> saveCreate(Roles roles, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if ("".equals(roles.getRoleName())) {
            errorList.add(messageSource.getString("role_name_required"));
        }
        if ("".equals(roles.getRoleDesc())) {
            errorList.add(messageSource.getString("role_desc_required"));
        }
        if ("".equals(roles.getRoleMenu())) {
            errorList.add(messageSource.getString("role_menu_required"));
        }
        if (errorList.size() == 0) {
            try {
                rolesDaoRemote.insert(roles);
            } catch (Exception ex) {
                Throwable cause = ex.getCause();
                boolean duplicate = false;
                while (cause != null) {
                    if (cause.toString().contains("Duplicate entry")) {
                        errorList.add(messageSource.getString("role_name_already_registered"));
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

    public List<String> saveUpdate(Roles roles, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if (roles.getRoleId() == null || roles.getRoleId().intValue() == 0) {
            errorList.add(messageSource.getString("role_id_required"));
        }
        if ("".equals(roles.getRoleName())) {
            errorList.add(messageSource.getString("role_name_required"));
        }
        if ("".equals(roles.getRoleDesc())) {
            errorList.add(messageSource.getString("role_desc_required"));
        }
        if ("".equals(roles.getRoleMenu())) {
            errorList.add(messageSource.getString("role_menu_required"));
        }
        if (errorList.size() == 0) {
            try {
                rolesDaoRemote.update(roles);
            } catch (Exception ex) {
                errorList.add(ex.toString());
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
            }
        }
        return errorList;
    }

    public List<String> saveDelete(Roles roles, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if (roles.getRoleId() == null || roles.getRoleId().intValue() == 0) {
            errorList.add(messageSource.getString("role_id_required"));
        }
        if (errorList.size() == 0) {
            try {
                rolesDaoRemote.delete(roles.getRoleId());
            } catch (Exception ex) {
                errorList.add(ex.toString());
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
            }
        }
        return errorList;
    }
}
