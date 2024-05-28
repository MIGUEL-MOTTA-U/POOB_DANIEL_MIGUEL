package domain;

/**
 * Create a wall.
 * 
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public abstract class Wall {
	protected Board board;
	protected String color;
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
	public Wall(String color) {
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

		setWallInSquare(initialRow, initialColumn);
		setWallInSquare(finalRow, finalColumn);
		setWallInNeighborSquares();

		board.addWallToBoard(this);
	}

	/**
	 * Delete the wall from the board
	 * 
	 * @throws QuoriPOOBException
	 */
	public void delWallFromBoard() throws QuoriPOOBException {
		if (this.board == null){
			QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.BOARD_UNDEFINED);
			Log.record(e);
			throw e;
		}
		this.board.delWallFromBoard(this);
		delWallFromBoardMatrix();

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
	public boolean blockToken(String token) {
		return true;
	}

	/**
	 * Return the wall color
	 * 
	 * @return the wall color
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * Return the inicial row of the wall
	 * 
	 * @return the inicial row of the wall
	 */
	public int getInicialRow() {
		return this.initialRow;
	}

	/**
	 * Return the inicial column of the wall
	 * 
	 * @return the inicial column of the wall
	 */
	public int getInicialColumn() {
		return this.initialColumn;
	}

	/**
	 * Return the side of the square where the wall is located
	 * 
	 * @return the side where the wall is located
	 */
	public String getSquareSide() {
		return this.squareSide;
	}

	/**
	 * Its the behavior of the wall
	 * 
	 * @throws QuoriPOOBException
	 */
	public abstract void act() throws QuoriPOOBException;

	/*
	 * Deletes the wall from the board Matrix
	 */
	protected void delWallFromBoardMatrix() {
		for (int i = initialRow; i <= finalRow; i++) {
			for (int j = initialColumn; j <= finalColumn; j++) {
				switch (squareSide.toUpperCase()) {
					case "UP":
						board.getMatrixBoard()[i][j].delWallUp();
						break;
					case "LEFT":
						board.getMatrixBoard()[i][j].delWallLeft();
						break;
					case "DOWN":
						board.getMatrixBoard()[i][j].delWallDown();
						break;
					case "RIGHT":
						board.getMatrixBoard()[i][j].delWallRight();
						break;
					default:
				}
			}
		}
	}

	/*
	 * Calculate the final row of the wall
	 */
	protected int finalRow(int initialRow, String squareSide) throws QuoriPOOBException {
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
				QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
				Log.record(e);
				throw e;
		}
	}

	/*
	 * Calculate the final column of the wall
	 */
	protected int finalColumn(int initialColumn, String squareSide) throws QuoriPOOBException {
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
				QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
				Log.record(e);
				throw e;
		}
	}

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

	/*
	 * Check the wall does not intersect other wall
	 */
	protected boolean intersectWall(int initialRow, int initialColumn, String squareSide, Board board)
			throws QuoriPOOBException {
		boolean res = false;
		int finalRow = finalRow(initialRow, squareSide);
		int finalColumn = finalColumn(initialColumn, squareSide);

		for (int i = initialRow; i <= finalRow; i++) {
			for (int j = initialColumn; j <= finalColumn; j++) {
				switch (squareSide.toUpperCase()) {
					case "UP":
						if (initialRow != 0 && j != finalColumn) {
							res = board.getMatrixBoard()[i][j].getWallRight() != null && board.getMatrixBoard()[i][j]
									.getWallRight().equals(board.getMatrixBoard()[i - 1][j].getWallRight());
						}
						break;
					case "DOWN":
						if (initialRow != board.getSize() - 1 && j != finalColumn) {
							res = board.getMatrixBoard()[i][j].getWallRight() != null && board.getMatrixBoard()[i][j]
									.getWallRight().equals(board.getMatrixBoard()[i + 1][j].getWallRight());
						}
						break;
					case "LEFT":
						if (initialColumn != 0 && i != finalRow) {
							res = board.getMatrixBoard()[i][j].getWallDown() != null && board.getMatrixBoard()[i][j]
									.getWallDown().equals(board.getMatrixBoard()[i][j - 1].getWallDown());
						}
						break;
					case "RIGHT":
						if (initialColumn != board.getSize() - 1 && i != finalRow) {
							res = board.getMatrixBoard()[i][j].getWallDown() != null && board.getMatrixBoard()[i][j]
									.getWallDown().equals(board.getMatrixBoard()[i][j + 1].getWallDown());
						}
						break;
					default:
						QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
						Log.record(e);
						throw e;
				}

				if (res)
					return true;
			}
		}
		
		return false;
	}

	/*
	 * Check if the wall is in the range of the board
	 */
	protected boolean outRange(int initialRow, int initialColumn, String squareSide, Board board)
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
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
				Log.record(e);
				throw e;
		}
	}

	/*
	 * Check if there is already a wall in the square where the wall will be placed
	 */
	protected boolean wallInSquare(int row, int column, String squareSide, Board board) throws QuoriPOOBException {
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
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
				Log.record(e);
				throw e;
		}
	}

	/*
	 * Add the wall in the square
	 */
	protected void setWallInSquare(int row, int column) throws QuoriPOOBException {
		Square square = this.board.getSquare(row, column);

		switch (this.squareSide.toUpperCase()) {
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
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
				Log.record(e);
				throw e;
		}
	}

	/*
	 * Set the wall in the neighbor walls
	 */
	protected void setWallInNeighborSquares() throws QuoriPOOBException {
		Square initialSquare;
		Square finalSquare;

		switch (this.squareSide.toUpperCase()) {
			case "UP":
				if (this.initialRow > 0) {
					initialSquare = this.board.getSquare(this.initialRow - 1, this.initialColumn);
					finalSquare = this.board.getSquare(this.initialRow - 1, this.initialColumn + 1);
					initialSquare.addWallDown(this);
					finalSquare.addWallDown(this);
				}
				break;
			case "LEFT":
				if (this.initialColumn > 0) {
					initialSquare = this.board.getSquare(this.initialRow, this.initialColumn - 1);
					finalSquare = this.board.getSquare(this.initialRow + 1, this.initialColumn - 1);
					initialSquare.addWallRight(this);
					finalSquare.addWallRight(this);
				}
				break;
			case "DOWN":
				if (this.initialRow < this.board.getSize() - 1) {
					initialSquare = this.board.getSquare(this.initialRow + 1, this.initialColumn);
					finalSquare = this.board.getSquare(this.initialRow + 1, this.initialColumn + 1);
					initialSquare.addWallUp(this);
					finalSquare.addWallUp(this);
				}
				break;
			case "RIGHT":
				if (this.initialColumn < this.board.getSize() - 1) {
					initialSquare = this.board.getSquare(this.initialRow, this.initialColumn + 1);
					finalSquare = this.board.getSquare(this.initialRow + 1, this.initialColumn + 1);
					initialSquare.addWallLeft(this);
					finalSquare.addWallLeft(this);
				}
				break;
			default:
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
				Log.record(e);
				throw e;
		}
	}
}
