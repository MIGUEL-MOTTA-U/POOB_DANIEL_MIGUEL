package test;
import spiderweb.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The test class SpiderWebTest for C2.
 *
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class SpiderWebTestC2 {
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
    public void accordingDCShouldCreateTheWeb() {
        SpiderWeb s = new SpiderWeb(20, 5, new int[][]{{73, 2},{105,6},{281, 7},{137, 6}});
        assertTrue(s.ok());

        int bridges = s.bridges().length;
        int spots = s.spots().length;

        System.out.println(s.bridges());
        System.out.println(s.spots());

        assertEquals(bridges, 4);
        assertEquals(spots, 1);
    }

    @Test
    public void accordingDCShouldNotUseBridges() {
        //
        spiderWeb.spiderSit(1);
        String[] expected = { "orange", "green", "cyan" };
        spiderWeb.spiderWalk(true);
        assertArrayEquals(spiderWeb.unusedBridges(), expected);
        //
        spiderWeb.spiderSit(20);
        expected = new String[] { "red", "orange", "magenta", "green", "blue" };
        spiderWeb.spiderWalk(true);
        assertArrayEquals(spiderWeb.unusedBridges(), expected);
        //
        spiderWeb.spiderSit(4);
        expected = new String[] { "red", "blue", "cyan" };
        spiderWeb.spiderWalk(true);
        assertArrayEquals(spiderWeb.unusedBridges(), expected);

        //
        spiderWeb.spiderSit(3);
        expected = new String[] { "red", "orange" };
        spiderWeb.spiderWalk(true);
        assertNotEquals(spiderWeb.unusedBridges(), expected);
        //
        spiderWeb.spiderSit(19);
        expected = new String[] { "red", "orange", "magenta", "green" };
        spiderWeb.spiderWalk(true);
        assertNotEquals(spiderWeb.unusedBridges(), expected);
    }

    @Test
    public void accordingDCShouldReachSpot() {
        //
        String[] expected = { "aquamarine" };
        assertArrayEquals(spiderWeb.reachableSpots(), expected);
        //
        spiderWeb.spiderSit(2);
        expected = new String[] { "yellow" };
        assertArrayEquals(spiderWeb.reachableSpots(), expected);
        //
        spiderWeb.spiderSit(3);
        expected = new String[] { null };
        assertArrayEquals(spiderWeb.reachableSpots(), expected);

        //
        expected = new String[] { "brown" };
        assertNotEquals(spiderWeb.reachableSpots(), expected);
        //
        spiderWeb.spiderSit(19);
        expected = new String[] { "yellow" };
        assertNotEquals(spiderWeb.reachableSpots(), expected);
        //
        expected = new String[] { null };
        assertNotEquals(spiderWeb.reachableSpots(), expected);
    }

    @Test
    public void accordingMUShouldExpand() throws Exception {
        spiderWeb.enlarge(150);
        assertEquals(spiderWeb.ok(), true);

        spiderWeb.enlarge(150);
        assertEquals(spiderWeb.ok(), true);

        spiderWeb.enlarge(-150);
        assertNotEquals(spiderWeb.ok(), true);

        spiderWeb.enlarge(-5);
        assertNotEquals(spiderWeb.ok(), true);
    }

    @Test
    public void accordingMUShouldAddStrand() throws Exception {
        for (int i = 1; i <= 5; i++) {
            spiderWeb.addStrand();
            assertEquals(spiderWeb.ok(), true);
            spiderWeb.addStrand();
            assertNotEquals(spiderWeb.ok(), false);
        }
    }
}