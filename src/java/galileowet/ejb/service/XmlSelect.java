package galileowet.ejb.service;

import javax.ejb.Remote;

/**
 * @author Samuel Franklyn
 * This is the business interface for XmlSelect enterprise bean.
 */
@Remote
public interface XmlSelect {

    String createSuta();

    void destroySuta(String sutaKey);

    String syncSubmit(String strIdentity, String strRequest, String strFilter, String sutaKey);

    void createAndConnectLocalHcm(String strConnectIp, String strConnectParams, 
            String strHcmLogFileSpec, String sutaKey);

    int beginSession(int desiredState, String sutaKey);

    String terminalSubmit(String strRequest, String sutaKey);

    void endSession(int sessionState, String sutaKey);

    String getTerminalBuffer(String sutaKey);

    boolean getUseManagedHcm(String sutaKey);

    void setUseManagedHcm(boolean pVal, String sutaKey);

    boolean getUseLoadBalancedHcm(String sutaKey);

    void setUseLoadBalancedHcm(boolean pVal, String sutaKey);

    int getTerminalTimeout(String sutaKey);

    void setTerminalTimeout(int pVal, String sutaKey);

    String getHcmName(String sutaKey);

    void setHcmName(String pVal, String sutaKey);
}
