package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import structure.RequestMethod;
import structure.ResponseStatus;
import util.URLFormatter;

import java.util.Date;

public class Window {
    private Stage stage;
    private Controller controller;
    private TextArea responseTextArea;
    private StringBuilder history;
    private TextArea logArea;
    private TextField hostField;

    public Window() {
        controller = new Controller(this);
        history = new StringBuilder();
        VBox vBox = new VBox();
        Label hostLabel = new Label("Host");
        Label requestLabel = new Label("Request");
        Label responseLabel = new Label("Response");
        Label historyLabel = new Label("History");
        hostField = new TextField();
        hostField.setText("www.martinbroadhurst.com");
        Label entityLabel = new Label("Entity body");
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

        Button sendRequestButton = new Button("Send request");
        sendRequestButton.setOnAction(e -> {
            responseTextArea.setText(history.toString());
            String request = comboBox.getSelectionModel().getSelectedItem() + "\n\n";
            if (comboBox.getSelectionModel().getSelectedItem() == RequestMethod.POST) {
                String[] paramsArray = entityField.getText().split("\n");
                String entityBody = "";
                for (String param : paramsArray) {
                    entityBody += param + "&";
                }
                entityBody = entityBody.substring(0, entityBody.length() - 1);
                request += entityBody;
            }
            history.append("REQUEST\n" + request + "\n");
            returnResponse(controller.sendRequest(hostField.getText(), request));
        });

        comboBox.setOnAction(e -> {
            boolean post = comboBox.getSelectionModel().getSelectedItem().toString().equals(RequestMethod.POST.toString());
            entityLabel.setVisible(post);
            entityField.setVisible(post);
        });

        Button clearHistoryButton = new Button("Clear");
        clearHistoryButton.setOnAction(e -> {
            history.delete(0, history.length());
            responseTextArea.setText("");
        });

        vBox.getChildren().addAll(
                hostLabel,
                hostField,
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

        Scene scene = new Scene(vBox);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("AiPOS 1 HTTP client");
    }

    private void returnResponse(String response) {
        history.append("RESPONSE\n" + response + "\n=================\n=================\n\n");
        responseTextArea.setText(history.toString());
    }

    public void logInfo(ResponseStatus status) {
        logArea.setText(logArea.getText() + "\n" + new Date() + "   "
                + hostField.getText() + "    " + status);
    }

    public void show() {
        stage.show();
    }

}
