package model;


import model.dijkstra.Graph;
import model.dijkstra.Node;
import model.dto.FavoriteDto;
import model.dto.StationDto;
import model.exception.RepositoryException;
import model.observer.Observable;
import model.repository.FavoriteRepository;
import model.repository.StationRepository;
import model.repository.StopRepository;
import java.util.List;

public class Model extends Observable {
    Graph graph;
    boolean dutchGraph;

    public Model() throws RepositoryException {
        graph = new Graph();
        makeGraph();
        addAdjacents();
    }
    public void setDutchGraph() {
        if(!dutchGraph){
            translateStations();
            this.dutchGraph = true;
        }
    }
    private void translateStations() {
        var dutchStations = getDutchStations();
        for (StationDto station: dutchStations) {
            graph.getNode(station.getKey()).setName(station.getName());
        }
    }

    public List<model.dto.StationDto> getStations() throws RepositoryException {
        try {
            var rep = new StationRepository(StationRepository.Language.FRENCH);
            return rep.selectAll();
        }catch (RepositoryException e){
            throw new RepositoryException("Cannot get the stations : "+e.getMessage());
        }
    }
    public model.dto.StationDto getStation(Integer stationId){
        var rep = new StationRepository(StationRepository.Language.FRENCH);
        return rep.select(stationId);
    }

    private void makeGraph() throws RepositoryException {
        try {
            var rep = new StationRepository(StationRepository.Language.FRENCH);
            var stations = rep.selectAll();
            for (model.dto.StationDto station: stations) {
                graph.addNode(new Node(station.getKey(),station.getName() ));
            }
        }catch (RepositoryException e){
            throw new RepositoryException("Cannot make the graphp: "+e.getMessage());
        }
    }
    private void addAdjacents() throws RepositoryException {
        try {
            var stopsRep = new StopRepository();
            List<Integer> lines = stopsRep.selectLines();

            for ( Integer l :lines ) {   // eviter for each line et dedans for each node : sol selectCompleteLign puis1 parcours!

                var lign = stopsRep.selectCompleteLign(l);
                for (int i = 0; i < lign.size() ; i++) {
                    Node cur = graph.getNode(lign.get(i).getKey().getValue());   //recup id_station
                    if(i > 0) {
                        cur.addAdjacent(graph.getNode(lign.get(i-1).getKey().getValue()),1);
                    }
                    if(i + 1< lign.size()){
                        cur.addAdjacent(graph.getNode(lign.get(i+1).getKey().getValue()),1);
                    }
                }
            }
        }catch (RepositoryException e){
            throw new RepositoryException("Cannot add stations'adjacents : "+e.getMessage());
        }
    }

    public List<Integer> selectLinesOfStop(Integer key) throws RepositoryException {
      try {
          var stopRep = new StopRepository();
          return stopRep.selectLineOfStop(key);
      }catch (RepositoryException e){
          throw new RepositoryException("Cannot select the lines that contain stop: "
                  +graph.getNode(key).getName() + " : "+e.getMessage());
      }
    }

    public void checkSearchParameters(Integer source,Integer dest) throws RepositoryException { // appeler par controller
        try {
            if(!graph.contains(source)) throw new RepositoryException("station "+source
                    +" does not exist in the STIB network");
            if(!graph.contains(dest)) throw new RepositoryException("station "+dest
                    +" does not exist in the STIB network");
            if(source.intValue() == dest.intValue()) throw new RepositoryException("origin " +
                    "and destination are the same!");
        }catch (RepositoryException e){
            throw new RepositoryException("No shortest path from "
                    + graph.getNode(source).getName() + " to "+ graph.getNode(dest).getName() + " : " + e.getMessage());
        }
    }
    public List<FavoriteDto> getFavorites() throws RepositoryException {
        try {
            var rep = new FavoriteRepository();
            return rep.selectAll();
        }catch (RepositoryException e){
            throw  new RepositoryException("Cannot select favorites "+e.getMessage());
        }
    }
    public Integer insertFavorite(FavoriteDto dto) throws RepositoryException {
        try {
            var rep = new FavoriteRepository();
            return rep.insert(dto);
        }catch (RepositoryException e){
            throw  new RepositoryException("Cannot insert favorite "+e.getMessage());
        }
    }
    public void deleteFavorite(FavoriteDto dto) throws RepositoryException {
        try {
            var rep = new FavoriteRepository();
            rep.delete(dto.getKey());
        }catch (RepositoryException e){
            throw  new RepositoryException("Cannot delete favorite "+e.getMessage());
        }
    }

    public List<StationDto> getDutchStations() {
        try {
            var rep = new StationRepository(StationRepository.Language.DUTCH);
            return rep.selectAll();
        }catch (RepositoryException e){
            throw  new RepositoryException("Cannot get Dutch named Stations "+e.getMessage());
        }
    }
    public StationDto getDutchStation(Integer key) {
        try {
            var rep = new StationRepository(StationRepository.Language.DUTCH);
            return rep.select(key);
        }catch (RepositoryException e){
            throw  new RepositoryException("Cannot get Dutch named Stations "+e.getMessage());
        }
    }

    public Graph getGraph() {
        return graph;
    }
}
