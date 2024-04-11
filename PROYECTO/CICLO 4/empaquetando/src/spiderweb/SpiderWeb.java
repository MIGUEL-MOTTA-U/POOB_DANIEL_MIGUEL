package spiderweb;
import javax.swing.JOptionPane;

/**
 * Create the simulation
 * 
 * @author Daniel Diaz && Miguel Motta
 * 
 * @version (2.3.3.24)
 */
public class SpiderWeb {
    private Web web;
    private boolean isVisible;

    /**
     * Constructor for objects of class SpiderWeb
     */
    public SpiderWeb(int strands, int radio) {
        this.isVisible = true;
        web = new Web(strands, radio, this.isVisible);
    }

    /**
     * Constructor for creating a SpiderWeb object.
     * 
     * @param strands  The total number of strands in the spider web.
     * @param favorite The index of the spider's favorite spot on the web.
     * @param bridges  A 2D array representing the connections between spots on the
     *                 web.
     *                 Each element is an array of two integers representing the
     *                 indices of the spots connected by a bridge.
     */
    public SpiderWeb(int strands, int favorite, int[][] bridges) {
        this.isVisible = true;
        web = new Web(strands, 350, this.isVisible);
        web.addSpot(favorite);
        for (int i = 0; i < bridges.length; i++) {
            web.addBridge(bridges[i][0], bridges[i][1]);
        }
    }

    /**
     * Add a strand to the SpiderWeb
     */
    public void addStrand() throws Exception {
        web.addOneStrand();
    }

    /**
     * Expand the radius of the web.
     * 
     * @param percentage, the expansion factor.
     */
    public void enlarge(int percentage) throws Exception {
        web.enlarge(percentage);
    }

    /**
     * Add a bridge to the web
     * 
     * @param color       The color of the bridge. Valid colors are "red", "black",
     *                    "blue", "yellow", "green", "magenta", "gray", "orange",
     *                    "purple", "brown", "cyan",
     *                    "darkgreen", "aquamarine", "lavender" and "salmon"
     * @param distance    the distance from the center to the bridge
     * @param firstStrand the strand where the bridge begin
     */
    public void addBridge(String color, int distance, int firstStrand) {
        web.addBridge("normal", color, distance, firstStrand);
    }

    /**
     * Add a bridge of a specific type to the web
     * 
     * @param type        the type of the bridge. Valid tipes are "normal", "fixed",
     *                    "transformer", "weak" and "mobile"
     * @param color       The color of the bridge. Valid colors are "red", "black",
     *                    "blue", "yellow", "green", "magenta", "gray", "orange",
     *                    "purple", "brown", "cyan",
     *                    "darkgreen", "aquamarine", "lavender" and "salmon"
     * @param distance    the distance from the center to the bridge
     * @param firstStrand the strand where the bridge begin
     */
    public void addBridge(String type, String color, int distance, int firstStrand) {
        web.addBridge(type, color, distance, firstStrand);
    }

    /**
     * Change the distance of the bridge
     * 
     * @param color    The color of the bridge. Valid colors are "red", "black",
     *                 "blue", "yellow", "green", "magenta", "gray", "orange",
     *                 "purple", "brown", "cyan",
     *                 "darkgreen", "aquamarine", "lavender" and "salmon"
     * @param distance the new distance from the center to the bridge
     */
    public void relocateBridge(String color, int distance) {
        web.relocateBridge(color, distance);
    }

    /**
     * Remove a bridge
     * 
     * @param color The color of the bridge. Valid colors are "red", "black",
     *              "blue", "yellow", "green", "magenta", "gray", "orange",
     *              "purple", "brown", "cyan",
     *              "darkgreen", "aquamarine", "lavender" and "salmon"
     */
    public void delBridge(String color) {
        web.delBridge(color);
    }

    /**
     * Add a spot to the web
     * 
     * @param color  The color of the spot. Valid colors are "red", "black", "blue",
     *               "yellow", "green", "magenta", "gray", "orange", "purple",
     *               "brown", "cyan",
     *               "darkgreen", "aquamarine", "lavender" and "salmon"
     * @param strand where the spot is locate
     */
    public void addSpot(String color, int strand) {
        web.addSpot("normal", color, strand);
    }

