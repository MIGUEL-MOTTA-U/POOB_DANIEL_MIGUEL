package domain;

public class QuoriPOOBException extends Exception {
	public static String TOKEN_OUT_OF_RANGE = "The token can not move out of the board";
	public static String WALL_OUT_OF_RANGE = "The wall can not be placed out of the board";
	public static String WALL_NOT_EXIST = "The wall does not exist";
	public static String SQUARE_NOT_EXIST = "The square does not exist";
	public static String SQUARE_SIDE_NOT_EXIST = "There is not square beside";
	public static String BOARD_UNDEFINED = "There is not board defined";
	public static String TOKEN_WRONG_DIRECTION = "The direction is not defined";
	public static String USER_NOT_EXIST = "The user not exist";
	public static String WRONG_SIZE = "Board size must be greater than 0";
	
	public QuoriPOOBException(String message) {
		super(message);
	}
}
