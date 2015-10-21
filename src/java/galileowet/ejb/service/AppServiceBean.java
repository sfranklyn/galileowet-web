/*
 * AppServiceBean.java
 * 
 * Created on Aug 11, 2009, 2:46:53 PM
 */
package galileowet.ejb.service;

import galileowet.ejb.dao.*;
import galileowet.jpa.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Samuel Franklyn
 */
@Stateless
public class AppServiceBean implements AppServiceRemote {

    private MessageDigest messageDigest = null;
    @EJB
    private PccsDaoRemote pccsDaoRemote = null;
    @EJB
    private UsersDaoRemote usersDaoRemote = null;
    @EJB
    private RolesDaoRemote rolesDaoRemote = null;
    @EJB
    private UsersRolesDaoRemote usersRolesDaoRemote = null;
    @EJB
    private UrlsRolesDaoRemote urlsRolesDaoRemote = null;

    public AppServiceBean() {
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
        }
    }

    private String getHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    public String hashPassword(String password) {
        byte[] hash = null;
        try {
            messageDigest.reset();
            hash = messageDigest.digest(password.getBytes());
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
        }
        return getHexString(hash);
    }

    private Roles insertRoleIfNotExist(String roleName, String roleDesc, String roleMenu) {
        Roles roles = rolesDaoRemote.selectByRoleName(roleName);
        if (roles == null) {
            roles = new Roles();
            roles.setRoleName(roleName);
            roles.setRoleDesc(roleDesc);
            roles.setRoleMenu(roleMenu);
            rolesDaoRemote.insert(roles);
            roles = rolesDaoRemote.selectByRoleName(roleName);
        }
        return roles;
    }

    private void insertUsersRolesIfNotExist(Users users, Roles roles) {
        UsersRoles usersRoles = usersRolesDaoRemote.selectByUserNameRoleName(users.getUserName(), roles.getRoleName());
        if (usersRoles == null) {
            usersRoles = new UsersRoles();
            usersRoles.setUsers(users);
            usersRoles.setRoles(roles);
            usersRoles.setUsersRolesPK(new UsersRolesPK());
            usersRoles.getUsersRolesPK().setRoleId(roles.getRoleId());
            usersRoles.getUsersRolesPK().setUserId(users.getUserId());
            usersRoles.setUsersRolesDesc(users.getUserName() + " " + roles.getRoleName());
            usersRolesDaoRemote.insert(usersRoles);
        }
    }

    private void insertUrlsRolesIfNotExist(String url, Roles roles) {
        UrlsRoles urlsRoles = urlsRolesDaoRemote.selectByUrlRoleName(url, roles.getRoleName());
        if (urlsRoles == null) {
            urlsRoles = new UrlsRoles();
            urlsRoles.setUrlsRolesPK(new UrlsRolesPK());
            urlsRoles.setRoles(roles);
            urlsRoles.getUrlsRolesPK().setUrlRole(url);
            urlsRoles.getUrlsRolesPK().setRoleId(roles.getRoleId());
            urlsRolesDaoRemote.insert(urlsRoles);
        }
    }

    private void insertPccsUrlsRoles(Roles roles) {
        String url = "/galileowet-web/faces/secure/pccs.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/pccsCreate.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/pccsRead.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/pccsUpdate.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/pccsDelete.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
    }

    private void insertUsersUrlsRoles(Roles roles) {
        String url = "/galileowet-web/faces/secure/users.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/usersCreate.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/usersRead.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/usersUpdate.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/usersDelete.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
    }

    private void insertRolesUrlsRoles(Roles roles) {
        String url = "/galileowet-web/faces/secure/roles.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/rolesCreate.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/rolesRead.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/rolesUpdate.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/rolesDelete.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
    }

    private void insertUsersRolesUrlsRoles(Roles roles) {
        String url = "/galileowet-web/faces/secure/users_roles.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/users_rolesCreate.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/users_rolesRead.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/users_rolesDelete.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
    }

    private void insertUrlsRolesUrlsRoles(Roles roles) {
        String url = "/galileowet-web/faces/secure/urls_roles.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/urls_rolesCreate.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/urls_rolesRead.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/urls_rolesDelete.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
    }

    private void insertConfigsUrlsRoles(Roles roles) {
        String url = "/galileowet-web/faces/secure/configs.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/configsCreate.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/configsRead.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/configsUpdate.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
        url = "/galileowet-web/faces/secure/configsDelete.xhtml";
        insertUrlsRolesIfNotExist(url, roles);
    }

    public void initDb() {
        Pccs pccs = pccsDaoRemote.selectByPcc("756O");
        if (pccs == null) {
            pccs = new Pccs();
            pccs.setPccPcc("756O");
            pccs.setPccDesc("Galileo Indonesia");
            pccsDaoRemote.insert(pccs);
            pccs = pccsDaoRemote.selectByPcc("756O");
        }

        String userName = "adm1";
        Users usersAdm = usersDaoRemote.selectByUserName(userName);
        if (usersAdm == null) {
            usersAdm = new Users();
            usersAdm.setUserName(userName);
            usersAdm.setUserHcm("Galileo2");
            usersAdm.setUserPassword(hashPassword("5x5+5=30"));
            usersAdm.setUserFname("First");
            usersAdm.setUserLname("Administrator");
            usersAdm.setPccId(pccs);
            usersDaoRemote.insert(usersAdm);
            usersAdm = usersDaoRemote.selectByUserName("adm1");
        }

        String roleName = "ADM";
        String roleDesc = "Administrator";
        String roleMenu = "adm.xhtml";
        Roles rolesAdm = insertRoleIfNotExist(roleName, roleDesc, roleMenu);

        roleName = "USR";
        roleDesc = "User";
        roleMenu = "user.xhtml";
        Roles rolesUsr = insertRoleIfNotExist(roleName, roleDesc, roleMenu);

        insertUsersRolesIfNotExist(usersAdm, rolesAdm);
        insertUsersRolesIfNotExist(usersAdm, rolesUsr);

        String url = "/galileowet-web/faces/secure/index.xhtml";
        insertUrlsRolesIfNotExist(url, rolesAdm);

        insertPccsUrlsRoles(rolesAdm);
        insertUsersUrlsRoles(rolesAdm);
        insertRolesUrlsRoles(rolesAdm);
        insertUsersRolesUrlsRoles(rolesAdm);
        insertUrlsRolesUrlsRoles(rolesAdm);
        insertConfigsUrlsRoles(rolesAdm);

        url = "/galileowet-web/faces/secure/index.xhtml";
        insertUrlsRolesIfNotExist(url, rolesUsr);
        url = "/galileowet-web/faces/secure/change_password.xhtml";
        insertUrlsRolesIfNotExist(url, rolesUsr);
        url = "/galileowet-web/faces/secure/change_signon_password.xhtml";
        insertUrlsRolesIfNotExist(url, rolesUsr);
        url = "/galileowet-web/faces/secure/signon.xhtml";
        insertUrlsRolesIfNotExist(url, rolesUsr);
        url = "/galileowet-web/faces/secure/terminal.xhtml";
        insertUrlsRolesIfNotExist(url, rolesUsr);
    }
}
