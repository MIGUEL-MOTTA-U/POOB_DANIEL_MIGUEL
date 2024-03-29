import java.util.HashMap;
import java.awt.geom.Line2D;
import javax.swing.JOptionPane;

/**
 * Create the web.
 * 
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class Web
{
    private HashMap<Integer, Strand> strands;
    private HashMap<String, Bridge> bridges;
    private HashMap<String, Spot> spots;
    private int numStrands;
    private int radio;
    
    private Canvas canvas;

    /**
     * Constructor for objects of class Web
     */
    public Web(int strands, int radio){
        canvas = Canvas.getCanvas();
        this.strands = new HashMap<>();
        this.bridges = new HashMap<>();
        this.spots = new HashMap<>();
        this.numStrands = strands;
        this.radio = radio;
        
        addStrands();
    }

    /*
     * Create the web
     */
    private void addStrands(){
        double angle = Math.toRadians((double) 360 / this.numStrands);
        double angleStrand = 0;
        for(int i = 0; i < this.numStrands; i++){
            Strand strand = new Strand(this.radio, angleStrand);
            this.strands.put(i, strand);
            angleStrand += angle;
        }
    }
    
    /**
     * Add a bridge to the web
     * @param   color       The color of the bridge
     * @param   distance    the distance from the center to the bridge
     * @param   firstStrand the strand where the bridge begin
     */
    public void addBridge(String color, int distance, int firstStrand){
        if(this.bridges.containsKey(color)){
            JOptionPane.showMessageDialog(null, "Ya existe un puente con el mismo color");
        }else{
            if(distance <= this.radio){
                int finalStrand = (firstStrand == this.numStrands - 1) ? 0 : firstStrand + 1;
                Bridge bridge = new Bridge(color, distance, firstStrand, finalStrand);
                bridge.addBridge(this.strands, firstStrand, finalStrand);
                bridges.put(color, bridge);
            }else{
                JOptionPane.showMessageDialog(null, "La distancia no debe ser mayor al radio.");
            }
        }
    }
    
    /**
     * Change the distance of the bridge
     * @param   color       The color of the bridge
     * @param   distance    the new distance from the center to the bridge
     */
    public void relocateBridge(String color, int distance){
        Bridge bridge = bridges.get(color);
        int firstStrand = bridge.getInicialStrand();
        delBridge(color);
        addBridge(color, distance, firstStrand);
    }
    
    /**
     * Remove a bridge
     * @param   color   The color of the bridge
     */
    public void delBridge(String color){
        Bridge bridge = bridges.get(color);
        bridge.makeInvisible();
        bridge = null;
        bridges.remove(color);
    }
    
    /**
     * Add a spot to the web
     * @param   color   The color of the spot
     * @param   strand  where the spot is locate
     */
    public void addSpot(String color, int strand){
        if(this.spots.containsKey(color)){
            JOptionPane.showMessageDialog(null, "Ya existe un spot con el mismo color");
        }else{
            Strand selectedStrand = strands.get(strand);
            Spot spot = new Spot(color, strand, selectedStrand.getBody().getX2(), selectedStrand.getBody().getY2());
            spots.put(color, spot);
        }
    }
    
    /**
     * Remove a spot from the web
     * @param   color   The color of the spot
     */
    public void delSpot(String color){
        Spot spot = spots.get(color);
        spot.makeInvisible();
        spot = null;
        spots.remove(color);
    }
    
    /**
     * Return the existing bridges in the web
     * @return  the colors of the bridges in the web
     */
    public String[] bridges(){
        String[] output = new String[bridges.size()];
        int i = 0;
        for(String color : bridges.keySet()){
            output[i] = color;
            i += 1;
        }
        return output; 
    }
    
    /**
     * Return the coordenates of the bridge, (Xo,Yo) & (Xf,Yf)
     * @param   color   the color of the bridge
     * @return  the coordenates of the bridge
     */
    public double[] bridge(String color){
        Bridge bridge = bridges.get(color);
        if(bridge != null){
            return bridge.bridge();
        }else{
            JOptionPane.showMessageDialog(null, "No existe un puente con ese color");
            return null;
        }
    }
    
    /**
     * Return the existing spots in the web
     * @return  the colors of the spots in the web
     */
    public String[] spots(){
        String[] output = new String[spots.size()];
        int i = 0;
        for(String color : spots.keySet()){
            output[i] = color;
            i += 1;
        }
        return output;
    }
    
    /**
     * Return the coordenates of the apot, (Xo,Yo) & (Xf,Yf)
     * @param   color   the color of the spot
     * @return  the coordenates of the spot
     */
    public double[] spot(String color){
        Spot spot = spots.get(color);
        if(spot != null){
            return spot.spot();
        }else{
            JOptionPane.showMessageDialog(null, "No existe un spot con ese color");
            return null;
        }
    }
    
    /**
     * Return the existing strands in the web
     * @return  a hashMap with the existing strands in the web
     */
    public HashMap<Integer, Strand> getStrands(){
        return this.strands;
    }
    
    /**
     * Return the existing bridges in the web
     * @return  a hashMap with the existing bridges in the web
     */
    public HashMap<String, Bridge> getBridges(){
        return this.bridges;
    }
    
    /**
     * Make the objects visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        for(Strand strand : strands.values()){
            strand.makeVisible();
        }
        for(Bridge bridge : bridges.values()){
            bridge.makeVisible();
        }
        for(Spot spot : spots.values()){
            spot.makeVisible();
        }
    }
    
    /**
     * Make the objects invisible. If it was already visible, do nothing.
     */
    public void makeInvisible(){
        for(Strand strand : strands.values()){
            strand.makeInvisible();
        }
        for(Bridge bridge : bridges.values()){
            bridge.makeInvisible();
        }
        for(Spot spot : spots.values()){
            spot.makeInvisible();
        }
    }
}
