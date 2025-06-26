import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class MapaBrasil extends JPanel {
    private Image mapa;
    private Map<String, Point> coordenadasCapitais;
    private List<String> rotaCalculada;
    private double distanciaTotal;
    private String origem;
    private String destino;

    private final int larguraOriginalMapaRef = 2000;
    private final int alturaOriginalMapaRef = 2000;

    public MapaBrasil() {
        // Caminho relativo (garanta que a pasta 'imagens' esteja no mesmo nível do .class/.java)
        String caminhoImagem = "imagens/mapa-brasil.jpg";
        ImageIcon icon = new ImageIcon(caminhoImagem);

        // Verificação do carregamento da imagem
        if (icon.getIconWidth() <= 0 || icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.err.println("❌ Erro ao carregar a imagem: " + caminhoImagem);
        } else {
            System.out.println("✅ Imagem carregada com sucesso: " + caminhoImagem);
        }

        mapa = icon.getImage();

        // Define o tamanho preferido do painel baseado na imagem (com fallback)
        int largura = (mapa != null && mapa.getWidth(this) > 0) ? mapa.getWidth(this) : 2000;
        int altura = (mapa != null && mapa.getHeight(this) > 0) ? mapa.getHeight(this) : 2000;
        setPreferredSize(new Dimension(largura, altura));

        inicializarCoordenadas();

        rotaCalculada = null;
        distanciaTotal = 0;
        origem = "";
        destino = "";
    }

    private void inicializarCoordenadas() {
        coordenadasCapitais = new HashMap<>();
        // (seu código de coordenadas permanece igual)
        coordenadasCapitais.put("Rio Branco", new Point(241, 795));
        coordenadasCapitais.put("Porto Velho", new Point(565, 807));
        coordenadasCapitais.put("Manaus", new Point(493, 541));
        coordenadasCapitais.put("Boa Vista", new Point(653, 239));
        coordenadasCapitais.put("Macapá", new Point(1129, 263));
        coordenadasCapitais.put("Belém", new Point(1067, 597));
        coordenadasCapitais.put("São Luís", new Point(1457, 585));
        coordenadasCapitais.put("Teresina", new Point(1561, 727));
        coordenadasCapitais.put("Fortaleza", new Point(1737, 603));
        coordenadasCapitais.put("Natal", new Point(1847, 600));
        coordenadasCapitais.put("João Pessoa", new Point(1877, 688));
        coordenadasCapitais.put("Recife", new Point(1867, 754));
        coordenadasCapitais.put("Maceió", new Point(1867, 804));
        coordenadasCapitais.put("Aracaju", new Point(1827, 846));
        coordenadasCapitais.put("Salvador", new Point(1593, 963));
        coordenadasCapitais.put("Belo Horizonte", new Point(1471, 1221));
        coordenadasCapitais.put("Vitória", new Point(1649, 1299));
        coordenadasCapitais.put("Rio de Janeiro", new Point(1543, 1427));
        coordenadasCapitais.put("São Paulo", new Point(1267, 1361));
        coordenadasCapitais.put("Curitiba", new Point(1117, 1559));
        coordenadasCapitais.put("Florianópolis", new Point(1143, 1645));
        coordenadasCapitais.put("Porto Alegre", new Point(1045, 1737));
        coordenadasCapitais.put("Campo Grande", new Point(971, 1271));
        coordenadasCapitais.put("Cuiabá", new Point(933, 985));
        coordenadasCapitais.put("Goiânia", new Point(1211, 1063));
        coordenadasCapitais.put("Palmas", new Point(1291, 843));
        coordenadasCapitais.put("Brasília", new Point(1313, 1084));
    }

    public void setRota(List<String> rota, double distanciaTotal, String origem, String destino) {
        this.rotaCalculada = rota;
        this.distanciaTotal = distanciaTotal;
        this.origem = origem;
        this.destino = destino;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (mapa != null) {
            int painelLargura = getWidth();
            int painelAltura = getHeight();

            int imagemCarregadaLargura = mapa.getWidth(this);
            int imagemCarregadaAltura = mapa.getHeight(this);

            if (imagemCarregadaLargura == -1 || imagemCarregadaAltura == -1) {
                imagemCarregadaLargura = 640;
                imagemCarregadaAltura = 504;
            }

            double escalaXMapa = (double) painelLargura / imagemCarregadaLargura;
            double escalaYMapa = (double) painelAltura / imagemCarregadaAltura;
            double escalaMapa = Math.min(escalaXMapa, escalaYMapa);

            int larguraDesenhoMapa = (int) (imagemCarregadaLargura * escalaMapa);
            int alturaDesenhoMapa = (int) (imagemCarregadaAltura * escalaMapa);

            int xOffsetMapa = (painelLargura - larguraDesenhoMapa) / 2;
            int yOffsetMapa = (painelAltura - alturaDesenhoMapa) / 2;

            g.drawImage(mapa, xOffsetMapa, yOffsetMapa, larguraDesenhoMapa, alturaDesenhoMapa, this);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Escala de pontos
            double escalaPontosX = (double) larguraDesenhoMapa / larguraOriginalMapaRef;
            double escalaPontosY = (double) alturaDesenhoMapa / alturaOriginalMapaRef;

            // Pontos das cidades
            for (Map.Entry<String, Point> entry : coordenadasCapitais.entrySet()) {
                Point p = entry.getValue();
                int x = (int) (p.x * escalaPontosX) + xOffsetMapa;
                int y = (int) (p.y * escalaPontosY) + yOffsetMapa;

                if (entry.getKey().equals(origem)) {
                    g2d.setColor(Color.GREEN.darker());
                } else if (entry.getKey().equals(destino)) {
                    g2d.setColor(Color.MAGENTA.darker());
                } else if (rotaCalculada != null && rotaCalculada.contains(entry.getKey())) {
                    g2d.setColor(Color.CYAN.darker());
                } else {
                    g2d.setColor(Color.BLUE);
                }

                g2d.fillOval(x - 5, y - 5, 10, 10);
                g2d.setColor(Color.BLACK);
                g2d.drawString(entry.getKey(), x + 7, y);
            }

            // Desenha rota
            if (rotaCalculada != null && rotaCalculada.size() > 1) {
                g2d.setColor(Color.BLUE);
                g2d.setStroke(new BasicStroke(3));
                for (int i = 0; i < rotaCalculada.size() - 1; i++) {
                    Point p1 = coordenadasCapitais.get(rotaCalculada.get(i));
                    Point p2 = coordenadasCapitais.get(rotaCalculada.get(i + 1));
                    if (p1 != null && p2 != null) {
                        int x1 = (int) (p1.x * escalaPontosX) + xOffsetMapa;
                        int y1 = (int) (p1.y * escalaPontosY) + yOffsetMapa;
                        int x2 = (int) (p2.x * escalaPontosX) + xOffsetMapa;
                        int y2 = (int) (p2.y * escalaPontosY) + yOffsetMapa;
                        g2d.drawLine(x1, y1, x2, y2);
                    }
                }
            }

            // Informações da rota
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            String texto = String.format("Origem: %s    Destino: %s    Distância: %.2f km", origem, destino, distanciaTotal);
            g2d.drawString(texto, 10, 20);
        }
    }
}
