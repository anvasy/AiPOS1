package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import structure.Headers;
import structure.RequestMethod;
import util.URLFormatter;

public class Window {
    private Scene scene;
    private Controller controller;
    private TextArea responseTextArea;
    private StringBuilder history;

    public Window(){
        responseTextArea=new TextArea();
        history=new StringBuilder();
        controller=new Controller(this);
        scene=new Scene(getContent());
    }

    private VBox getContent(){
        VBox vBox=new VBox();
        Label hostLabel = new Label("Host");
        Label portLabel=new Label("Port");
        Label requestLabel = new Label("Request");
        Label responseLabel = new Label("Response");
        Label historyLabel = new Label("History");
        TextField hostField = new TextField();
        hostField.setText("www.martinbroadhurst.com");
        TextField portField=new TextField();
        portField.setText("80");
        Label entityLabel=new Label("Entity body");
        TextArea entityField=new TextArea();

        ObservableList<RequestMethod> options =
                FXCollections.observableArrayList(RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD);
        ComboBox<RequestMethod> comboBox = new ComboBox<>(options);

        comboBox.getSelectionModel().selectFirst();
        responseTextArea = new TextArea();
        responseTextArea.setPrefSize(800,400);
        responseTextArea.setEditable(false);
        Button sendRequestButton = new Button("Send request");
        sendRequestButton.setOnAction(e->{
            int port=Integer.valueOf(portField.getText());
            responseTextArea.setText(history.toString());
            String request=comboBox.getSelectionModel().getSelectedItem()+"\n";
            Headers headers[]= Headers.values();
            for(Headers header:headers){
                request+=header+"\n";
            }
            request+="\n";
            if(comboBox.getSelectionModel().getSelectedItem()==RequestMethod.POST){
                String[] paramsArray=entityField.getText().split("\n");
                String entityBody="";
                for(String param:paramsArray){
                    entityBody+=param+"&";
                }
                entityBody=entityBody.substring(0,entityBody.length()-1);
                request+=entityBody;
            }
            history.append("REQUEST\n" + request+"\n");
            returnResponse(controller.sendRequest(hostField.getText(), port, request));
        });

        Button clearHistoryButton = new Button("Clear");
        clearHistoryButton.setOnAction(e->{
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
                clearHistoryButton
        );

        return vBox;
    }

    public Scene getScene(){
        return scene;
    }

    private void returnResponse(String response){
        history.append("RESPONSE\n" + response + "\n=================\n=================\n\n");
        responseTextArea.setText(history.toString());
        responseTextArea.setScrollTop(Double.POSITIVE_INFINITY);
        System.out.println(URLFormatter.getResponseStatus(response));
    }
}
