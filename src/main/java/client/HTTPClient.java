package client;

import controller.Controller;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class HTTPClient {
    private static Socket socket;
    private static PrintWriter writer;
    private static BufferedReader reader;
    private static final int PORT = 80;
    private static Controller controller;

    public static void sendRequest(String host, String request){
        try {
            try {
                socket = new Socket(host, PORT);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);
                writer.println(request);
                writer.flush();
                String response="";
                String str="";
                while ((str = reader.readLine()) != null) {
                    response+=str+"\n";
                }
                returnResponse(response);

            }
            finally {
                socket.close();
                writer.close();
                reader.close();
            }
        }
        catch (IOException e){
            e.getMessage();
        }
    }

    public static void setController(Controller c){
        controller=c;
    }

    private static void returnResponse(String response){
        controller.returnResponse(response);
    }
}
