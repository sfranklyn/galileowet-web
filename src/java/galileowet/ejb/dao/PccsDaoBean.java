/*
 * PccsDaoBean.java
 * 
 * Created on Aug 13, 2009, 2:17:33 PM
 */
package galileowet.ejb.dao;

import galileowet.jpa.Pccs;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Samuel Franklyn
 */
@Stateless
public class PccsDaoBean implements PccsDaoRemote {

    @PersistenceContext
    private EntityManager em;

    public void insert(Pccs pccs) {
        em.persist(pccs);
        em.flush();
    }

    public void delete(Integer pccsId) {
        Pccs pccs = em.find(Pccs.class, pccsId);
        em.remove(pccs);
        em.flush();
    }

    public void update(Pccs pccs) {
        em.merge(pccs);
        em.flush();
    }

    public Pccs selectByPcc(String pccPcc) {
        Query query = em.createNamedQuery("Pccs.selectByPcc");
        query.setParameter("pccPcc", pccPcc);
        Pccs pccs = null;
        try {
            pccs = (Pccs) query.getSingleResult();
        } catch (NoResultException ex) {
        }
        return pccs;
    }
}
