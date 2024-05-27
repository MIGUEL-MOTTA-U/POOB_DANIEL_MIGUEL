package domain;

/**
 * The exceptions for the game
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public class QuoriPOOBException extends Exception {
	public static String TOKEN_OUT_OF_RANGE = "The token can not move out of the board";
	public static String WALL_OUT_OF_RANGE = "The wall can not be placed out of the board";
	public static String SQUARE_OUT_OF_RANGE = "The square can not be placed out of the board";
	public static String WALL_NOT_EXIST = "The wall does not exist";
	public static String SQUARE_NOT_EXIST = "The square does not exist";
	public static String PLAYER_NOT_EXIST = "The user not exist";
	public static String TOKEN_NOT_EXIST = "The token not exist";
	public static String SQUARE_SIDE_NOT_EXIST = "There is not square beside";
	public static String MODE_NOT_EXIST = "The mode not exist";
	public static String BOARD_UNDEFINED = "There is not board defined";
	public static String WRONG_TOKEN_DIRECTION = "The direction is not defined";
	public static String WRONG_SIZE = "Board size must be greater than 1";
	public static String WRONG_NUMBER_PLAYERS = "There can be no more than 2 players";
	public static String WRONG_NUMBER_WALLS = "there must be 1 more wall to the size of the board";
	public static String WRONG_NUMER_SQUARES = "There can be no more squares than positions in the board";
	public static String INSUFFICIENT_WALLS = "There are no walls of this type";
	public static String SAME_PLAYER_COLOR = "There can be no 2 players with the same color";
	public static String MISSING_PLAYERS = "QuoriPOOB can just be played with TWO players";
	public static String TWO_PLAYER_MODE = "There can be no machines in two players mode";
	public static String ONE_PLAYER_MODE = "There should be only 1 machine and 1 human in one player mode";
	public static String MODE_UNDEFINED = "The game mode is undefined";
	public static String WALL_IN_SQUARE = "There is already a wall in this square";
	public static String TOKEN_IN_SQUARE = "There is already a token in this square";
	public static String RETURN_MOVES_NOT_POSSIBLE = "There are insufficient movements";
	public static String FORWARD_WALL = "The token cannot pass through a wall";
	public static String DIAGONAL_MOVES_BLOCK = "The token cannot be moved diagonally";
	public static String NAME_NULL = "The name of the player can not be empty";
	public static String COLOR_NULL = "The color of the player can not be empty";
	public static String TYPE_MACHINE_NULL = "The type of machine has to be defined";
	public static String MACHINE_ERROR = "The machine made a mistake calculating the path";
	public static String IMPPOSSIBLE_TO_REACH = "The given start point and finish point of the graph are imppossible to connect by a path.";
	public static String BLOCK_THE_WAY = "It cannot block the way of the token";

	/**
	 * Constructor of QuoriPOOBException
	 * 
	 * @param message the error message
	 */
	public QuoriPOOBException(String message) {
		super(message);
	}

	/**
	 * The message when the game is over
	 * 
	 * @param winner the game winner
	 * @return
	 */
	public static final String GAME_OVER(String winner) {
		winner = (winner == null) ? "None" : winner;

		return "GAME OVER the winner is: " + winner;
	}
}
