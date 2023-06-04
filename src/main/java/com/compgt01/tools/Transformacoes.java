package com.compgt01.tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.compgt01.controller.ConsoleController;
import com.compgt01.controller.MalhaController;
import com.compgt01.controller.MenuController;
import com.compgt01.model.MalhaModel;
import com.compgt01.model.Ponto;
import com.compgt01.model.PontoBasier;

import javafx.scene.control.CheckBox;

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

    public static void recursiveFill(ConsoleController console, MalhaModel malhaModel, Ponto ponto) {
        recursiveFill(console, malhaModel, ponto.getX(), ponto.getY());
    }

    private static void recursiveFill(ConsoleController console, MalhaModel malhaModel, int row, int col) {

        int rows = malhaModel.getX() * 2 + 1;
        int cols = malhaModel.getY() * 2 + 1;

        // Verificar se as coordenadas estão dentro dos limites da grade
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return;
        }

        // Verificar se a célula já está preenchida ou se é uma borda
        if (malhaModel.getGridCheckBox()[row][col].isSelected()) {
            return;
        }

        // Preencher a célula atual com a cor especificada

        malhaModel.getGridCheckBox()[row][col].setSelected(true);

        // Chamar recursivamente o preenchimento para as células vizinhas
        recursiveFill(console, malhaModel, row - 1, col); // Célula acima
        recursiveFill(console, malhaModel, row + 1, col); // Célula abaixo
        recursiveFill(console, malhaModel, row, col - 1); // Célula à esquerda
        recursiveFill(console, malhaModel, row, col + 1); // Célula à direita
    }

    public static void scanlineFill(ConsoleController console, MalhaModel malhaModel, Ponto ponto) {
        scanlineFill(console, malhaModel, ponto.getY(), ponto.getX());
    }

    public static void scanlineFill(ConsoleController console, MalhaModel malhaModel, int row, int col) {

        int rows = malhaModel.getY() * 2 + 1;
        int cols = malhaModel.getX() * 2 + 1;

        // Verificar se as coordenadas estão dentro dos limites da grade
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return;
        }

        // Verificar se a célula já está preenchida ou se é uma borda
        if (malhaModel.getGridCheckBox()[col][row].isSelected()) {
            return;
        }

        // Encontrar a coordenada do limite esquerdo
        int left = col;
        while (left >= 0 && !malhaModel.getGridCheckBox()[left][row].isSelected()) {
            left--;
        }
        left++;

        // Encontrar a coordenada do limite direito
        int right = col;
        while (right < cols && !malhaModel.getGridCheckBox()[right][row].isSelected()) {
            right++;
        }
        right--;

        // Preencher a linha atual entre os limites esquerdo e direito
        for (int c = left; c <= right; c++) {
            malhaModel.getGridCheckBox()[c][row].setSelected(true);
        }

        // Chamar recursivamente o algoritmo de varredura para as linhas acima e abaixo
        scanlineFill(console, malhaModel, row - 1, col); // Linha acima
        scanlineFill(console, malhaModel, row + 1, col); // Linha abaixo
    }

    public static List<Ponto> scalePontos(MalhaModel malha, List<Ponto> pontos, int pivotX, int pivotY, double scaleX,
            double scaleY) {
        List<Ponto> pontosTransformados = scalePontos(pontos, pivotX, pivotY, scaleX, scaleY);

        return pontosTransformados;
    }

    public static List<Ponto> scalePontos(List<Ponto> pontos, int pivotX, int pivotY, double scaleX, double scaleY) {
        List<Ponto> pontosTransformados = new ArrayList<>();

        for (Ponto ponto : pontos) {
            int x = ponto.getX();
            int y = ponto.getY();
            pontosTransformados.add(new Ponto((int) (pivotX + (x - pivotX) * scaleX),
                    (int) (pivotY + (y - pivotY) * scaleY)));
        }

        return pontosTransformados;
    }

    public static void bresenham(ConsoleController console,
            MenuController menuController,
            MalhaController malhaController,
            int centerX, int centerY, int destX, int destY) {

        Set<Ponto> pontos = calcularPontosReta(centerX, centerY, destX, destY);
        pontos.forEach(e -> {
            try {
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

    public static void bresenhamRealPoint(ConsoleController console,
            MalhaController malhaController,
            Ponto init, Ponto fim) {

        console.redirectToConsole(String.format("Pintando de %s a %s.\n", init, fim));

        Set<Ponto> pontos = calcularPontosReta(init.getX(), init.getY(), fim.getX(), fim.getY());
        pontos.forEach(e -> {
            try {
                System.out.println(e);
                malhaController.getMalhaModel().getGridCheckBox()[e.getX()][e.getY()]
                        .setSelected(true);
            } catch (IndexOutOfBoundsException exception) {
                console.redirectToConsole(String.format("x: %d y: %d fora da camada", e.getX(), e.getY(), "\n"));
            }
        });
    }

    public static void translacao(ConsoleController console,
            MenuController menuController,
            MalhaController malhaController,
            int x1, int y1) {

        CheckBox[][] gridCheckBox = malhaController.getMalhaModel().getGridCheckBox();
        CheckBox[][] gridCheckBoxCopy = new CheckBox[gridCheckBox.length][];

        for (int i = 0; i < gridCheckBox.length; i++) {
            gridCheckBoxCopy[i] = new CheckBox[gridCheckBox[i].length];
            for (int j = 0; j < gridCheckBox[i].length; j++) {
                CheckBox originalCheckBox = gridCheckBox[i][j];
                CheckBox copiedCheckBox = new CheckBox();
                copiedCheckBox.setSelected(originalCheckBox.isSelected());
                // Copy other properties as needed
                gridCheckBoxCopy[i][j] = copiedCheckBox;
            }
        }
        if (x1 >= 0 && y1 >= 0) {
            for (int i = (menuController.getMalhaModel().getX() * 2 + 1) - 1; i >= 0; i--) {
                for (int j = (menuController.getMalhaModel().getY() * 2 + 1) - 1; j >= 0; j--) {
                    CheckBox checkbox = gridCheckBoxCopy[i][j];
                    if (checkbox.isSelected()) {
                        try {
                            gridCheckBox[i][j].setSelected(false);
                            gridCheckBox[i + x1][j - y1].setSelected(true);
                        } catch (IndexOutOfBoundsException exception) {
                            console.redirectToConsole(
                                    String.format("x: %d y: %d fora da camada", i - menuController.getMalhaModel()
                                            .getX() + x1, j - menuController.getMalhaModel().getY() - y1, "\n"));

                        }
                    }
                }
            }
        } else {
            for (int i = 0; i <= (menuController.getMalhaModel().getX() * 2 + 1) - 1; i++) {
                for (int j = 0; j <= (menuController.getMalhaModel().getY() * 2 + 1) - 1; j++) {
                    CheckBox checkbox = gridCheckBoxCopy[i][j];
                    if (checkbox.isSelected()) {
                        try {
                            gridCheckBox[i][j].setSelected(false);
                            gridCheckBox[i + x1][j - y1].setSelected(true);
                        } catch (IndexOutOfBoundsException exception) {
                            console.redirectToConsole(
                                    String.format("x: %d y: %d fora da camada", i - menuController.getMalhaModel()
                                            .getX() + x1, j - menuController.getMalhaModel().getY() - y1, "\n"));

                        }
                    }
                }
            }
        }
    }

    public static int[] rotateVector(int[] vector, Double angle, Double[] pivot) { // Convert angle from degrees
                                                                                   // to radians
        double angleRad = Math.toRadians(angle);

        // Perform translation to pivot point
        double translatedX = vector[0] - pivot[0];
        double translatedY = vector[1] - pivot[1];

        // Perform rotation around the pivot point
        double rotatedX = translatedX * Math.cos(angleRad) - translatedY * Math.sin(angleRad);
        double rotatedY = translatedX * Math.sin(angleRad) + translatedY * Math.cos(angleRad);

        // Perform translation back from the pivot point
        double finalX = rotatedX + pivot[0];
        double finalY = rotatedY + pivot[1];
        // Return rotated vector

        return new int[] { (int) Math.round(finalX), (int) Math.round(finalY) };
    }

    public static void rotacao(ConsoleController console,
            MenuController menuController,
            MalhaController malhaController,
            Double angulo, Double[] pivo) {
        List<int[]> rots = new ArrayList<>();
        CheckBox[][] gridCheckBox = malhaController.getMalhaModel().getGridCheckBox();
        int x = (menuController.getMalhaModel().getX() * 2 + 1);
        int y = (menuController.getMalhaModel().getY() * 2 + 1);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                CheckBox checkbox = gridCheckBox[i][j];
                if (checkbox.isSelected()) {
                    try {
                        int[] vec = { i - menuController.getMalhaModel().getX(),
                                j - menuController.getMalhaModel().getY() };
                        rots.add(rotateVector(vec, angulo, pivo));
                        checkbox.setSelected(false);

                    } catch (IndexOutOfBoundsException exception) {
                        console.redirectToConsole(
                                String.format("x: %d y: %d fora da camada", x - i, y - j, "\n"));
                    }
                }
            }
        }

        rots.forEach(e -> {
            try {
                malhaController.getMalhaModel()
                        .getGridCheckBox()[e[0] + menuController.getMalhaModel()
                                .getX()][menuController.getMalhaModel()
                                        .getY() + e[1]]
                        .setSelected(true);
            } catch (IndexOutOfBoundsException exception) {
                console.redirectToConsole(String.format("x: %s y: %s fora da camada", e[0], e[1], "\n"));
            }
        });
    }

    private static HashSet<Ponto> calcularPontosReta(int x1, int y1, int x2, int y2) {
        HashSet<Ponto> pontos = new HashSet<>();

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1; // se para esquerda ou direita
        int sy = y1 < y2 ? 1 : -1; // se para cima ou para baixo
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
