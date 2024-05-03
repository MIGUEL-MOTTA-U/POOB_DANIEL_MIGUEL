package domain;

public class QuoriPOOBException extends Exception{
	public static String TOKEN_OUT_OF_RANGE= "THE TOKEN CAN NOT MOVE OUT OF THE BOARD";
	public static String WALL_OUT_OF_RANGE= "THE WALL CAN NOT BE PLACED OUT OF THE BOARD";
	public static String WALL_NOT_EXIST= "THE WALL DOES NOT EXIST";
	public static String SQUARE_NOT_EXIST= "THE SQUARE DOES NOT EXIST";
	public static String SQUARE_SIDE_NOT_EXIST= "THERE IS NOT SQUARE BESIDE";
	public static String BOARD_UNDEFINED= "THERE IS NOT BOARD DEFINED";
	public QuoriPOOBException(String message) {
		super(message);
	}
	
}
