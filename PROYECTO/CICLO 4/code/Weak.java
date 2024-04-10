import java.util.HashMap;

/**
 * This Bridge's subclass has the same behaviors that a normal Bridge,
 * except when the spider walks through it, it will be deleted.
 * 
 * @author (Daniel Diaz y Miguel Motta)
 * @version (CICLO 4, 2024 / 01)
 */

public class Weak extends Bridge {
    private Circle broken;

    /**
     * Constructor for objects of class Fixed
     */
    public Weak(String color, int distance, int inicialStrand, int finalStrand, Web web, boolean isVisible) {
        super(color, distance, inicialStrand, finalStrand, web, isVisible);
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

        this.broken = new Circle(x, y, "white", this.isVisible, 5);
    }

    /**
     * Add the bridge to the web with the new body
     * 
     * @param strands       the strands of the web
     * @param inicialStrand the strand where the bridge begin
     * @param finalStrand   the strand where the bridge end
     */
    @Override
    public void addBridge(HashMap<Integer, Strand> strands, int inicialStrand, int finalStrand) {
        super.addBridge(strands, inicialStrand, finalStrand);
        createBody();
        draw();
    }

    /**
     * It's the behavior of the bridge
     */
    @Override
    public void act() {
        this.deleteBridge();
    }

    /*
     * Draw the bridge with current specifications on screen.
     */
    @Override
    protected void draw() {
        super.draw();
        if (isVisible) {
            this.broken.makeVisible();        
        }
    }

    /*
     * Erase the bridge on screen.
     */
    @Override
    protected void erase() {
        super.erase();
        if (isVisible) {
            this.broken.makeInvisible();
        }
    }
}
