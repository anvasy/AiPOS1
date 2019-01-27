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
    private TextArea historyTextArea;

    public Window(){
        controller=new Controller(this);
        VBox vBox=new VBox();
        Label hostLabel=new Label("Host");
        Label requestLabel=new Label("Request");
        Label historyLabel=new Label("History");
        TextField hostField=new TextField();
        hostField.setText("www.martinbroadhurst.com");
        TextArea requestTextArea=new TextArea();
        requestTextArea.setText("GET / HTTP/1.0");
        requestTextArea.setPrefSize(800,200);
        historyTextArea=new TextArea();
        historyTextArea.setPrefSize(800,400);
        Button sendRequestButton=new Button("Send request");
        sendRequestButton.setOnAction(e->{
            controller.sendRequest(hostField.getText(),requestTextArea.getText()+"\n\n");
        });

        vBox.getChildren().addAll(
                hostLabel,
                hostField,
                requestLabel,
                requestTextArea,
                sendRequestButton,
                historyLabel,
                historyTextArea
        );

        Scene scene=new Scene(vBox);
        stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("AiPOS 1 HTTP client");
    }

    public void returnResponse(String response){
        historyTextArea.setText(response);
    }

    public void show(){
        stage.show();
    }
}
