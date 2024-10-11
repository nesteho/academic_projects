package view;

import controller.Controller;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import model.dpoo.Observer;

// model implémente interface observable
// la vue implémente interface observeur (regarde model)/model est observable : a meth update : chg textField de BMR et Calorie

/**
 * User Interface of the application
 */
public class View  implements Observer  {
    private Stage stage;
    private Controller controller;
    // le controleur qui crée la view se donne lui même en param de la vue qu'il crée
    private TextField tfBmr= new TextField();
    private TextField tfCal= new TextField();
    /**
     * Constructs a View
     * @param c the controller
     * @param stage the stage of the JavaFx  application
     * @throws NullPointerException when no given stage
     */
    public View(Controller c, Stage stage) throws NullPointerException {
        if (stage == null){
            throw new NullPointerException("Pas de stage fournis");
        }
        this.stage = stage;
        this.controller= c;
        start(this.stage);
    }
    /**
     * Creates and show the scene of the Application
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) {

        primaryStage.setTitle("calcul du BMR");
        VBox root = new VBox(10);
/////////////////////////////////////  MENU BAR
        MenuBar menuBar = new MenuBar();
        Menu options = new Menu("File");
        MenuItem exit = new MenuItem("Exit");

        exit.setOnAction( actionEvent ->{
            Platform.exit();

        });
        options.getItems().addAll(exit);
        menuBar.getMenus().add(options);
        root.getChildren().add(menuBar);
 //////////////////////////////////////      DATA

        HBox gridPanes = new HBox(10);
        root.getChildren().add(gridPanes);
        VBox.setMargin(gridPanes,new Insets(20)); // gridPanes(contient data, results ) qui est enfant<Vbox root : on lui met marges 4 cotés de 20

        GridPane data = new GridPane();
        gridPanes.getChildren().add(data);
        data.setHgap(10);
        data.setVgap(10);

        Label donnes = new Label("Données");
        donnes.setUnderline(true);
        data.addRow(0,donnes);

        Label height = new Label("Taille (cm)");
        TextField tfHeight = new TextField();
        tfHeight.setPromptText("Taille en cm");
        data.addRow(1,height,tfHeight);

        Label weight = new Label("Poids (kg)");
        TextField tfWeight = new TextField();
        tfWeight.setPromptText("Poids en kg");
        data.addRow(2,weight,tfWeight);

        Label age = new Label("Age (années)");
        TextField tfAge= new TextField();
        tfAge.setPromptText("Age en années");
        data.addRow(3,age,tfAge);

        Label sex = new Label("Sexe");
        RadioButton femme= new RadioButton("Femme");
        RadioButton homme = new RadioButton("Homme");
        ToggleGroup tg = new ToggleGroup();  // effet Radio : une seule valeur à la fois, jamais les deux en mm tps
        femme.setToggleGroup(tg);
        homme.setToggleGroup(tg);
        HBox sexs = new HBox(5,femme,homme);
        data.addRow(4,sex,sexs);

        Label lf = new Label("Style de vie");
        ChoiceBox cb = new ChoiceBox();
        cb.getItems().addAll("Sédentaire","Peu actif", "Actif","Fort actif", "Extrêmement actif");
        data.addRow(5,lf,cb);

////////////////////////////////////////////  FILTERS
        // ce filtre : intercepte+traite ev avant le TextField tfAge (qui ne sait qu'afficher le contenu)
        // fct lambda car ce handler n'a qu'1 meth : handle et ce handle appelle onlyDigitsAllowed(event)
        // event est le gestionnaire d'év

        tfHeight.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            onlyDigitsAllowed(event);
        });
        tfWeight.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            onlyDigitsAllowed(event);
        });
        tfAge.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            onlyDigitsAllowed(event);
        });
/////////////////////////////////////////////  RESULTS
        GridPane results = new GridPane();
        gridPanes.getChildren().add(results);

        Label r = new Label("Résultats");
        r.setUnderline(true);

        Label bmr = new Label("BMR");
        tfBmr.setPromptText("Résultats du BMR");

        Label cal = new Label("Calories");
        tfCal.setPromptText("Dépense en calories");

        results.addRow(0,r);
        results.addRow(1,bmr,tfBmr);
        results.addRow(2,cal,tfCal);
        results.setHgap(10);
        results.setVgap(10);    // ceci au lieu de 2 Hbox qu'on ajoute result : pas allignement entre les cases

//////////////////////////////
        Button calculateBMRButton = new Button("Calcul du BMR");
        calculateBMRButton.setPrefSize(480,20);

        calculateBMRButton.setOnAction( actionEvent -> {  // actionEvent est un objet de la classe EventHandler<ActionEvent>

            //  sexe passé en param ainsi : tg.getSelectedToggle().toString() -> retourne rep textuelle de l'objet du radio selectionné càd : RadioButton@6a22c193[styleClass=radio-button]'Homme'
            // ET PAS le String du radioButton : qui est 'Homme'  : sol -> caster en radio button puis récup texte
            try {
                calculateBmr( ( (RadioButton) tg.getSelectedToggle() ).getText(),
                        Double.parseDouble(tfHeight.getText()), Double.parseDouble(tfWeight.getText()),
                        Integer.parseInt(tfAge.getText()),cb.getSelectionModel().getSelectedItem().toString() );
            }
            catch (Exception e){
                Text failed = new Text("Failed!");
                tfBmr.setStyle("-fx-text-fill: red;"); // Affiche Failed! en rouge
                tfBmr.setText(failed.getText());
                tfCal.setStyle("-fx-text-fill: red;");
                tfCal.setText(failed.getText());
            }

        });
        root.getChildren().add(calculateBMRButton);
        VBox.setMargin(calculateBMRButton,new Insets(20));
////////////////////////////////////
        Button clearButton = new Button("Clear");
        clearButton.setPrefSize(480,20);

        clearButton.setOnAction( actionEvent -> {

            if( tg.getSelectedToggle() != null){
                tg.getSelectedToggle().setSelected(false);
            }
            if(cb.getSelectionModel() != null){
                cb.getSelectionModel().clearSelection();
            }
            tfHeight.clear();
            tfWeight.clear();
            tfAge.clear();
            tfBmr.clear();
            tfCal.clear();
        });
        root.getChildren().add(clearButton);
        VBox.setMargin(clearButton,new Insets(20));

/////////////////////////////////////
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void calculateBmr(String sex, double height, double weight, int age, String na) {

        if(height == 0 || weight == 0 || age == 0 ){
            String instruction = "Veuillez entrer une valeur supérieure à zéro";
            Alert value0 = new Alert(Alert.AlertType.ERROR, instruction);

            if(age == 0){
                value0.setHeaderText("Valeur de l'âge erronée !");
                value0.show();
            }
            if(weight == 0){
                value0.setHeaderText("Valeur du poids erronée !");
                value0.show();
            }
            if(height == 0){
                value0.setHeaderText("Valeur de la taille erronée !");
                value0.show();
            }
        }
        else {
            // controller donne ces param au model qui calcule et redonne val à afficher à la vue
            controller.calculateBmr(sex, height, weight, age, na);
        }
    }
    /**
     * Updates the current values with the given new values
     * @param bmr The updated  Basal Metabolic Rate (BMR) value.
     * @param cal The updated calorie value.
     */
    @Override
    public void update(double bmr, double cal) {
        // retour à  couleur par def (fct pas si ds catch : ne sera jamais affiché en rouge)
        // utile si  au moins 1 Failed! affiché (pour pas que bmr et cal soit affiché en rouge!)
        tfBmr.setStyle("");
        tfCal.setStyle("");
        tfBmr.setText(""+bmr);
        tfCal.setText(""+cal);
    }
    private void onlyDigitsAllowed(KeyEvent keyEvent){
        if ( !keyEvent.getCharacter().matches("\\d") ){
            keyEvent.consume();
        }
    }
}