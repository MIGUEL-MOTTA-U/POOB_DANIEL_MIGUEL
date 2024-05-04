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
	}

	public void setPlayers(HashMap<Color, Player> players) {
		this.players = players;
	}

	public void addWallToBoard(Wall w) {
		// putWallBoard
		// walls.remove(wallToPut);
	}

	public void moveTokenUp(Color color){
		// En Construccion
	}
	public void moveTokenDown(Color color){
		// En Construccion
	}
	public void moveTokenLeft(Color color){
		// En Construccion
	}
	public void moveTokenRight(Color color){
		// En Construccion
	}
	public void moveTokenUpLeft(Color color){
		// En Construccion
	}
	public void moveTokenUpRight(Color color){
		// En Construccion
	}
	public void moveTokenDownLeft(Color color){
		// En Construccion
	}
	public void moveTokenDownRight(Color color){
		// En Construccion
	}

	public Player getPlayerPlaying() {
		return this.playerPlaying;
	}
}
