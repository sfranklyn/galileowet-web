/*
 * PccsServiceRemote.java
 * 
 * Created on Sep 3, 2009, 12:37:12 PM
 */
package galileowet.ejb.service;

import galileowet.jpa.Pccs;
import java.util.List;
import java.util.Locale;
import javax.ejb.Remote;

/**
 *
 * @author Samuel Franklyn
 */
@Remote
public interface PccsServiceRemote {

    List<String> saveCreate(Pccs pccs, Locale locale);

    List<String> saveUpdate(Pccs pccs, Locale locale);

    List<String> saveDelete(Pccs pccs, Locale locale);
}
