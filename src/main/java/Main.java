import client.HTTPClient;
import javafx.application.Application;
import javafx.stage.Stage;
import util.URLFormatter;
import view.Window;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(URLFormatter.getSubstring("ru.stackoverflow.com/sdf",URLFormatter.HOST_PATTERN));
        Window window=new Window();
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
