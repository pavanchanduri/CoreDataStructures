package datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph {

    private HashMap<String, ArrayList<String>> adjList = new HashMap<>();

    public boolean addVertex(String vertex) {
        if(adjList.get(vertex)==null) {
            adjList.put(vertex, new ArrayList<>()); //This adds something like {"A", []}
            return true;
        }
        return false;
    }

    public boolean addEdge(String vertex1, String vertex2) {
        if(vertex1.equals(vertex2)) return false;
        if(adjList.get(vertex1)!=null && adjList.get(vertex2)!=null) {
            if(!adjList.get(vertex1).contains(vertex2)) {
                adjList.get(vertex1).add(vertex2);
            }
            if(!adjList.get(vertex2).contains(vertex1)) {
                adjList.get(vertex2).add(vertex1);
            }
            return true;
        }
        return false;
    }

    public boolean removeEdge(String vertex1, String vertex2) {
        if(adjList.get(vertex1)!=null && adjList.get(vertex2)!=null) {
            adjList.get(vertex1).remove(vertex2);
            adjList.get(vertex2).remove(vertex1);
            return true;
        }
        return false;
    }

    /**
     * 1. Idea here is to get the list of edges
     * 2. Go to each edge and remove the vertex which removes the edge
     * 3. This way we don't need to visit each and every vertex
     * 4. In the last, remove the vertex itself
     * @param vertex
     */
    public void removeVertex(String vertex) {
        for(String otherVertex: adjList.get(vertex)) {
            adjList.get(otherVertex).remove(vertex);
        }
        adjList.remove(vertex);
    }

    public void printGraph() {
        System.out.println(adjList);
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A","B");
        graph.addEdge("A","C");
        graph.addEdge("C","D");
        graph.addEdge("A","D");
        graph.addEdge("B","D");
        graph.printGraph();
        graph.removeVertex("D");
        graph.printGraph();
    }
}
