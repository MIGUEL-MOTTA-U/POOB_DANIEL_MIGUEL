package domain;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Board {

	private int size;
	private Player playerPlaying;
	private Square[][] matrixBoard;
	private ArrayList<Square> squares;
	private ArrayList<Wall> walls;
	private LinkedHashMap<Color, Token> tokens;
	private LinkedHashMap<Color, Player> players;

	public Board(int size, HashMap<String, int[][]> specialSquares) throws QuoriPOOBException {
		if (size <= 1) throw new QuoriPOOBException(QuoriPOOBException.WRONG_SIZE);

		this.size = size;
		this.matrixBoard = new Square[size][size];
		this.squares = new ArrayList<>();
		this.tokens = new LinkedHashMap<>(2);
		this.players = new LinkedHashMap<>(2);
		this.walls = new ArrayList<>();

		if (specialSquares != null) createSpecialSquares(specialSquares);
		createNormalSquares();
	}

	public void setPlayers(LinkedHashMap<Color, Player> players) {
		this.players = players;
		this.playerPlaying = this.players.entrySet().iterator().next().getValue();
	}
	
	public void setTokens(LinkedHashMap<Color, Token> tokens) throws QuoriPOOBException {
		this.tokens = tokens;
		setTokensToBoard();
	}

	public void addWallToBoard(Wall wall) {
		this.walls.add(wall);
		nextTurn();
	}

	public void delWallFromBoard(Wall wall) {
		this.walls.remove(wall);
	}

	public void moveTokenUp(Color colorToken) throws QuoriPOOBException {
		if (!this.tokens.containsKey(colorToken))
			throw new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);
		
		Token token = this.tokens.get(colorToken);
		Square square = token.getSquare();

		if (tokenAhead("UP", square)) {
			if (token.getRow() < 2)
				throw new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);

			int row = square.getCoordenates()[0];
			int column = square.getCoordenates()[1];
			Square nextSquare = this.matrixBoard[row - 1][column];
			
			if (!square.blockUp(colorToken) & !nextSquare.blockUp(colorToken)) {
				token.jumpTokenUp();
			} else {
				throw new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
			}
		} else {
			if (token.getRow() <= 0) 
				throw new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);

			if (!square.blockUp(colorToken)) {
				token.moveUp();
			} else {
				throw new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
			}
		}
	}

	public void moveTokenDown(Color colorToken) throws QuoriPOOBException {
		if (!this.tokens.containsKey(colorToken))
			throw new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);
		
		Token token = this.tokens.get(colorToken);
		Square square = token.getSquare();

		if (tokenAhead("DOWN", square)) {
			if (token.getRow() > this.size - 3)
				throw new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);

			int row = square.getCoordenates()[0];
			int column = square.getCoordenates()[1];
			Square nextSquare = this.matrixBoard[row + 1][column];
			
			if (!square.blockDown(colorToken) & !nextSquare.blockDown(colorToken)) {
				token.jumpTokenDown();
			} else {
				throw new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
			}
		} else {
			if (token.getRow() >= this.size - 1) 
			throw new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);


			if (!square.blockDown(colorToken)) {
				token.moveDown();
			} else {
				throw new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
			}
		}
	}

	public void moveTokenLeft(Color colorToken)  throws QuoriPOOBException {
		if (!this.tokens.containsKey(colorToken))
			throw new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);
		
		Token token = this.tokens.get(colorToken);
		Square square = token.getSquare();

		if (tokenAhead("LEFT", square)) {
			if (token.getColumn() < 2)
				throw new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);

			int row = square.getCoordenates()[0];
			int column = square.getCoordenates()[1];
			Square nextSquare = this.matrixBoard[row][column - 1];
			
			if (!square.blockLeft(colorToken) & !nextSquare.blockLeft(colorToken)) {
				token.jumpTokenLeft();
			} else {
				throw new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
			}
		} else {
			if (token.getColumn() == 0) 
				throw new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);


			if (!square.blockLeft(colorToken)) {
				token.moveLeft();
			} else {
				throw new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
			}
		}
	}

	public void moveTokenRight(Color colorToken) throws QuoriPOOBException{
		if (!this.tokens.containsKey(colorToken))
			throw new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);
		
		Token token = this.tokens.get(colorToken);
		Square square = token.getSquare();

		if (tokenAhead("RIGHT", square)) {
			if (token.getColumn() > this.size - 3)
				throw new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);

			int row = square.getCoordenates()[0];
			int column = square.getCoordenates()[1];
			Square nextSquare = this.matrixBoard[row][column + 1];
			
			if (!square.blockRight(colorToken) & !nextSquare.blockRight(colorToken)) {
				token.jumpTokenRight();
			} else {
				throw new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
			}
		} else {
			if (token.getColumn() >= this.size - 1) 
				throw new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);


			if (!square.blockRight(colorToken)) {
				token.moveRight();
			} else {
				throw new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
			}
		}
	}

	public void moveTokenUpLeft(Color colorToken) {
		// In Construction
	}

	public void moveTokenUpRight(Color colorToken) {
		// In Construction
	}

	public void moveTokenDownLeft(Color colorToken) {
		// In Construction
	}

	public void moveTokenDownRight(Color colorToken) {
		// In Construction
	}

	public void returnTwoMoves(Color colorToken) throws QuoriPOOBException {
		if (!this.tokens.containsKey(colorToken)) throw new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);

		Token token = this.tokens.get(colorToken);
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

	public Square[][] getMatrixBoard(){
		return this.matrixBoard;
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
		int position = (this.size % 2 == 0) ? (this.size / 2) - 1: (this.size / 2);
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

	private void nextTurn() {
		for (Player player : this.players.values()) {
			if (player != this.playerPlaying) {
				this.playerPlaying = player;
				break;
			}
		}
	}

	private boolean tokenAhead(String direction, Square currentSquare) throws QuoriPOOBException {
		Square nextSquare;
		int row = currentSquare.getCoordenates()[0];
		int column = currentSquare.getCoordenates()[1];
		switch (direction) {
			case "UP":
				nextSquare = this.matrixBoard[row - 1][column];
				return nextSquare.getToken() != null;
			case "LEFT":
				nextSquare = this.matrixBoard[row][column - 1];
				return nextSquare.getToken() != null;
			case "DOWN":
				nextSquare = this.matrixBoard[row + 1][column];
				return nextSquare.getToken() != null;
			case "RIGHT":
				nextSquare = this.matrixBoard[row][column + 1];
				return nextSquare.getToken() != null;
			default:
				throw new QuoriPOOBException(QuoriPOOBException.WRONG_TOKEN_DIRECTION);
		}
	}
}
