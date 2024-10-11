package graphic_view;

import graphic_view_controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PropertyName;
import model.dpoo.Observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
// faire un alerte : quand on test plus de 3 val

public class PlayView extends VBox implements Observer , PropertyChangeListener {
    // obs des chg de propritete de mes composants
    // todo  notes : classe qui a 1 meth abstraite peut être redefini en lamda
    private Controller controller;
    private Stage stage;
    private int nbChildren;
    private NumericalKeyBoard keyboard;
    private List<ValidatorGraphic> validators;

    // List de val et pas conteneur d'element graphique
    public PlayView(Controller controller, Stage stage){

        this.controller = controller;
        this.stage = stage;
        this.keyboard = new NumericalKeyBoard(10 ,3,1,5);
        this.validators = new ArrayList<>();
        this.setStyle("-fx-background-color: green;");
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10,10,10, 10)); //  (haut, droite, bas, gauche).
        this.setSpacing(25);
        play();
        keyboard.addPropertyChangeListener(this); // s'enregistrer comme observateur de ses enfants
    }
    public void play(){
        HBox playValidators = new HBox(50);
        char c = 'A';
        int nbValidators = controller.getValidatorsNb();
        for (int i = 0; i < nbValidators ; i++) {

            String robotPath = "/robot" + c + ".png"; // '/' pr chercher apt src
            ImageView robotImg = new ImageView(getClass().getResource(robotPath).toString()); // prend URL sous forme de string getClasse retourne ou se tv la classe
            String validatorPath  = "/card" + controller.getNumValidator(i) + ".png";  // i-1 car indice
            ImageView validatorImg = new ImageView(getClass().getResource(validatorPath).toString());
            var validatorName = new Label(c+"");

            var validator = new ValidatorGraphic(controller.getValidator(i), robotImg,validatorName, validatorImg);
            validators.add(validator); // ce qui est ajouté/validator doit être un composant graphic container-> ds Herite de VBox
            playValidators.getChildren().add(validator);
            validator.addPropertyChangeListener(this);
            c++;
        }

        this.getChildren().add(playValidators);
        VBox playInfo = new VBox(50);
        playInfo.setMinWidth(250);
        playInfo.setMinWidth(100);

        var roundInfo = createInfoHBox("Round : " );
        var scoreInfo = createInfoHBox("Score : " );
        playInfo.getChildren().addAll(roundInfo,scoreInfo);

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 20;");
        HBox hBox = new HBox(50);
        hBox.getChildren().addAll(playInfo,keyboard, errorLabel);
        this.getChildren().add(hBox);

        HBox nextMoves = createNextMovesHBox();
        nextMoves.setAlignment(Pos.CENTER);
        this.getChildren().add(nextMoves);

        HBox undoRedo = createUndoRedoHBox();
        undoRedo.setAlignment(Pos.CENTER);
        this.getChildren().add(undoRedo);

        this.nbChildren = this.getChildren().size();
        var s = new Scene(this); // tt les composant de la fenetre est mise sur une une scenes
        stage.setScene(s);
        stage.setTitle("Turing Game Machine");
        stage.show();
    }
    @Override
    public void update(Object... args) {// le model a notifie un chg
        var propertyChange = (PropertyName)  args[0];
        switch (propertyChange){
            case HAS_WON_THE_GAME,HAS_LOST_THE_GAME ->{
                var endGameMessage =  (String) args[1];
                showEndGameAlert(endGameMessage);
                stage.hide();
            }
            case VALIDATOR_RESULT ->{
                var valIndex = (int)args[2] ;
                var result = (boolean)args[3] ; // ds vue console : boolean = arg 3
                validators.get(valIndex).displayTestResult(result);
                displayError("");
            }
            case NEXT_ROUND ->{
                for ( ValidatorGraphic v :  validators) {
                    v.resetTestResult();
                }
                keyboard.unSelectAll();
                displayError("");
            }
            case UNDO_A_VALIDATOR_TEST -> {
                var valIndex = (int)args[1];
                var toUntest = (ValidatorGraphic) this.validators.get(valIndex);
                toUntest.resetTestResult();
            }
            case UN_ENTER_A_CODE -> {
                keyboard.unSelectAll();
            }
            case REDO_ENTER_A_CODE -> {
            }
            case REDO ->{
            }
        }
        setPlayInfo();
    }
    private void setPlayInfo() {
        var box =  (HBox) this.getChildren().get(1);
        var playInfoBox = ( (VBox) box.getChildren().get(0));
        var roundBox = (HBox) playInfoBox.getChildren().get(0);
        var scoreBox = (HBox) playInfoBox.getChildren().get(1);

        String round = Integer.toString(controller.getNbRound());
        String score = Integer.toString(controller.getScore());
        ( (Label) roundBox.getChildren().get(1)).setText(round);
        ( (Label) scoreBox.getChildren().get(1)).setText(score);
    }
    private void showEndGameAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End Game");
        alert.setHeaderText(null);
        alert.setContentText(message);
        /*   // alert.getButtonTypes().add(ButtonType.NO); // ou  ButtonType.YES : rajouter 2 boutons
        var b =ButtonType.YES;
        alert.getButtonTypes().add(b);
        var t = alert.showAndWait();
        if ( t.get() == b) {
           new Controller( controller.getTuring , stage); // rajouter getTuring dans controller
        }// return bool true -> nv partie, false ferme page
        */
        alert.showAndWait();
    }

    private HBox createInfoHBox(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 20;");
        Label number = new Label("");
        number.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 20;");
        var box = new HBox(label,number);
        return box;
    }

    // mes enfants m'ont notifié un ev (on a clique sur leur boutons
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName() == NumericalKeyBoard.PROPERTY_NAME){ // ici on sait quelle classe le clavier me dit qu'il a change de code
            int userCode = (int) evt.getNewValue();
            controller.testCode(userCode);
           // displayError(""); // aps testCode :  tt c'est bien passe-> pas d'erreur
        }
        if (evt.getPropertyName() == ValidatorGraphic.PROPERTY_NAME){
            var testedValidatorIndex = validators.indexOf(evt.getSource());
            controller.testValidator(testedValidatorIndex);
            //displayError("");
        }
    }
    public void displayError(String message){
        ( (Label)  ( (HBox) getChildren().get(nbChildren-3)).getChildren().get(2)).setText(message);
    }
    private HBox createUndoRedoHBox() {
        Button undoButton = new Button("Undo");
        undoButton.setOnAction(e -> {
            controller.undo();
        });
        Button redoButton = new Button("Redo");
        redoButton.setOnAction(e -> {
            controller.redo();
        });
        var undoRedo = new HBox(50);
        undoRedo.getChildren().addAll(undoButton, redoButton);
        return undoRedo;
    }
    private HBox createNextMovesHBox() {
        Button nextRoundButton = new Button("Next Round");
        nextRoundButton.setOnAction(e -> {
            controller.nextRound();
        });
        Button giveUpButton = new Button("Give Up");
        giveUpButton.setOnAction(event -> {
            this.stage.close();
        });
        Button guessCodeButton = new Button("Guess Code");
        guessCodeButton.setOnAction(e -> {
            controller.guessCode();
        });
        var box = new HBox(50);
        box.getChildren().addAll(nextRoundButton,giveUpButton, guessCodeButton);
        return box;
    }
}
