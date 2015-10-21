/*
 * RolesServiceRemote.java
 * 
 * Created on Sep 4, 2009, 3:09:32 PM
 */
package galileowet.ejb.service;

import galileowet.jpa.Roles;
import java.util.List;
import java.util.Locale;
import javax.ejb.Remote;

/**
 *
 * @author Samuel Franklyn
 */
@Remote
public interface RolesServiceRemote {

    List<String> saveCreate(Roles roles, Locale locale);

    List<String> saveUpdate(Roles roles, Locale locale);

    List<String> saveDelete(Roles roles, Locale locale);
}
