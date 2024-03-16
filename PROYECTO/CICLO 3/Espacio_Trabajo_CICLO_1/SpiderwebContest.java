import java.util.ArrayList;

/**
 * The SpiderwebContest class is intended to evaluate the algorithm for solving the Spider Walk problem. 
 * It uses the SpiderWeb class to simulate the solution.
 * 
 * @author (Daniel Diaz & Miguel Motta) 
 * @version (Version C3 15/03/2024)
 */
public class SpiderwebContest
{
    /**
     * This method solves the problem, figuring out the minimum number of bridges needed to add to reach the desire point.
     * @param strands is the number of strands that the SpiderWeb will have.
     * @param favorite is the favorite strand
     * @param bridges is an array of bridges with the position and it's strand.
     * @return res, it is the minimum number of bridges needed to go throught the spiderWeb to reach the favorite strand, 
     * one number for every bridge.
     */
    public int[] solve(int strands, int favorite , int[][] bridges ){
        ArrayList<int[][]> puentesCreados = new ArrayList(); // Esto lo necesitamos en simulate
        int[] res = null;
        /* 
         * int[i] = Corresponde al punto de Partida i que para queda hebra hay su respectivo número de puentes.
         * int[][] = (Nivel o Distancia respecto al centro, Numero de Hebra donde se colocó)  
        */ 
        
        return res;
    }
    
    public void simulate(int strands, int favorite , int[][] bridges , int strand){
        int[] puentes = solve(strands,favorite ,bridges );
        int NumeroPuentesMinimos = puentes[strand];
        // Calcular el donde colocar esos N numero de puentes
        
    }
}
