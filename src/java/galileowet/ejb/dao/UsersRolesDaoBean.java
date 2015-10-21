/*
 * UsersRolesDaoBean.java
 * 
 * Created on Aug 10, 2009, 3:40:50 PM
 */
package galileowet.ejb.dao;

import galileowet.jpa.UsersRoles;
import galileowet.jpa.UsersRolesPK;
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
public class UsersRolesDaoBean implements UsersRolesDaoRemote {

    @PersistenceContext
    private EntityManager em;

    public void insert(UsersRoles usersRoles) {
        em.persist(usersRoles);
        em.flush();
    }

    public void delete(UsersRolesPK usersRolesPK) {
        UsersRoles usersRoles = em.find(UsersRoles.class, usersRolesPK);
        em.remove(usersRoles);
        em.flush();
    }

    public void update(UsersRoles usersRoles) {
        em.merge(usersRoles);
        em.flush();
    }

    @SuppressWarnings("unchecked")
    public List<UsersRoles> selectByUserId(int userId) {
        Query query = em.createNamedQuery("UsersRoles.selectByUserId");
        query.setParameter("userId", userId);
        try {
            return query.getResultList();
        } catch (NoResultException ex) {
        }
        return new ArrayList<UsersRoles>();
    }

    public UsersRoles selectByUserNameRoleName(String userName, String roleName) {
        Query query = em.createNamedQuery("UsersRoles.selectByUserNameRoleName");
        query.setParameter("userName", userName);
        query.setParameter("roleName", roleName);
        UsersRoles usersRoles = null;
        try {
            usersRoles = (UsersRoles) query.getSingleResult();
        } catch (NoResultException ex) {
        }
        return usersRoles;
    }
}
