import java.awt.geom.Line2D;
import java.util.HashMap;

/**
 * Create and modify the bridges of the web.
 * 
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class Bridge
{
    private String color;
    private int distance;
    private int inicialStrand;
    private int finalStrand;
    private Line2D.Double body;
    
    private Canvas canvas;

    /**
     * Constructor for objects of class Bridge
     */
    public Bridge(String color, int distance, int inicialStrand, int finalStrand){
        canvas = Canvas.getCanvas();
        this.color = color;
        this.distance = distance;
        this.inicialStrand = inicialStrand;
        this.finalStrand = finalStrand;
    }

    /**
     * An example of a method - replace this comment with your own
     * @param   strands         the strands of the web
     * @param   inicialStrand   the strand where the bridge begin
     * @param   finalStrand     the strand where the bridge end
     */
    public void addBridge(HashMap<Integer, Strand> strands, int inicialStrand, int finalStrand){
        Strand firstStrand = strands.get(inicialStrand);
        Strand lastStrand = strands.get(finalStrand);
        double x1 = this.distance * Math.cos(firstStrand.getAngle());
        double y1 = this.distance * Math.sin(firstStrand.getAngle());
        double x2 = this.distance * Math.cos(lastStrand.getAngle());
        double y2 = this.distance * Math.sin(lastStrand.getAngle());
        body = new Line2D.Double(canvas.getCenterX() + x1, canvas.getCenterY() + y1, canvas.getCenterX() + x2, canvas.getCenterY() + y2);
        canvas.draw(body, color, body);
    }
    
    /**
     * Return the coordenates of the bridge, (Xo,Yo) & (Xf,Yf)
     * @return  the coordenates of the bridge
     */
    public double[] bridge(){
        double[] output = new double[4];
        output[0] = this.body.getX1();
        output[1] = this.body.getY1();
        output[2] = this.body.getX2();
        output[3] = this.body.getY2();
        return output; 
    }

    /**
     * Return the distance
     * @return  the distance from the center to the bridge
     */
    public String getColor(){
        return this.color;
    }
    
    /**
     * Return the distance
     * @return  the distance from the center to the bridge
     */
    public int getDistance(){
        return this.distance;
    }
    
    /**
     * Return the inicial strand of the bridge
     * @return  the strand where the bridge begin
     */
    public int getInicialStrand(){
        return this.inicialStrand;
    }
    
    /**
     * Return the final strand of the bridge
     * @return  the strand where the bridge end
     */
    public int getFinalStrand(){
        return this.finalStrand;
    }
    
    /**
     * Return the body of the bridge
     * @return  the line that represents the body of the bridge
     */
    public Line2D.Double getBody(){
        return this.body;
    }
        
    /**
     * Make visible the bridge
     */
    public void makeVisible(){
        canvas.draw(body, this.color, body);
    }
    
    /**
     * Make invisible the bridge
     */
    public void makeInvisible(){
        canvas.erase(body);
    }
}
