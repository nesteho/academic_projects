import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import model.exception.RepositoryException;
import presenter.Presenter;
import vue.Vue;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage)  {

        //un chargeur pour ce fichier
        //  FXMLLoader loader =  new FXMLLoader(App.class.getResource("stib.fxml"));
        // définir contrôleur av loader.load()-> éléments FXML  injectés dans Vue dès  chargement du fichier
        // Eviter  erreurs : accès aux éléments FXML non initialisés.

        FXMLLoader loader =  new FXMLLoader(App.class.getResource("stib.fxml"));
        Parent root;
        try {
            root = loader.load(); // recupère elementJFX à racine
        } catch (IOException e) {
            throw new RepositoryException(e);
        }

        Model model = new Model();
        Vue vue = loader.getController();
        // NOTE fx:controller="vue.FavoriteVue" dans fichier.fxml
        new Presenter(vue,model);

        var scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
/*
   Vue vue = new Vue();
        Parent root;
        loader.setController(vue);  // ok mieux si controllerfx a un attr mais ici la vue n'a rien : cstr par def
 */