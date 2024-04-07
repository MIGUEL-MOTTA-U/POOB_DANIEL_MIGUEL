
/**
 * This Bridge's subclass has the same behaviors that a normal Bridge,
 * except it can't be deleted
 * 
 * @author (Daniel Diaz y Miguel Motta)
 * @version (CICLO 4, 2024 / 01)
 */

public class Fixed extends Bridge {
    /**
     * Constructor for objects of class Fixed
     */
    public Fixed(String color, int distance, int inicialStrand, int finalStrand, Web web, boolean isVisible) {
        super(color, distance, inicialStrand, finalStrand, web, isVisible);
    }

    /**
     * Delete the bridge from the web
     */
    @Override
    public void deleteBridge() {
        System.out.println("No se puede eliminar un puente que sea de tipo fixed");
    }
}