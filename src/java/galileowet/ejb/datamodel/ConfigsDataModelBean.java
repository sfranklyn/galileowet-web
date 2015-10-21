/*
 * ConfigsDataModelBean.java
 * 
 * Created on Sep 7, 2009, 2:32:16 PM
 */
package galileowet.ejb.datamodel;

import javax.ejb.Stateless;

/**
 *
 * @author Samuel Franklyn
 */
@Stateless
public class ConfigsDataModelBean extends BaseDataModelBean implements ConfigsDataModelRemote {

    public static final String SELECT_ALL = "Configs.selectAll";
    public static final String SELECT_ALL_COUNT = "Configs.selectAllCount";
}
