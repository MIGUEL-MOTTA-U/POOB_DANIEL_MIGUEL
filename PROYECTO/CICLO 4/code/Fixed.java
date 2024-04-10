import java.util.HashMap;

/**
 * This Bridge's subclass has the same behaviors that a normal Bridge,
 * except it can't be deleted
 * 
 * @author (Daniel Diaz y Miguel Motta)
 * @version (CICLO 4, 2024 / 01)
 */

public class Fixed extends Bridge {
    private Circle leftEnd;
    private Circle rightEnd;

    /**
     * Constructor for objects of class Fixed
     */
    public Fixed(String color, int distance, int inicialStrand, int finalStrand, Web web, boolean isVisible) {
        super(color, distance, inicialStrand, finalStrand, web, isVisible);
    }

    private void createBody() {
        double x1 = this.body.getX1() - (5/2);
        double y1 = this.body.getY1() - (5/2);
        double x2 = this.body.getX2() - (5/2);
        double y2 = this.body.getY2() - (5/2);

        this.leftEnd = new Circle(x1, y1, this.color, this.isVisible, 5);
        this.rightEnd = new Circle(x2, y2, this.color, this.isVisible, 5);
    }

    @Override
    public void addBridge(HashMap<Integer, Strand> strands, int inicialStrand, int finalStrand) {
        super.addBridge(strands, inicialStrand, finalStrand);
        createBody();
        draw();
    }

    /**
     * Delete the bridge from the web
     */
    @Override
    public void deleteBridge() {
        System.out.println("No se puede eliminar un puente que sea de tipo fixed");
    }

    @Override
    protected void draw() {
        super.draw();
        if (isVisible) {
            leftEnd.makeVisible();
            rightEnd.makeVisible();
        }
    }

    @Override
    protected void erase() {
        super.erase();
        if (isVisible) {
            leftEnd.makeInvisible();
            rightEnd.makeInvisible();
        }
    }
}