
/**
 * This Bridge's subclass has the same behaviors that a normal Bridge, 
 * except when it is deleted, if it is possible it will create a spot of the same color.
 * 
 * @author (Daniel Diaz y Miguel Motta) 
 * @version (CICLO 4, 2024 / 01)
 */

public class Transformer extends Bridge {
    public Transformer(String color, int distance, int inicialStrand, int finalStrand, boolean isVisible) {
        super(color, distance, inicialStrand, finalStrand, isVisible);
    }
}

