package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import structure.Headers;
import structure.RequestMethod;
import structure.ResponseStatus;
import util.URLFormatter;
import constants.Constants;

import java.util.Date;

/**
 * GUI Class
 * @author Aliaksandr Yakutsin
 */
public class Window {
    private Scene scene;
    private Controller controller;
    private TextArea responseTextArea;
    private StringBuilder history;
    private TextArea logArea;
    private TextField hostField;

    /**
     *Window class constructor
     */
    public Window(){
        responseTextArea = new TextArea();
        history = new StringBuilder();
        controller = new Controller();
        scene = new Scene(getContent());
    }


    /**
     * Creates and fulfills content for scene
     * @return returns fulfilled VBox
     */
    private VBox getContent(){
        VBox vBox = new VBox();
        Label hostLabel = new Label(Constants.HOST);
        Label portLabel = new Label(Constants.PORT);
        Label requestLabel = new Label(Constants.REQUEST);
        Label responseLabel = new Label(Constants.RESPONSE);
        Label historyLabel = new Label(Constants.HISTORY);
        hostField = new TextField();
        hostField.setText(Constants.DEFAULT_HOST);

        TextField portField = new TextField();
        portField.setText(Constants.DEFAULT_PORT);
        Label entityLabel = new Label(Constants.ENTITY_BODY);
        TextArea entityField = new TextArea();
        entityField.setPrefSize(800, 100);
        entityField.setVisible(false);
        entityLabel.setVisible(false);
        logArea = new TextArea();
        logArea.setPrefSize(100, 200);

        ObservableList<RequestMethod> options =
                FXCollections.observableArrayList(RequestMethod.GET, RequestMethod.POST, RequestMethod.HEAD);
        ComboBox<RequestMethod> comboBox = new ComboBox<>(options);

        comboBox.getSelectionModel().selectFirst();
        responseTextArea = new TextArea();
        responseTextArea.setPrefSize(800, 300);
        responseTextArea.setEditable(false);

        Button sendRequestButton = new Button(Constants.SEND_REQUEST);
        sendRequestButton.setOnAction(e->{
            int port = Integer.valueOf(portField.getText());
            responseTextArea.setText(history.toString());
            String request = comboBox.getSelectionModel().getSelectedItem()+"\n";
            Headers headers[] = Headers.values();
            for(Headers header : headers){
                request += header + "\n";
            }
            request += "\n";
            if(comboBox.getSelectionModel().getSelectedItem() == RequestMethod.POST){
                String[] paramsArray = entityField.getText().split("\n");
                String entityBody = "";
                for(String param : paramsArray) {
                    entityBody += param + "&";
                }
                entityBody = entityBody.substring(0, entityBody.length() - 1);
                request += entityBody;
            }
            history.append("REQUEST\n" + request + "\n");
            returnResponse(controller.sendRequest(hostField.getText(), port, request));
        });

        comboBox.setOnAction(e -> {
            boolean post = comboBox.getSelectionModel().getSelectedItem().toString().equals(RequestMethod.POST.toString());
            entityLabel.setVisible(post);
            entityField.setVisible(post);
        });

        Button clearHistoryButton = new Button(Constants.CLEAR);
        clearHistoryButton.setOnAction(e -> {
            history.delete(0, history.length());
            responseTextArea.setText("");
        });

        vBox.getChildren().addAll(
                hostLabel,
                hostField,
                portLabel,
                portField,
                requestLabel,
                comboBox,
                entityLabel,
                entityField,
                sendRequestButton,
                responseLabel,
                historyLabel,
                responseTextArea,
                clearHistoryButton,
                logArea
        );

        return vBox;
    }

    /**
     * Get scene for adding it to Stage {@link Window#scene}
     * @return returns scene for adding it to Stage {@link Window#scene}
     */
    public Scene getScene(){
        return scene;
    }

    /**
     * Logs and append response to {@link Window#history}
     * @param response - Host's response to client's request
     */
    private void returnResponse(String response) {
        logInfo(URLFormatter.getResponseStatus(response));
        response = checkResponseStatus(response);
        history.append("RESPONSE\n" + response + "\n=================\n=================\n\n");
        responseTextArea.setText(history.toString());
        responseTextArea.setScrollTop(Double.POSITIVE_INFINITY);
    }

    /**
     * Adding logs to {@link Window#logArea}
     * @param status Response status - response code and message
     * @see ResponseStatus
     */
    public void logInfo(ResponseStatus status) {
        logArea.setText(logArea.getText() + "\n" + new Date() + "   "
                + hostField.getText() + "    " + status);
    }

    /**
     * Response status checking
     * @param response response
     * @return response
     */
    private String checkResponseStatus(String response) {
        ResponseStatus status = URLFormatter.getResponseStatus(response);
        if(status == null)
            return "Not found";
        if(!status.equals(ResponseStatus.OK) && status.getMessage()!= null)
            return status.getCode() + " - " + status.getMessage();
        return response;
    }
}
