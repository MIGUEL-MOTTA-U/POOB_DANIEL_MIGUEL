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
	
	/**
	 * Create a player by given name and color.
	 * @param name	The name of the player
	 * @param color	The color of the player and the respective token.
	 */
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
	
	public void addWalls(int normalW, int temporaryW, int longW, int alliedW) throws QuoriPOOBException{ // Check the reflection in Player.java, in the method addWall()
		// If there is just one player, do we have to create a Machine player? or throw an Exception?
		// Get the colors:
        ArrayList<Color> keysList = new ArrayList<Color>(players.keySet());
        Color key1 = keysList.get(0);
        Color key2 = keysList.get(1);
        // Add the walls
		for (int i=0;i < normalW;i++) {
			players.get(key1).addWall("NORMAL"); // Player 1
			players.get(key2).addWall("NORMAL"); // Player 2
			}
		for(int i=0;i< temporaryW;i++) {
			players.get(key1).addWall("TEMPORARY");
			players.get(key2).addWall("TEMPORARY");
			}
		for(int i=0;i< temporaryW;i++) {
			players.get(key1).addWall("LONG");
			players.get(key2).addWall("LONG");
			}	
		for(int i=0;i< temporaryW;i++) {
			players.get(key1).addWall("ALLIED");
			players.get(key2).addWall("ALLIED");
			}
		board.setWalls(players.get(key1).getWalls());
	}
	
	public void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide) {
		board.addWallToBoard(type, initialRow, initialColumn, squareSide);
	}
}
