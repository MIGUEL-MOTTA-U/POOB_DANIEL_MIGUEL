package domain;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {

	private int size;
	private Player playerPlaying;
	private Square[][] matrixBoard;
	private ArrayList<Square> squares;
	private ArrayList<Wall> walls;
	private HashMap<Color, Token> tokens;
	private HashMap<Color, Player> players;

	public Board(int size, HashMap<String, int[][]> specialSquares) throws QuoriPOOBException {
		if (size <= 1) throw new QuoriPOOBException(QuoriPOOBException.WRONG_SIZE);

		this.size = size;
		this.matrixBoard = new Square[size][size];
		this.squares = new ArrayList<>();
		this.tokens = new HashMap<>(2);
		this.players = new HashMap<>(2);

		if (specialSquares != null) createSpecialSquares(specialSquares);
		createNormalSquares();
		setTokensToBoard();
	}

	public void setPlayers(HashMap<Color, Player> players) {
		this.players = players;
	}
	
	public void setTokens(HashMap<Color, Token> tokens) {
		this.tokens = tokens;
	}

	public void addWallToBoard(Wall wall) {
		this.walls.add(wall);
	}

	public void delWallFromBoard(Wall wall) {
		this.walls.remove(wall);
	}

	public void moveTokenUp(Color colotToken) {
		// In Construction
	}

	public void moveTokenDown(Color colotToken) {
		// In Construction
	}

	public void moveTokenLeft(Color colotToken) {
		// In Construction
	}

	public void moveTokenRight(Color colotToken) {
		// In Construction
	}

	public void moveTokenUpLeft(Color colotToken) {
		// In Construction
	}

	public void moveTokenUpRight(Color colotToken) {
		// In Construction
	}

	public void moveTokenDownLeft(Color colotToken) {
		// In Construction
	}

	public void moveTokenDownRight(Color colotToken) {
		// In Construction
	}

	public void returnTwoMoves(Color colotToken) throws QuoriPOOBException {
		if (!this.tokens.containsKey(colotToken)) throw new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);

		Token token = this.tokens.get(colotToken);
		token.returnTwoMoves();
		int newRow = token.getRow();
		int newColumn = token.getColumn();
		
		Square square = token.getSquare();
		square.delToken();
		square = this.matrixBoard[newRow][newColumn];
		square.setToken(token);
		token.setSquare(square);
	}

	// Getters and Setters

	public Object[][] getMatrixBoard(){
		return matrixBoard;
	}
	
	public int getSize() {
		return this.size;
	}

	public Square getSquare(int row, int column) {
		return this.matrixBoard[row][column];
	}

	public Player getPlayerPlaying() {
		return this.playerPlaying;
	}

	public void setPlayerPlaying(Player player) {
		// In construction
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

	private void createSpecialSquares(HashMap<String, int[][]> specialSquares) throws QuoriPOOBException{
		for (Map.Entry<String, int[][]> entry : specialSquares.entrySet()) {
			String type = entry.getKey();
			int[][] squares = entry.getValue();
			if (squares.length > Math.pow(this.matrixBoard.length, 2)) throw new QuoriPOOBException(QuoriPOOBException.WRONG_NUMER_SQUARES);
			for (int i = 0; i < squares.length; i++) {
				int row = squares[i][0];
				int column = squares[i][1];
				Square square = createSquare(type, row, column);
				matrixBoard[row][column] = square;
				this.squares.add(square);
			}
		}
	}

	private void setTokensToBoard() throws QuoriPOOBException {
		int position = this.size / 2;
		Square squareToken1 = this.matrixBoard[0][position];
		Square squareToken2 = this.matrixBoard[this.size - 1][position];
	
		boolean assignToken1 = false;
		for (Token token : this.tokens.values()) {
			if (!assignToken1) {
				token.setInitialRow(0);
				token.setInitialColumn(position);
				token.setSquare(squareToken1);
				squareToken1.setToken(token);
				assignToken1 = true;
			} else {
				token.setInitialRow(this.size - 1);
				token.setInitialColumn(position);
				token.setSquare(squareToken2);
				squareToken2.setToken(token);
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
