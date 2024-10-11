package graphic_view_controller;

import graphic_view.PlayView;
import graphic_view.WelcomeView;
import javafx.stage.Stage;
import model.Turing;
import model.TuringGameException;
import model.validator.Validator;

/**
 * Controller represents in the MVC DP
 */
public class Controller {
    private Stage primary;
    private Turing turing;
    private WelcomeView view;
    private PlayView playView;

    /**
     * Constructs a Controller instance
     * @param turing the game facade
     * @param stage the stage to display
     */
    public Controller(Turing turing, Stage stage) {
        if (stage == null){
            throw new NullPointerException("Aucun stage n'a ete fourni");
        }
        this.turing = turing;
        this.view = new WelcomeView( this, stage);
        this.playView = null;
        this.primary = stage;// la vue doit avoir le controleur comme attr?
    }
    /**
     * Creates a new play
     */
    public void generateProblem(){
        try {
            turing.startGame(-1); // retourne la partie en cours
            //: passer à la vue les numéros des validaterus
            } catch (TuringGameException e) {
            throw new RuntimeException(e);
        }

       preparePlayView();
    }
    private void preparePlayView(){
        var stage = new Stage();
        playView = new PlayView(this, stage); // creer une scene/ fenetre creer une vue
        primary.hide(); // cacher la fenetre/ page d'accueil
        turing.register(playView);
    }

    /**
     * Creates a new play
     * @param index the index of the problem to play
     */
    public void chooseProblem(int index) {
        try {
            turing.startGame(index);
            preparePlayView();
        }
        catch (TuringGameException e) {
            throw new RuntimeException(e); // n'arrivera pas ds vue javafx car choicebox
        }
    }

    public String getProblemInfo(int index){
        try {
           return turing.showProblem(index);
        }
        catch (TuringGameException e) {
            throw new RuntimeException(e);
        }
    }
    public void testCode(int index){
        try {
            turing.enterCode(index);
        } catch (TuringGameException tge) {
            playView.displayError(tge.getMessage());
        }
    }
    public void testValidator(int index){
        try {
            turing.validatorResults(index);
        } catch (TuringGameException tge) {
            playView.displayError(tge.getMessage());
        }
    }
    public int getNumValidator(int index ){
        try {
            return turing.getValidatorId(index);
        } catch (TuringGameException tge) {
            playView.displayError(tge.getMessage());
        }
        return -1;
    }
    public int getValidatorsNb( ) {
        return turing.getValidatorsNb();
    }
    public Validator getValidator(int index){
        try {
            return turing.getValidator(index);
        } catch (TuringGameException tge) {
            playView.displayError(tge.getMessage());
        }
        return null;
    }
    public void undo() {
        try {
            turing.undo();
        } catch (TuringGameException tge) {
            playView.displayError(tge.getMessage());
        }
    }
    public void redo()  {
        try {
            turing.redo();
        } catch (TuringGameException tge) {
            playView.displayError(tge.getMessage());
        }
    }
    public void nextRound() {
        try {
            turing.nextRound();
        } catch (TuringGameException tge) {
            playView.displayError(tge.getMessage());
        }
    }
    public void guessCode(){
        try {
            turing.guessCode();
        } catch (TuringGameException tge) {
            playView.displayError(tge.getMessage());
        }
    }
    public int getScore() { return turing.getScore();}
    public int getNbRound() {return turing.getNbRound();}
}// try appel model catch  exception et dedans  : donne message : getMessage  a vue pr qu'elle affiche
