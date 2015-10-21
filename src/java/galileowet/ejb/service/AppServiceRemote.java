/*
 * AppServiceRemote.java
 * 
 * Created on Aug 11, 2009, 2:46:53 PM
 */
package galileowet.ejb.service;

import javax.ejb.Remote;

/**
 *
 * @author Samuel Franklyn
 */
@Remote
public interface AppServiceRemote {

    String hashPassword(final String password);

    void initDb();
}
