/*
 * UsersDataModelBean.java
 * 
 * Created on Sep 3, 2009, 4:43:18 PM
 */
package galileowet.ejb.datamodel;

import javax.ejb.Stateless;

/**
 *
 * @author Samuel Franklyn
 */
@Stateless
public class UsersDataModelBean extends BaseDataModelBean implements UsersDataModelRemote {

    public static final String SELECT_ALL = "Users.selectAll";
    public static final String SELECT_ALL_COUNT = "Users.selectAllCount";
}
