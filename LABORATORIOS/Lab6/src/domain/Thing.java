package domain;
import java.awt.Color;

/**
 * Represents entities that share shape, color and perform an action
 *
 * @author ECI-2024
 */
public interface Thing{
    public static final int ROUND = 1;
    public static final int SQUARE = 2;
    public static final int FLOWER = 3;

    /**
     * Defines the action to be performed by the Thing
     */
    public void act();
    
    /**
     * Return the shape of the thing
     * @return  an integer representing the shape of the thing
     */
    default public int shape(){
        return SQUARE;
    }
    
    /**
     * Return the color of the thing
     * @return  an object of type Color representing the color of the thing
     */
    default public Color getColor(){
        return Color.blue;
    };
    
    /**
     * Return if an object implements this interface
     * @return  that the object implementes this interface
     */
    default public boolean is(){
        return true;
    }
}
