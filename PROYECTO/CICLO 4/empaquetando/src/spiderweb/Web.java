package spiderweb;
import shapes.*;
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
public class Web {
    private Spider spider;
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
    public Web(int strands, int radio, boolean isVisible) {
        canvas = Canvas.getCanvas();
        this.strands = new HashMap<>();
        this.bridges = new HashMap<>();
        this.spots = new HashMap<>();
        this.numStrands = strands;
        this.radio = radio;
        this.isVisible = isVisible;
        this.ok = true;
        addStrands();
        this.spider = new Spider(1, isVisible);
    }

    /**
     * Add an strand to the web and update the relation of spots and bridges with
     * their respective strands.
     */
    public void addOneStrand() throws Exception {
        makeInvisible();
        HashMap<String, Bridge> oldBridges = this.bridges;
        HashMap<String, Spot> oldSpots = this.spots;

        this.strands.clear();
        this.numStrands++;
        addStrands();
        recalculateWeb();

        this.ok = this.bridges.equals(oldBridges) && this.spots.equals(oldSpots);
        makeVisible();
    }

    /**
     * Expand the web by a given percentage.
     * 
     * @param percentage, the expansion relation
     */
    public void enlarge(int percentage) throws Exception {
        if (percentage > 0) {
            makeInvisible();
            HashMap<String, Bridge> oldBridges = this.bridges;
            HashMap<String, Spot> oldSpots = this.spots;

            this.strands.clear();
            this.radio += percentage * radio / 100;
            addStrands();
            recalculateWeb();

            this.ok = this.bridges.equals(oldBridges) && this.spots.equals(oldSpots);
            makeVisible();
        } else {
            this.ok = false;
        }
    }

