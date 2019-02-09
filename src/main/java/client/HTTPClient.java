package client;

import exception.NoInternetConnectionException;

import java.io.*;
import java.net.Socket;

/**
 * Client for working with network
 * @author Aliaksandr Yakutsin
 */
public class HTTPClient {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    /**
     * Sends request to server
     * @param host Address of target node at network to send request
     * @param port Number of interface at host
     * @param request Request to server, specifies method, resource and version of HTTP protocol, also headers
     * @return Returns server's response
     * @throws NoInternetConnectionException Throws {@link NoInternetConnectionException} if connection can't be established
     */
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

            writer.close();
            reader.close();
            socket.close();

            return response.toString();
        }
        catch (IOException e){
            return e.getMessage();
        }
    }
}
