/*
 * UsersServiceBean.java
 * 
 * Created on Aug 10, 2009, 4:33:42 PM
 */
package galileowet.ejb.service;

import galileowet.ejb.dao.PccsDaoRemote;
import galileowet.ejb.dao.UsersDaoRemote;
import galileowet.ejb.dao.UsersRolesDaoRemote;
import galileowet.jpa.Pccs;
import galileowet.jpa.Users;
import galileowet.jpa.UsersRoles;
import java.util.ArrayList;
import java.util.Date;
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
public class UsersServiceBean implements UsersServiceRemote {

    private static final String MESSAGES = "messages";
    @EJB
    private UsersDaoRemote usersDaoRemote;
    @EJB
    private AppServiceRemote appServiceRemote;
    @EJB
    private UsersRolesDaoRemote usersRolesDaoRemote;
    @EJB
    private PccsDaoRemote pccsDaoRemote;

    public List<String> logIn(String userName, String userPassword,
            Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if (userName == null) {
            errorList.add(messageSource.getString("user_name_required"));
        }
        if ("".equals(userName)) {
            errorList.add(messageSource.getString("user_name_required"));
        }
        if (userPassword == null) {
            errorList.add(messageSource.getString("user_password_required"));
        }
        if ("".equals(userPassword)) {
            errorList.add(messageSource.getString("user_password_required"));
        }
        if (errorList.size() > 0) {
            return errorList;
        }
        Users users = usersDaoRemote.selectByUserName(userName);
        if (users == null) {
            errorList.add(messageSource.getString("user_name_not_registered"));
            return errorList;
        }
        if (!appServiceRemote.hashPassword(userPassword).equals(users.getUserPassword())) {
            errorList.add(messageSource.getString("user_password_not_match"));
        }
        if(errorList.isEmpty()) {
            users.setUserLastLogin(new Date());
            usersDaoRemote.update(users);
        }        
        return errorList;
    }

    public List<String> saveChangePassword(Users users,
            String userPassword1, String userPassword2, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if ("".equals(userPassword1)) {
            errorList.add(messageSource.getString("user_password_required"));
        }
        if ("".equals(userPassword2)) {
            errorList.add(messageSource.getString("reconfirm_user_password_required"));
        }
        if (!userPassword1.equals(userPassword2)) {
            errorList.add(messageSource.getString("user_password_reconfirm_not_match"));
        }
        if (errorList.isEmpty()) {
            try {
                users.setUserPassword(appServiceRemote.hashPassword(userPassword1));
                usersDaoRemote.update(users);
            } catch (Exception ex) {
                errorList.add(ex.toString());
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
            }
        }
        return errorList;
    }

    public List<String> saveCreate(Users users, String userPassword1, String userPassword2, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if ("".equals(users.getUserName())) {
            errorList.add(messageSource.getString("user_name_required"));
        }
        if ("".equals(users.getUserHcm())) {
            errorList.add(messageSource.getString("user_hcm_required"));
        }
        if (users.getPccId() == null) {
            errorList.add(messageSource.getString("pcc_pcc_required"));
        } else {
            if ("".equals(users.getPccId().getPccPcc())) {
                errorList.add(messageSource.getString("pcc_pcc_required"));
            } else {
                Pccs pcc = pccsDaoRemote.selectByPcc(users.getPccId().getPccPcc());
                if (pcc == null) {
                    errorList.add(messageSource.getString("pcc_pcc_not_registered"));
                }
                users.setPccId(pcc);
            }
        }
        if ("".equals(userPassword1)) {
            errorList.add(messageSource.getString("user_password_required"));
        }
        if ("".equals(userPassword2)) {
            errorList.add(messageSource.getString("reconfirm_user_password_required"));
        }
        if (!userPassword1.equals(userPassword2)) {
            errorList.add(messageSource.getString("user_password_reconfirm_not_match"));
        }
        if (errorList.isEmpty()) {
            if (userPassword1.length() < 5) {
                errorList.add(messageSource.getString("user_password_not_long_enough"));
            }
        }
        if ("".equals(users.getUserFname())) {
            errorList.add(messageSource.getString("user_fname_required"));
        }
        if ("".equals(users.getUserLname())) {
            errorList.add(messageSource.getString("user_lname_required"));
        }
        if (errorList.isEmpty()) {
            try {
                users.setUserPassword(appServiceRemote.hashPassword(userPassword1));
                usersDaoRemote.insert(users);
            } catch (Exception ex) {
                Throwable cause = ex.getCause();
                boolean duplicate = false;
                while (cause != null) {
                    if (cause.toString().contains("Duplicate entry")) {
                        errorList.add(messageSource.getString("user_name_already_registered"));
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

    public List<String> saveUpdate(Users users, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if (users.getUserId() == null || users.getUserId().intValue() == 0) {
            errorList.add(messageSource.getString("user_id_required"));
        }
        if ("".equals(users.getUserName())) {
            errorList.add(messageSource.getString("user_name_required"));
        }
        if ("".equals(users.getUserHcm())) {
            errorList.add(messageSource.getString("user_hcm_required"));
        }
        if (users.getPccId() == null) {
            errorList.add(messageSource.getString("pcc_pcc_required"));
        } else {
            if ("".equals(users.getPccId().getPccPcc())) {
                errorList.add(messageSource.getString("pcc_pcc_required"));
            } else {
                Pccs pcc = pccsDaoRemote.selectByPcc(users.getPccId().getPccPcc());
                if (pcc == null) {
                    errorList.add(messageSource.getString("pcc_pcc_not_registered"));
                }
                users.setPccId(pcc);
            }
        }
        if ("".equals(users.getUserFname())) {
            errorList.add(messageSource.getString("user_fname_required"));
        }
        if ("".equals(users.getUserLname())) {
            errorList.add(messageSource.getString("user_lname_required"));
        }
        if (errorList.isEmpty()) {
            try {
                usersDaoRemote.update(users);
            } catch (Exception ex) {
                errorList.add(ex.toString());
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
            }
        }
        return errorList;
    }

    public List<String> saveDelete(Users users, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if (users.getUserId() == null || users.getUserId().intValue() == 0) {
            errorList.add(messageSource.getString("user_id_required"));
        }
        if (errorList.isEmpty()) {
            try {
                List<UsersRoles> usersRolesList = usersRolesDaoRemote.selectByUserId(users.getUserId());
                for (UsersRoles usersRoles : usersRolesList) {
                    usersRolesDaoRemote.delete(usersRoles.getUsersRolesPK());
                }
                usersDaoRemote.delete(users.getUserId());
            } catch (Exception ex) {
                errorList.add(ex.toString());
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
            }
        }
        return errorList;
    }

    public String getMsgString(String key, Locale locale) {
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        return messageSource.getString(key);
    }

}
