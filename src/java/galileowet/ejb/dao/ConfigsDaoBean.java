/*
 * ConfigsDaoBean.java
 * 
 * Created on Sep 7, 2009, 2:35:31 PM
 */
package galileowet.ejb.dao;

import galileowet.jpa.Configs;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Samuel Franklyn
 */
@Stateless
public class ConfigsDaoBean implements ConfigsDaoRemote {

    @PersistenceContext
    private EntityManager em;

    public void insert(Configs configs) {
        em.persist(configs);
        em.flush();
    }

    public void delete(Integer configId) {
        Configs configs = em.find(Configs.class, configId);
        em.remove(configs);
        em.flush();
    }

    public void update(Configs configs) {
        em.merge(configs);
        em.flush();
    }
}
