package domain;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * A board class that represents the object displayed in the interface and the
 * environment in which the game is played
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public class Board {
	private Quoridor quoridor;
	private ArrayList<Square> squares;
	private ArrayList<Wall> walls;
	private LinkedHashMap<String, Token> tokens;
	private LinkedHashMap<String, Player> players;
	private Square[][] matrixBoard;
	private Player playerPlaying;
	private int size;

	/**
	 * Constructor of board
	 * 
	 * @param quoridor       the given quoridor game
	 * @param size           the size of the board
	 * @param specialSquares the set of special squares given by the user
	 * @throws QuoriPOOBException if it is not possible to create the Board
	 */
	public Board(Quoridor quoridor, int size, HashMap<String, int[][]> specialSquares) throws QuoriPOOBException {
		if (size <= 1){
			QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.WRONG_SIZE);
			Log.record(e);
			throw e;
			}
		this.quoridor = quoridor;
		this.size = size;
		this.matrixBoard = new Square[size][size];
		this.squares = new ArrayList<>();
		this.tokens = new LinkedHashMap<>(2);
		this.players = new LinkedHashMap<>(2);
		this.walls = new ArrayList<>();

		if (specialSquares != null) {
			createSpecialSquares(specialSquares);
		} else {
			createRandomSpecialSquares();
		}

		createNormalSquares();
	}

	/**
	 * Return the tokens of the board
	 * 
	 * @return the tokens of the board in a LinkedList
	 */
	public LinkedHashMap<String, Token> getTokens() {
		return tokens;
	}

	/**
	 * Returns if the player won
	 * 
	 * @param colorPlayer the color of the player
	 * @return true if the player won, false otherwise.
	 */
	public boolean checkWin(String colorPlayer) {
		Token tokenPlayer = tokens.get(colorPlayer);

		return tokenPlayer.checkWin();
	}

	/**
	 * Assign the players to the board.
	 * 
	 * @param players players to assign to the board
	 */
	public void setPlayers(LinkedHashMap<String, Player> players) {
		this.players = players;

		Iterator<Map.Entry<String, Player>> iterador = players.entrySet().iterator();
		Map.Entry<String, Player> secondPlayer = null;

		if (iterador.hasNext()) {
			iterador.next();
			if (iterador.hasNext()) {
				secondPlayer = iterador.next();
			}
		}

		if (secondPlayer != null) {
			this.playerPlaying = secondPlayer.getValue();
		}
	}

	/**
	 * Assign the tokens to the board if it is possible
	 * 
	 * @param tokens the tokens to add to the board
	 * @throws QuoriPOOBException if it is not possible to add to the board
	 */
	public void setTokens(LinkedHashMap<String, Token> tokens) throws QuoriPOOBException {
		this.tokens = tokens;
		setTokensToBoard();
	}

	/**
	 * Add a wall to the board
	 * 
	 * @param wall the wall to add to the board
	 */
	public void addWallToBoard(Wall wall) {
		this.walls.add(wall);
	}

	/**
	 * Deletes the wall of the array of walls from board
	 * 
	 * @param wall the given wall to remove from board
	 */
	public void delWallFromBoard(Wall wall) {
		this.walls.remove(wall);
	}

	/**
	 * Moves the token up if it is possible
	 * 
	 * @param colorToken the color of the token to move
	 * @throws QuoriPOOBException if it is not possible to move the token
	 */
	public void moveTokenUp(String colorToken) throws QuoriPOOBException {
		if (!this.tokens.containsKey(colorToken)){
			QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);
			Log.record(e);
				throw e;
			}

		Token token = this.tokens.get(colorToken);
		Square square = token.getSquare();

		if (token.getRow() <= 0){
			QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
			Log.record(e);
				throw e;
			}

		if (tokenAhead("UP", square)) {
			if (token.getRow() < 2){
				QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
				Log.record(e);
				throw e;
			}

			int row = square.getCoordenates()[0];
			int column = square.getCoordenates()[1];
			Square nextSquare = this.matrixBoard[row - 1][column];

			if (!square.blockUp(colorToken) & !nextSquare.blockUp(colorToken)) {
				token.jumpTokenUp();
			} else {
				QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		} else {
			if (!square.blockUp(colorToken)) {
				token.moveUp();
			} else {
				QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		}

		squaresAct();
		wallsAct();
	}

	/**
	 * Moves the token down if it is possible
	 * 
	 * @param colorToken the color of the token to move
	 * @throws QuoriPOOBException if it is not possible to move the token
	 */
	public void moveTokenDown(String colorToken) throws QuoriPOOBException {
		if (!this.tokens.containsKey(colorToken)){
			QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);
			Log.record(e);
				throw e;
			}

		Token token = this.tokens.get(colorToken);
		Square square = token.getSquare();

		if (token.getRow() >= this.size - 1){
			QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
			Log.record(e);
				throw e;
			}

		if (tokenAhead("DOWN", square)) {
			if (token.getRow() > this.size - 3){
				QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
				Log.record(e);
				throw e;
			}

			int row = square.getCoordenates()[0];
			int column = square.getCoordenates()[1];
			Square nextSquare = this.matrixBoard[row + 1][column];

			if (!square.blockDown(colorToken) & !nextSquare.blockDown(colorToken)) {
				token.jumpTokenDown();
			} else {
				QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		} else {
			if (!square.blockDown(colorToken)) {
				token.moveDown();
			} else {
				QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		}

		squaresAct();
		wallsAct();
	}

	/**
	 * Moves the token to the left if it is possible
	 * 
	 * @param colorToken the color of the token to move
	 * @throws QuoriPOOBException if it is not possible to move the token
	 */
	public void moveTokenLeft(String colorToken) throws QuoriPOOBException {
		if (!this.tokens.containsKey(colorToken)){
			QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);
			Log.record(e);
				throw e;
			}

		Token token = this.tokens.get(colorToken);
		Square square = token.getSquare();

		if (token.getColumn() <= 0){
			QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
			Log.record(e);
				throw e;
			}

		if (tokenAhead("LEFT", square)) {
			if (token.getColumn() < 2){
				QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
				Log.record(e);
				throw e;}

			int row = square.getCoordenates()[0];
			int column = square.getCoordenates()[1];
			Square nextSquare = this.matrixBoard[row][column - 1];

			if (!square.blockLeft(colorToken) & !nextSquare.blockLeft(colorToken)) {
				token.jumpTokenLeft();
			} else {
				QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		} else {
			if (!square.blockLeft(colorToken)) {
				token.moveLeft();
			} else {
				QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		}

		squaresAct();
		wallsAct();
	}

	/**
	 * Moves the token to the Right if it is possible
	 * 
	 * @param colorToken the color of the token to move
	 * @throws QuoriPOOBException if it is not possible to move the token
	 */
	public void moveTokenRight(String colorToken) throws QuoriPOOBException {
		if (!this.tokens.containsKey(colorToken)){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);
			Log.record(e);
			throw e;
		}

		Token token = this.tokens.get(colorToken);
		Square square = token.getSquare();

		if (token.getColumn() >= this.size - 1){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
			Log.record(e);
			throw e;	
		}
		if (tokenAhead("RIGHT", square)) {
			if (token.getColumn() > this.size - 3){
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
				Log.record(e);
				throw e;
			}
			int row = square.getCoordenates()[0];
			int column = square.getCoordenates()[1];
			Square nextSquare = this.matrixBoard[row][column + 1];

			if (!square.blockRight(colorToken) & !nextSquare.blockRight(colorToken)) {
				token.jumpTokenRight();
			} else {
				QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		} else {
			if (!square.blockRight(colorToken)) {
				token.moveRight();
			} else {
				QuoriPOOBException e =  new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		}

		squaresAct();
		wallsAct();
	}

	/**
	 * Moves the token to the up left corner if it is possible
	 * 
	 * @param colorToken the color of the token to move
	 * @throws QuoriPOOBException if it is not possible to move the token
	 */
	public void moveTokenUpLeft(String colorToken) throws QuoriPOOBException {
		if (!this.tokens.containsKey(colorToken)){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);
			Log.record(e);
				throw e;
			}

		Token token = this.tokens.get(colorToken);
		Square square = token.getSquare();

		if (square.blockDiagonalMovements()){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.DIAGONAL_MOVES_BLOCK);
			Log.record(e);
				throw e;
			}

		if (token.getRow() <= 0 || token.getColumn() <= 0){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
			Log.record(e);
				throw e;
			}

		if (tokenAhead("UPLEFT", square)) {
			if (token.getRow() < 2 || token.getColumn() < 2){
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
				Log.record(e);
				throw e;
			}

			int row = square.getCoordenates()[0];
			int column = square.getCoordenates()[1];
			Square nextSquare = this.matrixBoard[row - 1][column - 1];

			if (!square.blockUp(colorToken) & !square.blockLeft(colorToken)
					& !nextSquare.blockUp(colorToken) & !nextSquare.blockLeft(colorToken)) {
				token.jumpTokenUpLeft();
			} else {
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		} else {
			if (!square.blockUp(colorToken) & !square.blockLeft(colorToken)) {
				token.moveUpLeft();
			} else {
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		}

		squaresAct();
		wallsAct();
	}

	/**
	 * Moves the token to the up right corner if it is possible
	 * 
	 * @param colorToken the color of the token to move
	 * @throws QuoriPOOBException if it is not possible to move the token
	 */
	public void moveTokenUpRight(String colorToken) throws QuoriPOOBException {
		if (!this.tokens.containsKey(colorToken)){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);
			Log.record(e);
				throw e;
			}

		Token token = this.tokens.get(colorToken);
		Square square = token.getSquare();

		if (square.blockDiagonalMovements()){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.DIAGONAL_MOVES_BLOCK);
			Log.record(e);
				throw e;
			}

		if (token.getRow() <= 0 || token.getColumn() >= this.size - 1){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
			Log.record(e);
				throw e;
			}

		if (tokenAhead("UPRIGHT", square)) {
			if (token.getRow() < 2 || token.getColumn() > this.size - 3){
				QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
				Log.record(e);
				throw e;
			}

			int row = square.getCoordenates()[0];
			int column = square.getCoordenates()[1];
			Square nextSquare = this.matrixBoard[row - 1][column + 1];

			if (!square.blockUp(colorToken) & !square.blockRight(colorToken)
					& !nextSquare.blockUp(colorToken) & !nextSquare.blockRight(colorToken)) {
				token.jumpTokenUpRight();
			} else {
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		} else {
			if (!square.blockUp(colorToken) & !square.blockRight(colorToken)) {
				token.moveUpRight();
			} else {
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		}

		squaresAct();
		wallsAct();
	}

	/**
	 * Moves the token to the down left corner if it is possible
	 * 
	 * @param colorToken the color of the token to move
	 * @throws QuoriPOOBException if it is not possible to move the token
	 */
	public void moveTokenDownLeft(String colorToken) throws QuoriPOOBException {
		if (!this.tokens.containsKey(colorToken)){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);
			Log.record(e);
				throw e;
			}

		Token token = this.tokens.get(colorToken);
		Square square = token.getSquare();

		if (square.blockDiagonalMovements()){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.DIAGONAL_MOVES_BLOCK);
			Log.record(e);
				throw e;
			}

		if (token.getRow() >= this.size - 1 || token.getColumn() <= 0){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
			Log.record(e);
				throw e;
			}

		if (tokenAhead("DOWNLEFT", square)) {
			if (token.getRow() > this.size - 3 || token.getColumn() < 2){
				QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
				Log.record(e);
				throw e;
			}

			int row = square.getCoordenates()[0];
			int column = square.getCoordenates()[1];
			Square nextSquare = this.matrixBoard[row + 1][column - 1];

			if (!square.blockDown(colorToken) & !square.blockLeft(colorToken)
					& !nextSquare.blockDown(colorToken) & !nextSquare.blockLeft(colorToken)) {
				token.jumpTokenDownLeft();
			} else {
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		} else {
			if (!square.blockDown(colorToken) & !square.blockLeft(colorToken)) {
				token.moveDownLeft();
			} else {
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		}

		squaresAct();
		wallsAct();
	}

	/**
	 * Moves the token to the down right corner if it is possible
	 * 
	 * @param colorToken the color of the token to move
	 * @throws QuoriPOOBException if it is not possible to move the token
	 */
	public void moveTokenDownRight(String colorToken) throws QuoriPOOBException {
		if (!this.tokens.containsKey(colorToken)){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);
			Log.record(e);
				throw e;
			}

		Token token = this.tokens.get(colorToken);
		Square square = token.getSquare();

		if (square.blockDiagonalMovements()){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.DIAGONAL_MOVES_BLOCK);
			Log.record(e);
				throw e;
			}

		if (token.getRow() >= this.size - 1 || token.getColumn() >= this.size - 1){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
			Log.record(e);
				throw e;
			}

		if (tokenAhead("DOWNRIGHT", square)) {
			if (token.getRow() > this.size - 3 || token.getColumn() > this.size - 3){
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.TOKEN_OUT_OF_RANGE);
				Log.record(e);
				throw e;
			}

			int row = square.getCoordenates()[0];
			int column = square.getCoordenates()[1];
			Square nextSquare = this.matrixBoard[row + 1][column + 1];

			if (!square.blockDown(colorToken) & !square.blockRight(colorToken)
					& !nextSquare.blockDown(colorToken) & !nextSquare.blockRight(colorToken)) {
				token.jumpTokenDownRight();
			} else {
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		} else {
			if (!square.blockDown(colorToken) & !square.blockRight(colorToken)) {
				token.moveDownRight();
			} else {
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.FORWARD_WALL);
				Log.record(e);
				throw e;
			}
		}

		squaresAct();
		wallsAct();
	}

	/**
	 * Returns the token two movements back
	 * 
	 * @param colorToken color of the token to move
	 * @throws QuoriPOOBException if it is not possible to return
	 */
	public void returnTwoMoves(String colorToken) throws QuoriPOOBException {
		if (!this.tokens.containsKey(colorToken)){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);
			Log.record(e);
				throw e;
			}
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

	/**
	 * Changes the turn to the next player
	 */
	public void nextTurn() {
		for (Player player : this.players.values()) {
			if (player != this.playerPlaying) {
				this.playerPlaying = player;
				break;
			}
		}

		this.quoridor.startTurn();
	}

	/**
	 * Returns the matrix of the board
	 * 
	 * @return the matrix board
	 */
	public Square[][] getMatrixBoard() {
		return this.matrixBoard;
	}

	/**
	 * Returns the size of the board
	 * 
	 * @return the size of the board
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Returs a square in the given position if it is possible
	 * 
	 * @param row    the row of the square
	 * @param column the column of the square
	 * @return the Square in the given coordenates
	 */
	public Square getSquare(int row, int column) {
		return this.matrixBoard[row][column];
	}

	/**
	 * Returns the player that is playing
	 * 
	 * @return the player
	 */
	public Player getPlayerPlaying() {
		return this.playerPlaying;
	}

	/**
	 * The other player (that is not playing)
	 * 
	 * @return the player
	 */
	public Player getOtherPlayer() {
		Player res = null;

		for (Player player : players.values()) {
			if (player != playerPlaying) {
				res = player;
			}
		}

		return res;
	}

	/**
	 * Return the board walls
	 * 
	 * @return the board walls
	 */
	public ArrayList<Wall> getWalls() {
		return this.walls;
	}

	/**
	 * Returns the direction to put the square if it is possible
	 * 
	 * @param colour of the token
	 * @param square the square to move the token
	 * @return the direction to move the token
	 * @throws QuoriPOOBException if it is not possible to move the token
	 */
	public String getDirection(String colour, Square square) throws QuoriPOOBException {
		Token token = tokens.get(colour);
		int row = token.getRow();
		int column = token.getColumn();
		int[] coordenates = square.getCoordenates();
		String res = "";

		if (coordenates[0] == row && coordenates[1] == 1 + column) {
			res = "RIGHT";
		} else if (coordenates[0] == row && coordenates[1] == column - 1) {
			res = "LEFT";
		} else if (coordenates[0] == row + 1 && coordenates[1] == column) {
			res = "DOWN";
		} else if (coordenates[0] == row - 1 && coordenates[1] == column) {
			res = "UP";
		} else {
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.MACHINE_ERROR);
			Log.record(e);
			throw e;
		}

		return res;
	}

	/**
	 * Set a square on the board in the given position
	 * 
	 * @param square the square to add
	 * @param row    the row where the square will be added
	 * @param col    the row where the square will be added
	 */
	public void setSquare(Square square, int row, int col) {
		this.matrixBoard[row][col] = square;
	}

	/**
	 * Set the player playing
	 * 
	 * @param color the player color
	 * @throws QuoriPOOBException
	 */
	public void setPlayerPlaying(String color) throws QuoriPOOBException {
		if (!this.players.containsKey(color)){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.PLAYER_NOT_EXIST);
			Log.record(e);
			throw e;
		}

		this.playerPlaying = this.players.get(color);
	}

	/*
	 * Creates normal squares in the reamaining spaces
	 */
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

	/*
	 * creates the special squares in the board
	 */
	private void createSpecialSquares(HashMap<String, int[][]> specialSquares) throws QuoriPOOBException {
		if (noSpecialSquares(specialSquares))
			return;

		for (Map.Entry<String, int[][]> entry : specialSquares.entrySet()) {
			String type = entry.getKey();
			int[][] squares = entry.getValue();

			if (squares.length > Math.pow(this.size, 2)){
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.WRONG_NUMER_SQUARES);
				Log.record(e);
				throw e;
			}

			for (int i = 0; i < squares.length; i++) {
				int row = squares[i][0];
				int column = squares[i][1];

				if (row > this.size - 1 || column > this.size - 1){
					QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.SQUARE_OUT_OF_RANGE);
					Log.record(e);
					throw e;
				}

				Square square = createSquare(type, row, column);
				matrixBoard[row][column] = square;
				this.squares.add(square);
			}
		}
	}

	/*
	 * Check if the board will have special squares
	 */
	private boolean noSpecialSquares(HashMap<String, int[][]> specialSquares) {
		for (int[][] square : specialSquares.values()) {
			if (square != null) {
				return false;
			}
		}

		return true;
	}

	/*
	 * Creates the special squares in the board in a random way
	 */
	private void createRandomSpecialSquares() throws QuoriPOOBException {
		Random random = new Random();
		String[] squaresType = new String[] { "domain.DoubleTurn", "domain.Teleporter" };

		int totalSpecialSquares = random.nextInt((int) (Math.pow(this.size, 2) / 4));

		for (int i = 0; i < totalSpecialSquares; i++) {
			int row, col;

			do {
				row = random.nextInt(size);
				col = random.nextInt(size);
			} while (this.matrixBoard[row][col] != null);

			String squareType = squaresType[random.nextInt(squaresType.length)];
			Square square = createSquare(squareType, row, col);
			this.matrixBoard[row][col] = square;
		}
	}

	/*
	 * Put the tokens in the board
	 */
	private void setTokensToBoard() throws QuoriPOOBException {
		int position = (this.size % 2 == 0) ? (this.size / 2) - 1 : (this.size / 2);
		Square squareToken1 = this.matrixBoard[0][position];
		Square squareToken2 = this.matrixBoard[this.size - 1][position];
		boolean assignToken1 = false;

		for (Token token : this.tokens.values()) {
			if (!assignToken1) {
				token.setInitialRow(0, size - 1);
				token.setInitialColumn(position);
				token.setSquare(squareToken1);
				squareToken1.setToken(token);
				assignToken1 = true;
			} else {
				token.setInitialRow(this.size - 1, size - 1);
				token.setInitialColumn(position);
				token.setSquare(squareToken2);
				squareToken2.setToken(token);
			}
		}
	}

	/*
	 * Creates an square by the given type in the given coordenates
	 */
	private Square createSquare(String type, int row, int column) throws QuoriPOOBException {
		Square square = null;

		try {
			Class<?> cls = Class.forName(type);
			Constructor<?> constructor = cls.getDeclaredConstructor(int.class, int.class, Board.class);
			constructor.setAccessible(true);
			square = (Square) constructor.newInstance(row, column, this);
			this.squares.add(square);
		} catch (Exception e) {
			QuoriPOOBException q = new QuoriPOOBException(QuoriPOOBException.SQUARE_NOT_EXIST);
				Log.record(q);
				throw q;
		}

		return square;
	}

	/*
	 * Returns true if is a token in the given direction of the square, false
	 * otherwise.
	 */
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
			case "UPRIGHT":
				nextSquare = this.matrixBoard[row - 1][column + 1];
				return nextSquare.getToken() != null;
			case "UPLEFT":
				nextSquare = this.matrixBoard[row - 1][column - 1];
				return nextSquare.getToken() != null;
			case "DOWNRIGHT":
				nextSquare = this.matrixBoard[row + 1][column + 1];
				return nextSquare.getToken() != null;
			case "DOWNLEFT":
				nextSquare = this.matrixBoard[row + 1][column - 1];
				return nextSquare.getToken() != null;
			default:
				QuoriPOOBException e =new QuoriPOOBException(QuoriPOOBException.WRONG_TOKEN_DIRECTION); 
				Log.record(e);
				throw e;
		}
	}

	/*
	 * Act the square where is the token
	 */
	private void squaresAct() throws QuoriPOOBException {
		Player player = this.playerPlaying;
		Token token = tokens.get(player.getColor());
		token.getSquare().act();
	}

	/*
	 * Act the walls in the board
	 */
	private void wallsAct() throws QuoriPOOBException {
		for (int i = 0; i < walls.size(); i++) {
			walls.get(i).act();
		}
	}
}
