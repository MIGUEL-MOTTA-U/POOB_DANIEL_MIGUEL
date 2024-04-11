package spiderweb;
import java.util.ArrayList;

/**
 * Resolve the marathon problem and use SpiderWeb to simulate.
 * 
 * @author Daniel Diaz && Miguel Motta
 * 
 * @version (2.3.3.24)
 */

public class SpiderWebContest {
    /**
     * 
     * @param strands  The total number of strands in the spider web.
     * @param favorite The index of the spider's favorite spot on the web.
     * @param bridges  A 2D array representing the connections between spots on the
     *                 web.
     *                 Each element is an array of two integers representing the
     *                 indices of the spots connected by a bridge.
     * @return the minimum number of strands to add to reach the spot
     */
    public static int[] solve(int strands, int favorite, int[][] bridges) {
        Spider spider = new Spider(1, false);
        ArrayList<Integer> bridgesToAdd = spider.minBridgesToAdd(bridges, favorite, strands);

        int[] output = new int[strands];
        int i = 0;
        for (Integer b : bridgesToAdd) {
            output[i] = b;
            i += 1;
        }

        return output;
    }

    /**
     * Simulate the solution of the problem
     * 
     * @param strands  The total number of strands in the spider web.
     * @param favorite The index of the spider's favorite spot on the web.
     * @param bridges  A 2D array representing the connections between spots on the
     *                 web.
     *                 Each element is an array of two integers representing the
     *                 indices of the spots connected by a bridge.
     * @param strand   The strand where the spider starts to walk
     */
    public static void simulate(int strands, int favorite, int[][] bridges, int strand) {
        Spider s = new Spider(1, false);
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