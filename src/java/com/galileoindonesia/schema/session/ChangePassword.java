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
 *         &lt;element ref="{}NewPwd"/>
 *         &lt;element ref="{}NewKeyword"/>
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
    "newPwd",
    "newKeyword"
})
@XmlRootElement(name = "ChangePassword")
public class ChangePassword {

    @XmlElement(name = "NewPwd", required = true)
    protected String newPwd;
    @XmlElement(name = "NewKeyword", required = true)
    protected String newKeyword;

    /**
     * Gets the value of the newPwd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewPwd() {
        return newPwd;
    }

    /**
     * Sets the value of the newPwd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewPwd(String value) {
        this.newPwd = value;
    }

    /**
     * Gets the value of the newKeyword property.
     * 
     * @return
     *     possible object is
     *     {@link NewKeyword }
     *     
     */
    public String getNewKeyword() {
        return newKeyword;
    }

    /**
     * Sets the value of the newKeyword property.
     * 
     * @param value
     *     allowed object is
     *     {@link NewKeyword }
     *     
     */
    public void setNewKeyword(String value) {
        this.newKeyword = value;
    }

}
