//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.01 at 12:48:34 PM AMT 
//


package ru.rosreestr.client.isur.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
 *       &lt;all>
 *         &lt;element name="typeobject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="iddbegrp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cadastralnumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areasvalue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="areasunit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dopinfoname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typebuilding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conditionalcadatralnumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typeroom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codeokato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codekladr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postalcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationdistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typedistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="town" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typetown" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rayon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typerayon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationsoviet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typesoviet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="naselpunkt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typenaselpunkt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationstreet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typestreet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationhouse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationbuilding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationstructure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationapartment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apartment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adressother" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationother" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email_subscription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enable_subscription" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="kind_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "ServiceProperties")
public class ServiceProperties implements Serializable{

    @XmlElement(required = true)
    protected String typeobject;
    protected String iddbegrp;
    protected String cadastralnumber;
    protected BigDecimal areasvalue;
    protected String areasunit;
    protected String dopinfoname;
    protected String typebuilding;
    protected String conditionalcadatralnumber;
    protected String typeroom;
    protected String region;
    protected String codeokato;
    protected String codekladr;
    protected String postalcode;
    protected String locationdistrict;
    protected String typedistrict;
    protected String town;
    protected String typetown;
    protected String rayon;
    protected String typerayon;
    protected String locationsoviet;
    protected String typesoviet;
    protected String naselpunkt;
    protected String typenaselpunkt;
    protected String locationstreet;
    protected String typestreet;
    protected String locationhouse;
    protected String address1;
    protected String locationbuilding;
    protected String address2;
    protected String locationstructure;
    protected String address3;
    protected String locationapartment;
    protected String apartment;
    protected String adressother;
    protected String locationother;
    @XmlElement(name = "email_subscription")
    protected String emailSubscription;
    @XmlElement(name = "enable_subscription")
    protected BigInteger enableSubscription;
    @XmlElement(name = "kind_name")
    protected String kindName;

