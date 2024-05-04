package domain;

import java.util.HashMap;
import java.awt.Color;
import java.lang.reflect.Constructor;

public class QuoriPOOB {
	private String gameMode;
	private Board board;
	private HashMap<Color, Player> players;

	public QuoriPOOB() {
		this.players = new HashMap<>(2);
	}

	public void createBoard(int size, HashMap<String, int[][]> specialSquares) throws QuoriPOOBException {
		board = new Board(size, specialSquares);
		board.setPlayers(this.players);
		for (Player p : players.values()) {
			p.setBoard(board);
		}
	}

	public void createPlayerHuman(String name, Color color) throws QuoriPOOBException {
		if (this.players.size() >= 2) throw new QuoriPOOBException(QuoriPOOBException.WRONG_NUMBER_PLAYERS);
		if (samePlayerColor(color)) throw new QuoriPOOBException(QuoriPOOBException.SAME_PLAYER_COLOR);

		Human player = new Human(name, color);
		this.players.put(color, player);
	}

	public void createPlayerMachine(Color color, String type) throws QuoriPOOBException {
		if (this.players.size() >= 1) throw new QuoriPOOBException(QuoriPOOBException.WRONG_NUMBER_PLAYERS);
		if (samePlayerColor(color)) throw new QuoriPOOBException(QuoriPOOBException.SAME_PLAYER_COLOR);

		try {
			Class<?> cls = Class.forName(type);
			
			if (!Machine.class.isAssignableFrom(cls)) throw new QuoriPOOBException(QuoriPOOBException.PLAYER_NOT_EXIST);
			Constructor<?> constructor = cls.getDeclaredConstructor(String.class, Color.class);
			constructor.setAccessible(true);
			Machine machine = (Machine) constructor.newInstance("Machine", color);
			this.players.put(color, machine);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void addWalls(int normal, int temporary, int longWall, int allied) throws QuoriPOOBException {
		int numberWalls = normal + temporary + longWall + allied;
		if (numberWalls != this.board.getSize() + 1) throw new QuoriPOOBException(QuoriPOOBException.WRONG_NUMBER_WALLS);

		for (Player player : this.players.values()) {
			player.addWalls(normal, temporary, longWall, allied);
		}
	}

	public void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide) throws QuoriPOOBException {
		Player player = getCurrentPlayer();
		player.addWallToBoard(type, initialRow, initialColumn, squareSide);
	}

	public void moveToken(Color color, String direction) throws QuoriPOOBException {
		Player player = getCurrentPlayer();
		player.moveToken(color, direction);
	}

	// Getters and Setters

	public void setTime() {
		if (gameMode.toUpperCase().equals(board)) {

		}
	}

	public String[] getNames() {
		String[] names = new String[2];
		int i = 0;
		for (Player player : players.values()) {
			names[i] = player.getName();
			i++;
		}

		return names;
	}

	public Color getColor(String name) throws QuoriPOOBException {
		Color color = null;

		for (Player player : players.values()) {
			if (player.getName().equals(name)) {
				color = player.getColor();
			}
		}

		if (color == null) throw new QuoriPOOBException(QuoriPOOBException.PLAYER_NOT_EXIST);

		return color;
	}

	public Player getCurrentPlayer() {
		return board.getPlayerPlaying();
	}

	private boolean samePlayerColor(Color color) {
		boolean sameColor = false;
		for (Player player : this.players.values()) {
			if (player.getColor().equals(color)) {
				sameColor = true;
				break;
			}
		}

		return sameColor;
	}
}
