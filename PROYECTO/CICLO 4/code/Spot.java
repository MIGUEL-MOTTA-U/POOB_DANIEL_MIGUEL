import java.awt.geom.Ellipse2D;

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
    public Spot(String color, int strand, Web web, double xPosition, double yPosition, boolean isVisible) {
        canvas = Canvas.getCanvas();
        this.color = color;
        this.strand = strand;
        this.web = web;
        body = new Ellipse2D.Double(xPosition - 2.5, yPosition - 2.5, 5, 5);
        this.isVisible = isVisible;
        draw();
    }

    /**
     * Return the coordenates of the apot, (Xo,Yo) & (Xf,Yf)
     * 
     * @return the coordenates of the spot
     */
    public double[] spot() {
        double[] output = new double[2];
        output[0] = this.body.getX();
        output[1] = this.body.getX();
        return output;
    }

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
    private void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, body);
            canvas.wait(10);
        }
    }

    /*
     * Erase the spot on screen.
     */
    private void erase() {
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
