
/*
 * DataModelDao.java
 * 
 * Created on Sep 3, 2009, 11:46:07 AM
 */
package galileowet.ejb.datamodel;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Samuel Franklyn
 */
public interface DataModelDao {

    List getAll(String id, Map<String, Object> param, int first, int max);

    Long getAllCount(String id, Map<String, Object> param);
}
