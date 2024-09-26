package model.dijkstra;

import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Map<Integer,Node> nodes = new HashMap<>(); //MAP > Set : get et contains =O(1) grace à key
    public Map<Integer, Node> getNodes() {
        return nodes;
    }

    public void addNode(Node nodeA) {
        nodes.put(nodeA.getId(),nodeA);
    }

    public Node getNode(Integer id) {
        return nodes.get(id);  // va comparer les valeur : pas les ref des integer
    }
    public boolean contains(Integer id) {
      return nodes.containsKey(id);
    }
    public void resetDistances(){
        for ( Map.Entry<Integer,Node> station: nodes.entrySet()) {
            station.getValue().setDistance(Integer.MAX_VALUE);
        }
    }
    public void resetShortestPaths(){
        for ( Map.Entry<Integer,Node> station: nodes.entrySet()) {
            station.getValue().resetShortestPath();
        }
    }

}