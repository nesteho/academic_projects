package model.dijkstra;

import javafx.concurrent.Task;

import java.util.List;

public class FindShortestPath extends Task<List<Node>> {
    private Graph graph;
    private Node origin;
    private Node destination;

    public FindShortestPath(Graph graph, Node origin, Node destination) {
        this.graph = graph;
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    protected List<Node> call(){
        Dijkstra.calculateShortestPathFromSource(origin,graph);
        List<Node> shortestPath = destination.getShortestPath();
        shortestPath.add(destination); //pour voir destination dans tableau
        return shortestPath;
    }
}
