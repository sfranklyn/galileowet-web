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
 *         &lt;sequence minOccurs="0">
 *           &lt;element ref="{}AreaInfoReq"/>
 *           &lt;element ref="{}ChangeWorkArea"/>
 *           &lt;element ref="{}EmulatePCC"/>
 *           &lt;element ref="{}SignOff"/>
 *           &lt;element ref="{}SignOn"/>
 *         &lt;/sequence>
 *         &lt;element ref="{}ChangePassword"/>
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
    "areaInfoReq",
    "changeWorkArea",
    "emulatePCC",
    "signOff",
    "signOn",
    "changePassword"
})
@XmlRootElement(name = "SessionMods")
public class SessionMods {

    @XmlElement(name = "AreaInfoReq")
    protected AreaInfoReq areaInfoReq;
    @XmlElement(name = "ChangeWorkArea")
    protected ChangeWorkArea changeWorkArea;
    @XmlElement(name = "EmulatePCC")
    protected EmulatePCC emulatePCC;
    @XmlElement(name = "SignOff")
    protected SignOff signOff;
    @XmlElement(name = "SignOn")
    protected SignOn signOn;
    @XmlElement(name = "ChangePassword", required = true)
    protected ChangePassword changePassword;

    /**
     * Gets the value of the areaInfoReq property.
     * 
     * @return
     *     possible object is
     *     {@link AreaInfoReq }
     *     
     */
    public AreaInfoReq getAreaInfoReq() {
        return areaInfoReq;
    }

    /**
     * Sets the value of the areaInfoReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaInfoReq }
     *     
     */
    public void setAreaInfoReq(AreaInfoReq value) {
        this.areaInfoReq = value;
    }

    /**
     * Gets the value of the changeWorkArea property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeWorkArea }
     *     
     */
    public ChangeWorkArea getChangeWorkArea() {
        return changeWorkArea;
    }

    /**
     * Sets the value of the changeWorkArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeWorkArea }
     *     
     */
    public void setChangeWorkArea(ChangeWorkArea value) {
        this.changeWorkArea = value;
    }

    /**
     * Gets the value of the emulatePCC property.
     * 
     * @return
     *     possible object is
     *     {@link EmulatePCC }
     *     
     */
    public EmulatePCC getEmulatePCC() {
        return emulatePCC;
    }

    /**
     * Sets the value of the emulatePCC property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmulatePCC }
     *     
     */
    public void setEmulatePCC(EmulatePCC value) {
        this.emulatePCC = value;
    }

    /**
     * Gets the value of the signOff property.
     * 
     * @return
     *     possible object is
     *     {@link SignOff }
     *     
     */
    public SignOff getSignOff() {
        return signOff;
    }

    /**
     * Sets the value of the signOff property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignOff }
     *     
     */
    public void setSignOff(SignOff value) {
        this.signOff = value;
    }

    /**
     * Gets the value of the signOn property.
     * 
     * @return
     *     possible object is
     *     {@link SignOn }
     *     
     */
    public SignOn getSignOn() {
        return signOn;
    }

    /**
     * Sets the value of the signOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignOn }
     *     
     */
    public void setSignOn(SignOn value) {
        this.signOn = value;
    }

    /**
     * Gets the value of the changePassword property.
     * 
     * @return
     *     possible object is
     *     {@link ChangePassword }
     *     
     */
    public ChangePassword getChangePassword() {
        return changePassword;
    }

    /**
     * Sets the value of the changePassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangePassword }
     *     
     */
    public void setChangePassword(ChangePassword value) {
        this.changePassword = value;
    }

}