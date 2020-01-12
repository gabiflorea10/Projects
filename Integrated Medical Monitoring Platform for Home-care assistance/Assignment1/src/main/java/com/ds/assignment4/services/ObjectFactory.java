
package com.ds.assignment4.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ds.assignment4.services package. 
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

    private final static QName _AddRecommendation_QNAME = new QName("http://services.Assignment4.ds.com/", "addRecommendation");
    private final static QName _GetActivityForPatient_QNAME = new QName("http://services.Assignment4.ds.com/", "getActivityForPatient");
    private final static QName _GetActivitiesChartResponse_QNAME = new QName("http://services.Assignment4.ds.com/", "getActivitiesChartResponse");
    private final static QName _GetActivitiesChart_QNAME = new QName("http://services.Assignment4.ds.com/", "getActivitiesChart");
    private final static QName _AnnotateActivityResponse_QNAME = new QName("http://services.Assignment4.ds.com/", "annotateActivityResponse");
    private final static QName _AnnotateActivity_QNAME = new QName("http://services.Assignment4.ds.com/", "annotateActivity");
    private final static QName _GetMonitorForPatientAndDateResponse_QNAME = new QName("http://services.Assignment4.ds.com/", "getMonitorForPatientAndDateResponse");
    private final static QName _AddRecommendationResponse_QNAME = new QName("http://services.Assignment4.ds.com/", "addRecommendationResponse");
    private final static QName _GetActivityForPatientResponse_QNAME = new QName("http://services.Assignment4.ds.com/", "getActivityForPatientResponse");
    private final static QName _GetMonitorForPatientAndDate_QNAME = new QName("http://services.Assignment4.ds.com/", "getMonitorForPatientAndDate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ds.assignment4.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ActivitiesChartDTO }
     * 
     */
    public ActivitiesChartDTO createActivitiesChartDTO() {
        return new ActivitiesChartDTO();
    }

    /**
     * Create an instance of {@link ActivitiesChartDTO.ActivitiesMap }
     * 
     */
    public ActivitiesChartDTO.ActivitiesMap createActivitiesChartDTOActivitiesMap() {
        return new ActivitiesChartDTO.ActivitiesMap();
    }

    /**
     * Create an instance of {@link GetActivityForPatientResponse }
     * 
     */
    public GetActivityForPatientResponse createGetActivityForPatientResponse() {
        return new GetActivityForPatientResponse();
    }

    /**
     * Create an instance of {@link GetMonitorForPatientAndDate }
     * 
     */
    public GetMonitorForPatientAndDate createGetMonitorForPatientAndDate() {
        return new GetMonitorForPatientAndDate();
    }

    /**
     * Create an instance of {@link AddRecommendationResponse }
     * 
     */
    public AddRecommendationResponse createAddRecommendationResponse() {
        return new AddRecommendationResponse();
    }

    /**
     * Create an instance of {@link AnnotateActivity }
     * 
     */
    public AnnotateActivity createAnnotateActivity() {
        return new AnnotateActivity();
    }

    /**
     * Create an instance of {@link GetMonitorForPatientAndDateResponse }
     * 
     */
    public GetMonitorForPatientAndDateResponse createGetMonitorForPatientAndDateResponse() {
        return new GetMonitorForPatientAndDateResponse();
    }

    /**
     * Create an instance of {@link AnnotateActivityResponse }
     * 
     */
    public AnnotateActivityResponse createAnnotateActivityResponse() {
        return new AnnotateActivityResponse();
    }

    /**
     * Create an instance of {@link GetActivitiesChartResponse }
     * 
     */
    public GetActivitiesChartResponse createGetActivitiesChartResponse() {
        return new GetActivitiesChartResponse();
    }

    /**
     * Create an instance of {@link GetActivitiesChart }
     * 
     */
    public GetActivitiesChart createGetActivitiesChart() {
        return new GetActivitiesChart();
    }

    /**
     * Create an instance of {@link AddRecommendation }
     * 
     */
    public AddRecommendation createAddRecommendation() {
        return new AddRecommendation();
    }

    /**
     * Create an instance of {@link GetActivityForPatient }
     * 
     */
    public GetActivityForPatient createGetActivityForPatient() {
        return new GetActivityForPatient();
    }

    /**
     * Create an instance of {@link ActivityMonitorInfoDTO }
     * 
     */
    public ActivityMonitorInfoDTO createActivityMonitorInfoDTO() {
        return new ActivityMonitorInfoDTO();
    }

    /**
     * Create an instance of {@link MonitorPillsInfoDTO }
     * 
     */
    public MonitorPillsInfoDTO createMonitorPillsInfoDTO() {
        return new MonitorPillsInfoDTO();
    }

    /**
     * Create an instance of {@link ActivitiesChartDTO.ActivitiesMap.Entry }
     * 
     */
    public ActivitiesChartDTO.ActivitiesMap.Entry createActivitiesChartDTOActivitiesMapEntry() {
        return new ActivitiesChartDTO.ActivitiesMap.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddRecommendation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.Assignment4.ds.com/", name = "addRecommendation")
    public JAXBElement<AddRecommendation> createAddRecommendation(AddRecommendation value) {
        return new JAXBElement<AddRecommendation>(_AddRecommendation_QNAME, AddRecommendation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetActivityForPatient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.Assignment4.ds.com/", name = "getActivityForPatient")
    public JAXBElement<GetActivityForPatient> createGetActivityForPatient(GetActivityForPatient value) {
        return new JAXBElement<GetActivityForPatient>(_GetActivityForPatient_QNAME, GetActivityForPatient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetActivitiesChartResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.Assignment4.ds.com/", name = "getActivitiesChartResponse")
    public JAXBElement<GetActivitiesChartResponse> createGetActivitiesChartResponse(GetActivitiesChartResponse value) {
        return new JAXBElement<GetActivitiesChartResponse>(_GetActivitiesChartResponse_QNAME, GetActivitiesChartResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetActivitiesChart }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.Assignment4.ds.com/", name = "getActivitiesChart")
    public JAXBElement<GetActivitiesChart> createGetActivitiesChart(GetActivitiesChart value) {
        return new JAXBElement<GetActivitiesChart>(_GetActivitiesChart_QNAME, GetActivitiesChart.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnnotateActivityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.Assignment4.ds.com/", name = "annotateActivityResponse")
    public JAXBElement<AnnotateActivityResponse> createAnnotateActivityResponse(AnnotateActivityResponse value) {
        return new JAXBElement<AnnotateActivityResponse>(_AnnotateActivityResponse_QNAME, AnnotateActivityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnnotateActivity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.Assignment4.ds.com/", name = "annotateActivity")
    public JAXBElement<AnnotateActivity> createAnnotateActivity(AnnotateActivity value) {
        return new JAXBElement<AnnotateActivity>(_AnnotateActivity_QNAME, AnnotateActivity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMonitorForPatientAndDateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.Assignment4.ds.com/", name = "getMonitorForPatientAndDateResponse")
    public JAXBElement<GetMonitorForPatientAndDateResponse> createGetMonitorForPatientAndDateResponse(GetMonitorForPatientAndDateResponse value) {
        return new JAXBElement<GetMonitorForPatientAndDateResponse>(_GetMonitorForPatientAndDateResponse_QNAME, GetMonitorForPatientAndDateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddRecommendationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.Assignment4.ds.com/", name = "addRecommendationResponse")
    public JAXBElement<AddRecommendationResponse> createAddRecommendationResponse(AddRecommendationResponse value) {
        return new JAXBElement<AddRecommendationResponse>(_AddRecommendationResponse_QNAME, AddRecommendationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetActivityForPatientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.Assignment4.ds.com/", name = "getActivityForPatientResponse")
    public JAXBElement<GetActivityForPatientResponse> createGetActivityForPatientResponse(GetActivityForPatientResponse value) {
        return new JAXBElement<GetActivityForPatientResponse>(_GetActivityForPatientResponse_QNAME, GetActivityForPatientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMonitorForPatientAndDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.Assignment4.ds.com/", name = "getMonitorForPatientAndDate")
    public JAXBElement<GetMonitorForPatientAndDate> createGetMonitorForPatientAndDate(GetMonitorForPatientAndDate value) {
        return new JAXBElement<GetMonitorForPatientAndDate>(_GetMonitorForPatientAndDate_QNAME, GetMonitorForPatientAndDate.class, null, value);
    }

}
