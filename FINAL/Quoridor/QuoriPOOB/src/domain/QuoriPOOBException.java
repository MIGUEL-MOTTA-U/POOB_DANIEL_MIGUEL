package domain;

public class QuoriPOOBException extends Exception {
	public static String TOKEN_OUT_OF_RANGE = "The token can not move out of the board";
	public static String WALL_OUT_OF_RANGE = "The wall can not be placed out of the board";
	public static String WALL_NOT_EXIST = "The wall does not exist";
	public static String SQUARE_NOT_EXIST = "The square does not exist";
	public static String SQUARE_SIDE_NOT_EXIST = "There is not square beside";
	public static String BOARD_UNDEFINED = "There is not board defined";

	public QuoriPOOBException(String message) {
		super(message);
	}

	public static final String NO_SUBCLASS_OF_WALL(String type) {
		return "The " + type + " class is not a subclass of wall";
	}
}
