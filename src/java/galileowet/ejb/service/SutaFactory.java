package galileowet.ejb.service;

import com4j.COM4J;

/**
 * Defines methods to create COM objects
 */
public abstract class SutaFactory {
    private SutaFactory() {} // instanciation is not allowed
        
    /**
     * ScriptableUniversalTransAgent Class
     */
    public static Suta createScriptableUniversalTransAgent() {
        return COM4J.createInstance( Suta.class, "{9EC1CA0E-7768-11D3-87DE-00C04F5B8E26}" );
    }
}
