package shapes;

import java.awt.*;

/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Triangle extends Figure{
    
    public static int VERTICES=3;
    
    private double height;
    private double width;

    /**
     * Create a new triangle at default position with default color.
     */
    public Triangle(double x, double y, String color, boolean isVisible, int height, int width){
        super(x, y, color, isVisible);
        this.height = height;
        this.width = width;
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidht must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }

    /*
     * Draw the triangle with current specifications on screen.
     */
    protected void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { (int) xPosition, (int) (xPosition + (width/2)), (int) (xPosition - (width/2)) };
            int[] ypoints = { (int) yPosition, (int) (yPosition + height), (int) (yPosition + height) };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }
}
