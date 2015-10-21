/*
 * UrlsRolesServiceRemote.java
 * 
 * Created on Sep 7, 2009, 1:50:59 PM
 */
package galileowet.ejb.service;

import galileowet.jpa.UrlsRoles;
import java.util.List;
import java.util.Locale;
import javax.ejb.Remote;

/**
 *
 * @author Samuel Franklyn
 */
@Remote
public interface UrlsRolesServiceRemote {

    List<String> saveCreate(UrlsRoles urlsRoles, Locale locale);

    List<String> saveDelete(UrlsRoles urlsRoles, Locale locale);
}
