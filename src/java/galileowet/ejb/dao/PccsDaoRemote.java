/*
 * PccsDaoRemote.java
 * 
 * Created on Aug 13, 2009, 2:17:33 PM
 */
package galileowet.ejb.dao;

import galileowet.jpa.Pccs;
import javax.ejb.Remote;

/**
 *
 * @author Samuel Franklyn
 */
@Remote
public interface PccsDaoRemote {

    void insert(Pccs pccs);

    void delete(Integer pccsId);

    void update(Pccs pccs);

    Pccs selectByPcc(String pccPcc);
}
