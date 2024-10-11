package controller;
import javafx.stage.Stage;
import model.BmrModel;
import view.View;

/**
 *  Controller Represent in the Model-View-Controller pattern
 */
public class Controller  {
    private BmrModel model;
    private  View view;
    /**
     * Constructs a Controller
     * @param model The BmrModel to associate with the controller
     * @param stage The Stage used for the view
     * @throws NullPointerException when no given stage
     */
    public Controller(BmrModel model, Stage stage) throws NullPointerException {
        if (stage == null){
            throw new NullPointerException("Aucun stage n'a ete fourni");
        }
        this.model = model;
        this.view = new View(this, stage);
        model.registerObserver(view);   // ici on enregistre la vue comme un observateur du modele
    }
    /**
     * Initiates the calculation of BMR based on provided data and notifies observers
     * @param sex user's sex
     * @param height  user's height
     * @param weight user's weight
     * @param age user's age
     * @param activity_level user's activity level
     */
    public void calculateBmr(String sex, double height, double weight, int age, String activity_level) {
        model.calculateBmr(sex, height, weight, age,activity_level);
    }
}
