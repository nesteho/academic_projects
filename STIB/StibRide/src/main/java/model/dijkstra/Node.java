package model.dijkstra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {
    private Integer id;
    private String name;
    private List<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }
    public void resetShortestPath() {
        this.shortestPath = new LinkedList<>();;
    }
    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
        shortestPath = null;
        id = null;
    }

    public Integer getId() {
        return id;
    }

    public Node(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }
    public void addAdjacent(Node adjacentNode, Integer distance) {
//        System.out.println(adjacentNode.getName() + " adj of : this "+name);
        adjacentNodes.put(adjacentNode, distance);
    }

    public void setName(String name) {
        this.name = name;
    }
}
