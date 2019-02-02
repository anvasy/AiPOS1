package client;

import exception.NoInternetConnectionException;

import java.io.*;
import java.net.Socket;

public class HTTPClient {
    private static Socket socket;
    private static PrintWriter writer;
    private static BufferedReader reader;
    private static final int PORT = 80;

    public static String sendRequest(String host, String request) throws NoInternetConnectionException {
        try {
            socket = new Socket(host, PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            if(socket == null)
                throw new NoInternetConnectionException();

            writer.println(request);
            writer.flush();
            StringBuilder response = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {
                response.append(str + "\n");
            }

            socket.close();
            writer.close();
            reader.close();

            return response.toString();
        }
        catch (IOException e){
            return e.getMessage();
        }
    }
}
