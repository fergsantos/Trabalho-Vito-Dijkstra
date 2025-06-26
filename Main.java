// Classe Main
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicializa o grafo com 27 capitais
        Graph graph = new Graph(27);

        // Adiciona todas as capitais
        graph.addCity(new City("Aracaju", 0));
        graph.addCity(new City("Belém", 1));
        graph.addCity(new City("Belo Horizonte", 2));
        graph.addCity(new City("Boa Vista", 3));
        graph.addCity(new City("Brasília", 4));
        graph.addCity(new City("Campo Grande", 5));
        graph.addCity(new City("Cuiabá", 6));
        graph.addCity(new City("Curitiba", 7));
        graph.addCity(new City("Florianópolis", 8));
        graph.addCity(new City("Fortaleza", 9));
        graph.addCity(new City("Goiânia", 10));
        graph.addCity(new City("João Pessoa", 11));
        graph.addCity(new City("Macapá", 12));
        graph.addCity(new City("Maceió", 13));
        graph.addCity(new City("Manaus", 14));
        graph.addCity(new City("Natal", 15));
        graph.addCity(new City("Palmas", 16));
        graph.addCity(new City("Porto Alegre", 17));
        graph.addCity(new City("Porto Velho", 18));
        graph.addCity(new City("Recife", 19));
        graph.addCity(new City("Rio Branco", 20));
        graph.addCity(new City("Rio de Janeiro", 21));
        graph.addCity(new City("Salvador", 22));
        graph.addCity(new City("São Luís", 23));
        graph.addCity(new City("São Paulo", 24));
        graph.addCity(new City("Teresina", 25));
        graph.addCity(new City("Vitória", 26));

        // Adiciona arestas com distâncias reais aproximadas (em km)
        // Sudeste
        graph.addEdge(2, 21, 440);   // Belo Horizonte -> Rio de Janeiro
        graph.addEdge(2, 24, 580);   // Belo Horizonte -> São Paulo
        graph.addEdge(2, 26, 520);   // Belo Horizonte -> Vitória
        graph.addEdge(21, 24, 430);  // Rio de Janeiro -> São Paulo
        graph.addEdge(21, 26, 360);  // Rio de Janeiro -> Vitória
        graph.addEdge(24, 26, 530);  // São Paulo -> Vitória

        // Sul
        graph.addEdge(7, 8, 300);    // Curitiba -> Florianópolis
        graph.addEdge(7, 17, 990);   // Curitiba -> Porto Alegre
        graph.addEdge(7, 24, 400);   // Curitiba -> São Paulo
        graph.addEdge(8, 17, 700);   // Florianópolis -> Porto Alegre

        // Nordeste
        graph.addEdge(0, 13, 280);   // Aracaju -> Maceió
        graph.addEdge(0, 22, 290);   // Aracaju -> Salvador
        graph.addEdge(9, 15, 420);   // Fortaleza -> Natal
        graph.addEdge(9, 25, 540);   // Fortaleza -> Teresina
        graph.addEdge(11, 15, 180);  // João Pessoa -> Natal
        graph.addEdge(11, 19, 120);  // João Pessoa -> Recife
        graph.addEdge(13, 19, 200);  // Maceió -> Recife
        graph.addEdge(15, 19, 200);  // Natal -> Recife
        graph.addEdge(19, 22, 800);  // Recife -> Salvador
        graph.addEdge(23, 25, 330);  // São Luís -> Teresina
        graph.addEdge(22, 2, 1020);  // Salvador -> Belo Horizonte

        // Centro-Oeste
        graph.addEdge(4, 5, 940);    // Brasília -> Campo Grande
        graph.addEdge(4, 6, 1130);   // Brasília -> Cuiabá
        graph.addEdge(4, 10, 210);   // Brasília -> Goiânia
        graph.addEdge(5, 6, 700);    // Campo Grande -> Cuiabá
        graph.addEdge(6, 10, 930);   // Cuiabá -> Goiânia
        graph.addEdge(10, 2, 740);   // Goiânia -> Belo Horizonte

        // Norte
        graph.addEdge(1, 12, 580);   // Belém -> Macapá
        graph.addEdge(1, 23, 330);   // Belém -> São Luís
        graph.addEdge(3, 14, 1560);  // Boa Vista -> Manaus
        graph.addEdge(14, 18, 900);  // Manaus -> Porto Velho
        graph.addEdge(16, 1, 970);   // Palmas -> Belém
        graph.addEdge(16, 4, 970);   // Palmas -> Brasília
        graph.addEdge(18, 20, 510);  // Porto Velho -> Rio Branco

        // Conexões adicionais
        graph.addEdge(4, 22, 1440);  // Brasília -> Salvador
        graph.addEdge(4, 9, 1680);   // Brasília -> Fortaleza
        graph.addEdge(24, 7, 400);   // São Paulo -> Curitiba
        graph.addEdge(2, 4, 870);    // Belo Horizonte -> Brasília

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // Exibe cidades disponíveis
                System.out.println("\nCidades disponíveis:");
                for (City city : graph.getCities()) {
                    System.out.println(city.getId() + ": " + city.getName());
                }
        
                // Entrada da origem
                System.out.print("\nDigite o ID da capital de origem (ou -1 para sair): ");
                int source = scanner.nextInt();
                if (source == -1) break;
        
                // Entrada do destino
                System.out.print("Digite o ID da capital de destino: ");
                int destination = scanner.nextInt();
        
                // Validação
                if (source < 0 || source >= graph.getVertices() || destination < 0 || destination >= graph.getVertices()) {
                    System.out.println("IDs inválidos! Tente novamente.");
                    continue;
                }
        
                // Executa Dijkstra e exibe o resultado
                Dijkstra.Result result = Dijkstra.dijkstra(graph, source);
                Dijkstra.printPath(graph, result, source, destination);
            }
        
            System.out.println("Programa encerrado.");
        }
    }
}