//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-548 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.17 at 03:32:52 PM ICT 
//


package com.galileoindonesia.schema.session;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}UserID"/>
 *         &lt;element ref="{}Pwd"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userID",
    "pwd"
})
@XmlRootElement(name = "SignOff")
public class SignOff {

    @XmlElement(name = "UserID", required = true)
    protected UserID userID;
    @XmlElement(name = "Pwd", required = true)
    protected Pwd pwd;

    /**
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link UserID }
     *     
     */
    public UserID getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserID }
     *     
     */
    public void setUserID(UserID value) {
        this.userID = value;
    }

    /**
     * Gets the value of the pwd property.
     * 
     * @return
     *     possible object is
     *     {@link Pwd }
     *     
     */
    public Pwd getPwd() {
        return pwd;
    }

    /**
     * Sets the value of the pwd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pwd }
     *     
     */
    public void setPwd(Pwd value) {
        this.pwd = value;
    }

}
