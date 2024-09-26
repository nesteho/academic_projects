package presenter;

import javafx.application.Platform;
import javafx.scene.control.TextInputDialog;
import model.Model;
import model.dijkstra.FindShortestPath;
import model.dijkstra.Node;
import model.dto.FavoriteDto;
import model.dto.StationDto;
import model.exception.RepositoryException;

import model.observer.Observable;
import model.observer.Observer;
import vue.Vue;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Presenter implements Observer {
    private Model model;
    private Vue vue;
    public Presenter(Vue vue, Model model) {
        this.model = model;
        this.vue = vue;
        this.vue.initializeStationDropDowns(model.getStations());
        this.vue.initializeFavoriteDropDowns(model.getFavorites());
        this.vue.addSearchButtonHandler(this);
        this.vue.addFavoriteButtonHandler(this);
        this.vue.addDeleteFavoriteButtonHandler(this);
        this.vue.addShowPathButtonHandler(this);
        this.vue.addExitHandler(this);
        this.vue.addDutchStationHandler(this);
        model.addObserver(this);
    }
    void checkSourceDestNull(StationDto source,StationDto destination){
        if (source == null) throw new RepositoryException("No selected Origin");
        if (destination == null) throw new RepositoryException("No selected Destination");
    }
    void checkFavoriteNull(FavoriteDto favorite){
        if (favorite == null) throw new RepositoryException("No selected Favorite");
    }
    public void handleSearchButtonAction(){
        try {
            var source = vue.getFromSearchBoxValue();
            var destination = vue.getToSearchBoxValue();
            checkSourceDestNull(source,destination);
            findShortestPath(source.getKey(), destination.getKey());
            vue.hideErrorMessage();
        }catch (RepositoryException | NullPointerException e){
            vue.displayErrorMessage(e.getMessage());
        }
    }
    public void handleFavoriteButtonAction() {
        try {
            var source = vue.getFromSearchBoxValue();
            var destination = vue.getToSearchBoxValue();
            checkSourceDestNull(source,destination);
            var name = askFavoriteName();
            if(name == null) return;
            model.insertFavorite(new FavoriteDto(name,source.getKey(),destination.getKey()));
            vue.setFavoriteBoxItems(model.getFavorites());
            vue.hideErrorMessage();
        }catch (RepositoryException e){
            vue.displayErrorMessage(e.getMessage());
        }
    }
    public void handleDeleteFavoriteButtonAction() {
        try {
            var favorite = vue.getFavoriteBoxValue();
            checkFavoriteNull(favorite);
            model.deleteFavorite(favorite);
            vue.setFavoriteBoxItems(model.getFavorites());
            vue.hideErrorMessage();
        }catch (RepositoryException e){
            vue.displayErrorMessage(e.getMessage());
        }
    }
    public void handleShowPathButtonAction() {
        try{
            var favorite = vue.getFavoriteBoxValue();
            checkFavoriteNull(favorite);
            var source = model.getStation(favorite.getId_origin());
            var dest =  model.getStation(favorite.getId_destination());
            vue.setFromSearchBoxValue(source);
            vue.setToSearchBoxValue(dest);
            handleSearchButtonAction();
            vue.hideErrorMessage();
        }catch (RepositoryException e){
            vue.displayErrorMessage(e.getMessage());
        }
    }
    public void handleExitMenuItemAction(){
        Platform.exit();
    }
    private String askFavoriteName() {
        String name;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Favorite");
        dialog.setHeaderText(null);

        while (true) {
            dialog.setContentText("No Empty name ... Please enter a valid name :");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                name = result.get().trim();
                if (!name.isEmpty()) {
                    break;
                }
            } else {
                // L'utilisateur a cliqué sur Annuler ou fermé la boîte de dialogue
                name = null;
                break;
            }
        }
        return name;
    }

    private StationDto nodeToStation(Node node) {
        int id = node.getId();
        String name = node.getName();
        return new StationDto(id, name);
    }

    @Override
    public void update(Observable observable, Object arg) {
        var shortestPath = (List<Node>)arg;
        vue.displayShortestPath(makeSteps(shortestPath));
    }

    private List<Step> makeSteps(List<Node> path) {
        List<Step> steps = new ArrayList<>();
        for (Node node : path) {
            StationDto station = nodeToStation(node);
            List<Integer> lines = model.selectLinesOfStop(node.getId());
            steps.add(new Step(station, lines)); //p.e line={2,6} pour Madou
        }
        return steps;
    }
    private void setComboBoxesToDutch() {
        var savedOrigin = vue.getFromSearchBoxValue();
        var savedDestination = vue.getToSearchBoxValue();

        var dutchStations = model.getDutchStations();
        StationDto dutchOrigin = null;
        if (savedOrigin != null) {
            vue.setFromSearchBoxValue(null);
            dutchOrigin = model.getDutchStation(savedOrigin.getKey());
        }
        StationDto dutchDestination = null;
        if (savedDestination != null) {
            vue.setToSearchBoxValue(null);
            dutchDestination = model.getDutchStation(savedDestination.getKey());
        }
        vue.setFromSearchBoxItems(dutchStations);
        vue.setToSearchBoxItems(dutchStations);
        vue.setFromSearchBoxValue(dutchOrigin);
        vue.setToSearchBoxValue(dutchDestination);
    }
    private void setTableToDutch() {
        var steps = vue.getSteps();
        List<Step> list = new ArrayList<>();
        for (Step s : steps) {
            var station = model.getDutchStation(s.getStation().getKey());
            list.add(new Step(station, s.getLines()));
        }
        vue.displayShortestPath(list);
    }
    public void handleDutchStationAction() {
        model.setDutchGraph();
        setComboBoxesToDutch();
        if (!vue.isTableEmpty()) {
            setTableToDutch();
        }
        vue.hideErrorMessage();
    }

    private void findShortestPath(Integer source,Integer dest) throws RepositoryException { // appeler par controller

            model.checkSearchParameters(source,dest);
            var graph = model.getGraph();
            var findTask = new FindShortestPath(graph,graph.getNode(source),graph.getNode(dest));

            // Ajouter un gestionnaire de réussite pour mettre à jour la vue avec le résultat
            findTask.setOnSucceeded(event -> {
                List<Node> shortestPath = findTask.getValue();
                vue.displayShortestPath(makeSteps(shortestPath));
                Thread.currentThread().interrupt();

            });
            // Démarrer la tâche dans un nouveau thread
            Thread thread = new Thread(findTask);
            thread.setDaemon(true);
            thread.start();
        }
    }
