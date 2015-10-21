/*
 * BaseDataModelBean.java
 * 
 * Created on Sep 3, 2009, 11:46:59 AM
 */
package galileowet.ejb.datamodel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Samuel Franklyn
 */
public class BaseDataModelBean implements DataModelDao {

    @PersistenceContext
    private EntityManager em;

    public List getAll(String id, Map<String, Object> param, int first, int max) {
        Query query = em.createNamedQuery(id);
        query.setFirstResult(first);
        if (max > 0) {
            query.setMaxResults(max);
        }
        if ((param != null) && (param.size() > 0)) {
            for (String key : param.keySet()) {
                query.setParameter(key, param.get(key));
            }
        }
        return query.getResultList();
    }

    public Long getAllCount(String id, Map<String, Object> param) {
        Query query = em.createNamedQuery(id);
        if ((param != null) && (param.size() > 0)) {
            for (String key : param.keySet()) {
                query.setParameter(key, param.get(key));
            }
        }
        Long count = Long.valueOf(0);
        try {
            Object obj = query.getSingleResult();
            if (obj.getClass().getSimpleName().equals("Long")) {
                count = (Long) obj;
                return count;
            }
            if (obj.getClass().getSimpleName().equals("BigDecimal")) {
                BigDecimal bd = (BigDecimal) obj;
                count = bd.longValue();
                return count;
            }
            if (obj instanceof Object[]) {
                Object[] objArray = (Object[]) obj;
                if (objArray.length >= 1) {
                    if (objArray[0] != null) {
                        Object objValue = objArray[0];
                        if (objValue.getClass().getSimpleName().equals("Long")) {
                            count = (Long) objValue;
                            return count;
                        }
                        if (objValue.getClass().getSimpleName().equals("BigDecimal")) {
                            BigDecimal bd = (BigDecimal) objValue;
                            count = bd.longValue();
                            return count;
                        }
                    }
                }
            }
        } catch (NoResultException ex) {
        }
        return count;
    }
}
