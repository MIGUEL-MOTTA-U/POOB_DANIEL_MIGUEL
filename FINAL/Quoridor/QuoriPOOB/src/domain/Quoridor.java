package domain;

import java.util.*;
import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.Constructor;

public class Quoridor implements Serializable {
	private Board board;
	private LinkedHashMap<Color, Player> players;
	private LinkedHashMap<Color, Token> tokens;
    private Mode mode;
	private boolean onePlayer;
	private boolean twoPlayers;
	private boolean gameOver;
	private Player winner;

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

    public void setNormalMode() {
        this.mode = new NormalMode(this);
    }

    public void setTimeTrialMode() {
        this.mode = new TimeTrial(this);
    }

    public void setTimedMode() {
        this.mode = new Timed(this);
    }

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
		if (modeUndefined())
			throw new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
		if (gameModeUndefined())
			throw new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
		if (players.size() != 2)
			throw new QuoriPOOBException(QuoriPOOBException.MISSING_PLAYERS);

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
	public void createPlayerHuman(String name, Color color) throws QuoriPOOBException {
		checkGameFinish();
		if (name == null)
			throw new QuoriPOOBException(QuoriPOOBException.NAME_NULL);
		if (color == null)
			throw new QuoriPOOBException(QuoriPOOBException.COLOR_NULL);
		if (modeUndefined())
			throw new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
		if (gameModeUndefined())
			throw new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
		if (this.players.size() >= 2)
			throw new QuoriPOOBException(QuoriPOOBException.WRONG_NUMBER_PLAYERS);
		if (onePlayer) {
			if (humanPlayerExist())
				throw new QuoriPOOBException(QuoriPOOBException.ONE_PLAYER_MODE);
		}
		if (samePlayerColor(color))
			throw new QuoriPOOBException(QuoriPOOBException.SAME_PLAYER_COLOR);

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
	public void createPlayerMachine(Color color, String type) throws QuoriPOOBException {
		checkGameFinish();
		if (type == null)
			throw new QuoriPOOBException(QuoriPOOBException.TYPE_MACHINE_NULL);
		if (color == null)
			throw new QuoriPOOBException(QuoriPOOBException.COLOR_NULL);
		if (modeUndefined())
			throw new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
		if (this.players.size() >= 2)
			throw new QuoriPOOBException(QuoriPOOBException.WRONG_NUMBER_PLAYERS);
		if (twoPlayers)
			throw new QuoriPOOBException(QuoriPOOBException.TWO_PLAYER_MODE);
		if (onePlayer) {
			if (machinePlayerExist())
				throw new QuoriPOOBException(QuoriPOOBException.ONE_PLAYER_MODE);
		}
		if (samePlayerColor(color))
			throw new QuoriPOOBException(QuoriPOOBException.SAME_PLAYER_COLOR);

		try {
			Class<?> cls = Class.forName(type);
			Constructor<?> constructor = cls.getDeclaredConstructor(String.class, Color.class);
			constructor.setAccessible(true);
			Machine machine = (Machine) constructor.newInstance("Machine", color);
			this.players.put(color, machine);

			Token token = new Token(color);
			this.tokens.put(color, token);
		} catch (Exception e) {
			throw new QuoriPOOBException(QuoriPOOBException.PLAYER_NOT_EXIST);
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
		if (modeUndefined())
			throw new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
		if (gameModeUndefined())
			throw new QuoriPOOBException(QuoriPOOBException.MODE_UNDEFINED);
		if (this.board == null)
			throw new QuoriPOOBException(QuoriPOOBException.BOARD_UNDEFINED);
		if (players.size() != 2)
			throw new QuoriPOOBException(QuoriPOOBException.MISSING_PLAYERS);
		if (normal < 0 || temporary < 0 || allied < 0 || longWall < 0)
			throw new QuoriPOOBException(QuoriPOOBException.WRONG_NUMBER_WALLS);

		int numberWalls = normal + temporary + longWall + allied;
		if (numberWalls != this.board.getSize() + 1)
			throw new QuoriPOOBException(QuoriPOOBException.WRONG_NUMBER_WALLS);

		for (Player player : this.players.values()) {
			player.addWalls(normal, temporary, longWall, allied);
		}
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

		// if (this.onePlayer)
		// 	moveToken(null);

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

		// if (getCurrentPlayer() instanceof Machine)
		// 	this.moveToken(null);

		nextTurn();
	}

	public void finishGame() throws QuoriPOOBException {
		gameOver = true;
		this.mode.cancelTask();
		throw new QuoriPOOBException(QuoriPOOBException.GAME_OVER(null));
	}

    public void nextTurn() throws QuoriPOOBException {
        this.board.nextTurn();
		if (this.board.getPlayerPlaying() instanceof Machine) playMachine();
    }

	public void startTurn() {
		this.mode.startTurn();
	}

	public void addObserver(TimeObserver observer) {
		this.mode.addObserver(observer);
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

	public HashMap<Color, HashMap<String, Integer>> numberWalls() {
		HashMap<Color, HashMap<String, Integer>> res = new HashMap<>();

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
	public Color getColor(String name) throws QuoriPOOBException {
		Color color = null;

		for (Player player : players.values()) {
			if (player.getName().equals(name)) {
				color = player.getColor();
			}
		}

		if (color == null)
			throw new QuoriPOOBException(QuoriPOOBException.PLAYER_NOT_EXIST);

		return color;
	}

	public Square[][] getMatrixBoard() {
		return this.board.getMatrixBoard();
	}

    public boolean getTwoPlayers() {
        return this.twoPlayers;
    }

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
	 * Returns the board
	 * 
	 * @return the board of QuoriPOOB
	 */
	public Board getBoard() {
		return board;
	}

	/*
	 * Verify if there is a player with the same color
	 */
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
		//throw new QuoriPOOBException(QuoriPOOBException.GAME_OVER(player.getName()));
	}

	private void playMachine() throws QuoriPOOBException {
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
	}

	private void checkGameFinish() throws QuoriPOOBException {
		if (gameOver) {
			if (winner == null) {
				throw new QuoriPOOBException(QuoriPOOBException.GAME_OVER(null));
			} else {
				throw new QuoriPOOBException(QuoriPOOBException.GAME_OVER(winner.getName()));
			}
		}
	}
}
