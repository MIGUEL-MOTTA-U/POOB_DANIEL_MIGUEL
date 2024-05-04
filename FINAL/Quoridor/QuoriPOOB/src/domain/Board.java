package domain;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {

	private int size;
	private Player playerPlaying;
	private Object[][] matrixBoard;
	private ArrayList<Token> tokens;
	private ArrayList<Square> squares;
	private ArrayList<Wall> walls;
	private HashMap<Color, Player> players;

	public Board(int size, HashMap<String, int[][]> specialSquares) throws QuoriPOOBException {
		if (size <= 0) throw new QuoriPOOBException(QuoriPOOBException.WRONG_SIZE);
		this.size = size;
		if (specialSquares != null) createSpecialSquares(specialSquares);
		createNormalSquares();
	}

	public void setPlayers(HashMap<Color, Player> players) {
		this.players = players;
	}

	public void addWallToBoard(Wall w) {
		// putWallBoard
		// walls.remove(wallToPut);
	}

	public void moveTokenUp(Color color) {
		// In Construction
	}

	public void moveTokenDown(Color color) {
		// In Construction
	}

	public void moveTokenLeft(Color color) {
		// In Construction
	}

	public void moveTokenRight(Color color) {
		// In Construction
	}

	public void moveTokenUpLeft(Color color) {
		// In Construction
	}

	public void moveTokenUpRight(Color color) {
		// In Construction
	}

	public void moveTokenDownLeft(Color color) {
		// In Construction
	}

	public void moveTokenDownRight(Color color) {
		// In Construction
	}

	// Getters and Setters

	public int getSize() {
		return this.size;
	}

	public Player getPlayerPlaying() {
		return this.playerPlaying;
	}

	public void setPlayerPlaying(Player player) {
		this.playerPlaying = player;
	}

	// Private Methods

	private void createNormalSquares() throws QuoriPOOBException {
		for (int row = 0; row < this.size; row++) {
			for (int column = 0; column < this.size; column++) {
				if (matrixBoard[row][column] == null) {
					Square emptySquare = new NormalSquare(row, column, this);
					matrixBoard[row][column] = emptySquare;
					squares.add(emptySquare);
				}
			}
		}
	}

	private void createSpecialSquares(HashMap<String, int[][]> specialSquares) {
		this.matrixBoard = new Object[this.size][this.size];
		for (Map.Entry<String, int[][]> entry : specialSquares.entrySet()) {
			String type = entry.getKey();
			int[][] squares = entry.getValue();
			for (int i = 0; i < squares.length; i++) {
				int row = squares[i][0];
				int column = squares[i][1];
				Square square = createSquare(type, row, column);
				matrixBoard[row][column] = square;
				this.squares.add(square);
			}
		}
	}

	private Square createSquare(String type, int row, int column) {
		Square square = null;
		try {
			Class<?> cls = Class.forName(type);
			if (!Square.class.isAssignableFrom(cls)) throw new QuoriPOOBException(QuoriPOOBException.SQUARE_NOT_EXIST);
			Constructor<?> constructor = cls.getDeclaredConstructor(int.class, int.class, Board.class);
			constructor.setAccessible(true);
			square = (Square) constructor.newInstance(row, column, this);
			this.squares.add(square);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return square;
	}
}
