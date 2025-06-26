public class GraphFactory {
    public static Graph createGraph() {
        Graph graph = new Graph(27);

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

        // Sudeste, Sul e Nordeste (sem mudanças)
        graph.addEdge(2, 21, 440);
        graph.addEdge(2, 24, 580);
        graph.addEdge(2, 26, 520);
        graph.addEdge(21, 24, 430);
        graph.addEdge(21, 26, 360);
        graph.addEdge(24, 26, 530);
        graph.addEdge(7, 8, 300);
        graph.addEdge(7, 17, 990);
        graph.addEdge(7, 24, 400);
        graph.addEdge(8, 17, 700);
        graph.addEdge(0, 13, 280);
        graph.addEdge(0, 22, 290);
        graph.addEdge(9, 15, 420);
        graph.addEdge(9, 25, 540);
        graph.addEdge(11, 15, 180);
        graph.addEdge(11, 19, 120);
        graph.addEdge(13, 19, 200);
        graph.addEdge(15, 19, 200);
        graph.addEdge(19, 22, 800);
        graph.addEdge(23, 25, 330);
        graph.addEdge(22, 2, 1020);
        graph.addEdge(4, 5, 940);
        graph.addEdge(4, 6, 1130);
        graph.addEdge(4, 10, 210);
        graph.addEdge(5, 6, 700);
        graph.addEdge(6, 10, 930);
        graph.addEdge(10, 2, 740);
        graph.addEdge(1, 12, 580);
        graph.addEdge(1, 23, 330);
        graph.addEdge(4, 22, 1440);
        graph.addEdge(4, 9, 1680);
        graph.addEdge(2, 4, 870);
        graph.addEdge(24, 7, 400);

        // Região Norte reforçada:
        graph.addEdge(3, 14, 785);    // Boa Vista - Manaus
        graph.addEdge(3, 1, 1800);    // Boa Vista - Belém (corrigido para distância realista)
        graph.addEdge(14, 1, 1600);   // Manaus - Belém (corrigido)
        graph.addEdge(14, 18, 900);   // Manaus - Porto Velho
        graph.addEdge(18, 20, 510);   // Porto Velho - Rio Branco
        graph.addEdge(18, 6, 1100);   // Porto Velho - Cuiabá (menor distância)
        graph.addEdge(20, 6, 1300);   // Rio Branco - Cuiabá
        graph.addEdge(6, 16, 800);    // Cuiabá - Palmas
        graph.addEdge(16, 1, 970);    // Palmas - Belém

        return graph;
    }
}
