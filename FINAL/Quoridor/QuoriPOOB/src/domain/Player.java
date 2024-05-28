package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Constructor;
import java.util.List;

/**
 * An abstract class representing a player in the Quoridor game.
 * It implements the Serializable interface.
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2023
 */
public abstract class Player {
	protected String name;
	protected String color;
	protected String time;
	protected Board board;
	protected ArrayList<Wall> walls;

	/**
	 * Constructor for the Player class.
	 *
	 * @param name  The name of the player.
	 * @param color The color assigned to the player.
	 */
	public Player(String name, String color) {
		this.color = color;
		this.name = name;
		this.walls = new ArrayList<>();
	}

	/**
	 * Sets the game board for the player.
	 *
	 * @param board The game board.
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Adds walls of different types to the player's wall list.
	 *
	 * @param normal    The number of normal walls.
	 * @param temporary The number of temporary walls.
	 * @param WLong     The number of long walls.
	 * @param allied    The number of allied walls.
	 * @throws QuoriPOOBException If there is an issue with creating walls.
	 */
	public void addWalls(int normal, int temporary, int WLong, int allied) throws QuoriPOOBException {
		createWalls(normal, "domain.NormalWall");
		createWalls(temporary, "domain.Temporary");
		createWalls(WLong, "domain.LongWall");
		createWalls(allied, "domain.Allied");
	}

	/**
	 * Removes a wall from the player's wall list.
	 *
	 * @param wall The wall to be removed.
	 */
	public void delWall(Wall wall) {
		this.walls.remove(wall);
	}

	/**
	 * Return the number of walls of each type available to the player.
	 *
	 * @return A HashMap containing the number of walls for each type.
	 */
	public HashMap<String, Integer> numberWalls() {
		HashMap<String, Integer> numberWalls = new HashMap<>();
		int normalWalls = 0;
		int temporaryWalls = 0;
		int longWalls = 0;
		int alliedWalls = 0;

		for (Wall wall : this.walls) {
			String cls = wall.getClass().getSimpleName().toUpperCase();
			if (cls.equals("NORMALWALL")) {
				normalWalls++;
			} else if (cls.equals("TEMPORARY")) {
				temporaryWalls++;
			} else if (cls.equals("LONGWALL")) {
				longWalls++;
			} else {
				alliedWalls++;
			}
		}

		numberWalls.put("NormalWall", normalWalls);
		numberWalls.put("Temporary", temporaryWalls);
		numberWalls.put("LongWall", longWalls);
		numberWalls.put("Allied", alliedWalls);

		return numberWalls;
	}

