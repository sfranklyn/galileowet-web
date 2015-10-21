/*
 * UsersRolesDaoRemote.java
 * 
 * Created on Aug 10, 2009, 3:40:50 PM
 */
package galileowet.ejb.dao;

import galileowet.jpa.UsersRoles;
import galileowet.jpa.UsersRolesPK;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Samuel Franklyn
 */
@Remote
public interface UsersRolesDaoRemote {

    void insert(UsersRoles usersRoles);

    void delete(UsersRolesPK usersRolesPK);

    void update(UsersRoles usersRoles);

    List<UsersRoles> selectByUserId(int userId);

    UsersRoles selectByUserNameRoleName(String userName, String roleName);
}
