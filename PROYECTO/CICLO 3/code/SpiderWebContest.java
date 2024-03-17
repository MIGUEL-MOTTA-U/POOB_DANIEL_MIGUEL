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
        Spider s = new Spider(false);
        ArrayList<int[]> bridgesToAdd = s.bridgesToAdd(strands, favorite, bridges, strand);
        
        int[][] totalBridges = new int[bridges.length + bridgesToAdd.size()][2];
        
        for (int i = 0; i < bridges.length; i++) {
            totalBridges[i] = bridges[i];
        }
        
        int index = bridges.length;
        for (int[] bridge : bridgesToAdd) {
            totalBridges[index] = bridge;
            index++;
        }

        SpiderWeb spiderWeb = new SpiderWeb(strands, favorite, totalBridges);
        spiderWeb.spiderSit(strand);
        spiderWeb.spiderWalk(true);
    }

}