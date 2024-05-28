package domain;

import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.lang.reflect.Constructor;

/**
 * This class represents a Quoridor game.
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2023
 */
public class Quoridor {
	private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	private List<GameModeObserver> observers = new ArrayList<>();
	private Board board;
	private LinkedHashMap<String, Player> players;
	private LinkedHashMap<String, Token> tokens;
	private Mode mode;
	private boolean onePlayer;
	private boolean twoPlayers;
	private boolean gameOver;
	private Player winner;

	/**
	 * Constructor of Quoridor
	 */
	public Quoridor() {
		this.board = null;
		this.players = new LinkedHashMap<>(2);
		this.tokens = new LinkedHashMap<>(2);
		this.mode = null;
		this.onePlayer = false;
		this.twoPlayers = false;
		this.gameOver = false;
		this.winner = null;
	}

	/**
	 * Set the game mode to one player
	 */
	public void setOnePlayer() {
		this.onePlayer = true;
		this.twoPlayers = false;
	}

	/**
	 * Set the game mode to two players
	 */
	public void setTwoPlayers() {
		this.twoPlayers = true;
		this.onePlayer = false;
	}

	/**
	 * Assign the mode of play according to the given type
	 * 
	 * @param type the tyoe mode
	 * @throws QuoriPOOBException if it is not implemented the mode
	 */
	public void setMode(String type) throws QuoriPOOBException {
		try {
			Class<?> cls = Class.forName(type);
			Constructor<?> constructor = cls.getDeclaredConstructor(Quoridor.class);
			constructor.setAccessible(true);
			this.mode = (Mode) constructor.newInstance(this);
		} catch (Exception e) {
			QuoriPOOBException q = new QuoriPOOBException(QuoriPOOBException.MODE_NOT_EXIST);
			Log.record(q);
			throw q;
		}
	}

	/**
	 * Assign the time to the game mode
	 * 
	 * @param time the time mode
	 */
	public void setTime(int time) {
		this.mode.setTime(time);
	}

	/**
	 * Creates a Board for QuioriPOOB it its possible.
	 * 
	 * @param size           The size of the Board
	 * @param specialSquares The respective specialSquare
	 * @throws QuoriPOOBException The exception in case the Board is created
	 *                            with wrong parameters, or before create two
	 *                            players.
	 */
	public void createBoard(int size, HashMap<String, int[][]> specialSquares) throws QuoriPOOBException {
		if (modeUndefined()){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
			Log.record(e);
			throw e;
		}
		if (gameModeUndefined()){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
			Log.record(e);
			throw e;
		}
		if (players.size() != 2){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.MISSING_PLAYERS);
			Log.record(e);
			throw e;
		}

		board = new Board(this, size, specialSquares);
		board.setPlayers(this.players);
		board.setTokens(this.tokens);

		for (Player p : players.values()) {
			p.setBoard(board);
		}

