import java.awt.geom.Line2D;

/**
 * Create a strand in the web.
 * 
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class Strand
{
    private double angle;
    private Line2D.Double body;
    
    private Canvas canvas;

    /**
     * Constructor for objects of class Strand
     */
    public Strand(int radio, double angle){
        canvas = Canvas.getCanvas();
        this.angle = angle;
        
        addStrand(radio);
    }
    
    /*
     * Create a strand in the web
     */
    private void addStrand(int radio){
        double x = radio * Math.cos(this.angle);
        double y = radio * Math.sin(this.angle);
        this.body = new Line2D.Double(canvas.getCenterX(), canvas.getCenterY(), canvas.getCenterX() + x, canvas.getCenterY() + y);
        makeVisible();
    }
    
    /**
     * Return the angle of the strand
     * @return  the angle of the strand
     */
    public double getAngle(){
        return this.angle;
    }
    
    /**
     * Return the body of the strand
     * @return  the line that represents the body of the strand
     */
    public Line2D.Double getBody(){
        return this.body;
    }
    
    /**
     * Make visible the strand
     */
    public void makeVisible(){
        canvas.draw(body, "gray", body);
    }
    
    /**
     * Make invisible the strand
     */
    public void makeInvisible(){
        canvas.erase(body);
    }
}
