import javafx.application.Application;
import javafx.stage.Stage;
import util.URLFormatter;
import view.Window;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) {
        Window window=new Window();
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
