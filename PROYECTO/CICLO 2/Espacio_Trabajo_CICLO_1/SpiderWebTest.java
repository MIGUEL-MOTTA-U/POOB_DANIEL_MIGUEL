import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The test class SpiderWebTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SpiderWebTest{
    private static SpiderWeb spiderWeb;
    
    @BeforeClass
    public static void beforeClass(){
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
    
    @Before
    public void before(){
        spiderWeb.spiderSit(1);
    }
    
    @Test
    public void accordingDCShouldReachSpot(){
        String[] expected = {"aquamarine"};
        assertArrayEquals(spiderWeb.reachableSpots(), expected);
        spiderWeb.spiderSit(2);
        expected = new String[]{"yellow"};
        assertArrayEquals(spiderWeb.reachableSpots(), expected);
        spiderWeb.spiderSit(3);
        expected = new String[]{null};
        assertArrayEquals(spiderWeb.reachableSpots(), expected);
        
        expected = new String[]{"brown"};
        assertNotEquals(spiderWeb.reachableSpots(), expected);
        spiderWeb.spiderSit(19);
        expected = new String[]{"yellow"};
        assertNotEquals(spiderWeb.reachableSpots(), expected);
        expected = new String[]{null};
        assertNotEquals(spiderWeb.reachableSpots(), expected);
    }
    
    @Test
    public void accordingDCShouldNotUseBridges(){
       String[] expected = {"orange", "green", "cyan"};
       spiderWeb.spiderWalk(true);
       assertArrayEquals(spiderWeb.unusedBridges(), expected);
       spiderWeb.spiderSit(20);
       expected = new String[]{"red", "orange", "magenta", "green", "blue"};
       spiderWeb.spiderWalk(true);
       assertArrayEquals(spiderWeb.unusedBridges(), expected);
       spiderWeb.spiderSit(4);
       expected = new String[]{"red", "blue", "cyan"};
       spiderWeb.spiderWalk(true);
       assertArrayEquals(spiderWeb.unusedBridges(), expected);
       
       spiderWeb.spiderSit(3);
       expected = new String[]{"red", "orange"};
       spiderWeb.spiderWalk(true);
       assertNotEquals(spiderWeb.unusedBridges(), expected);
       spiderWeb.spiderSit(19);
       expected = new String[]{"red", "orange", "magenta", "green"};
       spiderWeb.spiderWalk(true);
       assertNotEquals(spiderWeb.unusedBridges(), expected);
    }
}
