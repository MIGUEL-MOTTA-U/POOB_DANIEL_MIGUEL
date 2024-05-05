package domain;

public class QuoriPOOBException extends Exception {
	public static String TOKEN_OUT_OF_RANGE = "The token can not move out of the board";
	public static String WALL_OUT_OF_RANGE = "The wall can not be placed out of the board";
	public static String SQUARE_OUT_OF_RANGE = "The square can not be placed out of the board";
	public static String WALL_NOT_EXIST = "The wall does not exist";
	public static String SQUARE_NOT_EXIST = "The square does not exist";
	public static String PLAYER_NOT_EXIST = "The user not exist";
	public static String SQUARE_SIDE_NOT_EXIST = "There is not square beside";
	public static String BOARD_UNDEFINED = "There is not board defined";
	public static String WRONG_TOKEN_DIRECTION = "The direction is not defined";
	public static String WRONG_SIZE = "Board size must be greater than 1";
	public static String WRONG_NUMBER_PLAYERS = "There can be no more than 2 players";
	public static String WRONG_NUMBER_WALLS = "there must be 1 more wall to the size of the board";
	public static String INSUFFICIENT_WALLS = "There are no walls of this type";
	public static String SAME_PLAYER_COLOR = "There can be no 2 players with the same color";
	public static String TWO_MACHINES = "There can be no 2 machines";
	public static String WRONG_NUMER_SQUARES = "There can be no more squares than positions in the board";
	
	
	public QuoriPOOBException(String message) {
		super(message);
	}
}
