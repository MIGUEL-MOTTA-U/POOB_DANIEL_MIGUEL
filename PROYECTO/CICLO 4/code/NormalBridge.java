import java.util.HashMap;

/**
 * This Bridge's subclass has the same behaviors that a normal Bridge,
 * 
 * @author (Daniel Diaz y Miguel Motta)
 * @version (CICLO 4, 2024 / 01)
 */
public class NormalBridge extends Bridge {
    /**
     * Constructor for objects of class Bridge
     */
    public NormalBridge(String color, int distance, int inicialStrand, int finalStrand, Web web, boolean isVisible) {
        super(color, distance, inicialStrand, finalStrand, web, isVisible);
    }

    /**
     * Add the bridge to the web
     * 
     * @param strands       the strands of the web
     * @param inicialStrand the strand where the bridge begin
     * @param finalStrand   the strand where the bridge end
     */
    @Override
    public void addBridge(HashMap<Integer, Strand> strands) {
        super.addBridge(strands);
        draw();
    }

    @Override
    public void act() {
        
    }
}
