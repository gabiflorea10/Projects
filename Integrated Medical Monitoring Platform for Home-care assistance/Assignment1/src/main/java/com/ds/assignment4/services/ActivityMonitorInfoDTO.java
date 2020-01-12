
package com.ds.assignment4.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for activityMonitorInfoDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="activityMonitorInfoDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="activity_date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activity_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activity_label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="caregiver_username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hours" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="is_anomalous" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patient_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "activityMonitorInfoDTO", propOrder = {
    "activityDate",
    "activityId",
    "activityLabel",
    "caregiverUsername",
    "hours",
    "isAnomalous",
    "patientName"
})
public class ActivityMonitorInfoDTO {

    @XmlElement(name = "activity_date")
    protected String activityDate;
    @XmlElement(name = "activity_id")
    protected String activityId;
    @XmlElement(name = "activity_label")
    protected String activityLabel;
    @XmlElement(name = "caregiver_username")
    protected String caregiverUsername;
    protected Double hours;
    @XmlElement(name = "is_anomalous")
    protected String isAnomalous;
    @XmlElement(name = "patient_name")
    protected String patientName;

    /**
     * Gets the value of the activityDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityDate() {
        return activityDate;
    }

    /**
     * Sets the value of the activityDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityDate(String value) {
        this.activityDate = value;
    }

    /**
     * Gets the value of the activityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * Sets the value of the activityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityId(String value) {
        this.activityId = value;
    }

    /**
     * Gets the value of the activityLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityLabel() {
        return activityLabel;
    }

    /**
     * Sets the value of the activityLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityLabel(String value) {
        this.activityLabel = value;
    }

    /**
     * Gets the value of the caregiverUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaregiverUsername() {
        return caregiverUsername;
    }

    /**
     * Sets the value of the caregiverUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaregiverUsername(String value) {
        this.caregiverUsername = value;
    }

    /**
     * Gets the value of the hours property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getHours() {
        return hours;
    }

    /**
     * Sets the value of the hours property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setHours(Double value) {
        this.hours = value;
    }

    /**
     * Gets the value of the isAnomalous property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsAnomalous() {
        return isAnomalous;
    }

    /**
     * Sets the value of the isAnomalous property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsAnomalous(String value) {
        this.isAnomalous = value;
    }

    /**
     * Gets the value of the patientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * Sets the value of the patientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientName(String value) {
        this.patientName = value;
    }

}
