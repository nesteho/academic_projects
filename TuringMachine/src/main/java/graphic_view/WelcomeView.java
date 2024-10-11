package graphic_view;

import graphic_view_controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.TuringGameException;
import model.dpoo.Observer;


/**
 * JavaFX App
 */
public class WelcomeView implements Observer {
    private Stage stage;
    private Controller controller;

    public WelcomeView(Controller controller, Stage stage) {
        if (stage == null){
            throw new NullPointerException("Pas de stage fournis");
        }
        this.controller = controller;
        this.stage = stage;
        start(this.stage);
    }
    public void start(Stage stage) {

        VBox root = new VBox(30); // le contenu de ma fenetre
        root.setStyle("-fx-background-color: white;");
        root.setPadding(new Insets(10,10,10, 10));
        root.setAlignment(Pos.CENTER);

        Label welcome = new Label("***** Welcome to Turing Machine! *****");
        root.getChildren().add(welcome);
        welcome.setStyle("-fx-fill:green; -fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-font-size: 34;");

        Image gameImage = new Image(getClass().getResourceAsStream("/welcome image.jpg"));
        ImageView gameImageView = new ImageView(gameImage);
        gameImageView.setFitHeight(250);
        gameImageView.setFitWidth(250);
        root.getChildren().add(gameImageView);

        Label askProblemId = new Label("Choose a Problem ");
        ChoiceBox problems = new ChoiceBox();
        for (int i = 1; i <= 16; i++) {
            problems.getItems().add(controller.getProblemInfo(i));
        }
        root.getChildren().addAll(askProblemId, problems);

        var askForproblem = new HBox(200);
        askForproblem.setAlignment(Pos.CENTER);
        Button generateProblemButton = new Button("Generate");
        Button chooseProblemButton = new Button("Choose");
        askForproblem.getChildren().addAll(generateProblemButton, chooseProblemButton);
        root.getChildren().add(askForproblem);

        generateProblemButton.setOnAction(actionEvent -> {
            controller.generateProblem();
        });
        chooseProblemButton.setOnAction(actionEvent -> {
            try {
                if(problems.getSelectionModel().getSelectedIndex() == -1){
                    throw new  TuringGameException("No selected problem !");
                }
                int problemIndex = problems.getSelectionModel().getSelectedIndex();
                controller.chooseProblem(problemIndex+1); // +1 car getSlectedIndex car sans :renvoie 0 pr pb 1
            }
            catch (TuringGameException e) {
                if(root.getChildren().size() == 5){
                    Label errorLabel = new Label("No selected problem !");
                    errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 20;");
                    root.getChildren().add(errorLabel);
                }
            }
        });


        var scene = new Scene( root, 700, 700);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void update(Object... args) {

    }
}