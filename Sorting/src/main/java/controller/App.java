package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.SortType;
import model.Sorting;
import view.FxmlController;

import java.io.IOException;

/**
 * JavaFX controller.App
 */
public class App extends Application {
    //controller
     private Sorting sorting;

    // modifier les explication des tri : sauver nb opération
    //erreur ld : supprimer le fichier module et dans dossier java et dans target

    //erreur constructeur : si cstr userdef -> plus de cstr implicite par def . Et comme le mien avait un param choicebox : erreur
    // IL FAUT CSTR PAR DEF (USERDEF OU PAS)
    //renommer Main

    /* <maven.compiler.source>20</maven.compiler.source>
        <maven.compiler.target>20</maven.compiler.target> */ // changer pour avoir 20

    private static Scene scene;

    public App() {
        this.sorting = new Sorting();
    }
    //idée graphic point : max /10 : si max = 100 : il y aura 10 tri : 1 avec 10elem/20elem/30elem...

    @Override
    public void start(Stage stage) throws IOException {
        //donner controller à la vue. comme ça quand bouton cliquer on peut venir ici

        FXMLLoader loader =  new FXMLLoader(getClass().getResource("../sort.fxml")); // attention au chemin! il cherche le fichier depuis dossier target/classes chemin relatif de App vers sort.fxml

        // si tu crée ici le controller de la vue FX, il ne faut PAS ds le fichier FXML ceci :  fx:controller="view.FxmlController" (qui est équivalebt d'un new FxmlController(sans param)
        FxmlController fxmlController = new FxmlController(this); // ici on peut lui donner ce qu'on veut
        loader.setController(fxmlController);
        sorting.registerObserver(fxmlController);

        Parent root = loader.load();
        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
    public void sort(SortType sT, int spinnerNb, int maxListsSize ) {
        sorting.start(sT,spinnerNb,maxListsSize);
    }
}