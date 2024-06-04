import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;

/**
 * Create a spider.
 * 
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class Spider
{
    private Ellipse2D.Double body;
    private int strand = 0;
    private int distance = 0;
    private ArrayList<Integer> lastPath;
    private HashMap<String, Bridge> usedBridges;
    private boolean isVisible;
    private boolean ok;
    
    private Canvas canvas;

    /**
     * Constructor for objects of class Spider
     */
    public Spider(boolean isVisible){
        canvas = Canvas.getCanvas();
        body = new Ellipse2D.Double(canvas.getCenterX() - 7.5, canvas.getCenterY() - 7.5, 15, 15);
        this.strand = 1;
        this.distance = 0;
        lastPath = new ArrayList<>();
        usedBridges = new HashMap<>();
        this.isVisible = isVisible;
        this.ok = true;
        draw();
    }
    
    /**
     * Seat the spider in the center with reference to a specific strand
     * @param   strand      the strand to wich the spider will refer
     * @param   numStrands  the number of strand existing on the web    
     */
    public void spiderSit(int strand, int numStrands){
        if(strand > 0 && strand <= numStrands){
            this.body.setFrame(canvas.getCenterX() - 7.5, canvas.getCenterY() - 7.5, 15, 15);
            draw();
            this.strand = strand;
            this.distance = 0;
            this.lastPath.clear();
            this.usedBridges.clear();
            this.ok = true;
        }else{
            JOptionPane.showMessageDialog(null, "Hebra fuera del rango");
                    this.ok = false;
        }
    }
    
    /**
     * The spider walks through the strands and bridges
     * @param   strands the existing strands in the web
     * @param   bridges the existing bridges in the web
     */
    public void spiderWalk(HashMap<Integer, Strand> strands, HashMap<String, Bridge> bridges){
        double x0 = strands.get(this.strand).getBody().getX1();
        double y0 = strands.get(this.strand).getBody().getY1();
        double x1, y1, x2, y2;
        Bridge bridge;
        
        do{
            bridge = bridgeToGo(bridges);
            this.lastPath.add(this.strand);
            if(bridge != null){
                if(bridge.getInicialStrand() == this.strand){
                    x1 = bridge.getBody().getX1();
                    y1 = bridge.getBody().getY1();
                    x2 = bridge.getBody().getX2();
                    y2 = bridge.getBody().getY2();
                    this.strand = bridge.getFinalStrand();
                }else{
                    x1 = bridge.getBody().getX2();
                    y1 = bridge.getBody().getY2();
                    x2 = bridge.getBody().getX1();
                    y2 = bridge.getBody().getY1();
                    this.strand = bridge.getInicialStrand();
                }
                
                moveSpider(x0, y0, x1, y1);
                moveSpider(x1, y1, x2, y2);
                this.distance = bridge.getDistance();
                usedBridges.put(bridge.getColor(), bridge);
                x0 = x2;
                y0 = y2;
            }
        }while(bridge != null);
        
        moveSpider(x0, y0, strands.get(this.strand).getBody().getX2(), strands.get(this.strand).getBody().getY2());
        this.ok = true;
    }
    
    /*
     * Move the spider trough a line
     */
    private void moveSpider(double x1, double y1, double x2, double y2){
        for(double i = 0; i <= 1; i += 0.01){
            Point2D.Double point = parameterizedSegment(x1, y1, x2, y2, i);
            this.body.setFrame(point.getX() - 7.5, point.getY() - 7.5, 15, 15);
            canvas.wait(16);
            draw();
        }
    }
    
    /*
     * Return a point belonging to the line
     */
    private Point2D.Double parameterizedSegment(double x1, double y1, double x2, double y2, double t){
        double x = x1 + t * (x2 - x1);
        double y = y1 + t * (y2 - y1);
        return new Point2D.Double(x, y);
    }
    
    /*
     * Return the bridge closest to the spider
     */
    private Bridge bridgeToGo(HashMap<String, Bridge> bridges){
        ArrayList<Bridge> bridgesInStrand = new ArrayList<>();
        
        for(Bridge bridge : bridges.values()){
            bridgesInStrand.add((bridge.getInicialStrand() == this.strand || bridge.getFinalStrand() == this.strand) ? bridge : null);
        }
        
        double minDistance = Double.MAX_VALUE;
        Bridge selectedBridge = null;
        
        for(Bridge bridge : bridgesInStrand){
            if(bridge != null){
                double distance = bridge.getDistance();
                if (distance > this.distance && distance < minDistance) {
                    minDistance = distance;
                    selectedBridge = bridge;
                }
            }
        }
        
        return (selectedBridge != null) ? selectedBridge : null;
    }
    
    /**
     * Return the path along wich the spider walked
     * @return  the strands where the spider walked
     */ 
    public int[] getLastPath(){
        int[] output = new int[lastPath.size()];
        for(int i = 0; i < lastPath.size(); i++){
            output[i] = lastPath.get(i);
        }
        this.ok = true;
        return output;
    }

    /**
     * Return the spots that the spider can reach
     * @param bridges   the existing bridges in the web
     * @param spots     the existing spots in the web
     * @return          the spots color that the spider can reach
     */
    public String[] reachableSpots(HashMap<String, Bridge> bridges, HashMap<String, Spot> spots){
        String[] reachableSpots = new String[1];
        Bridge bridge;
        int tempStrand = this.strand;
        int distance = this.distance;
            
        do{
            bridge = bridgeToGo(bridges);
            if(bridge != null){
                if(bridge.getInicialStrand() == this.strand){
                    this.strand = bridge.getFinalStrand();
                }else{
                    this.strand = bridge.getInicialStrand();
                }
                this.distance = bridge.getDistance();
            }
        }while(bridge != null);
        
        reachableSpots[0] = spotInStrand(this.strand, spots);
        this.strand = tempStrand;
        this.distance = distance;
        return reachableSpots;
    }

    /*
     * Return the color of the spot that is on the strand
     */
    private String spotInStrand(int strand, HashMap<String, Spot> spots){
        for(Spot spot : spots.values()){
            if(spot.getStrand() == strand){
                return spot.getColor();
            }
        }
        return null;
    }

    /**
     * Return the bridges that the spider didn't use
     * @param bridges   the existing bridges in the web
     * @return  the bridges color that the spider didn't use
     */
    public String[] unusedBridges(HashMap<String, Bridge> bridges){
        HashMap<String, Bridge> unusedBridges = new HashMap<>();

        for(Map.Entry<String, Bridge> entry : bridges.entrySet()){
            String key = entry.getKey();
            Bridge bridge = entry.getValue();
            if(!this.usedBridges.containsKey(key)){
                unusedBridges.put(key, bridge);
            }
        }

        String[] output = new String[unusedBridges.size()];
        int i = 0;
        for(String color : unusedBridges.keySet()){
            output[i] = color;
            i += 1;
        }
        this.ok = true;
        return output; 
    }

    /**
     * Return the minimum number of bridges to add for each strand to reach the spot
     * @param   bridges     the strand where the spot is located
     * @param   favorite    the strand where the spot is located
     * @param   strands     the number of strands in the web
     * @return  the minimum number of bridges to add
     */
    public ArrayList<Integer> minBridgesToAdd(int[][] bridges, int favorite, int strands){
        ArrayList<Integer> numBridges = new ArrayList<>();
        int originalStrand = this.strand;
        int leftBridges; int rightBridges;
        
        for(int i = 1; i <= strands; i++){
            this.strand = i;
            leftBridges = addBridgesLeft(bridges, favorite, strands).size();
            rightBridges = addBridgesRight(bridges, favorite, strands).size();
            numBridges.add((leftBridges < rightBridges) ? leftBridges : rightBridges);
        }
        
        this.strand = originalStrand;
        return numBridges;
    }
    
    /**
     * Return the bridges to add on the web to reach the spot
     * @param strands   the number of strands in the web
     * @param favorite  the strand where the spot is locate
     * @param bridges   the existing bridges in the web
     * @param strand    the strand where the spider start
     * @return  the bridges to add on the web
     */
    public ArrayList<int[]> bridgesToAdd(int strands, int favorite, int[][] bridges, int strand){
        int originalStrand = this.strand;
        ArrayList<int[]> leftBridges; ArrayList<int[]> rightBridges;
        this.strand = strand;
        leftBridges = addBridgesLeft(bridges, favorite, strands);
        rightBridges = addBridgesRight(bridges, favorite, strands);
        this.strand = originalStrand;
        return (leftBridges.size() <= rightBridges.size()) ? leftBridges : rightBridges;
    }

    /*
     * Return the number of bridges to be added on the left for each strand to reach the spot
     */
    private ArrayList<int[]> addBridgesLeft(int[][] bridges, int favorite, int strands){
        int newDistance = 0;
        ArrayList<int[]> bridgesToAdd = new ArrayList<>();
        int originalStrand = this.strand; int originalDistance = this.distance;
        int[] bridge = bridgeToGo(bridges, strands);
        
        while(!reachSpot(bridge, favorite)){
            newDistance = distanceBridgeAdded(true, bridge, bridges, strands);
            if(bridge != null){
                int inicialStrand = bridge[1];
                int finalStrand = (inicialStrand == strands) ? 1 : inicialStrand + 1;
                if(inicialStrand == this.strand){
                    this.strand = finalStrand;
                    this.distance = bridge[0];
                }else{
                    bridgesToAdd.add(new int[]{newDistance, this.strand});
                    this.strand = (this.strand == strands) ? 1 : this.strand + 1;
                    this.distance = newDistance;
                }
            }else{
                bridgesToAdd.add(new int[]{newDistance, this.strand});
                this.strand = (this.strand == strands) ? 1 : this.strand + 1;
                this.distance = newDistance;
            }
            bridge = bridgeToGo(bridges, strands);
        }
        
        newDistance = (this.strand != favorite) ? this.distance + ((350 - this.distance) / 2) : newDistance;
        for(int[] b : bridges){
            int finalStrand = (b[1] == strands) ? 1 : b[1] + 1;
            newDistance = ((b[1] == favorite || finalStrand == favorite) && b[0] == newDistance) ? newDistance + 10 : newDistance;
        }
        if(this.strand == favorite + 1){
            bridgesToAdd.add(new int[]{newDistance, this.strand - 1});
        }else if(this.strand == favorite - 1){
            bridgesToAdd.add(new int[]{newDistance, this.strand});       
        }

        this.strand = originalStrand;
        this.distance = originalDistance;
        return bridgesToAdd;
    }
    
    /*
    * Return the number of bridges to be added on the right for each strand to reach the spot
    */
    private ArrayList<int[]> addBridgesRight(int[][] bridges, int favorite, int strands){
        int newDistance = 0;
        ArrayList<int[]> bridgesToAdd = new ArrayList<>();
        int originalStrand = this.strand; int originalDistance = this.distance;
        int[] bridge = bridgeToGo(bridges, strands);
        
        while(!reachSpot(bridge, favorite)){
            newDistance = distanceBridgeAdded(false, bridge, bridges, strands);
            if(bridge != null){
                int inicialStrand = bridge[1];
                int finalStrand = (inicialStrand == strands) ? 1 : inicialStrand + 1;
                if(finalStrand == this.strand){
                    this.strand = inicialStrand;
                    this.distance = bridge[0];
                }else{
                    this.strand = (this.strand == 1) ? strands : this.strand - 1;
                    bridgesToAdd.add(new int[]{newDistance, this.strand});    
                    this.distance = newDistance;            
                }
            }else{
                this.strand = (this.strand == 1) ? strands : this.strand - 1;
                bridgesToAdd.add(new int[]{newDistance, this.strand});    
                this.distance = newDistance;            
            }
            bridge = bridgeToGo(bridges, strands);
        }

        newDistance = (this.strand != favorite) ? this.distance + ((350 - this.distance) / 2) : newDistance;
        for(int[] b : bridges){
            int finalStrand = (b[1] == strands) ? 1 : b[1] + 1;
            newDistance = ((b[1] == favorite || finalStrand == favorite) && b[0] == newDistance) ? newDistance + 10 : newDistance;
        }
        if(this.strand == favorite + 1){
            bridgesToAdd.add(new int[]{newDistance, this.strand - 1});
        }else if(this.strand == favorite - 1){
            bridgesToAdd.add(new int[]{newDistance, this.strand});       
        }
        this.strand = originalStrand;
        this.distance = originalDistance;
        return bridgesToAdd;
    }

    /*
     * Return the distance of the bridge to add
     */
    private int distanceBridgeAdded(boolean left, int[] bridge, int[][] bridges, int strands){
        int newDistance = 0;
        int[] bridgeNextSrand;
        int originalStrand = this.strand;
        if(left){
            if(bridge != null){
                if(bridge[1] != this.strand){
                    newDistance = this.distance + ((bridge[0] - this.distance) / 2);
                }
            }else{
                this.strand = (this.strand == strands) ? 1 : this.strand + 1;
                bridgeNextSrand = bridgeToGo(bridges, strands);
                if(bridgeNextSrand != null){
                    newDistance = this.distance + ((bridgeNextSrand[0] - this.distance) / 2);
                }else{
                    newDistance = this.distance + 10;
                }
            }
        }else{
            if(bridge != null){
                int finalStrand = (bridge[1] == strands) ? 1 : bridge[1] + 1;
                if(finalStrand != this.strand){
                    newDistance = this.distance + ((bridge[0] - this.distance) / 2);
                }
            }else{
                this.strand = (this.strand == 1) ? strands : this.strand - 1;
                bridgeNextSrand = bridgeToGo(bridges, strands);
                if(bridgeNextSrand != null){
                    newDistance = this.distance + ((bridgeNextSrand[0] - this.distance) / 2);
                }else{
                    newDistance = this.distance + 10;
                }
            }
        }

        this.strand = originalStrand;
        return newDistance;
    }

    /*
     * Check if the spot is reachable from the point where the spider is located
     */
    private boolean reachSpot(int[] bridgeToGo, int favorite){
        return (bridgeToGo == null && (this.strand >= favorite - 1 && this.strand <= favorite + 1)) ? true : false;
    }

    /*
     * Return the bridge closest to the spider
     */
    private int[] bridgeToGo(int[][] bridges, int strands){
        ArrayList<int[]> bridgesInStrand = new ArrayList<>();
        
        for(int[] bridge : bridges){
            int inicialStrand = bridge[1];
            int finalStrand = (inicialStrand == strands) ? 1 : inicialStrand + 1;
            bridgesInStrand.add((inicialStrand == this.strand || finalStrand == this.strand) ? bridge : null);
        }
        
        double minDistance = Double.MAX_VALUE;
        int[] selectedBridge = null;
        
        for(int[] bridge : bridgesInStrand){
            if(bridge != null){
                double distance = bridge[0];
                if (distance > this.distance && distance < minDistance) {
                    minDistance = distance;
                    selectedBridge = bridge;
                }
            }
        }

        return (selectedBridge != null) ? selectedBridge : null;
    }

    /**
     * This method returns the distance of the spider respect the origin.
     * @return  the distance of the spider respect the origin.
     */
    public int getDistance(){
        return this.distance;
    }

    /**
     * Return the status of the spider
     * @return  if an action could be completed
     */
    public boolean getOk(){
        return this.ok;
    }

    /**
     * Change the status of the spider
     * @param   ok    the new status of the spider
     */
    public void setOk(boolean ok){
        this.ok = ok;
    }

    /*
     * Draw the spider with current specifications on screen.
     */
    private void draw(){
        if(isVisible){
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, "black", body);
            canvas.wait(10);
        }
    }

    /*
     * Erase the spider on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

    /**
     * Make visible the spider
     */
    public void makeVisible(){
        this.isVisible = true;
        draw();
    }
    
    /**
     * Make invisible the spider
     */
    public void makeInvisible(){
        erase();
        this.isVisible = false;
    }
}
