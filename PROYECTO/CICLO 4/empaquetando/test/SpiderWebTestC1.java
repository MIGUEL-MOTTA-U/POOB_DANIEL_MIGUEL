package test;
import spiderweb.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The test class SpiderWebTest for C1.
 *
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class SpiderWebTestC1 {
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
    public void accordingDCShouldShowTheSpiderPath() {
        int[] expected = { 1, 2, 3, 4 };
        spiderWeb.spiderWalk(true);
        assertArrayEquals(spiderWeb.spiderLastPath(), expected);
        //
        spiderWeb.spiderSit(4);
        expected = new int[] { 4, 3, 2, 1 };
        spiderWeb.spiderWalk(true);
        assertArrayEquals(spiderWeb.spiderLastPath(), expected);
        //
        spiderWeb.spiderSit(3);
        expected = new int[] { 3, 2, 3 };
        spiderWeb.spiderWalk(true);
        assertArrayEquals(spiderWeb.spiderLastPath(), expected);

        //
        spiderWeb.spiderSit(20);
        expected = new int[] { 19, 20 };
        spiderWeb.spiderWalk(true);
        assertNotEquals(spiderWeb.spiderLastPath(), expected);
        //
        spiderWeb.spiderSit(2);
        expected = new int[] { 2, 1 };
        spiderWeb.spiderWalk(true);
        assertNotEquals(spiderWeb.spiderLastPath(), expected);
    }

    @Test
    public void accordingDCShouldShowAllTheBridges() {
        //
        String[] expected = { "red", "orange", "magenta", "green", "blue", "cyan" };
        assertArrayEquals(spiderWeb.bridges(), expected);
        //
        spiderWeb.delBridge("red");
        expected = new String[] { "orange", "magenta", "green", "blue", "cyan" };
        assertArrayEquals(spiderWeb.bridges(), expected);
        //
        spiderWeb.delBridge("blue");
        expected = new String[] { "orange", "magenta", "green", "cyan" };
        assertArrayEquals(spiderWeb.bridges(), expected);

        //
        spiderWeb.addBridge("blue", 80, 1);
        expected = new String[] { "orange", "magenta", "green", "cyan" };
        assertNotEquals(spiderWeb.bridges(), expected);
        //
        spiderWeb.addBridge("red", 130, 2);
        expected = new String[] { "red", "orange", "magenta", "green", "cyan" };
        assertNotEquals(spiderWeb.bridges(), expected);
    }

    @Test
    public void accordingDCShouldShowTheCoordinatesOfTheBridge() {
        //
        double[] expected = { 580.0, 400.0, 576.0845213036123, 375.2786404500042};
        assertArrayEquals(spiderWeb.bridge("blue"), expected, 0.1);
        //
        expected = new double[] { 770.0, 400.0, 756.7852593996914, 316.5654115187642 };
        assertArrayEquals(spiderWeb.bridge("orange"), expected, 0.1);

        //
        expected = new double[] { 580.0, 400.0, 576.0845213036123, 375.2786404500042 };
        assertNotEquals(spiderWeb.bridge("red"), expected);
        //
        expected = new double[] { 770.0, 400.0, 755.7852593996914, 316.5654115187642 };
        assertNotEquals(spiderWeb.bridge("orange"), expected);
    }

    @Test
    public void accordingDCShouldShowAllTheSpots() {
        //
        String[] expected = { "aquamarine", "yellow", "brown" };
        assertArrayEquals(spiderWeb.spots(), expected);
        //
        spiderWeb.delSpot("aquamarine");
        expected = new String[] { "yellow", "brown" };
        assertArrayEquals(spiderWeb.spots(), expected);
        //
        spiderWeb.delSpot("yellow");
        spiderWeb.delSpot("brown");
        expected = new String[] {};
        assertArrayEquals(spiderWeb.spots(), expected);

        //
        spiderWeb.addSpot("aquamarine", 4);
        expected = new String[] { null };
        assertNotEquals(spiderWeb.spots(), expected);
        //
        spiderWeb.addSpot("yellow", 2);
        expected = new String[] { "aquamarine" };
        assertNotEquals(spiderWeb.spots(), expected);
        //
        spiderWeb.addSpot("brown", 20);
        expected = new String[] { "aquamarine", "yellow", "black" };
        assertNotEquals(spiderWeb.spots(), expected);
    }

    @Test
    public void accordingDCShouldShowTheCoordinatesOfTheSpot() {
        //
        double[] expected = { 732.6141009169893, 732.6141009169893 };
        assertArrayEquals(spiderWeb.spot("aquamarine"), expected, 0.01);
        //
        expected = new double[] { 877.9226065180615, 877.9226065180615 };
        assertArrayEquals(spiderWeb.spot("brown"), expected, 0.01);

        //
        expected = new double[] { 732.6141009169893, 732.6141009169893 };
        assertNotEquals(spiderWeb.spot("yellow"), expected);
        //
        expected = new double[] { 876.9226065180615, 877.9226065180615 };
        assertNotEquals(spiderWeb.spot("brown"), expected);
    }

    @Test
    public void accordingMUShouldAddBridge() {
        spiderWeb.addBridge("blue", 20, 20);
        assertEquals(spiderWeb.ok(), false);

        spiderWeb.addBridge("black", 80, 1);
        assertEquals(spiderWeb.ok(), false);

        spiderWeb.addBridge("black", 701, 1);
        assertEquals(spiderWeb.ok(), false);

        spiderWeb.addBridge("black", 100, 25);
        assertEquals(spiderWeb.ok(), false);

        spiderWeb.addBridge("black", 50, 1000);
        assertEquals(spiderWeb.ok(), false);

        spiderWeb.addBridge("black", 75, 15);
        assertEquals(spiderWeb.ok(), true);
    }

    @Test
    public void accordingMUShouldDelBridge() {
        spiderWeb.addBridge("black", 75, 15);
        spiderWeb.delBridge("black");
        assertEquals(true, spiderWeb.ok());

        spiderWeb.delBridge("black");
        assertEquals(false, spiderWeb.ok());

        spiderWeb.addBridge("black", 75, 15);
        spiderWeb.delBridge("black");
        assertEquals(true, spiderWeb.ok());

        spiderWeb.addBridge("yellow", 100, 12);
        spiderWeb.delBridge("yellow");
        assertEquals(true, spiderWeb.ok());
    }

    @Test
    public void accordignMUShouldrelocateBridge() {
        spiderWeb.addBridge("black", 100, 14);
        spiderWeb.relocateBridge("black", 50);
        assertEquals(true, spiderWeb.ok());

        spiderWeb.relocateBridge("black", 100);
        assertEquals(true, spiderWeb.ok());

        spiderWeb.addBridge("black", 100, 14);
        spiderWeb.relocateBridge("black", 701);
        assertEquals(false, spiderWeb.ok());

        spiderWeb.relocateBridge("black", -50);
        assertEquals(false, spiderWeb.ok());
    }

    @Test
    public void accordingMUShouldAddSpot() {
        spiderWeb.addSpot("brown", 17);
        assertEquals(spiderWeb.ok(), false);

        spiderWeb.addSpot("black", 100);
        assertEquals(spiderWeb.ok(), false);

        int begin = spiderWeb.spots().length;
        spiderWeb.addSpot("black", 19);
        assertEquals(spiderWeb.spots().length, begin + 1);

        begin += 1;
        spiderWeb.addSpot("cyan", 17);
        assertEquals(spiderWeb.spots().length, begin + 1);

        begin += 1;
        spiderWeb.delSpot("cyan");
        spiderWeb.delSpot("black");
    }

    @Test
    public void accordingMUShouldDelSpot() {
        spiderWeb.delSpot("black");
        assertEquals(spiderWeb.ok(), false);

        spiderWeb.addSpot("cyan", 17);
        spiderWeb.delSpot("cyan");
        assertEquals(spiderWeb.ok(), true);

        spiderWeb.addSpot("black", 17);
        spiderWeb.delSpot("black");
        assertEquals(spiderWeb.ok(), true);

        spiderWeb.addSpot("green", 17);
        spiderWeb.delSpot("green");
        assertEquals(spiderWeb.ok(), true);
    }

    @Test
    public void accordingMUShouldSitSpider() {
        spiderWeb.spiderSit(0);
        assertEquals(spiderWeb.ok(), false);

        spiderWeb.spiderSit(555);
        assertEquals(spiderWeb.ok(), false);

        spiderWeb.spiderSit(5);
        assertEquals(spiderWeb.ok(), true);

        spiderWeb.spiderSit(5);
        assertEquals(spiderWeb.ok(), true);

        spiderWeb.spiderSit(15);
        assertEquals(spiderWeb.ok(), true);
    }

    @Test
    public void accordingMUShouldSpiderWalk() {
        spiderWeb.spiderWalk(true);
        assertEquals(spiderWeb.ok(), true);

        spiderWeb.spiderSit(15);
        assertEquals(spiderWeb.ok(), true);

        spiderWeb.spiderWalk(true);
        assertEquals(spiderWeb.ok(), true);

        spiderWeb.spiderWalk(false);
        assertEquals(spiderWeb.ok(), true);
    }
}
