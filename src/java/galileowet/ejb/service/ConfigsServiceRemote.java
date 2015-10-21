/*
 * ConfigsServiceRemote.java
 * 
 * Created on Sep 7, 2009, 2:33:33 PM
 */
package galileowet.ejb.service;

import galileowet.jpa.Configs;
import java.util.List;
import java.util.Locale;
import javax.ejb.Remote;

/**
 *
 * @author Samuel Franklyn
 */
@Remote
public interface ConfigsServiceRemote {

    Configs getByKey(String configKey);

    List<String> saveCreate(Configs configs, Locale locale);

    List<String> saveUpdate(Configs configs, Locale locale);

    List<String> saveDelete(Configs configs, Locale locale);
}
