package client;

import exception.NoInternetConnectionException;

import java.io.*;
import java.net.Socket;

public class HTTPClient {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public String sendRequest(String host, int port, String request) throws NoInternetConnectionException {
        try {
            socket = new Socket(host, port);
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
