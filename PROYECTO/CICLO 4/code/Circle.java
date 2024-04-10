import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 1.0. (15 July 2000)
 */

public class Circle extends Figure {
    public static final double PI = 3.1416;
    private double diameter;

    /**
     * Constructor for objects of class Circle
     */
    public Circle(double x, double y, String color, boolean isVisible, int diameter) {
        super(x, y, color, isVisible);
        this.diameter = diameter;
    }

    /**
     * Change the size.
     * 
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter) {
        erase();
        diameter = newDiameter;
        draw();
    }

    /*
     * Draw the circle with current specifications on screen.
     */
    @Override
    protected void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                    new Ellipse2D.Double(xPosition, yPosition,
                            diameter, diameter));
            canvas.wait(10);
        }
    }

}
