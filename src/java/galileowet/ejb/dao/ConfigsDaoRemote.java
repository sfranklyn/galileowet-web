/*
 * ConfigsDaoRemote.java
 * 
 * Created on Sep 7, 2009, 2:35:31 PM
 */
package galileowet.ejb.dao;

import galileowet.jpa.Configs;
import javax.ejb.Remote;

/**
 *
 * @author Samuel Franklyn
 */
@Remote
public interface ConfigsDaoRemote {

    void insert(Configs configs);

    void delete(Integer configId);

    void update(Configs configs);
}
