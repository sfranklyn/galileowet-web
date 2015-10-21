/*
 * UsersDaoBean.java
 * 
 * Created on Aug 6, 2009, 3:25:45 PM
 */
package galileowet.ejb.dao;

import galileowet.jpa.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class UsersDaoBean implements UsersDaoRemote {

    @PersistenceContext
    private EntityManager em;

    public Users selectByUserName(String userName) {
        Query query = em.createNamedQuery("Users.selectByUserName");
        query.setParameter("userName", userName);
        Users users = null;
        try {
            users = (Users) query.getSingleResult();
        } catch (NoResultException ex) {
        }
        return users;
    }

    public void insert(Users users) {
        em.persist(users);
        em.flush();
    }

    public void delete(Integer userId) {
        Users users = em.find(Users.class, userId);
        em.remove(users);
        em.flush();
    }

    public void update(Users users) {
        em.merge(users);
        em.flush();
    }

    @SuppressWarnings("unchecked")
    public List<Users> selectByUserIdUrl(Map<String, Object> param) {
        Query query = em.createNamedQuery("Users.selectByUserIdUrl");
        query.setParameter("userId", param.get("userId"));
        query.setParameter("urlRole", param.get("urlRole"));
        try {
            return query.getResultList();
        } catch (NoResultException ex) {
        }
        return new ArrayList<Users>();
    }

    @SuppressWarnings("unchecked")
    public List<Users> selectLikeUserName(String userName) {
        Query query = em.createNamedQuery("Users.likeUserName");
        query.setParameter("userName", userName + "%");
        List<Users> usersList = null;
        try {
            usersList = (List<Users>) query.getResultList();
        } catch (NoResultException ex) {
        }
        return usersList;
    }
}
