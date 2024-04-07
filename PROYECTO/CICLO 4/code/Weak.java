
/**
 * This Bridge's subclass has the same behaviors that a normal Bridge, 
 * except when the spider walks through it, then it will be deleted.
 * 
 * @author (Daniel Diaz y Miguel Motta) 
 * @version (CICLO 4, 2024 / 01)
 */

public class Weak extends Bridge {
    public Weak(String color, int distance, int inicialStrand, int finalStrand, Web web, boolean isVisible) {
        super(color, distance, inicialStrand, finalStrand, web, isVisible);
    }

    @Override
    public void act() {
        this.deleteBridge();
    }
}

