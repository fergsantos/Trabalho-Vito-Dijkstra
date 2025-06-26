// Classe MainGUI
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;

public class MainGUI {
    public static void main(String[] args) {
        // Cria o grafo e adiciona as cidades e conexões
        Graph graph = GraphFactory.createGraph(); // Usamos uma fábrica para separar a criação

        // Cria a janela
        JFrame frame = new JFrame("Rota entre Capitais - Mapa do Brasil");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Painel com o mapa
        MapaBrasil mapa = new MapaBrasil();

        // ComboBox de cidades
        JComboBox<City> origemBox = new JComboBox<>(graph.getCities().toArray(new City[0]));
        JComboBox<City> destinoBox = new JComboBox<>(graph.getCities().toArray(new City[0]));

        JButton calcular = new JButton("Calcular Rota");

        // Painel de controle
        JPanel controle = new JPanel();
        controle.add(new JLabel("Origem:"));
        controle.add(origemBox);
        controle.add(new JLabel("Destino:"));
        controle.add(destinoBox);
        controle.add(calcular);

        frame.add(controle, BorderLayout.NORTH);
        frame.add(mapa, BorderLayout.CENTER);

        // Ação do botão
        calcular.addActionListener(e -> {
            City origem = (City) origemBox.getSelectedItem();
            City destino = (City) destinoBox.getSelectedItem();

            if (origem == null || destino == null) return;

            Dijkstra.Result resultado = Dijkstra.dijkstra(graph, origem.getId());

            if (resultado.distances[destino.getId()] == Integer.MAX_VALUE) {
                JOptionPane.showMessageDialog(frame, "Não há caminho entre as cidades selecionadas.");
                return;
            }

            // Reconstrói o caminho
            List<City> path = GraphUtils.reconstruirCaminho(resultado.predecessors, destino.getId(), graph);
            List<String> nomes = path.stream().map(City::getName).collect(Collectors.toList());

            mapa.setRota(nomes, resultado.distances[destino.getId()], origem.getName(), destino.getName());
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}