    /**
     * Add a spot of a specific type to the web
     * 
     * @param type   the type of the spot. Valid tipes are "killer" and "bouncy"
     * @param color  The color of the spot. Valid colors are "red", "black", "blue",
     *               "yellow", "green", "magenta", "gray", "orange", "purple",
     *               "brown", "cyan",
     *               "darkgreen", "aquamarine", "lavender" and "salmon"
     * @param strand where the spot is locate
     */
    public void addSpot(String type, String color, int strand) {
        web.addSpot(type, color, strand);
    }

    /**
     * Remove a spot from the web
     * 
     * @param color The color of the spot. Valid colors are "red", "black", "blue",
     *              "yellow", "green", "magenta", "gray", "orange", "purple",
     *              "brown", "cyan",
     *              "darkgreen", "aquamarine", "lavender" and "salmon"
     */
    public void delSpot(String color) {
        web.delSpot(color);
    }

    /**
     * Seat the spider in the center with reference to a specific strand
     * 
     * @param strand the strand to wich the spider will sit
     */
    public void spiderSit(int strand) {
        web.spiderSit(strand);
    }

    /**
     * The spider walks through the strands and bridges
     * 
     * @param advance indicates wheter the spider should move
     */
    public void spiderWalk(boolean advance) {
        if (advance)
            web.spiderWalk();
    }

    /**
     * Return the path along wich the spider walked
     * 
     * @return the strands where the spider walked
     */
    public int[] spiderLastPath() {
        return web.spiderLastPath();
    }

    /**
     * Return the existing bridges in the web
     * 
     * @return the colors of the bridges in the web
     */
    public String[] bridges() {
        return web.bridges();
    }

    /**
     * Return the coordenates of the bridge, (Xo,Yo) & (Xf,Yf)
     * 
     * @param color the color of the bridge. Valid colors are "red", "black",
     *              "blue", "yellow", "green", "magenta", "gray", "orange",
     *              "purple", "brown", "cyan",
     *              "darkgreen", "aquamarine", "lavender" and "salmon"
     * @return the coordenates of the bridge
     */
    public double[] bridge(String color) {
        return web.bridge(color);
    }

    /**
     * Return the existing spots in the web
     * 
     * @return the colors of the spots in the web
     */
    public String[] spots() {
        return web.spots();
    }

    /**
     * Return the coordenates of the spot, (Xo,Yo) & (Xf,Yf)
     * 
     * @param color the color of the spot. Valid colors are "red", "black", "blue",
     *              "yellow", "green", "magenta", "gray", "orange", "purple",
     *              "brown", "cyan",
     *              "darkgreen", "aquamarine", "lavender" and "salmon"
     * @return the coordenates of the spot
     */
    public double[] spot(String color) {
        return web.spot(color);
    }

    /**
     * Return the spots that the spider can reach
     * 
     * @return the spots color that the spider can reach
     */
    public String[] reachableSpots() {
        return web.reachableSpots();
    }

    /**
     * Return the bridges that the spider didn't use
     * 
     * @return the bridges color that the spider didn't use
     */
    public String[] unusedBridges() {
        return web.unusedBridges();
    }

    /**
     * Make the objects visible. If it was already visible, do nothing.
     */
    public void makeVisible() {
        this.isVisible = true;
        web.makeVisible();
    }

    /**
     * Make the objects invisible. If it was already visible, do nothing.
     */
    public void makeInvisible() {
        web.makeInvisible();
        this.isVisible = false;
    }

    /**
     * Return the status of the simulation
     * 
     * @return TRUE, if an action could be completed. FALSE, otherwise
     */
    public boolean ok() {
        return web.ok();
    }

    /**
     * End the simulation
     */
    public void finish() {
        JOptionPane.showMessageDialog(null, "Gracias por usar nuestro simulador", "Simulacion finalizada",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
