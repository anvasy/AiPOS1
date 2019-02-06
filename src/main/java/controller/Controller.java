package controller;

import client.HTTPClient;
import exception.NoInternetConnectionException;
import structure.ResponseStatus;
import util.URLFormatter;
import view.Window;

public class Controller {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Controller.class);
    private Window window;
    private HTTPClient httpClient;
  
    public Controller(Window window) {
        this.window = window;
    }


    public Controller(Window window){
        this.window=window;
        httpClient=new HTTPClient();
    }

    public String sendRequest(String host, int port, String request){
        if(!URLFormatter.validateHost(host))
          return "INCORRECT HOST";

        host = URLFormatter.prepareHost(host);
        String response;
        try {
            response = HTTPClient.sendRequest(host, port, request);
            response = checkResponseStatus(response);
            logRequestStatus(host, URLFormatter.getResponseStatus(response));

        } catch (NoInternetConnectionException ex) {
            return "NO INTERNET CONNECTION!";
        }
        return response;
    }

    private void logRequestStatus(String host, ResponseStatus status) {
        logger.info("FOR HOST " + host + " RESPONSE STATUS: " + status);
        window.logInfo(status);
    }

    private String checkResponseStatus(String response) {
        ResponseStatus status = URLFormatter.getResponseStatus(response);
        if(status == null)
            return "Not found";
        if(!status.equals(ResponseStatus.OK) && status.getMessage()!= null)
            return status.getCode() + " - " + status.getMessage();
        return response;
    }
}