		for (Token token : this.tokens.values()) {
			token.setBoard(board);
		}
	}

	/**
	 * Creates a Human Player for QuoriPOOB and integrate it to the Game
	 * 
	 * @param name  The name of the Player
	 * @param color The respective color of the Player
	 * @throws QuoriPOOBException Throws an Exception in case the attempt to more
	 *                            than two players, or create a player with wrong
	 *                            parameters.
	 */
	public void createPlayerHuman(String name, String color) throws QuoriPOOBException {
		checkGameFinish();
		if (name == null){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.NAME_NULL);
			Log.record(e);
			throw e;	
		}
		if (color == null){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.COLOR_NULL);
			Log.record(e);
			throw e;	
		}
		if (modeUndefined()){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
			Log.record(e);
			throw e;	
		}
		if (gameModeUndefined()){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
			Log.record(e);
			throw e;	
		}
		if (this.players.size() >= 2){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.WRONG_NUMBER_PLAYERS);
			Log.record(e);
			throw e;	
		}
		if (onePlayer) {
			if (humanPlayerExist()){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.ONE_PLAYER_MODE);
				Log.record(e);
			throw e;	
			}
		}
		if (samePlayerColor(color)){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.SAME_PLAYER_COLOR);
			Log.record(e);
			throw e;	
		}

		Human player = new Human(name, color);
		this.players.put(color, player);
		Token token = new Token(color);
		this.tokens.put(color, token);
	}

	/**
	 * Creates a Machine Player for QuoriPOOB and integrate it to the Game according
	 * to the respective difficulty.
	 * 
	 * @param color The color of the Machine Player.
	 * @param type  The Type (difficulty) of the Machine.
	 * @throws QuoriPOOBException Throws an Exception in case there is other Machine
	 *                            created, or in case the given parameters are
	 *                            wrong, or
	 *                            there are two players already.
	 */
	public void createPlayerMachine(String color, String type) throws QuoriPOOBException {
		checkGameFinish();
		if (type == null){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.TYPE_MACHINE_NULL);
			Log.record(e);
			throw e;	
		}
		if (color == null){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.COLOR_NULL);
			Log.record(e);
			throw e;	
		}
		if (modeUndefined()){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
			Log.record(e);
			throw e;	
		}
		if (this.players.size() >= 2){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.WRONG_NUMBER_PLAYERS);
			Log.record(e);
			throw e;	
		}
		if (twoPlayers){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.TWO_PLAYER_MODE);
			Log.record(e);
			throw e;	
		}
		if (onePlayer) {
			if (machinePlayerExist()){
				QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.ONE_PLAYER_MODE);
				Log.record(e);
			throw e;	
			}
		}
		if (samePlayerColor(color)){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.SAME_PLAYER_COLOR);
			Log.record(e);
			throw e;	
		}

		try {
			Class<?> cls = Class.forName(type);
			Constructor<?> constructor = cls.getDeclaredConstructor(String.class, String.class);
			constructor.setAccessible(true);
			Machine machine = (Machine) constructor.newInstance("Machine", color);
			this.players.put(color, machine);
			Token token = new Token(color);
			this.tokens.put(color, token);
		} catch (Exception e) {
			QuoriPOOBException q = new QuoriPOOBException(QuoriPOOBException.PLAYER_NOT_EXIST);
			Log.record(q);
			throw q;
		}
	}

	/**
	 * Add the given number of walls to each player.
	 * 
	 * @param normal    The given number of normal Walls.
	 * @param temporary The given number of temporary Walls.
	 * @param longWall  The given number of long Walls.
	 * @param allied    The given number of allied Walls.
	 * @throws QuoriPOOBException Throws an Exception in case there is an attempt to
	 *                            add the Walls and there are not players created or
	 *                            a Board created, also if the parameters are wrong.
	 */
	public void addWalls(int normal, int temporary, int longWall, int allied) throws QuoriPOOBException {
		checkGameFinish();
		if (modeUndefined()){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
			Log.record(e);
			throw e;	
		}
		if (gameModeUndefined()){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
			Log.record(e);
			throw e;	
		}
		if (this.board == null){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.BOARD_UNDEFINED);
			Log.record(e);
			throw e;	
		}
		if (players.size() != 2){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.MISSING_PLAYERS);
			Log.record(e);
			throw e;	
		}
		if (normal < 0 || temporary < 0 || allied < 0 || longWall < 0){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.WRONG_NUMBER_WALLS);
			Log.record(e);
			throw e;	
		}
		int numberWalls = normal + temporary + longWall + allied;
		if (numberWalls != this.board.getSize() + 1){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.WRONG_NUMBER_WALLS);
			Log.record(e);
			throw e;	
		}

		for (Player player : this.players.values()) {
			player.addWalls(normal, temporary, longWall, allied);
		}
	}

	/**
	 * Add the given number of walls to a player.
	 * 
	 * @param color     The color of the player
	 * @param normal    The given number of normal Walls.
	 * @param temporary The given number of temporary Walls.
	 * @param longWall  The given number of long Walls.
	 * @param allied    The given number of allied Walls.
	 * @throws QuoriPOOBException Throws an Exception in case there is an attempt to
	 *                            add the Walls and there are not players created or
	 *                            a Board created, also if the parameters are wrong.
	 */
	public void addWallsPlayer(String color, int normal, int temporary, int longWall, int allied)
			throws QuoriPOOBException {
		checkGameFinish();
		if (modeUndefined()){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
			Log.record(e);
			throw e;
			}
		if (gameModeUndefined()){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
			Log.record(e);
			throw e;
			}
		if (normal < 0 || temporary < 0 || allied < 0 || longWall < 0){
			QuoriPOOBException e= new QuoriPOOBException(QuoriPOOBException.WRONG_NUMBER_WALLS);
			Log.record(e);
			throw e;
			}
		if (!this.players.containsKey(color)) {
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.PLAYER_NOT_EXIST);
			Log.record(e);
			throw e;
		}

		Player player = this.players.get(color);
		player.addWalls(normal, temporary, longWall, allied);
	}

	/**
	 * Add a wall to the board by the given parameters.
	 * 
	 * @param type          The type of board
	 * @param initialRow    The initial row where is located the board (reference to
	 *                      the Square).
	 * @param initialColumn The initial column where is located the board (reference
	 *                      to the Square).
	 * @param squareSide    The side of the referenced Square where the wall is
	 *                      located.
	 * @throws QuoriPOOBException Throws an Exception in case the given parameters
	 *                            are wrong or the action is not possible.
	 */
	public void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide)
			throws QuoriPOOBException {
		checkGameFinish();

		Player player = getCurrentPlayer();
		player.addWallToBoard(type, initialRow, initialColumn, squareSide);
		nextTurn();
	}

	/**
	 * Moves the token to the given position.
	 * 
	 * @param direction The respective direction where the token is moving.
	 * @throws QuoriPOOBException Throws an exception in case the parameters are
	 *                            wrong or the action is not possible.
	 */
	public void moveToken(String direction) throws QuoriPOOBException {
		checkGameFinish();

		Player player = getCurrentPlayer();
		player.moveToken(direction);

		if (player.checkWin())
			finishGame(player);
		nextTurn();
	}

	/**
	 * Finish the game when is over
	 */
	public void finishGame() {
		gameOver = true;
		if (this.mode != null) this.mode.cancelTask();
	}

	/**
	 * Set the next player to play (or machine)
	 * 
	 * @throws QuoriPOOBException if it is not possible to assign next turn to the
	 *                            other player
	 */
	public void nextTurn() throws QuoriPOOBException {
		this.board.nextTurn();

		if (this.board.getPlayerPlaying() instanceof Machine)
			startPlayMachine();
	}

	/**
	 * Start the turn of the player
	 */
	public void startTurn() {
		this.mode.startTurn();
	}

	/**
	 * Add the observer of the respective mode
	 * 
	 * @param observer the observer to add
	 */
	public void addObserverToMode(TimeObserver observer) {
		this.mode.addObserver(observer);
	}

	/**
	 * Add the Observer of quoridor
	 * 
	 * @param observer the observer to add
	 */
	public void addObserver(GameModeObserver observer) {
		observers.add(observer);
	}

	/**
	 * Returns the names of the players
	 * 
	 * @return Array of String, from the respective players.
	 */
	public String[] getNames() {
		String[] names = new String[2];
		int i = 0;

		for (Player player : players.values()) {
			names[i] = player.getName();
			i++;
		}

		return names;
	}

	/**
	 * Return the number of walls of each respective type of wall
	 * 
	 * @return the number of walls
	 */
	public HashMap<String, HashMap<String, Integer>> numberWalls() {
		HashMap<String, HashMap<String, Integer>> res = new HashMap<>();

		for (Player p : players.values()) {
			res.put(p.getColor(), p.numberWalls());
		}

		return res;
	}

	/**
	 * Returns the respective color by the given name.
	 * 
	 * @param name The name of the player.
	 * @return The color of the player
	 * @throws QuoriPOOBException Throws an exception in case the parameters are
	 *                            wrong or the action is not possible.
	 */
	public String getColor(String name) throws QuoriPOOBException {
		String color = null;

		for (Player player : players.values()) {
			if (player.getName().equals(name)) {
				color = player.getColor();
			}
		}

		if (color == null){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.PLAYER_NOT_EXIST);
			Log.record(e);
			throw e;
		}

		return color;
	}

	/**
	 * Returns the matrix of the board
	 * 
	 * @return Square matrix board
	 */
	public Square[][] getMatrixBoard() {
		return this.board.getMatrixBoard();
	}

	/**
	 * Returns true if two players play quoridor, false otherwise.
	 * 
	 * @return true if is human vs human, false otherwise
	 */
	public boolean getTwoPlayers() {
		return this.twoPlayers;
	}

	/**
	 * Return the time mode of the game, true if is normal
	 * false otherwise.
	 * 
	 * @return true if is normal mode, false otherwise
	 */
	public boolean timeMode() {
		return !(this.mode instanceof NormalMode);
	}

	/**
	 * The winner player
	 * 
	 * @return the winner player
	 */
	public Player getWinner() {
		return winner;
	}

	/**
	 * The size of the board
	 * 
	 * @return the size
	 */
	public int getSize() {
		return this.board.getSize();
	}

	/**
	 * Returns the player that is actually playing.
	 * 
	 * @return The player that has the turn to play.
	 */
	public Player getCurrentPlayer() {
		return board.getPlayerPlaying();
	}

	/**
	 * Return true if the game is over, false otherwise
	 * 
	 * @return game is over
	 */
	public boolean getGameOver() {
		return this.gameOver;
	}

	/**
	 * Return a token by the given color
	 * 
	 * @param color the token color
	 * @return the token
	 * @throws QuoriPOOBException
	 */
	public Token getToken(String color) throws QuoriPOOBException {
		if (!this.tokens.containsKey(color)){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.TOKEN_NOT_EXIST);
			Log.record(e);
			throw e;	
		}
		return this.tokens.get(color);
	}

	/**
	 * Return a player by the given color
	 * 
	 * @param color the player color
	 * @return the player
	 * @throws QuoriPOOBException
	 */
	public Player getPlayer(String color) throws QuoriPOOBException {
		if (!this.players.containsKey(color)){
			QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.PLAYER_NOT_EXIST);
			Log.record(e);
			throw e;
		}
		return this.players.get(color);
	}

	/**
	 * Return the last movements of the token
	 * 
	 * @param color the token color
	 * @return the last positions of the token
	 * @throws QuoriPOOBException
	 */
	public ArrayList<int[]> getTokenLastMovements(String color) throws QuoriPOOBException {
		Token token = getToken(color);
		return token.getLastMovements();
	}

	/**
	 * Get the time of the game
	 * 
	 * @return the time mode
	 */
	public int[] getTime() {
		return this.mode.getTime();
	}

	/**
	 * Returns the board
	 * 
	 * @return the board of QuoriPOOB
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Return the game mode
	 * 
	 * @return the game mode
	 */
	public Mode getMode() {
		return this.mode;
	}

	/**
	 * Return the game players
	 * 
	 * @return the game players
	 */
	public LinkedHashMap<String, Player> getPlayers() {
		return this.players;
	}

	/**
	 * Return the game tokens
	 * 
	 * @return the game tokens
	 */
	public LinkedHashMap<String, Token> getTokens() {
		return this.tokens;
	}

	/**
	 * Set if the game is over
	 * 
	 * @param bool TRUE, if the game is over. FALSE, otherwise
	 */
	public void setGameOver(boolean bool) {
		this.gameOver = bool;
	}

	/**
	 * Set the winner of the game
	 * 
	 * @param color the color of the game winner
	 * @throws QuoriPOOBException
	 */
	public void setWinner(String color) throws QuoriPOOBException {
		Player player = getPlayer(color);
		this.winner = player;
	}

	/*
	 * Verify if there is a player with the same color
	 */
	private boolean samePlayerColor(String color) {
		boolean sameColor = false;

		for (Player player : this.players.values()) {
			if (player.getColor().equals(color)) {
				sameColor = true;
				break;
			}
		}

		return sameColor;
	}

	/*
	 * Verify if exist a player machine
	 */
	private boolean machinePlayerExist() {
		boolean exist = false;

		for (Player player : this.players.values()) {
			if (player instanceof Machine) {
				exist = true;
				break;
			}
		}

		return exist;
	}

	/*
	 * Verify if exist a player human
	 */
	private boolean humanPlayerExist() {
		boolean exist = false;

		for (Player player : this.players.values()) {
			if (player instanceof Human) {
				exist = true;
				break;
			}
		}

		return exist;
	}

	/*
	 * verify if the game mode is undefined
	 */
	private boolean modeUndefined() {
		return (!this.onePlayer && !this.twoPlayers);
	}

	/*
	 * Return true if the game mode is not defined
	 * false otherwise
	 */
	private boolean gameModeUndefined() {
		return this.mode == null;
	}

	/*
	 * Finish the game by throwing the GAME_OVER exception and setting the winner
	 * player of QuoriPOOB
	 */
	private void finishGame(Player player) throws QuoriPOOBException {
		gameOver = true;
		winner = player;
		this.mode.cancelTask();
		notifyObservers();
		QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.GAME_OVER(player.getName()));
		Log.record(e);
		throw e;
	}

	/*
	 * This method starts the action of the machine to play with a delay of 1 second
	 */
	private void startPlayMachine() {
		executorService.schedule(this::playMachine, 1, TimeUnit.SECONDS);
	}

	/*
	 * The order for the machine to play
	 */
	private void playMachine() {
		try {
			Random random = new Random();
			Player player = this.board.getPlayerPlaying();
			checkGameFinish();

			if (random.nextBoolean()) {
				player.addWallToBoard(null, 0, 0, null);
			} else {
				player.moveToken(null);
			}

			if (player.checkWin())
				finishGame(player);
				
			nextTurn();
			notifyObservers();
		} catch (QuoriPOOBException e) {
		}
	}

	/*
	 * Verify if the game is finished
	 */
	private void checkGameFinish() throws QuoriPOOBException {
		if (gameOver) {
			if (winner == null) {
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.GAME_OVER(null));
				Log.record(e);
				throw e;
			} else {
				QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.GAME_OVER(winner.getName()));
				Log.record(e);
				throw e;
			}
		}
	}

	/*
	 * For the listeners observers of quoridor, it updates their status
	 */
	private void notifyObservers() {
		for (GameModeObserver observer : observers) {
			observer.onGameModeUpdated();
			observer.checkGameFinished();
		}
	}
}
