/*
 * PccsServiceBean.java
 * 
 * Created on Sep 3, 2009, 12:37:12 PM
 */

package galileowet.ejb.service;

import galileowet.ejb.dao.PccsDaoRemote;
import galileowet.jpa.Pccs;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Samuel Franklyn
 */
@Stateless
public class PccsServiceBean implements PccsServiceRemote {
    
    private static final Logger log = Logger.getLogger(PccsServiceBean.class.getName());
    private static final String MESSAGES = "messages";
    @EJB
    private PccsDaoRemote pccsDaoRemote = null;

    public List<String> saveCreate(Pccs pccs, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if ("".equals(pccs.getPccPcc())) {
            errorList.add(messageSource.getString("pcc_pcc_required"));
        }
        if ("".equals(pccs.getPccDesc())) {
            errorList.add(messageSource.getString("pcc_desc_required"));
        }
        if (errorList.size() == 0) {
            try {
                pccsDaoRemote.insert(pccs);
            } catch (Exception ex) {
                Throwable cause = ex.getCause();
                boolean duplicate = false;
                while (cause != null) {
                    if (cause.toString().contains("Duplicate entry")) {
                        errorList.add(messageSource.getString("pcc_pcc_already_registered"));
                        duplicate = true;
                    }
                    cause = cause.getCause();
                }
                if (errorList.size() == 0) {
                    errorList.add(ex.toString());
                }
                if (!duplicate) {
                    log.log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
                }
            }
        }
        return errorList;
    }

    public List<String> saveUpdate(Pccs pccs, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if (pccs.getPccId() == null || pccs.getPccId().intValue() == 0) {
            errorList.add(messageSource.getString("pcc_id_required"));
        }
        if ("".equals(pccs.getPccPcc())) {
            errorList.add(messageSource.getString("pcc_pcc_required"));
        }
        if ("".equals(pccs.getPccDesc())) {
            errorList.add(messageSource.getString("pcc_desc_required"));
        }
        if (errorList.size() == 0) {
            try {
                pccsDaoRemote.update(pccs);
            } catch (Exception ex) {
                errorList.add(ex.toString());
                log.log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
            }
        }
        return errorList;
    }

    public List<String> saveDelete(Pccs pccs, Locale locale) {
        List<String> errorList = new ArrayList<String>();
        ResourceBundle messageSource = ResourceBundle.getBundle(MESSAGES, locale);
        if (pccs.getPccId() == null || pccs.getPccId().intValue() == 0) {
            errorList.add(messageSource.getString("pcc_id_required"));
        }
        if (errorList.size() == 0) {
            try {
                pccsDaoRemote.delete(pccs.getPccId());
            } catch (Exception ex) {
                errorList.add(ex.toString());
                log.log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
            }
        }
        return errorList;
    }
 
}
