// Classe Graph
import java.util.*;

public class Graph {
    private final int vertices;
    private final List<List<Edge>> adjList;
    private final List<City> cities;

    public static class Edge {
        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjList = new ArrayList<>(vertices);
        this.cities = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public void addEdge(int src, int dest, int weight) {
        adjList.get(src).add(new Edge(dest, weight));
        adjList.get(dest).add(new Edge(src, weight)); // Grafo não direcionado
    }

    public List<City> getCities() {
        return cities;
    }

    public List<List<Edge>> getAdjList() {
        return adjList;
    }

    public int getVertices() {
        return vertices;
    }
}