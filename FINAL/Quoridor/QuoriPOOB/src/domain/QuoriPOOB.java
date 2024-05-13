package domain;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;

public class QuoriPOOB implements Serializable{
	private static QuoriPOOB quoriPOOBSingleton;

	private boolean onePlayer;
	private boolean twoPlayers;
	private Board board;
	private LinkedHashMap<Color, Player> players;
	private LinkedHashMap<Color, Token> tokens;
	private boolean gameOver;
	private Player winner;

	

	private QuoriPOOB() {
		this.players = new LinkedHashMap<>(2);
		this.tokens = new LinkedHashMap<>(2);
		this.board = null;
		this.onePlayer = false;
		this.twoPlayers = false;
		this.gameOver = false;
	}

	public static QuoriPOOB getQuoriPOOB() {
		if (quoriPOOBSingleton == null) {
			quoriPOOBSingleton = new QuoriPOOB();
		}
		return quoriPOOBSingleton;
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
		if (players.size() != 2)
			throw new QuoriPOOBException(QuoriPOOBException.MISSING_PLAYERS);

		board = new Board(size, specialSquares);
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
		if(gameOver) throw new QuoriPOOBException(QuoriPOOBException.GAME_OVER(winner.getName()));
		if(name == null) throw new QuoriPOOBException(QuoriPOOBException.NAME_NULL);
		if(color == null) throw new QuoriPOOBException(QuoriPOOBException.COLOR_NULL);
		if (modeUndefined())
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
		if(gameOver) throw new QuoriPOOBException(QuoriPOOBException.GAME_OVER(winner.getName()));
		
		if(type == null) throw new QuoriPOOBException(QuoriPOOBException.TYPE_MACHINE_NULL);
		if(color == null) throw new QuoriPOOBException(QuoriPOOBException.COLOR_NULL);
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
			if (!Machine.class.isAssignableFrom(cls))
				throw new QuoriPOOBException(QuoriPOOBException.PLAYER_NOT_EXIST);
			Constructor<?> constructor = cls.getDeclaredConstructor(String.class, Color.class);
			constructor.setAccessible(true);
			Machine machine = (Machine) constructor.newInstance("Machine", color);
			this.players.put(color, machine);

			Token token = new Token(color);
			this.tokens.put(color, token);
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
		if(gameOver) throw new QuoriPOOBException(QuoriPOOBException.GAME_OVER(winner.getName()));
		
		if (modeUndefined())
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
		if(gameOver) throw new QuoriPOOBException(QuoriPOOBException.GAME_OVER(winner.getName()));
		Player player = getCurrentPlayer();
		player.play(type, initialRow, initialColumn, squareSide);
		if(getCurrentPlayer() instanceof Machine) moveToken(null);
	}

	/**
	 * Moves the token to the given position.
	 * 
	 * @param direction The respective direction where the token is moving.
	 * @throws QuoriPOOBException Throws an exception in case the parameters are
	 *                            wrong or the action is not possible.
	 */
	public void moveToken(String direction) throws QuoriPOOBException {
		if(gameOver) throw new QuoriPOOBException(QuoriPOOBException.GAME_OVER(winner.getName()));
		Player player = getCurrentPlayer();
		player.play(direction);
		if(player.checkWin()) finishGame(player);
		if(getCurrentPlayer() instanceof Machine) moveToken(null);
	}

	/*
	 * Finish the game by throwing the GAME_OVER exception and setting the winner player of QuoriPOOB
	 */
	private void finishGame(Player p)throws QuoriPOOBException{
		// Finalizar el juego ()
		gameOver = true;
		winner = p;
		throw new QuoriPOOBException(QuoriPOOBException.GAME_OVER(p.getName()));
	}

	// Getters and Setters

	public void setTime() throws QuoriPOOBException{
		// if (gameMode.toUpperCase().equals(board)) {
		// }
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

	public HashMap<Color, HashMap<String, Integer>> numberWalls(){
		HashMap<Color, HashMap<String, Integer>> res = new HashMap<>();
		for(Player p: players.values()){
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

	public int getSize() {
		return this.board.getSize();
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
	 * The winner player
	 * @return the winner player
	 */
	public Player getWinner() {
		return winner;
	}

	/**
	 * Returns the player that is actually playing.
	 * 
	 * @return The player that has the turn to play.
	 */
	public Player getCurrentPlayer() {
		return board.getPlayerPlaying();
	}

	public boolean twoPlayers() {
		boolean check = false;
		if (this.twoPlayers) {
			check = true;
		}
		
		return check;
	}

	public void resetSingleton() {
		if(quoriPOOBSingleton != null){
			quoriPOOBSingleton.resetAll();
		}
		quoriPOOBSingleton = null;
	}

	private void resetAll(){
		this.onePlayer = false;
		this.twoPlayers = false;
		this.gameOver=false;
		
		this.players.clear();
		this.players = null;
		
		this.tokens.clear();
		this.tokens = null;
		
		this.board=null;
		this.winner=null;
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


	/**
     * Save the game in a file
     * @param file  the file to save
     */
    public void saveFile(File file){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject("QuoriPOOB storage\n");
            out.writeObject(this);
            out.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al guardar el archivo", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Open a file given by the user
     * @param file  the file to open
     * @return  the garden saved in the file
     * @throws QuoriPOOBException
     */
    public static QuoriPOOB openFile(File file) throws QuoriPOOBException{
        QuoriPOOB quori = null;

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            String s = (String) in.readObject();
            quori = (QuoriPOOB) in.readObject();
            in.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al abrir el archivo", JOptionPane.ERROR_MESSAGE);
        }

        return quori;
    }
	
}
