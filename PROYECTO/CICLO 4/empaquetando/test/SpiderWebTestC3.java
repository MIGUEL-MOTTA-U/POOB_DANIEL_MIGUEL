package test;
import spiderweb.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The test class SpiderWebTest for C3.
 *
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class SpiderWebTestC3 {
    private static SpiderWeb spiderWeb;

    @BeforeClass
    public static void beforeClass() {
    }

    @Before
    public void before() {
        spiderWeb = new SpiderWeb(20, 400);
        spiderWeb.addBridge("blue", 80, 1);
        spiderWeb.addBridge("red", 130, 2);
        spiderWeb.addBridge("magenta", 200, 3);
        spiderWeb.addBridge("green", 230, 2);
        spiderWeb.addBridge("orange", 270, 1);
        spiderWeb.addBridge("cyan", 50, 19);
        spiderWeb.addSpot("yellow", 2);
        spiderWeb.addSpot("aquamarine", 4);
        spiderWeb.addSpot("brown", 20);
    }

    @Test
    public void accordingDCShouldSolve() {
        int[] minBridges = SpiderWebContest.solve(7, 6,
                new int[][] { { 100, 1 }, { 150, 3 }, { 200, 3 }, { 250, 7 }, { 300, 5 } });
        int[] expected = new int[] { 2, 1, 1, 1, 0, 1, 2 };
        assertArrayEquals(minBridges, expected);

        minBridges = SpiderWebContest.solve(4, 2, new int[][] { { 100, 1 }, { 150, 2 }, { 200, 3 }, { 250, 4 } });
        expected = new int[] { 1, 2, 0, 1 };
        assertArrayEquals(minBridges, expected);

        minBridges = SpiderWebContest.solve(8, 4,
                new int[][] { { 100, 1 }, { 1150, 2 }, { 200, 2 }, { 230, 5 }, { 270, 8 }, { 300, 8 } });
        expected = new int[] { 1, 2, 1, 0, 1, 1, 2, 2 };
        assertArrayEquals(minBridges, expected);
    }

    
}