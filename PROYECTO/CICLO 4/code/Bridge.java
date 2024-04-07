import java.awt.geom.Line2D;
import java.util.HashMap;

/**
 * Create and modify the bridges of the web.
 * 
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class Bridge {
    protected String color;
    protected int distance;  
    protected int inicialStrand;
    protected int finalStrand;
    protected Web web;
    protected Line2D.Double body;
    protected boolean isVisible;

    protected Canvas canvas;

    /**
     * Constructor for objects of class Bridge
     */
    public Bridge(String color, int distance, int inicialStrand, int finalStrand, Web web, boolean isVisible) {
        canvas = Canvas.getCanvas();
        this.color = color;
        this.distance = distance;
        this.inicialStrand = inicialStrand;
        this.finalStrand = finalStrand;
        this.web = web;
        this.isVisible = isVisible;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param strands       the strands of the web
     * @param inicialStrand the strand where the bridge begin
     * @param finalStrand   the strand where the bridge end
     */
    public void addBridge(HashMap<Integer, Strand> strands, int inicialStrand, int finalStrand) {
        Strand firstStrand = strands.get(inicialStrand);
        Strand lastStrand = strands.get(finalStrand);
        double x1 = this.distance * Math.cos(firstStrand.getAngle());
        double y1 = this.distance * Math.sin(firstStrand.getAngle());
        double x2 = this.distance * Math.cos(lastStrand.getAngle());
        double y2 = this.distance * Math.sin(lastStrand.getAngle());
        body = new Line2D.Double(canvas.getCenterX() + x1, canvas.getCenterY() + y1, canvas.getCenterX() + x2,
                canvas.getCenterY() + y2);
        draw();
    }

    public void deleteBridge() {
        this.web.removeBridge(color);
        this.makeInvisible();
    }

    /**
     * Return the coordenates of the bridge, (Xo,Yo) & (Xf,Yf)
     * 
     * @return the coordenates of the bridge
     */
    public double[] bridge() {
        double[] output = new double[4];
        output[0] = this.body.getX1();
        output[1] = this.body.getY1();
        output[2] = this.body.getX2();
        output[3] = this.body.getY2();
        return output;
    }

    public void act() {

    }

    /**
     * Return the color of the bridge
     * 
     * @return the color of the bridge
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Return the distance
     * 
     * @return the distance from the center to the bridge
     */
    public int getDistance() {
        return this.distance;
    }

    /**
     * Return the inicial strand of the bridge
     * 
     * @return the strand where the bridge begin
     */
    public int getInicialStrand() {
        return this.inicialStrand;
    }

    /**
     * Return the final strand of the bridge
     * 
     * @return the strand where the bridge end
     */
    public int getFinalStrand() {
        return this.finalStrand;
    }

    /**
     * Return the body of the bridge
     * 
     * @return the line that represents the body of the bridge
     */
    public Line2D.Double getBody() {
        return this.body;
    }

    /*
     * Draw the bridge with current specifications on screen.
     */
    private void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, body);
            canvas.wait(10);
        }
    }

    /*
     * Erase the bridge on screen.
     */
    private void erase() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

    /**
     * Make visible the bridge
     */
    public void makeVisible() {
        this.isVisible = true;
        draw();
    }

    /**
     * Make invisible the bridge
     */
    public void makeInvisible() {
        erase();
        this.isVisible = false;
    }
}
