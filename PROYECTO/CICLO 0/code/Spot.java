import java.awt.geom.Ellipse2D;

/**
 * Create the spots of the spider.
 * 
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class Spot
{
    private String color;
    private int strand;
    private Ellipse2D.Double body;
    
    Canvas canvas;

    /**
     * Constructor for objects of class Spot
     */
    public Spot(String color, int strand, double xPosition, double yPosition){
        canvas = Canvas.getCanvas();
        this.color = color;
        this.strand = strand;
        body = new Ellipse2D.Double(xPosition - 2.5, yPosition - 2.5, 5, 5);        
        makeVisible();
    }
    
    /**
     * Return the coordenates of the apot, (Xo,Yo) & (Xf,Yf)
     * @return  the coordenates of the spot
     */
    public double[] spot(){
        double[] output = new double[2];
        output[0] = this.body.getX();
        output[1] = this.body.getX();
        return output;  
    }
    
    /**
     * Make visible the spot
     */
    public void makeVisible(){
        canvas.draw(body, this.color, body);
    }
    
    /**
     * Make invisible the spot
     */
    public void makeInvisible(){
        canvas.erase(body);
    }
}