	/*
	 * Moves the player's token in the specified direction.
	 */
	protected void moveToken(String direction) throws QuoriPOOBException {
		switch (direction.toUpperCase()) {
			case "UP":
				board.moveTokenUp(this.color);
				break;
			case "DOWN":
				board.moveTokenDown(this.color);
				break;
			case "LEFT":
				board.moveTokenLeft(this.color);
				break;
			case "RIGHT":
				board.moveTokenRight(this.color);
				break;
			case "UPLEFT":
				board.moveTokenUpLeft(this.color);
				break;
			case "UPRIGHT":
				board.moveTokenUpRight(this.color);
				break;
			case "DOWNLEFT":
				board.moveTokenDownLeft(this.color);
				break;
			case "DOWNRIGHT":
				board.moveTokenDownRight(this.color);
				break;
			default:
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.WRONG_TOKEN_DIRECTION);
				Log.record(e);
				throw e;
		}
	}

	/*
	 * Add a wall to the board if it is possible and it doesn't block the path of
	 * any token
	 */
	protected void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide)
			throws QuoriPOOBException {
		if (!numberWalls().containsKey(type)){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.WALL_NOT_EXIST);
			Log.record(e);
			throw e;
		}
		if (numberWalls().get(type) <= 0){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.INSUFFICIENT_WALLS);
			Log.record(e);
			throw e;
		}

		Wall wallToPut = null;

		for (Wall w : this.walls) {
			if (w.getClass().getSimpleName().toUpperCase().equals(type.toUpperCase())) {
				wallToPut = w;
				break;
			}
		}

		wallToPut.addWallToBoard(initialRow, initialColumn, squareSide, this.board);
		Grafo mappedBoard = mapBoard();

		if (blockWay(wallToPut, mappedBoard) || getOtherPlayer().blockWay(wallToPut, getOtherPlayer().mapBoard())) {
			wallToPut.delWallFromBoard();
			wallToPut = null;
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.BLOCK_THE_WAY);
			Log.record(e);
			throw e;
		}

		delWall(wallToPut);
	}

	/*
	 * This method verify if is it possible for the tokens to reach the final
	 * destiny
	 */
	protected boolean blockWay(Wall w, Grafo g) {
		boolean res = true;
		Token playerToken = board.getTokens().get(color);
		int destiny = playerToken.getDestiny(), size = board.getSize();
		int currentNode = ((playerToken.getRow()) * size) + playerToken.getColumn();

		// Recorro las casillas de meta
		for (int i = destiny * size; i < destiny * size + size; i++) {
			if (g.isConnected(currentNode, i) && checkDestinySquare((Square) g.getNodes().get(i), destiny))
				return false;
		}

		return res;
	}

	/*
	 * This method returns the shortest path for the player by representing the
	 * board matrix as a graph and using Dijkstra's algorithm to find the shortest.
	 */
	protected ArrayList<Square> calculateMyShorestPath() {
		Grafo graph = mapBoard();
		ArrayList<Square> res = new ArrayList<>();
		List<Integer> minPath = new ArrayList<>(), path = new ArrayList<>();
		Token playerToken = board.getTokens().get(color);
		int destiny = playerToken.getDestiny(), size = board.getSize();
		int currentNode = ((playerToken.getRow()) * size) + playerToken.getColumn();

		for (int i = destiny * size; i < destiny * size + size; i++) {
			try {
				path = graph.shortestWay(currentNode, i);
			} catch (QuoriPOOBException e) {
			}

			if (minPath.size() == 0)
				minPath = path;

			if ((path.size() < minPath.size() && path.size() > 1) &&
					checkDestinySquare((Square) graph.getNodes().get(i), destiny))
				minPath = path;
		}

		for (int node : minPath) {
			res.add((Square) graph.getNodes().get(node));
		}

		return res;
	}

	/**
	 * Verify if the player won after move the token
	 * 
	 * @return true if is winner, false otherwise.
	 */
	public boolean checkWin() {
		return board.checkWin(color);
	}

	/**
	 * Return the name of the player
	 * 
	 * @return name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the color of the player
	 * 
	 * @return color
	 */
	public String getColor() {
		return color;
	}

	/*
	 * Return the opposing player.
	 */
	protected Player getOtherPlayer() {
		return board.getOtherPlayer();
	}

	/**
	 * Assign a time to the player
	 * 
	 * @param time of the player
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/*
	 * Creates the wall for the player
	 */
	private void createWalls(int quantity, String type) throws QuoriPOOBException {
		Wall wall = null;

		for (int i = 0; i < quantity; i++) {
			wall = createWall(type);
			this.walls.add(wall);
		}
	}

	/*
	 * Creates a wall for the player by the given type
	 */
	private Wall createWall(String type) throws QuoriPOOBException {
		Wall wall = null;

		try {
			Class<?> cls = Class.forName(type);
			Constructor<?> constructor = cls.getDeclaredConstructor(String.class);
			constructor.setAccessible(true);
			wall = (Wall) constructor.newInstance(this.color);
		} catch (Exception e) {
			QuoriPOOBException q = new QuoriPOOBException(QuoriPOOBException.WALL_NOT_EXIST);
			Log.record(q);
			throw q;
		}

		return wall;
	}

	/*
	 * Verify if the given square is a possible final destiny and it is not blocked
	 */
	private boolean checkDestinySquare(Square box, int destiny) {
		if (destiny == 0) {
			return box.getWallUp() == null;
		} else {
			return box.getWallDown() == null;
		}
	}

	/*
	 * Map the matrix board to return a map of the squares with the respective
	 * conections
	 */
	private Grafo mapBoard() {
		Grafo graph = new Grafo(board.getSize() * board.getSize());
		int id = 0;
		int size = board.getSize();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Square box = board.getMatrixBoard()[i][j];
				graph.addNode(id, box);

				if (makeConectionLeft(box)) {// No hay ni muro a la izquierda de box, o un borde
					graph.addVertex(id, id - 1, 1);
				} else if (makeJumpLeft(box)) {
					graph.addVertex(id, id - 2, 1);
				}
				if (makeConectionUp(box)) {
					graph.addVertex(id, id - size, 1);
				} else if (makeJumpUp(box)) {
					graph.addVertex(id, id - 2 * size, 1);
				}

				id++;
			}
		}

		return graph;
	}

	/*
	 * Return true if it is possible to jump the token, false otherwise
	 */
	private boolean makeJumpUp(Square box) {
		int row = box.getCoordenates()[0], column = box.getCoordenates()[1];
		return (row > 1) &&
				(board.getMatrixBoard()[row - 2][column].getToken() == null ||
						board.getMatrixBoard()[row - 2][column].getToken().getColor().equals(this.color))
				&&
				(box.getWallUp() == null || !box.getWallUp().blockToken(this.color)) &&
				(board.getMatrixBoard()[row - 2][column].getWallUp() == null
						|| !board.getMatrixBoard()[row - 2][column].getWallUp().blockToken(this.color));
	}

	/*
	 * Return true if it is possible to jump the token, false otherwise
	 */
	private boolean makeJumpLeft(Square box) {
		int row = box.getCoordenates()[0], column = box.getCoordenates()[1];
		return (column > 1) &&
				(board.getMatrixBoard()[row][column - 2].getToken() == null ||
						board.getMatrixBoard()[row][column - 2].getToken().getColor().equals(this.color))
				&&
				(box.getWallLeft() == null || !box.getWallLeft().blockToken(this.color)) &&
				(board.getMatrixBoard()[row][column - 2].getWallLeft() == null
						|| !board.getMatrixBoard()[row][column - 2].getWallLeft().blockToken(this.color));
	}

	/*
	 * Return true if it is possible to move up the token, false otherwise
	 */
	private boolean makeConectionUp(Square box) {
		int row = box.getCoordenates()[0];

		return (row != 0) && (box.getWallUp() == null || !box.getWallUp().blockToken(this.color));
	}

	/*
	 * Return true if it is possible to move left the token, false otherwise
	 */
	private boolean makeConectionLeft(Square box) {
		int column = box.getCoordenates()[1];

		return (column != 0) && (box.getWallLeft() == null || !box.getWallLeft().blockToken(this.color));
	}
}
