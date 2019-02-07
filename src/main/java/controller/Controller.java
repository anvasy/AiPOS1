package controller;

import client.HTTPClient;
import exception.NoInternetConnectionException;
import structure.ResponseStatus;
import util.URLFormatter;

public class Controller {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Controller.class);
    private HTTPClient httpClient;
  
    public Controller() {
        httpClient = new HTTPClient();
    }

    public String sendRequest(String host, int port, String request){
        if(!URLFormatter.validateHost(host))
          return "INCORRECT HOST";

        host = URLFormatter.prepareHost(host);
        String response;
        try {
            response = httpClient.sendRequest(host, port, request);
            logRequestStatus(host, URLFormatter.getResponseStatus(response));
        } catch (NoInternetConnectionException ex) {
            return "NO INTERNET CONNECTION!";
        }
        return response;
    }

    private void logRequestStatus(String host, ResponseStatus status) {
        logger.info("FOR HOST " + host + " RESPONSE STATUS: " + status);
    }
}
