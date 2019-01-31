package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.URLFormatter;

public class Window {
    private Stage stage;
    private Controller controller;
    private TextArea responseTextArea;
    private StringBuilder history;

    public Window(){
        controller = new Controller(this);
        history = new StringBuilder();
        VBox vBox = new VBox();
        Label hostLabel = new Label("Host");
        Label requestLabel = new Label("Request");
        Label responseLabel = new Label("Response");
        Label historyLabel = new Label("History");
        TextField hostField = new TextField();
        hostField.setText("www.martinbroadhurst.com");
        //TextArea requestTextArea=new TextArea();
        //requestTextArea.setText("GET / HTTP/1.0");

        ObservableList<String> options =
                FXCollections.observableArrayList("GET / HTTP/1.0", "HEAD / HTTP/1.0", "POST / HTTP/1.0");
        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.getSelectionModel().selectFirst();

        //requestTextArea.setPrefSize(300,150);
        responseTextArea = new TextArea();
        responseTextArea.setPrefSize(800,400);
        responseTextArea.setEditable(false);
        Button sendRequestButton = new Button("Send request");
        sendRequestButton.setOnAction(e->{
            //history.append("REQUEST\n" + requestTextArea.getText() + "\n\n");
            history.append("REQUEST\n" + comboBox.getSelectionModel().getSelectedItem()+ "\n\n");
            responseTextArea.setText(history.toString());
            //controller.sendRequest(hostField.getText(),requestTextArea.getText()+"\n\n");
            returnResponse(controller.sendRequest(hostField.getText(),comboBox.getSelectionModel().getSelectedItem() +"\n\n"));
        });

        Button clearHistoryButton = new Button("Clear");
        clearHistoryButton.setOnAction(e->{
            history.delete(0, history.length());
            responseTextArea.setText("");
        });

        vBox.getChildren().addAll(
                hostLabel,
                hostField,
                requestLabel,
                comboBox,
                //requestTextArea,
                sendRequestButton,
                responseLabel,
                historyLabel,
                responseTextArea,
                clearHistoryButton
        );

        Scene scene = new Scene(vBox);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("AiPOS 1 HTTP client");
    }

    private void returnResponse(String response){
        history.append("RESPONSE\n" + response + "\n=================\n=================\n\n");
        responseTextArea.setText(history.toString());
        System.out.println(URLFormatter.getResponseStatus(response));
    }

    public void show(){
        stage.show();
    }
}
