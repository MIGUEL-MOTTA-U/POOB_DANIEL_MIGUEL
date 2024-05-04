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

	public void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide) {
		Wall wallToPut = null;
		for (Wall w : walls) {
			if (w.getClass().toString().equals(type)) {
				wallToPut = w;
			}
		}
		// putWallBoard
		// walls.remove(wallToPut);

	}

	public Player getPlayerPlaying() {
		return this.playerPlaying;
	}
}
