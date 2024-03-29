import java.util.HashMap;
import javax.swing.JOptionPane;


/**
 * Create the simulation
 * 
 * @author Daniel Diaz && Miguel Motta
 * 
 * @version (2.3.3.24)
 */
public class SpiderWeb
{
    private Web web;
    private Spider spider;
    private boolean isVisible;

    /**
     * Constructor for objects of class SpiderWeb
     */
    public SpiderWeb(int strands, int radio){
        this.isVisible = true;
        web = new Web(strands, radio, this.isVisible);
        spider = new Spider(this.isVisible);
    }
    
    /**
     * Constructor for creating a SpiderWeb object.
     * @param strands   The total number of strands in the spider web.
     * @param favorite  The index of the spider's favorite spot on the web.
     * @param bridges   A 2D array representing the connections between spots on the web. 
     * Each element is an array of two integers representing the indices of the spots connected by a bridge.
     */
    public SpiderWeb(int strands, int favorite, int[][] bridges){
        this.isVisible = true;
        web = new Web(strands, 350, this.isVisible);
        web.addSpot(favorite);
        for(int i = 0; i < bridges.length; i++){
            web.addBridge(bridges[i][0], bridges[i][1]);
        }
        spider = new Spider(this.isVisible);
    }
    
    /**
     * Returns the web of the Spider web.
     * @return the web of Spider Web
     */
    public Web getWeb(){
        return this.web;
    }
    
    /**
     * This Method adds an strand to the SpiderWeb
     */
    public void addStrand(){
        spider = new Spider(this.isVisible);
        web.addOneStrand();
    }
    
    /**
     * Method to expand the radius of the web.
     * @param percentage, the expansion factor.
     */
    public void enlarge(int percentage){
        web.enlarge(percentage);
        if (spider.getDistance() != 0){
            this.spiderWalk(true);
        }
    }
    
    
    /**
     * Add a bridge to the web
     * @param   color       The color of the bridge. Valid colors are "red", "black", "blue", "yellow", "green", "magenta", "gray", "orange", "purple", "brown", "cyan", 
     * "darkgreen", "aquamarine", "lavender" and "salmon"
     * @param   distance    the distance from the center to the bridge
     * @param   firstStrand the strand where the bridge begin
     */
    public void addBridge(String color, int distance, int firstStrand){
        web.addBridge(color, distance, firstStrand);
    }
    
    /**
     * Change the distance of the bridge
     * @param   color       The color of the bridge. Valid colors are "red", "black", "blue", "yellow", "green", "magenta", "gray", "orange", "purple", "brown", "cyan", 
     * "darkgreen", "aquamarine", "lavender" and "salmon"
     * @param   distance    the new distance from the center to the bridge
     */
    public void relocateBridge(String color, int distance){
        web.relocateBridge(color, distance);
    }
    
    /**
     * Remove a bridge
     * @param   color   The color of the bridge. Valid colors are "red", "black", "blue", "yellow", "green", "magenta", "gray", "orange", "purple", "brown", "cyan", 
     * "darkgreen", "aquamarine", "lavender" and "salmon"
     */
    public void delBridge(String color){
        web.delBridge(color);
    }
    
    /**
     * Add a spot to the web
     * @param   color   The color of the spot. Valid colors are "red", "black", "blue", "yellow", "green", "magenta", "gray", "orange", "purple", "brown", "cyan", 
     * "darkgreen", "aquamarine", "lavender" and "salmon"
     * @param   strand  where the spot is locate
     */
    public void addSpot(String color, int strand){
        web.addSpot(color, strand);
    }
    
    /**
     * Remove a spot from the web
     * @param   color   The color of the spot. Valid colors are "red", "black", "blue", "yellow", "green", "magenta", "gray", "orange", "purple", "brown", "cyan", 
     * "darkgreen", "aquamarine", "lavender" and "salmon"
     */
    public void delSpot(String color){
        web.delSpot(color);
    }
    
    /**
     * Seat the spider in the center with reference to a specific strand
     * @param   strand  the strand to wich the spider will refer
     */
    public void spiderSit(int strand){
        int numStrands = web.getNumStrands();
        spider.spiderSit(strand, numStrands);
    }
    
    /**
     * The spider walks through the strands and bridges
     * @param   advance     indicates wheter the spider should move
     */
    public void spiderWalk(boolean advance){
        if(advance == true){
            HashMap<Integer, Strand> strands = web.getStrands();
            HashMap<String, Bridge> bridges = web.getBridges();
            spider.spiderWalk(strands, bridges);
        }
    }
    
    /**
     * Return the path along wich the spider walked
     * @return  the strands where the spider walked
     */
    public int[] spiderLastPath(){
        return spider.getLastPath();
    }
    
    /**
     * Return the existing bridges in the web
     * @return  the colors of the bridges in the web
     */
    public String[] bridges(){
        return web.bridges();
    }
    
    /**
     * Return the coordenates of the bridge, (Xo,Yo) & (Xf,Yf)
     * @param   color   the color of the bridge. Valid colors are "red", "black", "blue", "yellow", "green", "magenta", "gray", "orange", "purple", "brown", "cyan", 
     * "darkgreen", "aquamarine", "lavender" and "salmon"
     * @return  the coordenates of the bridge
     */
    public double[] bridge(String color){
        return web.bridge(color);
    }
    
    /**
     * Return the existing spots in the web
     * @return  the colors of the spots in the web
     */
    public String[] spots(){
        return web.spots();
    }
    
    /**
     * Return the coordenates of the apot, (Xo,Yo) & (Xf,Yf)
     * @param   color   the color of the spot. Valid colors are "red", "black", "blue", "yellow", "green", "magenta", "gray", "orange", "purple", "brown", "cyan", 
     * "darkgreen", "aquamarine", "lavender" and "salmon"
     * @return  the coordenates of the spot
     */
    public double[] spot(String color){
        return web.spot(color);
    }

    /**
     * Return the spots that the spider can reach
     * @return  the spots color that the spider can reach
     */
    public String[] reachableSpots(){
        HashMap<String, Bridge> bridges = web.getBridges();
        HashMap<String, Spot> spots = web.getSpots();
        return spider.reachableSpots(bridges, spots);
    }

    /**
     * Return the bridges that the spider didn't use
     * @return  the bridges color that the spider didn't use
     */
    public String[] unusedBridges(){
        HashMap<String, Bridge> bridges = web.getBridges();
        return spider.unusedBridges(bridges);
    }
    
    /**
     * Make the objects visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        this.isVisible = true;
        web.makeVisible();
        spider.makeVisible();
    }
    
    /**
     * Make the objects invisible. If it was already visible, do nothing.
     */
    public void makeInvisible(){
        web.makeInvisible();
        spider.makeInvisible();
        this.isVisible = false;
    }

    /**
     * Return the status of the simulation
     * @return  if an action could be completed
     */
    public boolean ok(){
        boolean webOk = web.getOk();
        boolean spiderOk = spider.getOk();
        web.setOk(true);
        spider.setOk(true);
        return (webOk == false || spiderOk == false) ? false : true ;
    }
    
    /**
     * End the simulation
     */
    public void finish(){
        JOptionPane.showMessageDialog(null, "Gracias por usar nuestro simulador", "Simulacion finalizada", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
