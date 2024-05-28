package domain;

/**
 * This wall's subclass has the same behaviors that a normal wall, except it
 * has a length of 3 squares.
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2023
 */
public class LongWall extends Wall {
	private int middleRow;
	private int middleColumn;

	/**
	 * Constructor of Long Wall
	 * 
	 * @param color the color of the wall
	 */
	public LongWall(String color) {
		super(color);
	}

	@Override
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
		this.middleRow = middleRow(initialRow, squareSide);
		this.middleColumn = middleColumn(initialColumn, squareSide);
		this.finalRow = finalRow(initialRow, squareSide);
		this.finalColumn = finalColumn(initialColumn, squareSide);
		this.squareSide = squareSide;
		this.board = board;

		setWallInSquare(initialRow, initialColumn);
		setWallInSquare(middleRow, middleColumn);
		setWallInSquare(finalRow, finalColumn);
		setWallInNeighborSquares();

		board.addWallToBoard(this);
	}

	@Override
	/*
	 * Check if the wall can be placed in the position desired by the player
	 */
	protected void validate(int initialRow, int initialColumn, String squareSide, Board board)
			throws QuoriPOOBException {
		if (initialRow > board.getSize() - 1 || initialColumn > board.getSize() - 1 || initialRow < 0
				|| initialColumn < 0){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.WALL_OUT_OF_RANGE);
			Log.record(e);
				throw e;
			}
		if (outRange(initialRow, initialColumn, squareSide, board)){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.WALL_OUT_OF_RANGE);
			Log.record(e);
				throw e;
			}
		if (wallInSquare(initialRow, initialColumn, squareSide, board)){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.WALL_IN_SQUARE);
			Log.record(e);
				throw e;
			}
		if (wallInSquare(middleRow(initialRow, squareSide), middleColumn(initialColumn, squareSide), squareSide, board)){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.WALL_IN_SQUARE);
			Log.record(e);
				throw e;
			}
		if (wallInSquare(finalRow(initialRow, squareSide), finalColumn(initialColumn, squareSide), squareSide, board)){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.WALL_IN_SQUARE);
			Log.record(e);
				throw e;
			}
		if (intersectWall(initialRow, initialColumn, squareSide, board)){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.WALL_IN_SQUARE);
			Log.record(e);
				throw e;
			}
	}

	@Override
	/*
	 * Calculate the final row of the wall
	 */
	protected int finalRow(int initialRow, String squareSide) throws QuoriPOOBException {
		switch (squareSide.toUpperCase()) {
			case "UP":
				return initialRow;
			case "LEFT":
				return initialRow += 2;
			case "DOWN":
				return initialRow;
			case "RIGHT":
				return initialRow += 2;
			default:
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
				Log.record(e);
				throw e;
		}
	}

	@Override
	/*
	 * Calculate the final column of the wall
	 */
	protected int finalColumn(int initialColumn, String squareSide) throws QuoriPOOBException {
		switch (squareSide.toUpperCase()) {
			case "UP":
				return initialColumn += 2;
			case "LEFT":
				return initialColumn;
			case "DOWN":
				return initialColumn += 2;
			case "RIGHT":
				return initialColumn;
			default:
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
				Log.record(e);
				throw e;
		}
	}

	@Override
	/*
	 * Check if the wall is in the range of the board
	 */
	protected boolean outRange(int initialRow, int initialColumn, String squareSide, Board board)
			throws QuoriPOOBException {
		switch (squareSide.toUpperCase()) {
			case "UP":
				return initialColumn >= (board.getSize() - 2);
			case "LEFT":
				return initialRow >= (board.getSize() - 2);
			case "DOWN":
				return initialColumn >= (board.getSize() - 2);
			case "RIGHT":
				return initialRow >= (board.getSize() - 2);
			default:
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
				Log.record(e);
				throw e;
		}
	}

	@Override
	/*
	 * Set the wall in the neighbor walls
	 */
	protected void setWallInNeighborSquares() throws QuoriPOOBException {
		Square initialSquare;
		Square middleSquare;
		Square finalSquare;

		switch (this.squareSide.toUpperCase()) {
			case "UP":
				if (this.initialRow > 0) {
					initialSquare = this.board.getSquare(this.initialRow - 1, this.initialColumn);
					middleSquare = this.board.getSquare(this.initialRow - 1, this.initialColumn + 1);
					finalSquare = this.board.getSquare(this.initialRow - 1, this.initialColumn + 2);
					initialSquare.addWallDown(this);
					middleSquare.addWallDown(this);
					finalSquare.addWallDown(this);
				}
				break;
			case "LEFT":
				if (this.initialColumn > 0) {
					initialSquare = this.board.getSquare(this.initialRow, this.initialColumn - 1);
					middleSquare = this.board.getSquare(this.initialRow + 1, this.initialColumn - 1);
					finalSquare = this.board.getSquare(this.initialRow + 2, this.initialColumn - 1);
					initialSquare.addWallRight(this);
					middleSquare.addWallRight(this);
					finalSquare.addWallRight(this);
				}
				break;
			case "DOWN":
				if (this.initialRow < this.board.getSize() - 1) {
					initialSquare = this.board.getSquare(this.initialRow + 1, this.initialColumn);
					middleSquare = this.board.getSquare(this.initialRow + 1, this.initialColumn + 1);
					finalSquare = this.board.getSquare(this.initialRow + 1, this.initialColumn + 2);
					initialSquare.addWallUp(this);
					middleSquare.addWallUp(this);
					finalSquare.addWallUp(this);
				}
				break;
			case "RIGHT":
				if (this.initialColumn < this.board.getSize() - 1) {
					initialSquare = this.board.getSquare(this.initialRow, this.initialColumn + 1);
					middleSquare = this.board.getSquare(this.initialRow + 1, this.initialColumn + 1);
					finalSquare = this.board.getSquare(this.initialRow + 2, this.initialColumn + 1);
					initialSquare.addWallLeft(this);
					middleSquare.addWallLeft(this);
					finalSquare.addWallLeft(this);
				}
				break;
			default:
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
				Log.record(e);
				throw e;
		}
	}

	/*
	 * Calculate the final row of the wall
	 */
	private int middleRow(int initialRow, String squareSide) throws QuoriPOOBException {
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
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
				Log.record(e);
				throw e;
		}
	}

	/*
	 * Calculate the final column of the wall
	 */
	private int middleColumn(int initialColumn, String squareSide) throws QuoriPOOBException {
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
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
				Log.record(e);
				throw e;
		}
	}

	/*
	 * There is not a special behaviour in Long Wall
	 */
	@Override
	public void act() {
	}
}
