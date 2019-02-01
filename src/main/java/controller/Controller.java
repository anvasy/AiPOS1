package controller;

import client.HTTPClient;
import exception.NoInternetConnectionException;
import view.Window;

public class Controller {
    private Window window;

    public Controller(Window window){
        this.window = window;
        HTTPClient.setController(this);
    }

    public String sendRequest(String host, String request){
        String response;
        try {
            response = HTTPClient.sendRequest(host, request);
        } catch (NoInternetConnectionException ex) {
            //window.returnResponse("NO INTERNET CONNECTION!");
            return "NO INTERNET CONNECTION!";
        }
        return response;
    }
}
