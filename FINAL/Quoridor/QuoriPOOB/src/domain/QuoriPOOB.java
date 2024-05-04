package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;

public class QuoriPOOB {
	private String gameMode;
	private Board board;
	private HashMap<Color, Player> players;

	public QuoriPOOB() {

	}

	public void createBoard(int size, HashMap<String, int[][]> specialSquares) {
		board = new Board(size, specialSquares);
		board.setPlayers(this.players);
		for (Player p : players.values()) {
			p.setBoard(board);
		}
	}

	public void addWalls(int normal, int temporary, int longWall, int allied) { 
		// Toca hacer la verificacion de que el jugador jugando obtenido concuerde con uno de los jugadores de esta clase
		Player player = board.getPlayerPlaying();
		player.addWalls(normal, temporary, longWall, allied);
	}

	public void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide) {
		board.addWallToBoard(type, initialRow, initialColumn, squareSide);
	}
}
