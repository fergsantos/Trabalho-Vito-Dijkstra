// Classe GraphUtils
import java.util.*;

public class GraphUtils {
    public static List<City> reconstruirCaminho(int[] predecessores, int destino, Graph graph) {
        List<City> caminho = new ArrayList<>();
        for (int at = destino; at != -1; at = predecessores[at]) {
            caminho.add(graph.getCities().get(at));
        }
        Collections.reverse(caminho);
        return caminho;
    }
}