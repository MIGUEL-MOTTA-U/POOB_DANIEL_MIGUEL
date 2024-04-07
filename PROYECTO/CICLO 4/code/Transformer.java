
/**
 * This Bridge's subclass has the same behaviors that a normal Bridge, 
 * except when it is deleted, if it is possible it will create a spot of the same color.
 * 
 * @author (Daniel Diaz y Miguel Motta) 
 * @version (CICLO 4, 2024 / 01)
 */

public class Transformer extends Bridge {
    public Transformer(String color, int distance, int inicialStrand, int finalStrand, Web web, boolean isVisible) {
        super(color, distance, inicialStrand, finalStrand, web, isVisible);
    }

    @Override
    public void deleteBridge(){
        super.deleteBridge();
        Boolean spotInStrand = this.web.spotInStrand(this.inicialStrand);
        if (!spotInStrand) {
            this.web.addSpot("normal", this.color, this.inicialStrand);
        }
    }

    @Override
    public void act() {
    };
}

