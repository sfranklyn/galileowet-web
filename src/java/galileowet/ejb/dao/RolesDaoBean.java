/*
 * RolesDaoBeanBean.java
 * 
 * Created on Aug 10, 2009, 3:38:48 PM
 */
package galileowet.ejb.dao;

import galileowet.jpa.Roles;
import java.util.ArrayList;
import java.util.List;
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
public class RolesDaoBean implements RolesDaoRemote {

    @PersistenceContext
    private EntityManager em;

    public Boolean isAdminByUserName(String userName) {
        Query query = em.createNamedQuery("Roles.isAdminByUserName");
        query.setParameter("userName", userName);
        if (query.getResultList().size() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> getMenuList(String userName) {
        Query query = em.createNamedQuery("Roles.selectMenuByUserName");
        query.setParameter("userName", userName);
        try {
            return (List<String>) query.getResultList();
        } catch (NoResultException ex) {
        }
        return new ArrayList<String>();
    }

    public Roles selectByRoleName(String roleName) {
        Query query = em.createNamedQuery("Roles.selectByRoleName");
        query.setParameter("roleName", roleName);
        Roles roles = null;
        try {
            roles = (Roles) query.getSingleResult();
        } catch (NoResultException ex) {
        }
        return roles;
    }

        public Roles selectByUserName(String userName) {
        Query query = em.createNamedQuery("Roles.selectByUserName");
        query.setParameter("userName", userName);
        Roles roles = null;
        try {
            roles = (Roles) query.getSingleResult();
        } catch (NoResultException ex) {
        }
        return roles;
    }

    public void insert(Roles roles) {
        em.persist(roles);
        em.flush();
    }

    public void delete(Integer roleId) {
        Roles roles = em.find(Roles.class, roleId);
        em.remove(roles);
        em.flush();
    }

    public void update(Roles roles) {
        em.merge(roles);
        em.flush();
    }
}
