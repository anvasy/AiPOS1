package controller;

import client.HTTPClient;
import exception.NoInternetConnectionException;
import util.URLFormatter;
import view.Window;

public class Controller {

    public String sendRequest(String host, String request){
        if(!URLFormatter.validateHost(host))
            return "INCORRECT HOST";

        host = URLFormatter.prepareHost(host);
        String response;
        try {
            response = HTTPClient.sendRequest(host, request);
        } catch (NoInternetConnectionException ex) {
            return "NO INTERNET CONNECTION!";
        }
        return response;
    }

}
