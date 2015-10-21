/*
 * RolesDaoBeanRemote.java
 * 
 * Created on Aug 10, 2009, 3:38:49 PM
 */
package galileowet.ejb.dao;

import galileowet.jpa.Roles;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Samuel Franklyn
 */
@Remote
public interface RolesDaoRemote {

    Boolean isAdminByUserName(String userName);

    List<String> getMenuList(String userName);

    Roles selectByRoleName(String roleName);

    Roles selectByUserName(String userName);

    void insert(Roles roles);

    void delete(Integer roleId);

    void update(Roles roles);
}
