package domain;
import java.util.HashMap;
import java.awt.Color;
public class QuoriPOOB {
	private String gameMode;
	private Board board;
	private HashMap<Color, Player> players;
	public QuoriPOOB() {
		
	}
	
	public void createPlayer(String name, Color color) {
		players.put(color, new Player(name, color));
	}
	
	public void createBoard(int size, HashMap<String, int[][]> specialSquares) {
		board = new Board(size, specialSquares);
		board.setPlayers(players);
		for(Player p: players.values()) {
			p.setBoard(board);
		}
	}
	
	public void addWalls(int normalW, int temporaryW, int longW, int alliedW) {
	for (int i=0;i < normalW;i++) {
		players.get(0).addWall("NORMAL"); // Jugador 1
		players.get(1).addWall("NORMAL"); // Jugador 2
		}
	for(int i=0;i< temporaryW;i++) {
		players.get(0).addWall("TEMPORARY");
		players.get(1).addWall("NORMAL");
	}
	for(int i=0;i< temporaryW;i++) {
		players.get(0).addWall("LONG");
		players.get(1).addWall("NORMAL");
	}	
	for(int i=0;i< temporaryW;i++) {
		players.get(0).addWall("ALLIED");
		players.get(1).addWall("NORMAL");
	}
	}
	
}
