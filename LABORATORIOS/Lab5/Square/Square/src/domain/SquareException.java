package domain;

/**
 * @autor: Daniel Diaz and Miguel Motta
 * This class has all the Exceptions that class Square might present.
 */
public class SquareException extends Exception{
    public static final String WRONG_DIMENSIONS = "Dimensions must greater than 0";
    public static final String WRONG_DIRECTION = "The Direction is Incorrect, just can move to North, West, East or South";
    public static final String LIMIT_TOKENS = "Has exceeded the limit of Tokens";
    public static final String UNKNOWN_TOKEN = "The Token does not exist in the Square";
    public static final String TOKEN_EXISTENT = "The Token already exist in the Square";
    

    public SquareException(String message){
        super(message);
    }
}