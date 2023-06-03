package com.compgt01.tools;

import java.util.HashSet;
import java.util.Set;

import com.compgt01.controller.ConsoleController;
import com.compgt01.controller.MalhaController;
import com.compgt01.controller.MenuController;
import com.compgt01.model.Ponto;
import com.compgt01.model.PontoBasier;

public class Transformacoes {

    /**
     * pontos 1 1 1
     * matriz
     * x y z ...
     * 1 2 3
     * 3 2 1
     * result
     * x y z
     * 2 3 4
     * 4 3 2
     *
     * @param pontos
     * @param matriz
     * @return
     */

    public static void imprimirMatriz(int[][] matriz) {
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz[row].length; col++) {
                System.out.printf("%d ", matriz[row][col]);
            }
            System.out.println();
        }

    }

    static int[] calculate(String coordenada) {

        int[] result = new int[2];

        int commaLoc = coordenada.strip().indexOf(",");

        result[0] = Integer.parseInt(coordenada.substring(commaLoc + 2, coordenada.length() - 1));
        result[1] = Integer.parseInt(coordenada.substring(1, commaLoc));

        return result;
    }

    public static void bresenham(ConsoleController console,
            MenuController menuController,
            MalhaController malhaController,
            int centerX, int centerY, int destX, int destY) {

        System.out.println(String.format("%d %d %d %d", centerX, centerY, destX, destY));
        Set<Ponto> pontos = calcularPontosReta(centerX, centerY, destX, destY);
        System.out.println(pontos.isEmpty());
        pontos.forEach(e -> {
            try {
                System.out.println(e);
                malhaController.getMalhaModel().getGridCheckBox()[e.getX()
                        + menuController.getMalhaModel().getX()][menuController.getMalhaModel().getY() - e.getY()]
                        .setSelected(true);
            } catch (IndexOutOfBoundsException exception) {
                console.redirectToConsole(String.format("x: %d y: %d fora da camada", e.getX(), e.getY(), "\n"));
            }

        });
    }

    public static void bresenham(ConsoleController console,
            MenuController menuController,
            MalhaController malhaController,
            Ponto init, Ponto fim) {

        console.redirectToConsole(String.format("Pintando de %s a %s.\n", init, fim));

        System.out.println(String.format("%d %d %d %d", init.getX(), init.getY(), fim.getX(), fim.getY()));
        Set<Ponto> pontos = calcularPontosReta(init.getX(), init.getY(), fim.getX(), fim.getY());
        System.out.println(pontos.isEmpty());
        pontos.forEach(e -> {
            try {
                System.out.println(e);
                malhaController.getMalhaModel().getGridCheckBox()[e.getX()
                        + menuController.getMalhaModel().getX()][menuController.getMalhaModel().getY() - e.getY()]
                        .setSelected(true);
            } catch (IndexOutOfBoundsException exception) {
                console.redirectToConsole(String.format("x: %d y: %d fora da camada", e.getX(), e.getY(), "\n"));
            }

        });
    }

    public static void main(String[] args) {
        Set<Ponto> pontos = calcularPontosReta(0, 0, 10, 10);
        pontos.forEach(System.out::println);
    }

    private static HashSet<Ponto> calcularPontosReta(int x1, int y1, int x2, int y2) {
        HashSet<Ponto> pontos = new HashSet<>();

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        int x = x1;
        int y = y1;

        while (x != x2 || y != y2) {
            pontos.add(new Ponto(x, y));

            int err2 = 2 * err;

            if (err2 > -dy) {
                err -= dy;
                x += sx;
            }

            if (err2 < dx) {
                err += dx;
                y += sy;
            }
        }

        pontos.add(new Ponto(x2, y2));

        return pontos;
    }

    public static void desenharCirculo(ConsoleController console, MenuController menuController,
            MalhaController malhaController, int raio,
            int centerX, int centerY) {

        HashSet<Ponto> pontos = new HashSet<>(0);
        desenharCirculo(pontos, raio, centerX, centerY);
        pontos.forEach(e -> {

            try {
                malhaController.getMalhaModel().getGridCheckBox()[menuController.getMalhaModel().getY() - e.getY()][e
                        .getX() + menuController.getMalhaModel().getX()]
                        .setSelected(true);
            } catch (IndexOutOfBoundsException exception) {
                console.redirectToConsole(String.format("x: %d y: %d fora da camada", e.getX(), e.getY(), "\n"));
            }

        });

    }

    private static void desenharCirculo(Set<Ponto> pontos, int radius, int centerX, int centerY) {
        int x = 0;
        int y = radius;
        int d = 1 - radius;

        while (x <= y) {
            pontosSimetricosCirculo(pontos, x, y, centerX, centerY);
            if (d < 0) {
                d = d + 2 * x + 3;
                x = x + 1;
            } else {
                d = d + 2 * x - 2 * y + 5;
                x = x + 1;
                y = y - 1;
            }
        }
    }

    private static void pontosSimetricosCirculo(Set<Ponto> pontos, int x, int y, int centerX, int centerY) {
        pontos.add(new Ponto(centerX + x, centerY + y));
        pontos.add(new Ponto(centerX - x, centerY + y));
        pontos.add(new Ponto(centerX + x, centerY - y));
        pontos.add(new Ponto(centerX - x, centerY - y));
        pontos.add(new Ponto(centerX + y, centerY + x));
        pontos.add(new Ponto(centerX - y, centerY + x));
        pontos.add(new Ponto(centerX + y, centerY - x));
        pontos.add(new Ponto(centerX - y, centerY - x));
    }

    /**
     * Basier Init
     *
     * @param tInicio
     * @param tFim
     * @param incremento
     * @param pontosControle
     * @return
     */

    public static void desenharCurvaBasier(ConsoleController console,
            MenuController menuController,
            MalhaController malhaController,
            Set<PontoBasier> pontosControle) {

        // Calcular pontos na curva de Bezier
        double tInicio = 0;
        double tFim = 1;
        double incremento = 0.01;
        Set<PontoBasier> pontosBezier = calcularPontosBezier(tInicio, tFim, incremento, pontosControle);

        pontosBezier.forEach(e -> {

            try {
                malhaController.getMalhaModel().getGridCheckBox()[e.getX()
                        + menuController.getMalhaModel().getX()][menuController.getMalhaModel().getY() - e.getY()]
                        .setSelected(true);
            } catch (IndexOutOfBoundsException exception) {
                console.redirectToConsole(String.format("x: %d y: %d fora da camada", e.getX(), e.getY(), "\n"));
            }

        });

    }

    private static Set<PontoBasier> calcularPontosBezier(double tInicio, double tFim, double incremento,
            Set<PontoBasier> pontosControle) {
        Set<PontoBasier> pontosBezier = new HashSet<>();

        for (double t = tInicio; t <= tFim; t += incremento) {
            PontoBasier pontoBezier = calcularPontoBezier(t, pontosControle);
            pontosBezier.add(pontoBezier);
        }

        return pontosBezier;
    }

    private static PontoBasier calcularPontoBezier(double t, Set<PontoBasier> pontosControle) {
        int n = pontosControle.size() - 1;

        double x = 0;
        double y = 0;

        for (PontoBasier pontoControle : pontosControle) {
            double coeficiente = calcularCoeficienteBinomial(n, pontoControle.getIndice(), t);
            x += coeficiente * pontoControle.getX();
            y += coeficiente * pontoControle.getY();
        }

        return new PontoBasier((int) x, (int) y, 0);
    }

    private static double calcularCoeficienteBinomial(int n, int k, double t) {
        double coeficiente = 1;

        for (int i = 1; i <= k; i++) {
            coeficiente *= (n - i + 1) / (double) i;
        }

        coeficiente *= Math.pow(t, k) * Math.pow(1 - t, n - k);

        return coeficiente;
    }
}
