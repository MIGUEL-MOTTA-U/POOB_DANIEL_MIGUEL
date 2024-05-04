package domain;

public abstract class  Wall {
	private String color;
	protected int initialRow;
	protected int initialColumn;
	protected String positionInSquare;

	public Wall() {
		// Creates a Wall
	}

	public void addWall(int initialRow,int initialColumn,String squareSide){
		this.initialColumn = initialColumn;
		this.initialRow = initialRow;
		this.positionInSquare = squareSide;
	}
}
