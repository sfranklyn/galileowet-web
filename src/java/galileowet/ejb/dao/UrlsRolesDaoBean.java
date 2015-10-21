/*
 * UrlsRolesDaoBean.java
 * 
 * Created on Aug 10, 2009, 3:42:12 PM
 */
package galileowet.ejb.dao;

import galileowet.jpa.UrlsRoles;
import galileowet.jpa.UrlsRolesPK;
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
public class UrlsRolesDaoBean implements UrlsRolesDaoRemote {

    @PersistenceContext
    private EntityManager em;

    public void insert(UrlsRoles urlsRoles) {
        em.persist(urlsRoles);
        em.flush();
    }

    public void delete(UrlsRolesPK urlsRolesPK) {
        UrlsRoles urlsRoles = em.find(UrlsRoles.class, urlsRolesPK);
        em.remove(urlsRoles);
        em.flush();
    }

    public void update(UrlsRoles urlsRoles) {
        em.merge(urlsRoles);
        em.flush();
    }

    public UrlsRoles selectByUrlRoleName(String urlRole,String roleName) {
        Query query = em.createNamedQuery("UrlsRoles.selectByUrlRoleName");
        query.setParameter("urlRole", urlRole);
        query.setParameter("roleName", roleName);
        UrlsRoles usersRoles = null;
        try {
            usersRoles = (UrlsRoles) query.getSingleResult();
        } catch (NoResultException ex) {
        }
        return usersRoles;
        
    }
}
