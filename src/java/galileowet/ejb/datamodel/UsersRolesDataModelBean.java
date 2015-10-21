/*
 * UsersRolesDataModelBean.java
 * 
 * Created on Sep 7, 2009, 10:27:36 AM
 */
package galileowet.ejb.datamodel;

import javax.ejb.Stateless;

/**
 *
 * @author Samuel Franklyn
 */
@Stateless
public class UsersRolesDataModelBean extends BaseDataModelBean implements UsersRolesDataModelRemote {

    public static final String SELECT_ALL = "UsersRoles.selectAll";
    public static final String SELECT_ALL_COUNT = "UsersRoles.selectAllCount";
}
