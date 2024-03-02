import java.util.HashMap;
import javax.swing.JOptionPane;


/**
 * Create the simulation
 * 
 * @author Daniel Diaz && Miguel Motta
 * 
 * @version (a version number or a date)
 */
public class SpiderWeb
{
    private Web web;
    private Spider spider;

    /**
     * Constructor for objects of class SpiderWeb
     */
    public SpiderWeb(int strands, int radio){
        web = new Web(strands, radio);
        spider = new Spider();
    }

    public SpiderWeb(int strands, int favorite, int[][] bridges){
        web = new Web(strands, 300);
        web.addSpot(favorite);
        for(int i = 0; i < bridges.length; i++){
            web.addBridge(bridges[i][0], bridges[i][1]);
        }
        spider = new Spider();
    }

    /**
     * Add a bridge to the web
     * @param   color       The color of the bridge. Valid colors are "red", "yellow", "blue", "green", "magenta", "black", "gray", "orange", "purple", "brown",
     * "cyan" and "darkgreen
     * @param   distance    the distance from the center to the bridge
     * @param   firstStrand the strand where the bridge begin
     */
    public void addBridge(String color, int distance, int firstStrand){
        web.addBridge(color, distance, firstStrand);
    }
    
    /**
     * Change the distance of the bridge
     * @param   color       The color of the bridge. Valid colors are "red", "yellow", "blue", "green", "magenta", "black", "gray", "orange", "purple", "brown",
     * "cyan" and "darkgreen
     * @param   distance    the new distance from the center to the bridge
     */
    public void relocateBridge(String color, int distance){
        web.relocateBridge(color, distance);
    }
    
    /**
     * Remove a bridge
     * @param   color   The color of the bridge. Valid colors are "red", "yellow", "blue", "green", "magenta", "black", "gray", "orange", "purple", "brown",
     * "cyan" and "darkgreen
     */
    public void delBridge(String color){
        web.delBridge(color);
    }
    
    /**
     * Add a spot to the web
     * @param   color   The color of the spot. Valid colors are "red", "yellow", "blue", "green", "magenta", "black", "gray", "orange", "purple", "brown",
     * "cyan" and "darkgreen
     * @param   strand  where the spot is locate
     */
    public void addSpot(String color, int strand){
        web.addSpot(color, strand);
    }
    
    /**
     * Remove a spot from the web
     * @param   color   The color of the spot. Valid colors are "red", "yellow", "blue", "green", "magenta", "black", "gray", "orange", "purple", "brown",
     * "cyan" and "darkgreen
     */
    public void delSpot(String color){
        web.delSpot(color);
    }
    
    /**
     * Seat the spider in the center with reference to a specific strand
     * @param   strand  the strand to wich the spider will refer
     */
    public void spiderSit(int strand){
        spider.spiderSit(strand);
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
     * @param   color   the color of the bridge. Valid colors are "red", "yellow", "blue", "green", "magenta", "black", "gray", "orange", "purple", "brown",
     * "cyan" and "darkgreen
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
     * @param   color   the color of the spot. Valid colors are "red", "yellow", "blue", "green", "magenta", "black", "gray", "orange", "purple", "brown",
     * "cyan" and "darkgreen
     * @return  the coordenates of the spot
     */
    public double[] spot(String color){
        return web.spot(color);
    }

    public String[] unusedBridges(){
        HashMap<String, Bridge> bridges = web.getBridges();
        return spider.unusedBridges(bridges);
    }
    
    /**
     * Make the objects visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        web.makeVisible();
        spider.makeVisible();
    }
    
    /**
     * Make the objects invisible. If it was already visible, do nothing.
     */
    public void makeInvisible(){
        web.makeInvisible();
        spider.makeInvisible();
    }
    
    /**
     * End the simulation
     */
    public void finish(){
        JOptionPane.showMessageDialog(null, "Gracias por usar nuestro simulador", "Simulacion finalizada", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
