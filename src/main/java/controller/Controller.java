package controller;

import client.HTTPClient;
import view.Window;

public class Controller {
    private Window window;

    public Controller(Window window){
        this.window=window;
        HTTPClient.setController(this);
    }

    public void sendRequest(String host, String request){
        HTTPClient.sendRequest(host, request);
    }

    public void returnResponse(String response){
        window.returnResponse(response);
    }
}
