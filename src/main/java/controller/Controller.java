package controller;

import client.HTTPClient;
import exception.NoInternetConnectionException;
import structure.ResponseStatus;
import util.URLFormatter;

/**
 * MVC Controller
 * @author Aliaksandr Yakutsin
 */
public class Controller {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Controller.class);
    private HTTPClient httpClient;

    /**
     * Class constructor
     * @see HTTPClient
     */
    public Controller() {
        httpClient = new HTTPClient();
    }

    /**
     * Sends request to server
     * @param host Address of target node at network to send request
     * @param port Number of interface at host
     * @param request Request to server, specifies method, resource and version of HTTP protocol, also headers
     * @return If host is invalid returns "INCORRECT HOST". If everything is fine returns server's response.
     */
    public String sendRequest(String host, int port, String request){
        if(!URLFormatter.validateHost(host)) {
            return "INCORRECT HOST";
        }

        host = URLFormatter.prepareHost(host);
        String response;
        try {
            response = httpClient.sendRequest(host, port, request);
            logRequestStatus(host, URLFormatter.getResponseStatus(response));
        } catch (NoInternetConnectionException ex) {
            logger.error(ex);
            return "NO INTERNET CONNECTION!";
        }
        return response;
    }


    /**
     * Logging information about requests to some host
     * @param host Address of target node at network to send request
     * @param status Status of response - code and message
     */
    private void logRequestStatus(String host, ResponseStatus status) {
        logger.info("FOR HOST " + host + " RESPONSE STATUS: " + status);
    }
}
