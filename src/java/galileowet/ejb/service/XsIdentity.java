/*
 * XsIdentity.java
 *
 * Created on August 27, 2007, 3:26 PM
 *
 */
package galileowet.ejb.service;

/**
 *
 * @author Samuel Franklyn
 */
public class XsIdentity {

    public static String identity(final String userId, final String pseudo) {
        String idXML = "<Application>\n";
        idXML = idXML.concat("<VendorId>FLCN</VendorId>\n");
        idXML = idXML.concat("<VendorType>G</VendorType>\n");
        idXML = idXML.concat("<SourceId>GNDOTS</SourceId>\n");
        idXML = idXML.concat("<SourceType>G</SourceType>\n");
        idXML = idXML.concat("</Application>\n");
        idXML = idXML.concat("<User>\n");
        idXML = idXML.concat("<UserId>");
        idXML = idXML.concat(userId);
        idXML = idXML.concat("</UserId>\n");              
        idXML = idXML.concat("<Pseudo>");
        idXML = idXML.concat(pseudo);
        idXML = idXML.concat("</Pseudo>\n");              
        idXML = idXML.concat("</User>");

        return idXML;
    }
}
