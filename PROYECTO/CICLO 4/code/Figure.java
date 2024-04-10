/**
 * A Figure that can be manipulated and that draws itself on a canvas.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 1.0. (15 July 2000)
 */

public abstract class Figure {
    protected double xPosition;
    protected double yPosition;
    protected String color;
    protected boolean isVisible;

    /**
     * Constructor for objects of class Figure
     */
    public Figure(double x, double y, String color, boolean isVisible) {
        this.xPosition = x;
        this.yPosition = y;
        this.color = color;
        this.isVisible = isVisible;
    }

    /**
     * Make this figure visible. If it was already visible, do nothing.
     */
    public void makeVisible() {
        isVisible = true;
        draw();
    }

    /**
     * Make this figure invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible() {
        erase();
        isVisible = false;
    }

    /**
     * Move the figure a few pixels to the right.
     */
    public void moveRight() {
        moveHorizontal(20);
    }

    /**
     * Move the figure a few pixels to the left.
     */
    public void moveLeft() {
        moveHorizontal(-20);
    }

    /**
     * Move the figure a few pixels up.
     */
    public void moveUp() {
        moveVertical(-20);
    }

    /**
     * Move the figure a few pixels down.
     */
    public void moveDown() {
        moveVertical(20);
    }

    /**
     * Move the figure horizontally.
     * 
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance) {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the figure vertically.
     * 
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance) {
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the figure horizontally.
     * 
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance) {
        int delta;

        if (distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for (int i = 0; i < distance; i++) {
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the figure vertically
     * 
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance) {
        int delta;

        if (distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for (int i = 0; i < distance; i++) {
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the color.
     * 
     * @param color the new color. Valid colors are "red", "black",
     *              "blue", "yellow", "green", "magenta", "gray", "orange",
     *              "purple", "brown", "cyan",
     *              "darkgreen", "aquamarine", "lavender" and "salmon"
     */
    public void changeColor(String newColor) {
        color = newColor;
        draw();
    }

    /*
     * Erase the figure on screen.
     */
    protected void erase() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

    protected abstract void draw();
}
