import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.JOptionPane;

/**
 * The test class SpiderWebTest.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpiderWebTest {
    private static SpiderWeb spiderWeb;

    @BeforeClass
    public static void beforeClass() {
    }

    @Before
    public void before() {
        spiderWeb = new SpiderWeb(20, 400);
        spiderWeb.makeInvisible();
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
        double[] expected = { 580.0, 374.0, 576.0845213036123, 349.2786404500042};
        assertArrayEquals(spiderWeb.bridge("blue"), expected, 0.1);
        //
        expected = new double[] { 770.0, 374.0, 756.7852593996914, 290.5654115187642 };
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

    @Test
    public void accordingDCShouldDelFixed() {
        spiderWeb.addBridge("fixed", "black", 75, 15);
        int numBridges = spiderWeb.bridges().length;
        assertEquals(numBridges, 7);
        spiderWeb.delBridge("black");
        assertEquals(true, spiderWeb.ok());
        numBridges = spiderWeb.bridges().length;
        assertEquals(numBridges, 7);

        spiderWeb.addBridge("fixed", "yellow", 100, 12);
        numBridges = spiderWeb.bridges().length;
        assertEquals(numBridges, 8);
        spiderWeb.delBridge("yellow");
        assertEquals(true, spiderWeb.ok());
        numBridges = spiderWeb.bridges().length;
        assertEquals(numBridges, 8);
    }

    @Test
    public void accordingDCShouldDelTransformer() {
        spiderWeb.addBridge("transformer", "black", 75, 15);
        int numBridges = spiderWeb.bridges().length;
        int numSpots = spiderWeb.spots().length;
        assertEquals(numBridges, 7);
        assertEquals(numSpots, 3);
        spiderWeb.delBridge("black");
        assertEquals(true, spiderWeb.ok());
        numBridges = spiderWeb.bridges().length;
        numSpots = spiderWeb.spots().length;
        assertEquals(numBridges, 6);
        assertEquals(numSpots, 4);

        spiderWeb.addBridge("transformer", "yellow", 100, 20);
        numBridges = spiderWeb.bridges().length;
        numSpots = spiderWeb.spots().length;
        assertEquals(numBridges, 7);
        assertEquals(numSpots, 4);
        spiderWeb.delBridge("yellow");
        assertEquals(false, spiderWeb.ok());
        numBridges = spiderWeb.bridges().length;
        numSpots = spiderWeb.spots().length;
        assertEquals(numBridges, 7);
        assertEquals(numSpots, 4);
    }

    @Test
    public void accordingDCShouldActWeak() {
        spiderWeb.addBridge("weak", "black", 382, 3);
        int numBridges = spiderWeb.bridges().length;
        assertEquals(numBridges, 7);
        spiderWeb.spiderSit(3);
        spiderWeb.spiderWalk(true);
        assertEquals(true, spiderWeb.ok());
        numBridges = spiderWeb.bridges().length;
        assertEquals(numBridges, 6);

        spiderWeb.addBridge("weak", "yellow", 320, 1);
        numBridges = spiderWeb.bridges().length;
        assertEquals(numBridges, 7);
        spiderWeb.spiderSit(4);
        spiderWeb.spiderWalk(true);
        assertEquals(true, spiderWeb.ok());
        numBridges = spiderWeb.bridges().length;
        assertEquals(numBridges, 6);
    }

    @Test
    public void accordingDCShouldActMobile() {
        spiderWeb.addBridge("mobile", "aquamarine", 185, 3);
        spiderWeb.spiderWalk(true);
        assertEquals(true, spiderWeb.ok());

        spiderWeb.addBridge("purple", 110, 3);
        spiderWeb.addBridge("mobile", "yellow", 65, 1);
        spiderWeb.spiderSit(1);
        spiderWeb.spiderWalk(true);
        assertEquals(true, spiderWeb.ok());
    }

    @Test
    public void accordingDCShouldActBouncy() {
        spiderWeb.addSpot("bouncy", "red", 3);
        spiderWeb.spiderSit(3);
        spiderWeb.spiderWalk(true);
        assertEquals(true, spiderWeb.ok());

        spiderWeb.addSpot("bouncy", "blue", 19);
        spiderWeb.spiderSit(19);
        spiderWeb.spiderWalk(true);
        assertEquals(true, spiderWeb.ok());
    }

    @Test
    public void accordingDCShouldActKiller() {
        spiderWeb.addSpot("killer", "red", 3);
        spiderWeb.spiderSit(3);
        spiderWeb.spiderWalk(true);
        assertEquals(true, spiderWeb.ok());

        spiderWeb.addSpot("killer", "blue", 19);
        spiderWeb.spiderSit(19);
        spiderWeb.spiderWalk(true);
        assertEquals(true, spiderWeb.ok());
    }

    @Test
    public void spiderWebAtest() {
        spiderWeb.makeVisible();
        spiderWeb.addBridge("weak", "yellow", 98, 4);
        spiderWeb.addBridge("weak", "aquamarine", 114, 3);
        spiderWeb.addSpot("bouncy", "red", 3);
        spiderWeb.spiderSit(5);
        spiderWeb.spiderWalk(true);

        int respuesta = JOptionPane.showConfirmDialog(null,
                "Teniendo en cuenta que los 2 primeros puentes son de tipo Weak; y el spot alcanzado es de tipo bouncy, el simulador funciona de manera adecuada?",
                "Confirmación", JOptionPane.YES_NO_OPTION);

        assertEquals(respuesta, JOptionPane.YES_OPTION);
    }

    @Test
    public void spiderWebAtest2() {
        spiderWeb.makeVisible();
        spiderWeb.addBridge("black", 110, 3);
        spiderWeb.addBridge("mobile", "yellow", 65, 1);
        spiderWeb.addBridge("transformer", "purple", 300, 2);
        spiderWeb.addBridge("fixed", "aquamarine", 371, 1);
        spiderWeb.addSpot("killer", "red", 1);
        spiderWeb.spiderWalk(true);
        spiderWeb.delSpot("purple");

        int respuesta = JOptionPane.showConfirmDialog(null,
                "Teniendo en cuenta que el primer puente es de tipo Mobile, el penultimo de tipo transformer (el cual se elimina al final) y el ultimo de tipo fixed; y el spot alcanzado es de tipo Killer, el simulador funciona de manera adecuada?",
                "Confirmación", JOptionPane.YES_NO_OPTION);

        assertEquals(respuesta, JOptionPane.YES_OPTION);
    }
}
