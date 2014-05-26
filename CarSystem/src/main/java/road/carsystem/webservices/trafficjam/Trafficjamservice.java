
package road.carsystem.webservices.trafficjam;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "trafficjamservice", targetNamespace = "http://services.jamsystem.road/", wsdlLocation = "http://localhost:8080/jam/trafficjamservice?wsdl")
public class Trafficjamservice
    extends Service
{

    private final static URL TRAFFICJAMSERVICE_WSDL_LOCATION;
    private final static WebServiceException TRAFFICJAMSERVICE_EXCEPTION;
    private final static QName TRAFFICJAMSERVICE_QNAME = new QName("http://services.jamsystem.road/", "trafficjamservice");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/jam/trafficjamservice?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TRAFFICJAMSERVICE_WSDL_LOCATION = url;
        TRAFFICJAMSERVICE_EXCEPTION = e;
    }

    public Trafficjamservice() {
        super(__getWsdlLocation(), TRAFFICJAMSERVICE_QNAME);
    }

    public Trafficjamservice(WebServiceFeature... features) {
        super(__getWsdlLocation(), TRAFFICJAMSERVICE_QNAME, features);
    }

    public Trafficjamservice(URL wsdlLocation) {
        super(wsdlLocation, TRAFFICJAMSERVICE_QNAME);
    }

    public Trafficjamservice(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TRAFFICJAMSERVICE_QNAME, features);
    }

    public Trafficjamservice(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Trafficjamservice(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns TrafficJamServiceConnection
     */
    @WebEndpoint(name = "TrafficJamServiceConnectionPort")
    public TrafficJamServiceConnection getTrafficJamServiceConnectionPort() {
        return super.getPort(new QName("http://services.jamsystem.road/", "TrafficJamServiceConnectionPort"), TrafficJamServiceConnection.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TrafficJamServiceConnection
     */
    @WebEndpoint(name = "TrafficJamServiceConnectionPort")
    public TrafficJamServiceConnection getTrafficJamServiceConnectionPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://services.jamsystem.road/", "TrafficJamServiceConnectionPort"), TrafficJamServiceConnection.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TRAFFICJAMSERVICE_EXCEPTION!= null) {
            throw TRAFFICJAMSERVICE_EXCEPTION;
        }
        return TRAFFICJAMSERVICE_WSDL_LOCATION;
    }

}