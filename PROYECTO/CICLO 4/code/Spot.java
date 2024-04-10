import java.awt.geom.Ellipse2D;
import java.util.HashMap;

/**
 * Create the spots of the spider.
 * 
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class Spot {
    protected String color;
    protected int strand;
    protected Web web;
    protected Ellipse2D.Double body;
    protected boolean isVisible;

    Canvas canvas;

    /**
     * Constructor for objects of class Spot
     */
    public Spot(String color, int strand, Web web, boolean isVisible) {
        canvas = Canvas.getCanvas();
        this.color = color;
        this.strand = strand;
        this.web = web;
        this.isVisible = isVisible;
    }

    /**
     * Add the spot to the web
     * 
     * @param strands       the strands of the web
     */
    public void addSpot(HashMap<Integer, Strand> strands) {
        Strand strand = strands.get(this.strand);
        Double x2 = strand.getBody().getX2();
        Double y2 = strand.getBody().getY2();
        
        body = new Ellipse2D.Double(x2 - 2.5, y2 - 2.5, 5, 5);
    }

    /**
     * Return the coordenates of the spot, (Xo,Yo) & (Xf,Yf)
     * 
     * @return the coordenates of the spot
     */
    public double[] spot() {
        double[] output = new double[2];
        output[0] = this.body.getX();
        output[1] = this.body.getX();
        return output;
    }

    /**
     * Delete the spot from the web
     */
    public void deleteSpot() {
        this.web.removeSpot(color);
        this.makeInvisible();
    }

    /**
     * Its the behavior of the spot
     */
    public void act() {

    };

    /**
     * Return the color of the spot
     * 
     * @return the color of the spot
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Return the strand of the spot
     * 
     * @return the strand where is locate the spot
     */
    public int getStrand() {
        return this.strand;
    }

    /*
     * Draw the spot with current specifications on screen.
     */
    protected void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, body);
            canvas.wait(10);
        }
    }

    /*
     * Erase the spot on screen.
     */
    protected void erase() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

    /**
     * Make visible the spot
     */
    public void makeVisible() {
        this.isVisible = true;
        draw();
    }

    /**
     * Make invisible the spot
     */
    public void makeInvisible() {
        erase();
        this.isVisible = false;
    }
}
