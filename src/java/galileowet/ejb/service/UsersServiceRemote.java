/*
 * UsersServiceRemote.java
 * 
 * Created on Aug 10, 2009, 4:33:42 PM
 */
package galileowet.ejb.service;

import galileowet.jpa.Users;
import java.util.List;
import java.util.Locale;
import javax.ejb.Remote;

/**
 *
 * @author Samuel Franklyn
 */
@Remote
public interface UsersServiceRemote {

    List<String> logIn(String userName, String userPassword, Locale locale);

    List<String> saveChangePassword(Users users, String userPassword1,
            String userPassword2, Locale locale);

    List<String> saveCreate(Users users, String userPassword1,
            String userPassword2, Locale locale);

    List<String> saveUpdate(Users users, Locale locale);

    List<String> saveDelete(Users users, Locale locale);

    String getMsgString(String key, Locale locale);
    
}
