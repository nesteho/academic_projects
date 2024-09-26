package vue;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.FavoriteDto;
import model.dto.StationDto;
import org.controlsfx.control.SearchableComboBox;
import presenter.Presenter;
import presenter.Step;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Vue implements Initializable { // controller FXML revoir 4.3 scenebuilder

    @FXML
    private SearchableComboBox<StationDto> fromSearchBox;
    @FXML
    private SearchableComboBox<StationDto> toSearchBox;
    @FXML
    private TableView<Step> table;
    @FXML
    private TableColumn<Step, StationDto> stationCol;
    @FXML
    private TableColumn<Step, List<Integer>> linesCol;
    @FXML
    private Button searchButton;
    @FXML
    private Button addFavoriteButton;
    @FXML
    private SearchableComboBox<FavoriteDto> favoriteBox;
    @FXML
    private Button showPathButton;
    @FXML
    private Button deleteFavoriteButton;
    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private Label errorLabel;
    @FXML
    private MenuItem dutchStationMenuItem;
    private void initialize() {
        stationCol.setCellValueFactory(new PropertyValueFactory<>("station"));
        linesCol.setCellValueFactory(new PropertyValueFactory<>("lines"));
        ObservableList<Step> observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
        ObservableList<FavoriteDto> favoritesList = FXCollections.observableArrayList();
        favoriteBox.setItems(favoritesList);
    }
    public void displayShortestPath(List<Step> steps) {
        table.getItems().clear();
        table.getItems().addAll(steps);
    }
    public void initializeStationDropDowns(List<StationDto> stations)  {
        fromSearchBox.getItems().addAll(stations);  //avec redef toString : affiche name
        toSearchBox.getItems().addAll(stations);
    }
    public void initializeFavoriteDropDowns(List<FavoriteDto> favorites)  {
        favoriteBox.getItems().addAll(favorites);
    }
    public void addSearchButtonHandler(Presenter presenter) {
        SearchButtonHandler handler = new SearchButtonHandler(presenter);
        searchButton.setOnAction(handler);
    }
    public void addExitHandler(Presenter presenter) {
        ExitMenuItemHandler handler = new ExitMenuItemHandler(presenter);
        exitMenuItem.setOnAction(handler);
    }
    public void addFavoriteButtonHandler(Presenter presenter) {
        FavoriteButtonHandler handler = new FavoriteButtonHandler(presenter);
        addFavoriteButton.setOnAction(handler);
    }
    public void addShowPathButtonHandler(Presenter presenter) {
        ShowPathButtonHandler handler = new ShowPathButtonHandler(presenter);
        showPathButton.setOnAction(handler);
    }
    public void addDeleteFavoriteButtonHandler(Presenter presenter) {
        DeleteFavoriteButtonHandler handler = new DeleteFavoriteButtonHandler(presenter);
        deleteFavoriteButton.setOnAction(handler);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize();
    }
    public StationDto getFromSearchBoxValue() {
        return fromSearchBox.getValue();
    }

    public StationDto getToSearchBoxValue() {
        return toSearchBox.getValue();
    }
    public FavoriteDto getFavoriteBoxValue() {
        return favoriteBox.getValue();
    }
    public void setFromSearchBoxValue(StationDto stationDto) {
        fromSearchBox.setValue(stationDto);
    }
    public void setToSearchBoxValue(StationDto stationDto) {
        toSearchBox.setValue(stationDto);
    }
    public void setFavoriteBoxItems(List<FavoriteDto> favorites) {
        favoriteBox.setValue(null);
        ObservableList<FavoriteDto> observableStations = FXCollections.observableArrayList(favorites);
        favoriteBox.setItems(observableStations);
    }

    public void displayErrorMessage(String message){
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
    public void hideErrorMessage(){
        errorLabel.setVisible(false);
    }
    public void addDutchStationHandler(Presenter presenter){
        DutchStationHandler handler = new DutchStationHandler(presenter);
        dutchStationMenuItem.setOnAction(handler);
    }
    public void setFromSearchBoxItems(List<StationDto> stations) {
            ObservableList<StationDto> observableStations = FXCollections.observableArrayList(stations);
            fromSearchBox.getItems().setAll(observableStations);
    }
    public void setToSearchBoxItems(List<StationDto> stations) {
        ObservableList<StationDto> observableStations = FXCollections.observableArrayList(stations);
        toSearchBox.getItems().setAll(observableStations);
    }

    public boolean isTableEmpty() {
        return table.getItems().isEmpty();
    }
    public List<Step> getSteps() {
        return table.getItems();
    }
}
