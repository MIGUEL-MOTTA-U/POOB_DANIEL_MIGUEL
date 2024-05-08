package domain;

import java.awt.Color;

/**
 * Create a wall.
 * 
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public abstract class Wall {
	protected Board board;
	protected Color color;
	protected int initialRow;
	protected int initialColumn;
	protected int finalRow;
	protected int finalColumn;
	protected String squareSide;

	/**
	 * Constructor for objects of class Wall
	 * 
	 * @param color the color of the wall
	 */
	public Wall(Color color) {
		this.color = color;
	}

	/**
	 * Add the wall to the board
	 * 
	 * @param initialRow    the row where the wall begins
	 * @param initialColumn the column where the wall begins
	 * @param squareSide    the side of the square where the wall will be located
	 * @param board         the board where the wall will be placed
	 * @throws QuoriPOOBException
	 */
	public void addWallToBoard(int initialRow, int initialColumn, String squareSide, Board board)
			throws QuoriPOOBException {
		validate(initialRow, initialColumn, squareSide, board);

		this.initialRow = initialRow;
		this.initialColumn = initialColumn;
		this.finalRow = finalRow(initialRow, squareSide);
		this.finalColumn = finalColumn(initialColumn, squareSide);
		this.squareSide = squareSide;
		this.board = board;

		setWallInSquare(initialRow, initialColumn, squareSide, board);
		setWallInSquare(finalRow, finalColumn, squareSide, board);

		board.addWallToBoard(this);
	}

	/**
	 * Delete the wall from the board
	 * 
	 * @throws QuoriPOOBException
	 */
	public void delWallFromBoard() throws QuoriPOOBException {
		if (this.board == null)
			throw new QuoriPOOBException(QuoriPOOBException.BOARD_UNDEFINED);

		this.board.delWallFromBoard(this);
		this.initialColumn = 0;
		this.initialRow = 0;
		this.finalRow = 0;
		this.finalColumn = 0;
		this.squareSide = null;
		this.board = null;
	}

	/**
	 * Check if the wall is blocking the way to the token
	 * 
	 * @param token the token to pass
	 * @return TRUE, if the wall block the tocken. FALSE, otherwise
	 */
	public boolean blockToken(Color token) {
		return true;
	}

	/**
	 * Its the behavior of the wall
	 * 
	 * @throws QuoriPOOBException
	 */
	public abstract void act() throws QuoriPOOBException;

	/*
	 * Calculate the final row of the wall
	 */
	private int finalRow(int initialRow, String squareSide) throws QuoriPOOBException {
		switch (squareSide.toUpperCase()) {
			case "UP":
				return initialRow;
			case "LEFT":
				return ++initialRow;
			case "DOWN":
				return initialRow;
			case "RIGHT":
				return ++initialRow;
			default:
				throw new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
		}
	}

	/*
	 * Calculate the final column of the wall
	 */
	private int finalColumn(int initialColumn, String squareSide) throws QuoriPOOBException {
		switch (squareSide.toUpperCase()) {
			case "UP":
				return ++initialColumn;
			case "LEFT":
				return initialColumn;
			case "DOWN":
				return ++initialColumn;
			case "RIGHT":
				return initialColumn;
			default:
				throw new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
		}
	}

	/*
	 * Check if the wall can be placed in the position desired by the player
	 */
	private void validate(int initialRow, int initialColumn, String squareSide, Board board) throws QuoriPOOBException {
		if (initialRow > board.getSize() - 1 || initialColumn > board.getSize() - 1 || initialRow < 0
				|| initialColumn < 0)
			throw new QuoriPOOBException(QuoriPOOBException.WALL_OUT_OF_RANGE);
		if (outRange(initialRow, initialColumn, squareSide, board))
			throw new QuoriPOOBException(QuoriPOOBException.WALL_OUT_OF_RANGE);
		if (wallInSquare(initialRow, initialColumn, squareSide, board))
			throw new QuoriPOOBException(QuoriPOOBException.WALL_IN_SQUARE);
		if (wallInSquare(finalRow(initialRow, squareSide), finalColumn(initialColumn, squareSide), squareSide, board))
			throw new QuoriPOOBException(QuoriPOOBException.WALL_IN_SQUARE);
	}

	/*
	 * Check if the wall is in the range of the board
	 */
	private boolean outRange(int initialRow, int initialColumn, String squareSide, Board board)
			throws QuoriPOOBException {
		switch (squareSide.toUpperCase()) {
			case "UP":
				return initialColumn >= (board.getSize() - 1);
			case "LEFT":
				return initialRow >= (board.getSize() - 1);
			case "DOWN":
				return initialColumn >= (board.getSize() - 1);
			case "RIGHT":
				return initialRow >= (board.getSize() - 1);
			default:
				throw new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
		}
	}

	/*
	 * Check if there is already a wall in the square where the wall will be placed
	 */
	private boolean wallInSquare(int row, int column, String squareSide, Board board) throws QuoriPOOBException {
		Square square = board.getSquare(row, column);
		switch (squareSide.toUpperCase()) {
			case "UP":
				return square.getWallUp() != null;
			case "LEFT":
				return square.getWallLeft() != null;
			case "DOWN":
				return square.getWallDown() != null;
			case "RIGHT":
				return square.getWallRight() != null;
			default:
				throw new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
		}
	}

	/*
	 * Add the wall in the square
	 */
	private void setWallInSquare(int row, int column, String squareSide, Board board) throws QuoriPOOBException {
		Square square = board.getSquare(row, column);
		switch (squareSide.toUpperCase()) {
			case "UP":
				square.addWallUp(this);
				break;
			case "LEFT":
				square.addWallLeft(this);
				break;
			case "DOWN":
				square.addWallDown(this);
				break;
			case "RIGHT":
				square.addWallRight(this);
				break;
			default:
				throw new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
		}
	}
}
