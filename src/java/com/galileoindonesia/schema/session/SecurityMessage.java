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
 *         &lt;element ref="{}Num"/>
 *         &lt;element ref="{}TextLen"/>
 *         &lt;element ref="{}Text"/>
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
    "num",
    "textLen",
    "text"
})
@XmlRootElement(name = "SecurityMessage")
public class SecurityMessage {

    @XmlElement(name = "Num", required = true)
    protected Num num;
    @XmlElement(name = "TextLen", required = true)
    protected TextLen textLen;
    @XmlElement(name = "Text", required = true)
    protected Text text;

    /**
     * Gets the value of the num property.
     * 
     * @return
     *     possible object is
     *     {@link Num }
     *     
     */
    public Num getNum() {
        return num;
    }

    /**
     * Sets the value of the num property.
     * 
     * @param value
     *     allowed object is
     *     {@link Num }
     *     
     */
    public void setNum(Num value) {
        this.num = value;
    }

    /**
     * Gets the value of the textLen property.
     * 
     * @return
     *     possible object is
     *     {@link TextLen }
     *     
     */
    public TextLen getTextLen() {
        return textLen;
    }

    /**
     * Sets the value of the textLen property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextLen }
     *     
     */
    public void setTextLen(TextLen value) {
        this.textLen = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link Text }
     *     
     */
    public Text getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link Text }
     *     
     */
    public void setText(Text value) {
        this.text = value;
    }

}