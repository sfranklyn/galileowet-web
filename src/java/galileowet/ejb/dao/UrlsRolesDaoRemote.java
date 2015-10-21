/*
 * UrlsRolesDaoRemote.java
 * 
 * Created on Aug 10, 2009, 3:42:12 PM
 */
package galileowet.ejb.dao;

import galileowet.jpa.UrlsRoles;
import galileowet.jpa.UrlsRolesPK;
import javax.ejb.Remote;

/**
 *
 * @author Samuel Franklyn
 */
@Remote
public interface UrlsRolesDaoRemote {

    void insert(UrlsRoles urlsRoles);

    void delete(UrlsRolesPK urlsRolesPK);

    void update(UrlsRoles urlsRoles);

    UrlsRoles selectByUrlRoleName(String urlRole,String roleName);
}
