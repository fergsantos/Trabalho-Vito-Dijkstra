// Classe Dijkstra
import java.util.*;

public class Dijkstra {
public static class Result {
        int[] distances;
        int[] predecessors;

        Result(int[] distances, int[] predecessors) {
            this.distances = distances;
            this.predecessors = predecessors;
        }
    }

    public static Result dijkstra(Graph graph, int source) {
        int vertices = graph.getVertices();
        int[] distances = new int[vertices];
        int[] predecessors = new int[vertices];
        boolean[] visited = new boolean[vertices];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);
        distances[source] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(v -> distances[v]));
        pq.offer(source);

        while (!pq.isEmpty()) {
            int u = pq.poll();
            if (visited[u]) continue;
            visited[u] = true;

            for (Graph.Edge edge : graph.getAdjList().get(u)) {
                int v = edge.destination;
                int weight = edge.weight;

                if (!visited[v] && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    predecessors[v] = u;
                    pq.offer(v);
                }
            }
        }

        return new Result(distances, predecessors);
    }

    public static void printPath(Graph graph, Dijkstra.Result result, int source, int destination) {
        List<City> cities = graph.getCities();
        int[] predecessors = result.predecessors;
        int[] distances = result.distances;

        if (distances[destination] == Integer.MAX_VALUE) {
            System.out.println("Não há caminho entre " + cities.get(source).getName() + " e " + cities.get(destination).getName());
            return;
        }

        List<City> path = new ArrayList<>();
        for (int at = destination; at != -1; at = predecessors[at]) {
            path.add(cities.get(at));
        }
        Collections.reverse(path);

        System.out.print("Menor caminho: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).getName());
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println("\nDistância total: " + distances[destination] + " km");
    }
}                        
