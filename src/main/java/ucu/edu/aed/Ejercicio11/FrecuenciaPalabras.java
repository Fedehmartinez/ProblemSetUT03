package ucu.edu.aed.Ejercicio11;

import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Cuenta cuántas veces aparece cada palabra del libro y grafica las 10 más frecuentes.
 */
public class FrecuenciaPalabras {

    private static final String ARCHIVO_LIBRO = "src/main/resources/ut03/libro.txt";
    private static final String ARCHIVO_GRAFICO = "src/main/java/ucu/edu/aed/Ejercicio11/grafico-top10.png";

    public static void main(String[] args) throws IOException {

        HashMap<String, Integer> frecuencias = new HashMap<>(); // palabra -> cantidad de veces

        Scanner archivo = new Scanner(new File(ARCHIVO_LIBRO), StandardCharsets.UTF_8);

        while (archivo.hasNext()) {

            String palabra = archivo.next()
                    .toLowerCase()
                    .replaceAll("[^a-záéíóúüñ]", ""); // así "Faro," y "faro" cuentan igual

            if (!palabra.isEmpty()) {
                frecuencias.put(
                        palabra,
                        frecuencias.getOrDefault(palabra, 0) + 1 // si no estaba arranca en 0
                );
            }
        }

        archivo.close();

        List<Map.Entry<String, Integer>> palabras =
                new ArrayList<>(frecuencias.entrySet());

        // el HashMap no tiene orden, se pasa a una lista y se ordena de mayor a menor
        palabras.sort(
                Map.Entry.comparingByValue(Comparator.reverseOrder())
        );

        System.out.println("Palabras distintas: " + palabras.size());
        System.out.println("Las 10 palabras más frecuentes:");

        int cantidad = Math.min(10, palabras.size());
        List<Map.Entry<String, Integer>> top = palabras.subList(0, cantidad);

        for (int i = 0; i < cantidad; i++) {
            Map.Entry<String, Integer> entrada = top.get(i);

            System.out.println(
                    (i + 1) + ". " +
                    entrada.getKey() + " : " +
                    entrada.getValue()
            );
        }

        graficarConsola(top);
        graficarImagen(top, ARCHIVO_GRAFICO);
        System.out.println("\nGráfico guardado en " + ARCHIVO_GRAFICO);
    }

    // gráfico de barras en la consola, la barra más larga mide 50 '#'
    public static void graficarConsola(List<Map.Entry<String, Integer>> top) {
        int maximo = top.get(0).getValue();
        System.out.println("\nGráfico:");
        for (Map.Entry<String, Integer> entrada : top) {
            System.out.printf("%-8s | %s %d%n",
                    entrada.getKey(),
                    "#".repeat(entrada.getValue() * 50 / maximo),
                    entrada.getValue());
        }
    }

    // gráfico de barras en una imagen PNG
    public static void graficarImagen(List<Map.Entry<String, Integer>> top, String ruta) throws IOException {
        int ancho = 900;
        int alto = 550;
        int margenIzq = 70;
        int margenDer = 30;
        int margenArriba = 90;
        int margenAbajo = 70;

        BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = imagen.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, ancho, alto);

        // título
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString("Las 10 palabras más frecuentes de libro.txt", margenIzq, 35);

        int maximo = top.get(0).getValue();
        int altoGrafico = alto - margenArriba - margenAbajo;
        int anchoGrafico = ancho - margenIzq - margenDer;
        int anchoColumna = anchoGrafico / top.size();
        int anchoBarra = (int) (anchoColumna * 0.7);

        // líneas de referencia del eje y
        g.setFont(new Font("SansSerif", Font.PLAIN, 12));
        int paso = Math.max(1, maximo / 5);
        for (int v = 0; v <= maximo; v += paso) {
            int y = alto - margenAbajo - v * altoGrafico / maximo;
            g.setColor(new Color(225, 225, 225));
            g.drawLine(margenIzq, y, ancho - margenDer, y);
            g.setColor(Color.DARK_GRAY);
            g.drawString(String.valueOf(v), margenIzq - 35, y + 4);
        }

        // barras
        for (int i = 0; i < top.size(); i++) {
            String palabra = top.get(i).getKey();
            int valor = top.get(i).getValue();
            int altoBarra = valor * altoGrafico / maximo; // proporcional a la más alta
            int x = margenIzq + i * anchoColumna + (anchoColumna - anchoBarra) / 2;
            int y = alto - margenAbajo - altoBarra;

            g.setColor(new Color(52, 101, 164));
            g.fillRect(x, y, anchoBarra, altoBarra);

            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD, 13));
            String textoValor = String.valueOf(valor);
            g.drawString(textoValor, x + (anchoBarra - g.getFontMetrics().stringWidth(textoValor)) / 2, y - 6);

            g.setFont(new Font("SansSerif", Font.PLAIN, 14));
            g.drawString(palabra, x + (anchoBarra - g.getFontMetrics().stringWidth(palabra)) / 2, alto - margenAbajo + 22);
        }

        // ejes
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.drawLine(margenIzq, margenArriba, margenIzq, alto - margenAbajo);
        g.drawLine(margenIzq, alto - margenAbajo, ancho - margenDer, alto - margenAbajo);

        g.setFont(new Font("SansSerif", Font.PLAIN, 13));
        g.drawString("Palabra", ancho / 2 - 25, alto - 20);
        g.drawString("Frecuencia", 10, margenArriba - 15);

        g.dispose();
        ImageIO.write(imagen, "png", new File(ruta));
    }
}
