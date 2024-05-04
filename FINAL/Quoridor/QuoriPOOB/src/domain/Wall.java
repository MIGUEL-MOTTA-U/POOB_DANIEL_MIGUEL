package domain;

import java.awt.Color;

public abstract class  Wall {
	protected Board board;
	protected Color color;
	protected int initialRow;
	protected int initialColumn;
	protected String squareSide;

	public Wall(Color color) {
		this.color = color;
	}

	public void addWall(int initialRow, int initialColumn, String squareSide, Board board) throws QuoriPOOBException {
		if (initialRow > board.getSize() || initialColumn > board.getSize()) throw new QuoriPOOBException(QuoriPOOBException.WALL_OUT_OF_RANGE);

		this.initialColumn = initialColumn;
		this.initialRow = initialRow;
		this.squareSide = squareSide;
	}
}
