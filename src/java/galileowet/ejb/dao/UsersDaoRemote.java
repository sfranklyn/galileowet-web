/*
 * UsersDaoRemote.java
 * 
 * Created on Aug 6, 2009, 3:25:45 PM
 */
package galileowet.ejb.dao;

import galileowet.jpa.Users;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;

/**
 *
 * @author Samuel Franklyn
 */
@Remote
public interface UsersDaoRemote {

    public List<Users> selectByUserIdUrl(Map<String, Object> param);

    void insert(Users users);

    void delete(Integer userId);

    void update(Users users);

    Users selectByUserName(String userName);

    List<Users> selectLikeUserName(String userName);
}
