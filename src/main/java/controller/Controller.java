package controller;

import client.HTTPClient;
import exception.NoInternetConnectionException;
import util.URLFormatter;
import view.Window;

public class Controller {
    private Window window;
    private HTTPClient httpClient;

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
            response = httpClient.sendRequest(host, port, request);
        } catch (NoInternetConnectionException ex) {
            return "NO INTERNET CONNECTION!";
        }
        return response;
    }
}