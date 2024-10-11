package graphic_view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.validator.Validator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents a graphic represention of a Validator
 */
public class ValidatorGraphic extends VBox {
    //prperty ch sup doit rien implementer
    private Validator validator;
    private int nbChildren;
    private PropertyChangeSupport propChS;
    public  static  final String PROPERTY_NAME = "trying to test a validator";

    // est selec -> doit le dire à Vue2 (parent)
    // je suis x -< signale que je suis selectionne

    /**
     * Constructs a ValidatorGraphic instance
     * @param validator the validator
     * @param robotHead the validator head
     * @param validatorName the validator name
     * @param validatorImg the validator description
     */
    public ValidatorGraphic(Validator validator, ImageView robotHead, Label validatorName, ImageView validatorImg) {
        this.validator = validator;
        propChS = new PropertyChangeSupport(this);

        robotHead.setFitHeight(65);
        robotHead.setFitWidth(65);
        validatorName.setStyle("-fx-font-weight: bold; -fx-font-size: 16;" +
                " -fx-text-fill: white;");

        Button testValidatorButton = new Button("Test Me !");
        testValidatorButton.setOnAction(e -> {
             notifiyPropertyChange();
        });
        var h = new HBox(20);
        h.getChildren().addAll(testValidatorButton);

        validatorImg.setFitHeight(180);
        validatorImg.setFitWidth(180);

        this.getChildren().addAll(robotHead,validatorName, validatorImg, h);
        nbChildren = this.getChildren().size();
        this.setAlignment(Pos.CENTER); // alignement des enfants
        this.setSpacing(15);
    }
    /**
     * Displays the result of the tested validator
     * @param result the result of the tested validator
     */
     void displayTestResult(boolean result){
        ImageView resultIcon;
        if (result) {
            resultIcon = new ImageView(new Image(getClass().getResource("/green tick.png").toString()));
        } else {
            resultIcon = new ImageView(new Image(getClass().getResource("/red cross.png").toString()));
        }
        resultIcon.setFitHeight(40);
        resultIcon.setFitWidth(40);
        var hBox = (HBox) this.getChildren().get(nbChildren - 1);
        hBox.getChildren().add(resultIcon);
    }
    /**
     * Resets the result of the tested validator
     */
     void resetTestResult(){
        var hBox = (HBox) this.getChildren().get(nbChildren - 1);
        if (hBox.getChildren().size() == 2){
            hBox.getChildren().remove(hBox.getChildren().size()-1);
        }
    }
    /**
     * Adds a PropertyChangeListener to the list of listeners.
     * @param pcs the PropertyChangeListener
     */
    void addPropertyChangeListener (PropertyChangeListener pcs ){
        propChS.addPropertyChangeListener(pcs);
    }

    /**
     * Removes a PropertyChangeListener from the list of listeners.
     * @param pcs the PropertyChangeListener
     */
    void removePropertyChangeListener (PropertyChangeListener pcs ){
        propChS.removePropertyChangeListener(pcs);
    }
    private void notifiyPropertyChange(){
        propChS.firePropertyChange(PROPERTY_NAME, 1 ,-1);  // pas de bool car c pas clique qui set le state
        // c qd notifiy du model -> vue  que state       change
        // le userCode est deja connu qd on clique button
    }
}
