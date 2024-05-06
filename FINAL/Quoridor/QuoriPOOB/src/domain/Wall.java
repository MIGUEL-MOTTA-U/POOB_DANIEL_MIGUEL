package domain;

import java.awt.Color;

public abstract class Wall {
	protected Board board;
	protected Square square;
	protected Color color;
	protected int initialRow;
	protected int initialColumn;
	protected int finalRow;
	protected int finalColumn;
	protected String squareSide;

	public Wall(Color color) {
		this.color = color;
	}

	public void addWallToBoard(int initialRow, int initialColumn, String squareSide, Board board) throws QuoriPOOBException {
		if (initialRow > board.getSize() || initialColumn > board.getSize() || initialRow < 0 || initialColumn < 0)
			throw new QuoriPOOBException(QuoriPOOBException.WALL_OUT_OF_RANGE);
		if (!checkSquareSide(squareSide))
			throw new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
		if (!checkRange(initialRow, initialColumn, squareSide, board))
			throw new QuoriPOOBException(QuoriPOOBException.WALL_OUT_OF_RANGE);

		setWallInSquare(initialRow, initialColumn, squareSide);
		setWallInSquare(initialRow + 1, initialColumn + 1, squareSide);

		this.initialColumn = initialColumn;
		this.initialRow = initialRow;
		this.finalRow = initialRow + 1;
		this.finalColumn = initialColumn + 1;
		this.squareSide = squareSide;
		this.board = board;

		board.addWallToBoard(this);
	}

	private boolean checkSquareSide(String squareSide) {
		switch (squareSide.toUpperCase()) {
			case "UP":
				return true;
			case "LEFT":
				return true;
			case "DOWN":
				return true;
			case "RIGHT":
				return true;
			default:
				return false;
		}
	}

	private boolean checkRange(int initialRow, int initialColumn, String squareSide, Board board) {
		switch (squareSide.toUpperCase()) {
			case "UP":
				return (initialColumn >= board.getSize()) ? false : true;
			case "LEFT":
				return (initialRow >= board.getSize()) ? false : true;
			case "DOWN":
				return (initialColumn >= board.getSize()) ? false : true;
			case "RIGHT":
				return (initialRow >= board.getSize()) ? false : true;
			default:
				return false;
		}
	}

	private void setWallInSquare(int row, int column, String squareSide) throws QuoriPOOBException {
		Square square = this.board.getSquare(row, column);
		switch (squareSide.toUpperCase()) {
			case "UP":
				if (square.getWallUp() != null) throw new QuoriPOOBException(QuoriPOOBException.WALL_IN_SQUARE);
				square.addWallUp(this);
			case "LEFT":
				if (square.getWallLeft() != null) throw new QuoriPOOBException(QuoriPOOBException.WALL_IN_SQUARE);
				square.addWallLeft(this);
			case "DOWN":
				if (square.getWallDown() != null) throw new QuoriPOOBException(QuoriPOOBException.WALL_IN_SQUARE);
				square.addWallDown(this);
			case "RIGHT":
				if (square.getWallRight() != null) throw new QuoriPOOBException(QuoriPOOBException.WALL_IN_SQUARE);
				square.addWallRight(this);
			default:
				throw new QuoriPOOBException(QuoriPOOBException.SQUARE_SIDE_NOT_EXIST);
		}
	}
}
