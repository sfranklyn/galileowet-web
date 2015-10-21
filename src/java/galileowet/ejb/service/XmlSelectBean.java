/*
 * XmlSelectBean.java
 *
 * Created on August 21, 2007, 2:21 PM
 *
 */
package galileowet.ejb.service;

import com4j.COM4J;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Samuel Franklyn
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
public class XmlSelectBean implements XmlSelect {

    private static final Map<String, Suta> sutaMap = new Hashtable<String, Suta>();
    private static final Logger log = Logger.getLogger(XmlSelectBean.class.getName());

    @Override
    public String createSuta() {
        Suta suta = SutaFactory.createScriptableUniversalTransAgent();
        String sutaKey = suta.toString();
        sutaMap.put(sutaKey, suta);
        return sutaKey;
    }

    @Override
    public void destroySuta(String sutaKey) {
        Suta suta = sutaMap.get(sutaKey);
        if (suta != null) {
            suta.dispose();
            COM4J.cleanUp();
            suta = null;
            sutaMap.remove(sutaKey);
        }
    }

    @Override
    public String syncSubmit(String strIdentity, String strRequest, String strFilter, String sutaKey) {
        Suta suta = sutaMap.get(sutaKey);
        try {
            if (suta != null) {
                return suta.syncSubmit(strIdentity, strRequest, strFilter);
            } else {
                throw new IllegalArgumentException(sutaKey + " not found");
            }
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            return ex.getMessage();
        }
    }

    @Override
    public void createAndConnectLocalHcm(String strConnectIp, String strConnectParams,
            String strHcmLogFileSpec, String sutaKey) {
        Suta suta = sutaMap.get(sutaKey);
        if (suta != null) {
            suta.createAndConnectLocalHcm(strConnectIp, strConnectParams, strHcmLogFileSpec);
        } else {
            throw new IllegalArgumentException(sutaKey + " not found");
        }
    }

    @Override
    public int beginSession(int desiredState, String sutaKey) {
        Suta suta = sutaMap.get(sutaKey);
        if (suta != null) {
            return suta.beginSession(desiredState);
        } else {
            throw new IllegalArgumentException(sutaKey + " not found");
        }
    }

    @Override
    public String terminalSubmit(String strRequest, String sutaKey) {
        Suta suta = sutaMap.get(sutaKey);
        try {
            if (suta != null) {
                String result = suta.terminalSubmit(strRequest);
                result = result.replace('\u001d', ' ');
                return result;
            } else {
                throw new IllegalArgumentException(sutaKey + " not found");
            }
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            return ex.getMessage();
        }
    }

    @Override
    public void endSession(int sessionState, String sutaKey) {
        Suta suta = sutaMap.get(sutaKey);
        if (suta != null) {
            suta.endSession(sessionState);
        } else {
            throw new IllegalArgumentException(sutaKey + " not found");
        }
    }

    @Override
    public String getTerminalBuffer(String sutaKey) {
        Suta suta = sutaMap.get(sutaKey);
        if (suta != null) {
            return suta.getTerminalBuffer();
        } else {
            throw new IllegalArgumentException(sutaKey + " not found");
        }
    }

    @Override
    public int getTerminalTimeout(String sutaKey) {
        Suta suta = sutaMap.get(sutaKey);
        if (suta != null) {
            return suta.terminalTimeout();
        } else {
            throw new IllegalArgumentException(sutaKey + " not found");
        }
    }

    @Override
    public void setTerminalTimeout(int pVal, String sutaKey) {
        Suta suta = sutaMap.get(sutaKey);
        if (suta != null) {
            suta.terminalTimeout(pVal);
        } else {
            throw new IllegalArgumentException(sutaKey + " not found");
        }
    }

    @Override
    public String getHcmName(String sutaKey) {
        Suta suta = sutaMap.get(sutaKey);
        if (suta != null) {
            return suta.hcmName();
        } else {
            throw new IllegalArgumentException(sutaKey + " not found");
        }
    }

    @Override
    public void setHcmName(String pVal, String sutaKey) {
        Suta suta = sutaMap.get(sutaKey);
        if (suta != null) {
            suta.hcmName(pVal);
        } else {
            throw new IllegalArgumentException(sutaKey + " not found");
        }
    }

    @Override
    public boolean getUseManagedHcm(String sutaKey) {
        Suta suta = sutaMap.get(sutaKey);
        if (suta != null) {
            return suta.useManagedHcm();
        } else {
            throw new IllegalArgumentException(sutaKey + " not found");
        }
    }

    @Override
    public void setUseManagedHcm(boolean pVal, String sutaKey) {
        Suta suta = sutaMap.get(sutaKey);
        if (suta != null) {
            suta.useManagedHcm(pVal);
        } else {
            throw new IllegalArgumentException(sutaKey + " not found");
        }
    }

    @Override
    public boolean getUseLoadBalancedHcm(String sutaKey) {
        Suta suta = sutaMap.get(sutaKey);
        if (suta != null) {
            return suta.useLoadBalancedHcm();
        } else {
            throw new IllegalArgumentException(sutaKey + " not found");
        }
    }

    @Override
    public void setUseLoadBalancedHcm(boolean pVal, String sutaKey) {
        Suta suta = sutaMap.get(sutaKey);
        if (suta != null) {
            suta.useLoadBalancedHcm(pVal);
        } else {
            throw new IllegalArgumentException(sutaKey + " not found");
        }
    }
}
