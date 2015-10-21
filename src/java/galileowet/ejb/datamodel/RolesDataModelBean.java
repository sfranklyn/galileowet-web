/*
 * RolesDataModelBean.java
 * 
 * Created on Sep 4, 2009, 3:08:17 PM
 */
package galileowet.ejb.datamodel;

import javax.ejb.Stateless;

/**
 *
 * @author Samuel Franklyn
 */
@Stateless
public class RolesDataModelBean extends BaseDataModelBean implements RolesDataModelRemote {

    public static final String SELECT_ALL = "Roles.selectAll";
    public static final String SELECT_ALL_COUNT = "Roles.selectAllCount";
}
