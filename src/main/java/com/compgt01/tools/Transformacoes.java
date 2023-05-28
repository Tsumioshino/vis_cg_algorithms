package com.compgt01.tools;

import com.compgt01.controller.MalhaController;
import com.compgt01.controller.MenuController;

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
    public static int[][] translacao(int[] pontos, int[][] matriz) {
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz[row].length; col++) {
                matriz[row][col] = matriz[row][col] + pontos[col];
            }
        }
        return matriz;
    }

    public static void imprimirMatriz(int[][] matriz) {
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz[row].length; col++) {
                System.out.printf("%d ", matriz[row][col]);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        bresenham(0, 0, 3, 5);
    }

    public static void bresenham(MenuController menuController,
            MalhaController malhaController,
            int x1, int y1, int x2, int y2) {
        int slope;
        int dx, dy, incE, incNE, d, x, y;
        // Onde inverte a linha x1 > x2
        if (x1 > x2) {
            bresenham(x2, y2, x1, y1);
            return;
        }
        dx = x2 - x1;
        dy = y2 - y1;

        if (dy < 0) {
            slope = -1;
            dy = -dy;
        } else {
            slope = 1;
        }
        // Constante de Bresenham
        incE = 2 * dy;
        incNE = 2 * dy - 2 * dx;
        d = 2 * dy - dx;
        y = y1;
        for (x = x1; x <= x2; x++) {
            System.out.printf("x:%d y:%d %n", x, y); // AQUI TEM OS PONTOS A SEREM PINTADOS
            malhaController.getMalhaModel().getCoordinates()
                    .get(y + menuController.getMalhaModel().getY())
                    .get(x + menuController.getMalhaModel().getX())
                    .setSelected(true);
            if (d <= 0) {
                d += incE;
            } else {
                d += incNE;
                y += slope;
            }
        }
    }

    static int[] calculate(String coordenada) {

        int[] result = new int[2];

        int commaLoc = coordenada.strip().indexOf(",");

        result[0] = Integer.parseInt(coordenada.substring(commaLoc + 2, coordenada.length() - 1));
        result[1] = Integer.parseInt(coordenada.substring(1, commaLoc));

        return result;
    }
}
