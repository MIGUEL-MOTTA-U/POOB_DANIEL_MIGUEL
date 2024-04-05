import java.util.HashMap;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Random;
import java.util.Map;


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
    private boolean isVisible;
    private boolean ok;

    private Canvas canvas;

    /**
     * Constructor for objects of class Web
     */
    public Web(int strands, int radio, boolean isVisible){
        if(strands > 0){
            canvas = Canvas.getCanvas();
            this.strands = new HashMap<>();
            this.bridges = new HashMap<>();
            this.spots = new HashMap<>();
            this.numStrands = strands;
            this.radio = radio;
            this.isVisible = isVisible;
            this.ok = true;
            addStrands();
        }else{
            JOptionPane.showMessageDialog(null, "El numero de hebras debe ser mayor a 0");
            this.ok = false;
        }
    }
    
    /**
     * Add an strand to the web and update the relation of spots and bridges with their respective strands.
     */
    public void addOneStrand(){
        if(this.radio > 0 && this.numStrands > 0 && this.numStrands <= 360){
            this.strands.clear();
            this.numStrands ++;
            addStrands();
            recalculateWeb();
        } else {
                this.ok = false;
            }
    }
    
    /**
     * Expand the web by a given percentage.
     * @param percentage, the expansion relation
     */
    public void enlarge(int percentage){
        if(percentage >0){
            this.strands.clear();
            this.radio += percentage * radio/100;
            addStrands();
            recalculateWeb();
            this.ok = true;
        } else {
            this.ok = false;
        }
    }
    
    /**
     * Add a bridge to the web
     * @param   type        the type of the bridge. Valid tipes are "normal", "fixed", "transformer", "weak" and "mobile"
     * @param   color       The color of the bridge
     * @param   distance    the distance from the center to the bridge
     * @param   firstStrand the strand where the bridge begin
     */
    public void addBridge(String type, String color, int distance, int firstStrand){
        Bridge bridge = null;

        if (this.bridges.containsKey(color) || distance < 0 || firstStrand <= 0 || firstStrand > this.numStrands) {
            this.ok = false;
        } else {
            if (distance <= this.radio) {
                int finalStrand = (firstStrand == this.numStrands) ? 1 : firstStrand + 1;
                boolean contiguo = contiguosBridges(distance, firstStrand, finalStrand);
                if (!contiguo) {
                    if (type.equals("normal")) {
                        bridge = new Bridge(color, distance, firstStrand, finalStrand, this, this.isVisible);
                    } else if (type.equals("fixed")) {
                        bridge = new Fixed(color, distance, firstStrand, finalStrand, this, this.isVisible);
                    } else if (type.equals("transformer")) {
                        bridge = new Transformer(color, distance, firstStrand, finalStrand, this, this.isVisible);
                    } else if (type.equals("weak")) {
                        bridge = new Weak(color, distance, firstStrand, finalStrand, this, this.isVisible);
                    } else if (type.equals("mobile")) {
                        bridge = new Mobile(color, distance, firstStrand, finalStrand, this, this.isVisible);
                    }
                    bridge.addBridge(this.strands, firstStrand, finalStrand);
                    bridges.put(color, bridge);
                    this.ok = true;
                } else {
                    JOptionPane.showMessageDialog(null, "No pueden haber puentes contiguos");
                    this.ok = false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "La distancia no debe ser mayor al radio.");
                this.ok = false;
            }
        }
    }

    /**
     * Add a bridge to the web with a random color
     * @param   distance    the distance from the center to the bridge
     * @param   firstStrand the strand where the bridge begin
     */
    public void addBridge(int distance, int firstStrand){
        ArrayList<String> colors = canvas.getColors();
        Random random = new Random();
        String color = "";

        do{
            int indexRandom = random.nextInt(colors.size());
            color = colors.get(indexRandom);
        }while(this.spots.containsKey(color));

        addBridge("normal", color, distance, firstStrand);
    }

    /**
     * Change the distance of the bridge
     * @param   color       The color of the bridge
     * @param   distance    the new distance from the center to the bridge
     */
    public void relocateBridge(String color, int distance){
        if (bridges.containsKey(color) && this.radio >= distance && distance > 0){
            Bridge bridge = bridges.get(color);
            String type = bridgeType(bridge);
            delBridge(color);
            addBridge(type, color, distance, bridge.getInicialStrand());
            this.ok = true;
        } else{
            this.ok = false;
        }
    }
    
    /**
     * Remove a bridge
     * @param   color   The color of the bridge
     */
    public void delBridge(String color){
        Bridge bridge = bridges.get(color);
        if (bridge != null) {
            if (!(bridge instanceof Fixed)) bridges.remove(color);
            bridge.deleteBridge();   
            this.ok = true;
        } else {
            JOptionPane.showMessageDialog(null, "No existe un puente con ese color");
            this.ok = false;
        }
    }
    
    /**
     * Add a spot to the web.
     * @param   type     the type of the spot. Valid tipes are "killer" and "bouncy"  
     * @param   color   The color of the spot
     * @param   strand  where the spot is locate
     */
    public void addSpot(String type, String color, int strand){
        Spot spot = null;

        if(this.spots.containsKey(color) || strand <0 || strand > strands.size()){
            this.ok = false;
        }else{
            Strand selectedStrand = strands.get(strand);
            if (type.equals("normal")) {
                spot = new Spot(color, strand, this, selectedStrand.getBody().getX2(), selectedStrand.getBody().getY2(), this.isVisible);
            } else if (type.equals("killer")) {
                spot = new Killer(color, strand, this, selectedStrand.getBody().getX2(), selectedStrand.getBody().getY2(), this.isVisible);
            } else if (type.equals("bouncy")) {
                spot = new Bouncy(color, strand, this, selectedStrand.getBody().getX2(), selectedStrand.getBody().getY2(), this.isVisible);
            }
            spots.put(color, spot);
            this.ok = true;
        }
    }

    /**
     * Add a spot to the web with a random color
     * @param   strand  where the spot is locate
     */
    public void addSpot(int strand){
        ArrayList<String> colors = canvas.getColors();
        Random random = new Random();
        String color = "";
        do{
            int indexRandom = random.nextInt(colors.size());
            color = colors.get(indexRandom);
        }while(this.spots.containsKey(color));
        addSpot("normal", color, strand);
    }
    
    /**
     * Remove a spot from the web
     * @param   color   The color of the spot
     */
    public void delSpot(String color){
        Spot spot = spots.get(color);
        if(spot != null){
            spot.makeInvisible();
            spot = null;
            spots.remove(color);
            this.ok = true;
        }else{
            JOptionPane.showMessageDialog(null, "No existe un spot con ese color");
            this.ok = false;
        }
    }

    /**
     * Verify if there is a spot in the strand
     * @param strand    the strand to verify
     * @return  TRUE, there is a spot in the strand. FALSE, otherwise
     */
    public boolean spotInStrand(int strand){
        for (Spot spot : this.spots.values()) {
            if (spot.getStrand() == strand) {
                return true;
            }
        }
        return false;
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
            this.ok = true;
            return bridge.bridge();
        }else{
            JOptionPane.showMessageDialog(null, "No existe un puente con ese color");
            this.ok = false;
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
            this.ok = true;
            return spot.spot();
        }else{
            JOptionPane.showMessageDialog(null, "No existe un spot con ese color");
            this.ok = false;
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
     * Return the existing spots in the web
     * @return  a hashMap with the existing spots in the web
     */
    public HashMap<String, Spot> getSpots(){
        return this.spots;
    }

    /**
     * Returns the numbre of strands
     * @return   the number of strands on the web
     */
    public int getNumStrands(){
        return this.numStrands;
    }

    /**
     * Returns the value of the radio, the length.
     * @return   the radio of the web
     */
    public int getRadio(){
        return this.radio;
    }

    /**
     * Return the status of the web
     * @return  if an action could be completed
     */
    public boolean getOk(){
        return this.ok;
    }

    /**
     * Change the status of the web
     * @param   ok    the new status of the web
     */
    public void setOk(boolean ok){
        this.ok = ok;
    }
    
    /**
     * Make the objects visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        this.isVisible = true;
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
        this.isVisible = false;
    }

    /*
     * Create the web
     */
    private void addStrands(){
        double angle = Math.toRadians((double) 360 / this.numStrands);
        double angleStrand = 0;
        for(int i = 1; i <= this.numStrands; i++){
            Strand strand = new Strand(this.radio, angleStrand, this.isVisible);
            this.strands.put(i, strand);
            angleStrand -= angle;   
        }
    }

    /*
     * Recreates the web according to the new specifications.
     */
    private void recalculateWeb(){
        HashMap<String, Bridge> copyBridges = new HashMap<>(this.bridges);
        HashMap<String, Spot> copySpots = new HashMap<>(this.spots);
        this.spots.clear();
        this.bridges.clear();

        for (Map.Entry<String, Bridge> element : copyBridges.entrySet()){
            Bridge b = element.getValue();
            String type = bridgeType(b);

            this.addBridge(type, b.getColor(), b.getDistance(), b.getInicialStrand());
        }

        for (Map.Entry<String, Spot> element : copySpots.entrySet()){
            Spot s = element.getValue();
            String type = spotType(s);
            this.addSpot(type, s.getColor(), s.getStrand());
        }
    }

    /*
     * Verify if exists contiguos bridges.
     */
    private boolean contiguosBridges(int distance, int firstStrand, int finalStrand){
        for(Bridge b: bridges.values()){
            int bDistance = b.getDistance();
            int bInicialStrand = b.getInicialStrand();
            int bFinalStrand = b.getFinalStrand();

            if((bDistance == distance) && ((bInicialStrand == firstStrand || bInicialStrand == finalStrand) ||
            (bFinalStrand == firstStrand || bFinalStrand == finalStrand))){
                return true;
            }
        }

        return false;
    }

    /*
     * Return the type of the bridge
     */
    private String bridgeType(Bridge bridge){
        String type; 

        if (bridge instanceof Fixed) {
            type = "fixed";
        } else if (bridge instanceof Transformer) {
            type = "transformer";
        } else if (bridge instanceof Weak) {
            type = "weak";
        } else if (bridge instanceof Mobile) {
            type = "mobile";
        } else {
            type = "normal";
        }

        return type;
    }

    /*
    * Return the type of the bridge
    */
    private String spotType(Spot spot){
        String type; 

        if (spot instanceof Killer) {
            type = "killer";
        } else if (spot instanceof Bouncy) {
            type = "bouncy";
        } else {
            type = "normal";
        }

        return type;
    }
}
