package domain;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class QuoriPOOB implements Serializable {
	private static final long serialVersionUID = 1L;
	private static QuoriPOOB quoriPOOBSingleton;

	private Quoridor quoridor;

	private QuoriPOOB() {
		this.quoridor = new Quoridor();
	}

	public static QuoriPOOB getQuoriPOOB() {
		if (quoriPOOBSingleton == null) {
			quoriPOOBSingleton = new QuoriPOOB();
		}
		return quoriPOOBSingleton;
	}

	private Object readResolve() {
        quoriPOOBSingleton = this;
        return quoriPOOBSingleton;
    }

    private Object writeReplace() {
        return this;
    }

	/**
	 * Set the game mode to one player
	 */
	public void setOnePlayer() {
		this.quoridor.setOnePlayer();
	}

	/**
	 * Set the game mode to two players
	 */
	public void setTwoPlayers() {
		this.quoridor.setTwoPlayers();
	}

	public void setMode(String mode) throws QuoriPOOBException {
		this.quoridor.setMode(mode);
	}

	public void setTime(int time) {
		this.quoridor.setTime(time);
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
		this.quoridor.createBoard(size, specialSquares);
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
		this.quoridor.createPlayerHuman(name, color);
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
		this.quoridor.createPlayerMachine(color, type);
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
		this.quoridor.addWalls(normal, temporary, longWall, allied);
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
		this.quoridor.addWallToBoard(type, initialRow, initialColumn, squareSide);
	}

	/**
	 * Moves the token to the given position.
	 * 
	 * @param direction The respective direction where the token is moving.
	 * @throws QuoriPOOBException Throws an exception in case the parameters are
	 *                            wrong or the action is not possible.
	 */
	public void moveToken(String direction) throws QuoriPOOBException {
		this.quoridor.moveToken(direction);
	}

	public void startTurn() {
		this.quoridor.startTurn();
	}

	public void addObserver(TimeObserver observer) {
		this.quoridor.addObserver(observer);
	}

	public void resetSingleton() {
		if (quoriPOOBSingleton != null) {
			this.quoridor = null;
		}
		
		quoriPOOBSingleton = null;
	}

	public boolean getTwoPlayers() {
		return this.quoridor.getTwoPlayers();
	}

	public boolean timeMode() {
		return this.quoridor.timeMode();
	}

	/**
	 * Returns the names of the players
	 * 
	 * @return Array of String, from the respective players.
	 */
	public String[] getNames() {
		return this.quoridor.getNames();
	}

	public HashMap<Color, HashMap<String, Integer>> numberWalls() {
		return this.quoridor.numberWalls();
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
		return this.quoridor.getColor(name);
	}

	public Square[][] getMatrixBoard() {
		return this.quoridor.getMatrixBoard();
	}

	public int getSize() {
		return this.quoridor.getSize();
	}

	/**
	 * Returns the board
	 * 
	 * @return the board of QuoriPOOB
	 */
	public Board getBoard() {
		return this.quoridor.getBoard();
	}

	/**
	 * The winner player
	 * 
	 * @return the winner player
	 */
	public Player getWinner() {
		return this.quoridor.getWinner();
	}

	/**
	 * Returns the player that is actually playing.
	 * 
	 * @return The player that has the turn to play.
	 */
	public Player getCurrentPlayer() {
		return this.quoridor.getCurrentPlayer();
	}

	public boolean getGameOver() {
		return this.quoridor.getGameOver();
	}

	/**
	 * Save the game in a file
	 * 
	 * @param file the file to save
	 */
	public void saveFile(File file) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(quoridor);
			out.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error al guardar el archivo",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Open a file given by the user
	 * 
	 * @param file the file to open
	 * @return the garden saved in the file
	 * @throws QuoriPOOBException
	 */
	public static QuoriPOOB openFile(File file) throws QuoriPOOBException {
		QuoriPOOB quoriPOOB = null;

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			quoriPOOB = (QuoriPOOB) in.readObject();
			in.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error al abrir el archivo", JOptionPane.ERROR_MESSAGE);
		}

		quoriPOOBSingleton = quoriPOOB;
		return quoriPOOB;
	}
}
