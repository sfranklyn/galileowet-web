/*
 * PccsDataModelBean.java
 * 
 * Created on Sep 3, 2009, 12:45:17 PM
 */
package galileowet.ejb.datamodel;

import javax.ejb.Stateless;

/**
 *
 * @author Samuel Franklyn
 */
@Stateless
public class PccsDataModelBean extends BaseDataModelBean implements PccsDataModelRemote {

    public static final String SELECT_ALL = "Pccs.selectAll";
    public static final String SELECT_ALL_COUNT = "Pccs.selectAllCount";
    public static final String SELECT_BY_PCC = "Pccs.selectByPcc";
    public static final String SELECT_BY_PCC_COUNT = "Pccs.selectByPccCount";
}
