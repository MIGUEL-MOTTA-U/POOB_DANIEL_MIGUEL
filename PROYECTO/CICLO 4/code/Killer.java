
/**
 * This Spot's subclass has the same behaviors that a normal Spot,
 * except that when the spider reaches this spot, it dies and disappears.
 * 
 * @author (Daniel Diaz y Miguel Motta) 
 * @version (CICLO 4 / 2024 - 01)
 */
public class Killer extends Spot
{
    public Killer(String color, int strand, Web web, double xPosition, double yPosition, boolean isVisible){
        super(color, strand, web, xPosition, yPosition, isVisible);
    }

    @Override
    public void act() {
        Boolean spiderInspot = this.web.spiderInspot(this);
        if (spiderInspot) {
            this.web.spiderKill();
        }
    }
}
