package spiderweb;
import shapes.*;
import java.util.HashMap;

/**
 * This Spot's subclass has the same behaviors that a normal Spot,
 * except that when the spider reaches this spot, it dies and disappears.
 * 
 * @author (Daniel Diaz y Miguel Motta)
 * @version (CICLO 4 / 2024 - 01)
 */
public class Killer extends Spot {
    private Circle centerCircle;

    /**
     * Constructor for objects of class Killer
     */
    public Killer(String color, int strand, Web web, boolean isVisible) {
        super(color, strand, web, isVisible);
    }

    /**
     * Add the spot to the web
     * 
     * @param strands       the strands of the web
     */
    @Override
    public void addSpot(HashMap<Integer, Strand> strands) {
        super.addSpot(strands);
        createBody();
        draw();
    }

    /**
     * Its the behavior of the spot
     */
    @Override
    public void act() {
        this.web.spiderKill();
    }

    /*
     * Draw the bridge with current specifications on screen.
     */
    @Override
    protected void draw() {
        super.draw();
        if (isVisible) {
            this.centerCircle.makeVisible();        
        }
    }

    /*
     * Erase the bridge on screen.
     */
    @Override
    protected void erase() {
        super.erase();
        if (isVisible) {
            this.centerCircle.makeInvisible();
        }
    }

    /*
     * Crete the new body of the bridge to differentiate it
     */
    private void createBody() {
        double x = this.body.getCenterX() - 1;
        double y = this.body.getCenterY() - 1;

        this.centerCircle = new Circle(x, y, "white", this.isVisible, 2);
    }
}
