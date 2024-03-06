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
    private boolean isVisible;
    
    private Canvas canvas;

    /**
     * Constructor for objects of class Strand
     */
    public Strand(int radio, double angle, boolean isVisible){
        canvas = Canvas.getCanvas();
        this.angle = angle;
        this.isVisible = isVisible;
        
        addStrand(radio);
    }
    
    /*
     * Create a strand in the web
     */
    private void addStrand(int radio){
        double x = radio * Math.cos(this.angle);
        double y = radio * Math.sin(this.angle);
        this.body = new Line2D.Double(canvas.getCenterX(), canvas.getCenterY(), canvas.getCenterX() + x, canvas.getCenterY() + y);
        draw();
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
    
    /*
     * Draw the spider with current specifications on screen.
     */
    private void draw(){
        if(isVisible){
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, "gray", body);
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
     * Make visible the strand
     */
    public void makeVisible(){
        this.isVisible = true;
        draw();
    }
    
    /**
     * Make invisible the strand
     */
    public void makeInvisible(){
        erase();
        this.isVisible = false;
    }
}
