/*
 * UrlsRolesDataModelBean.java
 * 
 * Created on Sep 7, 2009, 1:49:40 PM
 */
package galileowet.ejb.datamodel;

import javax.ejb.Stateless;

/**
 *
 * @author Samuel Franklyn
 */
@Stateless
public class UrlsRolesDataModelBean extends BaseDataModelBean implements UrlsRolesDataModelRemote {

    public static final String SELECT_ALL = "UrlsRoles.selectAll";
    public static final String SELECT_ALL_COUNT = "UrlsRoles.selectAllCount";
}
