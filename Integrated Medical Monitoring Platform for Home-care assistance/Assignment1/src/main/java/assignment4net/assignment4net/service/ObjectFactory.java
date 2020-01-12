
package assignment4net.assignment4net.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the assignment4net.assignment4net.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ViewRecommendations_QNAME = new QName("http://service.assignment4net.Assignment4Net/", "viewRecommendations");
    private final static QName _ViewRecommendationsResponse_QNAME = new QName("http://service.assignment4net.Assignment4Net/", "viewRecommendationsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: assignment4net.assignment4net.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ViewRecommendationsResponse }
     * 
     */
    public ViewRecommendationsResponse createViewRecommendationsResponse() {
        return new ViewRecommendationsResponse();
    }

    /**
     * Create an instance of {@link ViewRecommendations }
     * 
     */
    public ViewRecommendations createViewRecommendations() {
        return new ViewRecommendations();
    }

    /**
     * Create an instance of {@link RecommendationDTO }
     * 
     */
    public RecommendationDTO createRecommendationDTO() {
        return new RecommendationDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewRecommendations }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.assignment4net.Assignment4Net/", name = "viewRecommendations")
    public JAXBElement<ViewRecommendations> createViewRecommendations(ViewRecommendations value) {
        return new JAXBElement<ViewRecommendations>(_ViewRecommendations_QNAME, ViewRecommendations.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewRecommendationsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.assignment4net.Assignment4Net/", name = "viewRecommendationsResponse")
    public JAXBElement<ViewRecommendationsResponse> createViewRecommendationsResponse(ViewRecommendationsResponse value) {
        return new JAXBElement<ViewRecommendationsResponse>(_ViewRecommendationsResponse_QNAME, ViewRecommendationsResponse.class, null, value);
    }

}
