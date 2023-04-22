package facomp.ufpa.br.tools;

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
    public static int[][] translacao(int[] pontos, int[][] matriz){
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz[row].length; col++) {
                matriz[row][col] = matriz[row][col] + pontos[col];
            }
        }
        return matriz;
    }

    public static void imprimirMatriz(int[][] matriz){
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz[row].length; col++) {
                System.out.printf("%d ",matriz[row][col]);                
            }
            System.out.println();                
        }

    }
    
}
