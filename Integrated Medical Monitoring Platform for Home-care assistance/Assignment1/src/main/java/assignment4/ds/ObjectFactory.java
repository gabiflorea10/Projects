
package assignment4.ds;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the assignment4.ds package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: assignment4.ds
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
     * Create an instance of {@link ArrayOfRecommendationDTO }
     * 
     */
    public ArrayOfRecommendationDTO createArrayOfRecommendationDTO() {
        return new ArrayOfRecommendationDTO();
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

}
