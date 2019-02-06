import javafx.application.Application;
import javafx.stage.Stage;
import util.URLFormatter;
import view.Window;

import java.util.regex.Matcher;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) {
        Window window=new Window();
        primaryStage.setScene(window.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
