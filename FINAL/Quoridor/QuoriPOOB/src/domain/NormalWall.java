package domain;

/**
 * This wall's subclass has the same behaviors that a normal wall.
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2023
 */
public class NormalWall extends Wall {
    /**
     * Constructor for objects of class NormalWall
     * 
     * @param color the color of the wall
     */
    public NormalWall(String color) {
        super(color);
    }

    /**
     * Its the behavior of the wall.
     */
    @Override
    public void act() {
    }
}
