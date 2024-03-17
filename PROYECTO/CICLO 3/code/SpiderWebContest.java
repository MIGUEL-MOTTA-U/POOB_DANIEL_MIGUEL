import java.util.ArrayList;

/**
 * Resolve the marathon problem and use SpiderWeb to simulate.
 * 
 * @author Daniel Diaz && Miguel Motta
 * 
 * @version (2.3.3.24)
 */

public class SpiderWebContest {
    public static int[] solve(int strands, int favorite, int[][] bridges) {
        Spider spider = new Spider(false);
        ArrayList<Integer> bridgesToAdd = spider.minBridgesToAdd(bridges, favorite, strands);
        
        int[] output = new int[strands];
        int i = 0;
        for(Integer b : bridgesToAdd){
            output[i] = b;
            i += 1;
        }
        
        return output;
    }

    public static void simulate(int strands, int favorite, int[][] bridges, int strand){
        Spider spider = new Spider(false);
        ArrayList<int[]> bridgesToAdd = spider.bridgesToAdd(strands, favorite, bridges, strand);
        for(int[] b : bridgesToAdd){
            System.out.println("[" + b[0] + ", " + b[1] + "]");
        }
    }

}