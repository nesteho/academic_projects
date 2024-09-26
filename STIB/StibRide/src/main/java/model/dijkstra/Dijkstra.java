package model.dijkstra;
import java.util.*;

public class Dijkstra {


    //depuis la source : calcule tous plus court chemin vers toutes les autres stations
    // -> stock dans shortestPath

    //0 le plus proche : src v
    // dist 0
    //pr chq adjacent : chg distance
    //retiens plus court

    //A B C car les distance de A vers adjacent B (en passant par B) est > distance A-C

    // aps calculateShortestPathFromSource  -> chq noeud : shortestPath =contient plus court chemin dp src jusque noeud en question!! : s

    public static Graph calculateShortestPathFromSource(Node source, Graph graph) {
        resetDistances(graph);
        resetShortestPaths(graph);
        // modifie indirectement le graph en modifiant chacun des noeud
        source.setDistance(0);
        Set<Node> settledNodes = new HashSet<>(); //bleu
        Set<Node> unsettledNodes = new HashSet<>(); // ps encore visité
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) { // tant que pas visité/settled tous les noeuds!!
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode); // n'a pas rajouté d'adjacent pour la source : false
            for (Map.Entry< Node, Integer> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }
    private static void resetDistances(Graph graph){
        graph.resetDistances();
    }
    private static void resetShortestPaths(Graph graph){
        graph.resetShortestPaths();
    }

    private static Node getLowestDistanceNode(Set < Node > unsettledNodes) {
        Node lowestDistanceNode = null;    //trouver le prochain curNode
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
    private static void calculateMinimumDistance(Node evaluationNode,
                                                 Integer edgeWeigh, Node sourceNode) { //sourceNode : curNode-> voisin plus proche
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) { // A=0 B=+INF : 0 + 15 < +INF
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}

