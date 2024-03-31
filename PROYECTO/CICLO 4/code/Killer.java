
/**
 * This Spot's subclass has the same behaviors that a normal Spot,
 * except that when the spider reaches this spot, it dies and disappears.
 * 
 * @author (Daniel Diaz y Miguel Motta) 
 * @version (CICLO 4 / 2024 - 01)
 */
public class Killer extends Spot
{
    public Killer(String color, int strand, double xPosition, double yPosition, boolean isVisible){
        super(color, strand, xPosition, yPosition, isVisible);
    }
}
