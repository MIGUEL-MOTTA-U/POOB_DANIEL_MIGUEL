package spiderweb;
import shapes.*;
import java.util.HashMap;

/**
 * This Bridge's subclass has the same behaviors that a normal Bridge,
 * except when it is deleted, if it is possible it will create a spot of the
 * same color in the same strand.
 * 
 * @author (Daniel Diaz y Miguel Motta)
 * @version (CICLO 4, 2024 / 01)
 */

public class Transformer extends Bridge {
    private Circle centerCircle;

    /**
     * Constructor for objects of class Fixed
     */
    public Transformer(String color, int distance, int inicialStrand, int finalStrand, Web web, boolean isVisible) {
        super(color, distance, inicialStrand, finalStrand, web, isVisible);
    }

    /**
     * Add the bridge to the web with the new body
     * 
     * @param strands       the strands of the web
     * @param inicialStrand the strand where the bridge begin
     * @param finalStrand   the strand where the bridge end
     */
    @Override
    public void addBridge(HashMap<Integer, Strand> strands) {
        super.addBridge(strands);
        createBody();
        draw();
    }

    /**
     * Delete the bridge from the web
     */
    @Override
    public void deleteBridge() {
        Boolean spotInStrand = this.web.spotInStrand(this.inicialStrand);
        if (!spotInStrand) {
            super.deleteBridge();
            this.web.addSpot("normal", this.color, this.inicialStrand);
            this.web.setOk(true);
        } else {
            this.web.setOk(false);
        }
    }

    @Override
    public void act() {
        
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
        double x1 = this.body.getX1();
        double y1 = this.body.getY1();
        double x2 = this.body.getX2();
        double y2 = this.body.getY2();
        double x = ((x1 + x2) / 2) - (5/2);
        double y = ((y1 + y2) / 2) - (5/2);

        this.centerCircle = new Circle(x, y, this.color, this.isVisible, 5);
    }
}
