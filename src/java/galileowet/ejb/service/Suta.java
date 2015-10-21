package galileowet.ejb.service;

import com4j.Com4jObject;
import com4j.IID;
import com4j.VTID;

/**
 * IScriptableUniversalTransAgent Interface
 */
@IID("{9EC1CA0D-7768-11D3-87DE-00C04F5B8E26}")
public interface Suta extends Com4jObject {
    /**
     * method SyncSubmit
     */
    @VTID(7)
    java.lang.String syncSubmit(
            java.lang.String strIdentity,
            java.lang.String strRequest,
            java.lang.String strFilter);
    
    /**
     * method CreateAndConnectLocalHcm
     */
    @VTID(8)
    void createAndConnectLocalHcm(
            java.lang.String strConnectIp,
            java.lang.String strConnectParams,
            java.lang.String strHcmLogFileSpec);
    
    /**
     * method BeginSession
     */
    @VTID(9)
    int beginSession(
            int desiredState);
    
    /**
     * method TerminalSubmit
     */
    @VTID(10)
    java.lang.String terminalSubmit(
            java.lang.String strRequest);
    
    /**
     * method EndSession
     */
    @VTID(11)
    void endSession(
            int sessionState);
    
    /**
     * method GetTerminalBuffer
     */
    @VTID(12)
    java.lang.String getTerminalBuffer();
    
    /**
     * property TerminalTimeout
     */
    @VTID(13)
    int terminalTimeout();
    
    /**
     * property TerminalTimeout
     */
    @VTID(14)
    void terminalTimeout(
            int pVal);
    
    /**
     * property HcmName
     */
    @VTID(15)
    java.lang.String hcmName();
    
    /**
     * property HcmName
     */
    @VTID(16)
    void hcmName(
            java.lang.String pVal);
    
    /**
     * property UseManagedHcm
     */
    @VTID(17)
    boolean useManagedHcm();
    
    /**
     * property UseManagedHcm
     */
    @VTID(18)
    void useManagedHcm(
            boolean pVal);
    
    /**
     * property UseLoadBalancedHcm
     */
    @VTID(19)
    boolean useLoadBalancedHcm();
    
    /**
     * property UseLoadBalancedHcm
     */
    @VTID(20)
    void useLoadBalancedHcm(
            boolean pVal);
    
}
