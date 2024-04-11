import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This Bridge's subclass has the same behaviors that a normal Bridge,
 * except it relocates it's position if it's possible jumping to the next strand
 * increasing
 * its radius by a 20% (if it's possible).
 * 
 * @author (Daniel Diaz y Miguel Motta)
 * @version (CICLO 4, 2024 / 01)
 */

public class Mobile extends Bridge {
    private ArrayList<Circle> circles;

    /**
     * Constructor for objects of class Mobile
     */
    public Mobile(String color, int distance, int inicialStrand, int finalStrand, Web web, boolean isVisible) {
        super(color, distance, inicialStrand, finalStrand, web, isVisible);
        this.circles = new ArrayList<>();
    }

    /**
     * Add the bridge to the web with the new body
     * 
     * @param strands the strands of the web
     */
    @Override
    public void addBridge(HashMap<Integer, Strand> strands) {
        super.addBridge(strands);
        createBody();
        draw();
    }

    /*
     * Crete the new body of the bridge to differentiate it
     */
    private void createBody() {
        double x1 = this.body.getX1();
        double y1 = this.body.getY1();
        double x2 = this.body.getX2();
        double y2 = this.body.getY2();

        double diameter = distance(x1, x2, y1, y2) / 10;

        for (double i = 0; i <= 1; i += 0.1) {
            Point2D.Double point = parameterizedSegment(x1, y1, x2, y2, i);
            double x = point.getX() - (diameter / 2);
            double y = point.getY() - (diameter / 2);
            Circle circle = new Circle(x, y, this.color, this.isVisible, diameter);
            this.circles.add(circle);
        }
    }

    /**
     * Its the behavior of the bridge
     */
    @Override
    public void act() {
        int newDistance = (int) (this.distance + this.distance * 0.2);
        if (this.web.getRadio() >= newDistance) {
            super.deleteBridge();
            if (this.finalStrand != this.web.getNumStrands()) {
                this.web.addBridge("mobile", this.color, newDistance, this.inicialStrand + 1);
            } else {
                this.web.addBridge("mobile", this.color, newDistance, 1);
            }
        }
    }

    /*
     * Return the length of the bridge
     */
    private double distance(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /*
     * Return a point belonging to the line
     */
    private Point2D.Double parameterizedSegment(double x1, double y1, double x2, double y2, double t) {
        double x = x1 + t * (x2 - x1);
        double y = y1 + t * (y2 - y1);
        return new Point2D.Double(x, y);
    }

    /*
     * Draw the bridge with current specifications on screen.
     */
    @Override
    protected void draw() {
        super.draw();
        if (isVisible) {
            for (Circle circle : this.circles) {
                circle.makeVisible();
            }
        }
    }

    /*
     * Erase the bridge on screen.
     */
    @Override
    protected void erase() {
        super.erase();
        if (isVisible) {
            for (Circle circle : this.circles) {
                circle.makeInvisible();
            }
        }
    }
}
