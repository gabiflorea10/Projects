
package assignment4.ds;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="viewRecommendationsResult" type="{http://ds.Assignment4/}ArrayOfRecommendationDTO" minOccurs="0"/>
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
    "viewRecommendationsResult"
})
@XmlRootElement(name = "viewRecommendationsResponse")
public class ViewRecommendationsResponse {

    protected ArrayOfRecommendationDTO viewRecommendationsResult;

    /**
     * Gets the value of the viewRecommendationsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRecommendationDTO }
     *     
     */
    public ArrayOfRecommendationDTO getViewRecommendationsResult() {
        return viewRecommendationsResult;
    }

    /**
     * Sets the value of the viewRecommendationsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRecommendationDTO }
     *     
     */
    public void setViewRecommendationsResult(ArrayOfRecommendationDTO value) {
        this.viewRecommendationsResult = value;
    }

}
