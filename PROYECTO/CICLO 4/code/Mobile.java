/**
 * This Bridge's subclass has the same behaviors that a normal Bridge, 
 * except it relocates it's position if it's possible jumping to the next strand increasing 
 * its radius by a 20% (if it's possible).
 * 
 * @author (Daniel Diaz y Miguel Motta) 
 * @version (CICLO 4, 2024 / 01)
 */

public class Mobile extends Bridge {
    /**
     * Constructor for objects of class Mobile
     */
    public Mobile(String color, int distance, int inicialStrand, int finalStrand, Web web, boolean isVisible) {
        super(color, distance, inicialStrand, finalStrand, web, isVisible);
    }

    /**
     * Its the behavior of the bridge
     */
    @Override
    public void act() {
        super.deleteBridge();
        int newDistance = (int) (this.distance + this.distance * 0.2);
        if (this.finalStrand != this.web.getNumStrands()) {
            this.web.addBridge("mobile", this.color, newDistance, this.inicialStrand + 1);
        } else {
            this.web.addBridge("mobile", this.color, newDistance, 1);
        }
    }
}

