package test;
import spiderweb.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.JOptionPane;

/**
 * The test class SpiderWebTest for C4.
 *
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class SpiderWebTestC4 {
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
    public void spiderWebAtest()  throws InterruptedException  {
        spiderWeb.makeVisible();
        JOptionPane.showMessageDialog(null, "Los puentes y spots existentes son normales");
        Thread.sleep(2000);
        showMessageWithDelay("Se agrega un puente (lavender) tipo weak", 2000);
        spiderWeb.addBridge("weak", "lavender", 98, 4);
        Thread.sleep(2000);
        showMessageWithDelay("Se agrega un puente (aguamarina) tipo weak", 2000);
        spiderWeb.addBridge("weak", "aquamarine", 114, 3);
        Thread.sleep(2000);
        showMessageWithDelay("Se agrega un spot (rojo) tipo bouncy", 2000);
        spiderWeb.addSpot("bouncy", "red", 3);
        Thread.sleep(2000);
        spiderWeb.spiderSit(5);
        spiderWeb.spiderWalk(true);
        Thread.sleep(2000);

        int respuesta = JOptionPane.showConfirmDialog(null,
                "Teniendo en cuenta las condiciones dada, considera que el simulador funciona de manera adecuada?",
                "Confirmación", JOptionPane.YES_NO_OPTION);

        assertEquals(respuesta, JOptionPane.YES_OPTION);

        spiderWeb.makeInvisible();
    }

    @Test
    public void spiderWebAtest2() throws InterruptedException {
        spiderWeb.makeVisible();
        JOptionPane.showMessageDialog(null, "Los puentes y spots existentes son normales");
        Thread.sleep(200);
        showMessageWithDelay("Se agrega un puente normal (negro)", 2000);
        spiderWeb.addBridge("black", 110, 3);
        Thread.sleep(2000);
        showMessageWithDelay("Se agrega un puente (amarillo) tipo mobile", 2000);
        spiderWeb.addBridge("mobile", "yellow", 65, 1);
        Thread.sleep(2000);
        showMessageWithDelay("Se agrega un puente (morado) tipo transformer", 2000);
        spiderWeb.addBridge("transformer", "purple", 300, 2);
        Thread.sleep(2000);
        showMessageWithDelay("Se agrega un puente (aguamarina) tipo fixed", 2000);
        spiderWeb.addBridge("fixed", "aquamarine", 371, 1);
        Thread.sleep(2000);
        showMessageWithDelay("Se agrega un spot (rojo) tipo killer", 2000);
        spiderWeb.addSpot("killer", "red", 1);
        Thread.sleep(2000);
        spiderWeb.spiderWalk(true);
        Thread.sleep(2000);
        showMessageWithDelay("Se elimina el spot amarillo (normal)", 2000);
        spiderWeb.delSpot("yellow");
        Thread.sleep(2000);
        showMessageWithDelay("Se elimina el puente morado (transformer)", 2000);
        spiderWeb.delBridge("purple");
        Thread.sleep(2000);
        showMessageWithDelay("Se elimina el puente aguamarina (fixed)", 2000);
        spiderWeb.delBridge("aquamarine");
        Thread.sleep(2000);

        int respuesta = JOptionPane.showConfirmDialog(null,
                "Teniendo en cuenta las condiciones dada, considera que el simulador funciona de manera adecuada?",
                "Confirmación", JOptionPane.YES_NO_OPTION);

        assertEquals(respuesta, JOptionPane.YES_OPTION);

        spiderWeb.makeInvisible();
    }

    private void showMessageWithDelay(String message, int delay) throws InterruptedException {
        JOptionPane.showMessageDialog(null, message);
        Thread.sleep(delay);
    }
}