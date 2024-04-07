
/**
 * This Spot's subclass has the same behaviors that a normal Spot,
 * except that when the spider reaches this spot, it dies and disappears.
 * 
 * @author (Daniel Diaz y Miguel Motta)
 * @version (CICLO 4 / 2024 - 01)
 */
public class Killer extends Spot {
    /**
     * Constructor for objects of class Killer
     */
    public Killer(String color, int strand, Web web, double xPosition, double yPosition, boolean isVisible) {
        super(color, strand, web, xPosition, yPosition, isVisible);
    }

    /**
     * Its the behavior of the spot
     */
    @Override
    public void act() {
        this.web.spiderKill();
    }
}
