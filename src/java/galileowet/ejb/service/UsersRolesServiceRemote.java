/*
 * UsersRolesServiceRemote.java
 * 
 * Created on Sep 7, 2009, 10:20:00 AM
 */
package galileowet.ejb.service;

import galileowet.jpa.UsersRoles;
import java.util.List;
import java.util.Locale;
import javax.ejb.Remote;

/**
 *
 * @author Samuel Franklyn
 */
@Remote
public interface UsersRolesServiceRemote {

    List<String> saveCreate(UsersRoles usersRoles, Locale locale);

    List<String> saveDelete(UsersRoles usersRoles, Locale locale);
}
