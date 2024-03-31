/**
 * This Bridge's subclass has the same behaviors that a normal Bridge, 
 * except it relocates it's position if it's possible jumping to the next strand increasing 
 * its radius by a 20% (if it's possible).
 * 
 * @author (Daniel Diaz y Miguel Motta) 
 * @version (CICLO 4, 2024 / 01)
 */

public class Fixed extends Bridge {
    public Fixed(String color, int distance, int inicialStrand, int finalStrand, boolean isVisible) {
        super(color, distance, inicialStrand, finalStrand, isVisible);
    }
}