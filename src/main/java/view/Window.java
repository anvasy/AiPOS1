package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Window {
    private Stage stage;
    private Controller controller;
    private TextArea responseTextArea;
    private StringBuilder history;

    public Window(){
        controller=new Controller(this);
        history=new StringBuilder();
        VBox vBox=new VBox();
        Label hostLabel=new Label("Host");
        Label requestLabel=new Label("Request");
        Label responseLabel=new Label("History");
        TextField hostField=new TextField();
        hostField.setText("www.martinbroadhurst.com");
        TextArea requestTextArea=new TextArea();
        requestTextArea.setText("GET / HTTP/1.0");
        requestTextArea.setPrefSize(800,200);
        responseTextArea=new TextArea();
        responseTextArea.setPrefSize(800,400);
        Button sendRequestButton=new Button("Send request");
        sendRequestButton.setOnAction(e->{
            history.append("REQUEST\n"+requestTextArea.getText()+"\n");
            responseTextArea.setText(history.toString());
            controller.sendRequest(hostField.getText(),requestTextArea.getText()+"\n\n");
        });

        vBox.getChildren().addAll(
                hostLabel,
                hostField,
                requestLabel,
                requestTextArea,
                sendRequestButton,
                responseLabel,
                responseTextArea
        );

        Scene scene=new Scene(vBox);
        stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("AiPOS 1 HTTP client");
    }

    public void returnResponse(String response){
        history.append("RESPONSE\n"+response);
        responseTextArea.setText(history.toString());
    }

    public void show(){
        stage.show();
    }
}
