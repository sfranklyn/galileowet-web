/*
 * ConfigsServiceBean.java
 * 
 * Created on Sep 7, 2009, 2:33:33 PM
 */
package galileowet.ejb.service;

import galileowet.ejb.dao.ConfigsDaoRemote;
import galileowet.jpa.Configs;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
public class ConfigsServiceBean implements ConfigsServiceRemote {

    private static final String MESSAGES = "messages";
    @PersistenceContext
    private EntityManager em;
    @EJB
    private ConfigsDaoRemote configsDaoRemote = null;

    public Configs getByKey(String configKey) {
        Query query = em.createNamedQuery("Configs.findByConfigKey");
        query.setParameter("configKey", configKey);
        Configs configs = null;
        try {
            configs = (Configs) query.getSingleResult();
        } catch (NoResultException ex) {
        }
        return configs;
    }

    public List<String> saveCreate(Configs configs, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if ("".equals(configs.getConfigKey())) {
            errorList.add(messageSource.getString("config_key_required"));
        }
        if ("".equals(configs.getConfigDesc())) {
            errorList.add(messageSource.getString("config_desc_required"));
        }
        if ("".equals(configs.getConfigType())) {
            errorList.add(messageSource.getString("config_type_required"));
        }
        if ("".equals(configs.getConfigValue())) {
            errorList.add(messageSource.getString("config_value_required"));
        }
        if (errorList.size() == 0) {
            try {
                configsDaoRemote.insert(configs);
            } catch (Exception ex) {
                Throwable cause = ex.getCause();
                boolean duplicate = false;
                while (cause != null) {
                    if (cause.toString().contains("Duplicate entry")) {
                        errorList.add(messageSource.getString("config_key_already_registered"));
                        duplicate = true;
                    }
                    cause = cause.getCause();
                }
                if (errorList.size() == 0) {
                    errorList.add(ex.toString());
                }
                if (!duplicate) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
                }
            }
        }
        return errorList;
    }

    public List<String> saveUpdate(Configs configs, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if (configs.getConfigId() == null || configs.getConfigId().intValue() == 0) {
            errorList.add(messageSource.getString("config_id_required"));
        }
        if ("".equals(configs.getConfigKey())) {
            errorList.add(messageSource.getString("config_key_required"));
        }
        if ("".equals(configs.getConfigDesc())) {
            errorList.add(messageSource.getString("config_desc_required"));
        }
        if ("".equals(configs.getConfigType())) {
            errorList.add(messageSource.getString("config_type_required"));
        }
        if ("".equals(configs.getConfigValue())) {
            errorList.add(messageSource.getString("config_value_required"));
        }
        if (errorList.size() == 0) {
            try {
                configsDaoRemote.update(configs);
            } catch (Exception ex) {
                errorList.add(ex.toString());
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
            }
        }
        return errorList;
    }

    public List<String> saveDelete(Configs configs, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if (configs.getConfigId() == null || configs.getConfigId().intValue() == 0) {
            errorList.add(messageSource.getString("config_id_required"));
        }
        if (errorList.size() == 0) {
            try {
                configsDaoRemote.delete(configs.getConfigId());
            } catch (Exception ex) {
                errorList.add(ex.toString());
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
            }
        }
        return errorList;
    }
}