    /**
     * Add a bridge to the web
     * 
     * @param type        the type of the bridge. Valid tipes are "normal", "fixed",
     *                    "transformer", "weak" and "mobile"
     * @param color       The color of the bridge
     * @param distance    the distance from the center to the bridge
     * @param firstStrand the strand where the bridge begin
     */
    public void addBridge(String type, String color, int distance, int firstStrand) {
        if (this.bridges.containsKey(color) || distance < 0 || firstStrand <= 0 || firstStrand > this.numStrands) {
            this.ok = false;
        } else {
            if (distance <= this.radio) {
                int finalStrand = (firstStrand == this.numStrands) ? 1 : firstStrand + 1;
                boolean contiguo = contiguosBridges(distance, firstStrand, finalStrand);
                if (!contiguo) {
                    Bridge bridge = createBridge(type, color, distance, firstStrand, finalStrand);
                    bridge.addBridge(this.strands);
                    bridges.put(color, bridge);
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
     * 
     * @param distance    the distance from the center to the bridge
     * @param firstStrand the strand where the bridge begin
     */
    public void addBridge(int distance, int firstStrand) {
        ArrayList<String> colors = canvas.getColors();
        Random random = new Random();
        String color = "";

        do {
            int indexRandom = random.nextInt(colors.size());
            color = colors.get(indexRandom);
        } while (this.spots.containsKey(color));

        addBridge("normal", color, distance, firstStrand);
    }

    /**
     * Change the distance of the bridge
     * 
     * @param color    The color of the bridge
     * @param distance the new distance from the center to the bridge
     */
    public void relocateBridge(String color, int distance) {
        if (bridges.containsKey(color) && this.radio >= distance && distance > 0) {
            Bridge bridge = bridges.get(color);
            String type = bridge.getClass().getSimpleName().toLowerCase();
            type = (type.equals("normalbridge")) ? "normal" : type;
            bridge.makeInvisible();
            removeBridge(color);;
            addBridge(type, color, distance, bridge.getInicialStrand());
            this.ok = true;
        } else {
            this.ok = false;
        }
    }

    /**
     * Remove a bridge
     * 
     * @param color The color of the bridge
     */
    public void delBridge(String color) {
        Bridge bridge = bridges.get(color);
        if (bridge != null) {
            this.ok = true;
            bridge.deleteBridge();
        } else {
            JOptionPane.showMessageDialog(null, "No existe un puente con ese color");
            this.ok = false;
        }
    }

    /**
     * Add a spot to the web.
     * 
     * @param type   the type of the spot. Valid tipes are "killer" and "bouncy"
     * @param color  The color of the spot
     * @param strand where the spot is locate
     */
    public void addSpot(String type, String color, int strand) {
        if (this.spots.containsKey(color) || strand < 0 || strand > this.numStrands) {
            this.ok = false;
        } else {
            Strand selectedStrand = strands.get(strand);
            Spot spot = this.createSpot(type, color, strand, selectedStrand);
            spot.addSpot(strands);
            this.spots.put(color, spot);
        }
    }

    /**
     * Add a spot to the web with a random color
     * 
     * @param strand where the spot is locate
     */
    public void addSpot(int strand) {
        ArrayList<String> colors = canvas.getColors();
        Random random = new Random();
        String color = "";

        do {
            int indexRandom = random.nextInt(colors.size());
            color = colors.get(indexRandom);
        } while (this.spots.containsKey(color));

        addSpot("normal", color, strand);
    }

    /**
     * Remove a spot from the web
     * 
     * @param color The color of the spot
     */
    public void delSpot(String color) {
        Spot spot = spots.get(color);
        if (spot != null) {
            this.ok = true;
            spot.deleteSpot();
        } else {
            JOptionPane.showMessageDialog(null, "No existe un spot con ese color");
            this.ok = false;
        }
    }

    /**
     * Seat the spider in the center with reference to a specific strand
     * 
     * @param strand the strand to wich the spider will refer
     */
    public void spiderSit(int strand) {
        if (strand > 0 && strand <= numStrands) {
            this.spider.makeInvisible();
            this.spider = new Spider(strand, this.isVisible);
            this.ok = true;
        } else {
            JOptionPane.showMessageDialog(null, "Hebra fuera del rango");
            this.ok = false;
        }
    }

    /**
     * The spider walks through the strands and bridges
     */
    public void spiderWalk() {
        spider.spiderWalk(this.strands, this.bridges);
        Spot spot = this.spiderInspot();
        if (spot != null)
            spot.act();
    }

    /**
     * Return the path along wich the spider walked
     * 
     * @return the strands where the spider walked
     */
    public int[] spiderLastPath() {
        return spider.getLastPath();
    }

    /**
     * Return the existing bridges in the web
     * 
     * @return the colors of the bridges in the web
     */
    public String[] bridges() {
        String[] output = new String[bridges.size()];
        int i = 0;
        for (String color : bridges.keySet()) {
            output[i] = color;
            i += 1;
        }
        return output;
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
        Bridge bridge = bridges.get(color);
        if (bridge != null) {
            this.ok = true;
            return bridge.bridge();
        } else {
            JOptionPane.showMessageDialog(null, "No existe un puente con ese color");
            this.ok = false;
            return null;
        }
    }

    /**
     * Return the existing spots in the web
     * 
     * @return the colors of the spots in the web
     */
    public String[] spots() {
        String[] output = new String[spots.size()];
        int i = 0;
        for (String color : spots.keySet()) {
            output[i] = color;
            i += 1;
        }
        return output;
    }

    /**
     * Return the coordenates of the apot, (Xo,Yo) & (Xf,Yf)
     * 
     * @param color the color of the spot. Valid colors are "red", "black", "blue",
     *              "yellow", "green", "magenta", "gray", "orange", "purple",
     *              "brown", "cyan",
     *              "darkgreen", "aquamarine", "lavender" and "salmon"
     * @return the coordenates of the spot
     */
    public double[] spot(String color) {
        Spot spot = spots.get(color);
        if (spot != null) {
            this.ok = true;
            return spot.spot();
        } else {
            JOptionPane.showMessageDialog(null, "No existe un spot con ese color");
            this.ok = false;
            return null;
        }
    }

    /**
     * Return the spots that the spider can reach
     * 
     * @return the spots color that the spider can reach
     */
    public String[] reachableSpots() {
        return spider.reachableSpots(this.bridges, this.spots);
    }

    /**
     * Return the bridges that the spider didn't use
     * 
     * @return the bridges color that the spider didn't use
     */
    public String[] unusedBridges() {
        return spider.unusedBridges(this.bridges);
    }

    /**
     * Make the objects visible. If it was already visible, do nothing.
     */
    public void makeVisible() {
        this.isVisible = true;
        for (Strand strand : strands.values()) {
            strand.makeVisible();
        }
        for (Bridge bridge : bridges.values()) {
            bridge.makeVisible();
        }
        for (Spot spot : spots.values()) {
            spot.makeVisible();
        }
        spider.makeVisible();
    }

    /**
     * Make the objects invisible. If it was already visible, do nothing.
     */
    public void makeInvisible() {
        for (Strand strand : strands.values()) {
            strand.makeInvisible();
        }
        for (Bridge bridge : bridges.values()) {
            bridge.makeInvisible();
        }
        for (Spot spot : spots.values()) {
            spot.makeInvisible();
        }
        spider.makeInvisible();
        this.isVisible = false;
    }

    /**
     * Return the status of the simulation
     * 
     * @return TRUE, if an action could be completed. FALSE, otherwise
     */
    public boolean ok() {
        boolean webOk = this.ok;
        boolean spiderOk = spider.getOk();
        this.ok = true;
        spider.setOk(true);
        return (webOk == false || spiderOk == false) ? false : true;
    }

    /**
     * Verify if the spider is sitting on a spot
     * 
     * @return the spot where the spider is
     */
    public Spot spiderInspot() {
        for (Spot s : this.spots.values()) {
            if (s.getStrand() == this.spider.getStrand()) {
                return s;
            }
        }
        return null;
    }

    /**
     * The spider jump to the next strand
     */
    public void spiderJump() {
        this.spider.spiderJump(this.strands, this.numStrands);
    }

    /**
     * The spider dies
     */
    public void spiderKill() {
        this.spider.spiderKill();
    }

    /**
     * Verify if there is a spot in the strand
     * 
     * @param strand the strand to verify
     * @return TRUE, there is a spot in the strand. FALSE, otherwise
     */
    public boolean spotInStrand(int strand) {
        for (Spot spot : this.spots.values()) {
            if (spot.getStrand() == strand) {
                return true;
            }
        }
        return false;
    }

    /**
     * Delete a bridge form the arrayList
     * 
     * @param color
     */
    public void removeBridge(String color) {
        this.bridges.remove(color);
    }

    /**
     * Delete a spot form the arrayList
     * 
     * @param color
     */
    public void removeSpot(String color) {
        this.spots.remove(color);
    }

    /**
     * Return the existing strands in the web
     * 
     * @return a hashMap with the existing strands in the web
     */
    public HashMap<Integer, Strand> getStrands() {
        return this.strands;
    }

    /**
     * Return the existing bridges in the web
     * 
     * @return a hashMap with the existing bridges in the web
     */
    public HashMap<String, Bridge> getBridges() {
        return this.bridges;
    }

    /**
     * Return the existing spots in the web
     * 
     * @return a hashMap with the existing spots in the web
     */
    public HashMap<String, Spot> getSpots() {
        return this.spots;
    }

    /**
     * Returns the numbre of strands
     * 
     * @return the number of strands on the web
     */
    public int getNumStrands() {
        return this.numStrands;
    }

    /**
     * Returns the value of the radio, the length.
     * 
     * @return the radio of the web
     */
    public int getRadio() {
        return this.radio;
    }

    /**
     * Change the status of the web
     * 
     * @param ok the new status of the web
     */
    public void setOk(boolean ok) {
        this.ok = ok;
    }

    /*
     * Create all the strands of the web
     */
    private void addStrands() {
        double angle = Math.toRadians((double) 360 / this.numStrands);
        double angleStrand = 0;
        for (int i = 1; i <= this.numStrands; i++) {
            Strand strand = new Strand(this.radio, angleStrand, this.isVisible);
            this.strands.put(i, strand);
            angleStrand -= angle;
        }
    }

    /*
     * Recreates the web according to the new specifications.
     */
    private void recalculateWeb() throws Exception {
        HashMap<String, Bridge> copyBridges = new HashMap<>(this.bridges);
        HashMap<String, Spot> copySpots = new HashMap<>(this.spots);
        this.spots.clear();
        this.bridges.clear();

        for (Map.Entry<String, Bridge> element : copyBridges.entrySet()) {
            Bridge b = element.getValue();
            String type = b.getClass().getSimpleName().toLowerCase();
            type = (type.equals("normalbridge")) ? "normal" : type;
            this.addBridge(type, b.getColor(), b.getDistance(), b.getInicialStrand());
        }

        for (Map.Entry<String, Spot> element : copySpots.entrySet()) {
            Spot s = element.getValue();
            String type = s.getClass().getSimpleName().toLowerCase();
            type = (type.equals("normalspot")) ? "normal" : type;
            this.addSpot(type, s.getColor(), s.getStrand());
        }
    }

    /*
     * Verify if exists contiguos bridges.
     */
    private boolean contiguosBridges(int distance, int firstStrand, int finalStrand) {
        for (Bridge b : bridges.values()) {
            int bDistance = b.getDistance();
            int bInicialStrand = b.getInicialStrand();
            int bFinalStrand = b.getFinalStrand();

            if ((bDistance == distance) && ((bInicialStrand == firstStrand || bInicialStrand == finalStrand) ||
                    (bFinalStrand == firstStrand || bFinalStrand == finalStrand))) {
                return true;
            }
        }

        return false;
    }

    /*
     * Create a type of bridge
     */
    private Bridge createBridge(String type, String color, int distance, int firstStrand, int finalStrand) {
        Bridge bridge = null;
        this.ok = true;
        switch (type) {
            case "normal":
                bridge = new NormalBridge(color, distance, firstStrand, finalStrand, this, this.isVisible);
                break;
            case "fixed":
                bridge = new Fixed(color, distance, firstStrand, finalStrand, this, this.isVisible);
                break;
            case "transformer":
                bridge = new Transformer(color, distance, firstStrand, finalStrand, this, this.isVisible);
                break;
            case "weak":
                bridge = new Weak(color, distance, firstStrand, finalStrand, this, this.isVisible);
                break;
            case "mobile":
                bridge = new Mobile(color, distance, firstStrand, finalStrand, this, this.isVisible);
                break;
            default:
                this.ok = false;
                throw new IllegalArgumentException("Tipo de puente no válido: " + type);
        }
        return bridge;
    }

    /*
     * Create a type of spot
     */
    private Spot createSpot(String type, String color, int strand, Strand selectedStrand) {
        Spot spot = null;
        this.ok = true;

        switch (type) {
            case "normal":
                spot = new NormalSpot(color, strand, this, this.isVisible);
                break;
            case "bouncy":
                spot = new Bouncy(color, strand, this, this.isVisible);
                break;
            case "killer":
                spot = new Killer(color, strand, this, this.isVisible);
                break;
            case "suicide":
                spot = new Suicide(color, strand, this, this.isVisible);
                break;
            default:
                this.ok = false;
                throw new IllegalArgumentException("Tipo de puente no válido: " + type);
        }
        return spot;
    }
}
