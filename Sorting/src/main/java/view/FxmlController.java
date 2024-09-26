package view;

import controller.App;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import model.dpoo.Observer;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class FxmlController implements Initializable, Observer {
    public FxmlController(App app) {
        this.app = app;
    }

    //vue (controller de la vue fxml
    private App app ;
    @FXML
    private ChoiceBox sortChoice;
    @FXML
    private ChoiceBox configurationChoice;
    @FXML
    private Spinner threadSpinner;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private TableView<Sort> table;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn sizeCol;
    @FXML
    private TableColumn swapCol;
    @FXML
    private TableColumn durationCol;
    @FXML
    private LineChart chart;
    @FXML
    private Label begin;
    @FXML
    private Label end;
    @FXML
    private Label duration;
    @FXML
    private Label activeThreads;
    private int sortedArrays;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        threadSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10));
        sortChoice.getItems().addAll("Tri fusion", "Tri bulles", "Tri insertion", "Tri sélection", "Tri rapide");
        sortChoice.setValue(sortChoice.getItems().get(0));
        configurationChoice.getItems().addAll("Very easy : 100", "Easy : 1000", "Medium : 10000","Hard : 100000");
        configurationChoice.setValue(configurationChoice.getItems().get(0));

        ObservableList<Sort> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("sortType"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        swapCol.setCellValueFactory(new PropertyValueFactory<>("operations"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));

        sortedArrays = 0;
    }
    @FXML
    private void handleStartButtonAction(){
        Platform.runLater(() -> progressBar.setProgress(0.0));

        var beginTime = LocalDateTime.now();
        String type =  sortChoice.getSelectionModel().getSelectedItem().toString();
        var sT = stringToSortType(type);

        XYChart.Series data = new XYChart.Series(); //initialiser  chart qd  on connait type du tri choisi
        chart.getData().add(data);
        data.setName(type);

        var level =  configurationChoice.getSelectionModel().getSelectedItem().toString();
        int maxListsSize =  Integer.parseInt( level.substring(  level.indexOf(":")+2) ) ;
        int spinnerNb = Integer.parseInt( threadSpinner.getValue().toString());
        app.sort(sT,spinnerNb,maxListsSize);

        var endTime = LocalDateTime.now();
        Duration entireDuration = Duration.between(beginTime, endTime);
        setExecutionInfo(beginTime,endTime,entireDuration);
    }
    private SortType stringToSortType(String type) {
        SortType sT;
        switch (type) {
            case "Tri fusion":
                sT = SortType.TRI_FUSION;
                break;
            case "Tri bulles":
                sT = SortType.TRI_BULLES;
                break;
            case "Tri insertion":
                sT = SortType.TRI_INSERTION;
                break;
            case "Tri sélection":
                sT = SortType.TRI_SELECTION;
                break;
            case "Tri rapide":
                sT = SortType.TRI_RAPIDE;
                break;
            default:
                throw new IllegalArgumentException("Type de tri invalide : " + type);
        }
        return sT;
    }
    private void setExecutionInfo(LocalDateTime beginTime, LocalDateTime endTime, Duration entireDuration){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        begin.setText("Début : " + beginTime.format(formatter));
        end.setText("Fin : " + endTime.format(formatter));
        duration.setText("Durée : " + entireDuration.getSeconds()*1000 + "ms"); // miliseconde
    }
    private void setActiveThreads(){
        activeThreads.setText("Threads actifs " + Thread.activeCount());
        System.out.println("Threads actifs " + Thread.activeCount());
    }
    private void setProgressBar(){
        sortedArrays++;// TODO prendre ça dans le modèle attribut numero 1-10
        double progress = sortedArrays / 10;
        Platform.runLater(() -> progressBar.setProgress(progress));
    }

    private void addEntry(Sort sort) {
       table.getItems().add(sort); // on peut donner l'objet direct (sans donner chaq attr avec getter soi mm) , compilateur comprendra dir quel getters appele
    }
    void setChart(int size, long nbOperations){
        int nbSort =  chart.getData().size();
        var data = (XYChart.Series) chart.getData().get(nbSort-1);
        data.getData().add(new XYChart.Data(size,nbOperations) );
    }
    @Override
    public void update(Object... args) {
        if(args.length != 1 ){
            // throw error
        }
        Sort sort =  (Sort) args[0];
        Platform.runLater( () -> {
            addEntry(sort);
            //meth statique de classe platforme qui dit laisse ce code etre executer PAR le thread JavaFx (le seul capable de modifier la vue FX/graphique)
            // addEntry appelle add dans est une Observable list de tableView et à chaq add() cet appel est fait  Platform.runLater() est fait
            setChart(sort.getSize(), sort.getOperations());

            setProgressBar();
            setActiveThreads();
        } );
    }



}
