import client.HTTPClient;
import javafx.application.Application;
import javafx.stage.Stage;
import view.Window;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Window window=new Window();
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
