import java.util.HashMap;

/**
 * This Spot's subclass has the same behaviors that a normal Spot,
 * except that when the spider reaches this spot, this spot disappear.
 * 
 * @author (Daniel Diaz y Miguel Motta)
 * @version (CICLO 4 / 2024 - 01)
 */
public class Suicide extends Spot {
    private Circle leftArm;
    private Circle rightArm;
    private Circle downArm;
    private Circle upArm;
    /**
     * Constructor for objects of class Suicide
     */
    public Suicide(String color, int strand, Web web, boolean isVisible) {
        super(color, strand, web, isVisible);
    }

    /**
     * Add the spot to the web
     * 
     * @param strands the strands of the web
     */
    @Override
    public void addSpot(HashMap<Integer, Strand> strands) {
        super.addSpot(strands);
        createBody();
        draw();
    }
    
     /*
     * Crete the new body of the suicide spot to differentiate it
     */
    private void createBody() {
        
        double x = this.body.getX();
        double y = this.body.getY();

        this.leftArm = new Circle(x-5, y, this.color, this.isVisible, 5);
        this.rightArm = new Circle(x+5, y, this.color, this.isVisible, 5);
        this.downArm = new Circle(x, y-5, this.color, this.isVisible, 5);
        this.upArm = new Circle(x, y+5, this.color, this.isVisible, 5);
    }

    /**
     * Its the behavior of the spot
     */
    @Override
    public void act() {
        this.web.delSpot(this.color);
    }
    
        /*
     * Erase the spot on screen.
     */
    @Override
    protected void draw() {
        super.draw();
        if (isVisible) {
            this.leftArm.makeVisible();
            this.rightArm.makeVisible();
            this.upArm.makeVisible();
            this.downArm.makeVisible();
        }
    }
    
    /*
     * Draw the spot with current specifications on screen.
     */
    @Override
    protected void erase() {
        super.erase();
        if (isVisible) {
            this.leftArm.makeInvisible();
            this.rightArm.makeInvisible();
            this.upArm.makeInvisible();
            this.downArm.makeInvisible();
        }
    }
}
