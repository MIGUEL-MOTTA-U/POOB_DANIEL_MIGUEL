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
	
}
