package domain;
/**
 * @autor: Daniel Diaz and Miguel Motta
 * This class has all the Exceptions that, class Square might present.
 */
public class SquareException extends Exception{
    public static final String WRONG_DIMENSIONS = "Dimensions must be between 1 and 16 (The max number of colors)";
    public static final String WRONG_DIRECTION = "The Direction is Incorrect, just can move to North, West, East or South";
    public static final String LOSER = "The Token dropped into a wrong hollow, GAME OVER.";
    
    public SquareException(String message){
        super(message);
    }
}