    /**
     * Gets the value of the typeobject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeobject() {
        return typeobject;
    }

    /**
     * Sets the value of the typeobject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeobject(String value) {
        this.typeobject = value;
    }

    /**
     * Gets the value of the iddbegrp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIddbegrp() {
        return iddbegrp;
    }

    /**
     * Sets the value of the iddbegrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIddbegrp(String value) {
        this.iddbegrp = value;
    }

    /**
     * Gets the value of the cadastralnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCadastralnumber() {
        return cadastralnumber;
    }

    /**
     * Sets the value of the cadastralnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCadastralnumber(String value) {
        this.cadastralnumber = value;
    }

    /**
     * Gets the value of the areasvalue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAreasvalue() {
        return areasvalue;
    }

    /**
     * Sets the value of the areasvalue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAreasvalue(BigDecimal value) {
        this.areasvalue = value;
    }

    /**
     * Gets the value of the areasunit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreasunit() {
        return areasunit;
    }

    /**
     * Sets the value of the areasunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreasunit(String value) {
        this.areasunit = value;
    }

    /**
     * Gets the value of the dopinfoname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDopinfoname() {
        return dopinfoname;
    }

    /**
     * Sets the value of the dopinfoname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDopinfoname(String value) {
        this.dopinfoname = value;
    }

    /**
     * Gets the value of the typebuilding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypebuilding() {
        return typebuilding;
    }

    /**
     * Sets the value of the typebuilding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypebuilding(String value) {
        this.typebuilding = value;
    }

    /**
     * Gets the value of the conditionalcadatralnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConditionalcadatralnumber() {
        return conditionalcadatralnumber;
    }

    /**
     * Sets the value of the conditionalcadatralnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConditionalcadatralnumber(String value) {
        this.conditionalcadatralnumber = value;
    }

    /**
     * Gets the value of the typeroom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTyperoom() {
        return typeroom;
    }

    /**
     * Sets the value of the typeroom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTyperoom(String value) {
        this.typeroom = value;
    }

    /**
     * Gets the value of the region property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the value of the region property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegion(String value) {
        this.region = value;
    }

    /**
     * Gets the value of the codeokato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeokato() {
        return codeokato;
    }

    /**
     * Sets the value of the codeokato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeokato(String value) {
        this.codeokato = value;
    }

    /**
     * Gets the value of the codekladr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodekladr() {
        return codekladr;
    }

    /**
     * Sets the value of the codekladr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodekladr(String value) {
        this.codekladr = value;
    }

    /**
     * Gets the value of the postalcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalcode() {
        return postalcode;
    }

    /**
     * Sets the value of the postalcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalcode(String value) {
        this.postalcode = value;
    }

    /**
     * Gets the value of the locationdistrict property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationdistrict() {
        return locationdistrict;
    }

    /**
     * Sets the value of the locationdistrict property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationdistrict(String value) {
        this.locationdistrict = value;
    }

    /**
     * Gets the value of the typedistrict property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypedistrict() {
        return typedistrict;
    }

    /**
     * Sets the value of the typedistrict property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypedistrict(String value) {
        this.typedistrict = value;
    }

    /**
     * Gets the value of the town property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTown() {
        return town;
    }

    /**
     * Sets the value of the town property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTown(String value) {
        this.town = value;
    }

    /**
     * Gets the value of the typetown property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypetown() {
        return typetown;
    }

    /**
     * Sets the value of the typetown property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypetown(String value) {
        this.typetown = value;
    }

    /**
     * Gets the value of the rayon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRayon() {
        return rayon;
    }

    /**
     * Sets the value of the rayon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRayon(String value) {
        this.rayon = value;
    }

    /**
     * Gets the value of the typerayon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTyperayon() {
        return typerayon;
    }

    /**
     * Sets the value of the typerayon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTyperayon(String value) {
        this.typerayon = value;
    }

    /**
     * Gets the value of the locationsoviet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationsoviet() {
        return locationsoviet;
    }

    /**
     * Sets the value of the locationsoviet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationsoviet(String value) {
        this.locationsoviet = value;
    }

    /**
     * Gets the value of the typesoviet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypesoviet() {
        return typesoviet;
    }

    /**
     * Sets the value of the typesoviet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypesoviet(String value) {
        this.typesoviet = value;
    }

    /**
     * Gets the value of the naselpunkt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaselpunkt() {
        return naselpunkt;
    }

    /**
     * Sets the value of the naselpunkt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaselpunkt(String value) {
        this.naselpunkt = value;
    }

    /**
     * Gets the value of the typenaselpunkt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypenaselpunkt() {
        return typenaselpunkt;
    }

    /**
     * Sets the value of the typenaselpunkt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypenaselpunkt(String value) {
        this.typenaselpunkt = value;
    }

    /**
     * Gets the value of the locationstreet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationstreet() {
        return locationstreet;
    }

    /**
     * Sets the value of the locationstreet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationstreet(String value) {
        this.locationstreet = value;
    }

    /**
     * Gets the value of the typestreet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypestreet() {
        return typestreet;
    }

    /**
     * Sets the value of the typestreet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypestreet(String value) {
        this.typestreet = value;
    }

    /**
     * Gets the value of the locationhouse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationhouse() {
        return locationhouse;
    }

    /**
     * Sets the value of the locationhouse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationhouse(String value) {
        this.locationhouse = value;
    }

    /**
     * Gets the value of the address1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets the value of the address1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress1(String value) {
        this.address1 = value;
    }

    /**
     * Gets the value of the locationbuilding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationbuilding() {
        return locationbuilding;
    }

    /**
     * Sets the value of the locationbuilding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationbuilding(String value) {
        this.locationbuilding = value;
    }

    /**
     * Gets the value of the address2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Sets the value of the address2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress2(String value) {
        this.address2 = value;
    }

    /**
     * Gets the value of the locationstructure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationstructure() {
        return locationstructure;
    }

    /**
     * Sets the value of the locationstructure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationstructure(String value) {
        this.locationstructure = value;
    }

    /**
     * Gets the value of the address3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress3() {
        return address3;
    }

    /**
     * Sets the value of the address3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress3(String value) {
        this.address3 = value;
    }

    /**
     * Gets the value of the locationapartment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationapartment() {
        return locationapartment;
    }

    /**
     * Sets the value of the locationapartment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationapartment(String value) {
        this.locationapartment = value;
    }

    /**
     * Gets the value of the apartment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApartment() {
        return apartment;
    }

    /**
     * Sets the value of the apartment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApartment(String value) {
        this.apartment = value;
    }

    /**
     * Gets the value of the adressother property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdressother() {
        return adressother;
    }

    /**
     * Sets the value of the adressother property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdressother(String value) {
        this.adressother = value;
    }

    /**
     * Gets the value of the locationother property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationother() {
        return locationother;
    }

    /**
     * Sets the value of the locationother property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationother(String value) {
        this.locationother = value;
    }

    /**
     * Gets the value of the emailSubscription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailSubscription() {
        return emailSubscription;
    }

    /**
     * Sets the value of the emailSubscription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailSubscription(String value) {
        this.emailSubscription = value;
    }

    /**
     * Gets the value of the enableSubscription property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEnableSubscription() {
        return enableSubscription;
    }

    /**
     * Sets the value of the enableSubscription property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEnableSubscription(BigInteger value) {
        this.enableSubscription = value;
    }

    /**
     * Gets the value of the kindName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKindName() {
        return kindName;
    }

    /**
     * Sets the value of the kindName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKindName(String value) {
        this.kindName = value;
    }

}
