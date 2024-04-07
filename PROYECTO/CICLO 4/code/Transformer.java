
/**
 * This Bridge's subclass has the same behaviors that a normal Bridge,
 * except when it is deleted, if it is possible it will create a spot of the
 * same color in the same strand.
 * 
 * @author (Daniel Diaz y Miguel Motta)
 * @version (CICLO 4, 2024 / 01)
 */

public class Transformer extends Bridge {
    /**
     * Constructor for objects of class Fixed
     */
    public Transformer(String color, int distance, int inicialStrand, int finalStrand, Web web, boolean isVisible) {
        super(color, distance, inicialStrand, finalStrand, web, isVisible);
    }

    /**
     * Delete the bridge from the web
     */
    @Override
    public void deleteBridge() {
        Boolean spotInStrand = this.web.spotInStrand(this.inicialStrand);
        if (!spotInStrand) {
            super.deleteBridge();
            this.web.addSpot("normal", this.color, this.inicialStrand);
            this.web.setOk(true);
        } else {
            this.web.setOk(false);
        }
    }
}
