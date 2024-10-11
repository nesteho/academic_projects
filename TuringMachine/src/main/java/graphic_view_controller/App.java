package graphic_view_controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Turing;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    // stage est créer par boite noire de java qd on execute le main L10
    @Override
    public void start(Stage stage) throws Exception {
        var turing = new Turing();
        var controller = new Controller(turing, stage);
    }
}
