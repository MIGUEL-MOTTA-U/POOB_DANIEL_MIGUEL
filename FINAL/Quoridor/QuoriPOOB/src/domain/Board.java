package domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {

	private int rows;
	private int numWalls;
	private Player playerPlaying;
	private Object[][] matrixBoard;
	private ArrayList<Token> tokens;
	private ArrayList<Square> squares;
	private ArrayList<Wall> walls;
	private HashMap<Color, Player> players;

	public Board(int size, HashMap<String, int[][]> specialSquares) {
		rows = size;
		createMatrix(size);
		createSpecialSquares(specialSquares);
	}

	public void setPlayers(HashMap<Color, Player> players) {
		this.players = players;
	}

	public void addWallToBoard(Wall w) {
		// putWallBoard
		// walls.remove(wallToPut);
	}

	public void moveTokenUp(Color color){
		// In Construction
	}
	public void moveTokenDown(Color color){
		// In Construction
	}
	public void moveTokenLeft(Color color){
		// In Construction
	}
	public void moveTokenRight(Color color){
		// In Construction
	}
	public void moveTokenUpLeft(Color color){
		// In Construction
	}
	public void moveTokenUpRight(Color color){
		// In Construction
	}
	public void moveTokenDownLeft(Color color){
		// In Construction
	}
	public void moveTokenDownRight(Color color){
		// In Construction
	}

	// Getters and Setters
	public Player getPlayerPlaying() {
		return this.playerPlaying;
	}

	// Private Methods

	private void createMatrix(int n){
		matrixBoard = new Object[n][n];
		for (int i = 0; i< n; i++){
			for (int j = 0; j < n; j++){
				Square emptySquare = new Square(i,j);
				matrixBoard[i][j] = emptySquare;
				squares.add(emptySquare);
			}
		}
	}

	private void createSpecialSquares(HashMap<String, int[][]> specialSquares){
		
	}
}
