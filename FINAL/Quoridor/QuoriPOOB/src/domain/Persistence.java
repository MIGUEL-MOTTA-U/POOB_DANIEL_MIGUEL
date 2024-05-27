package domain;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;

public class Persistence {
	Quoridor quoridor = QuoriPOOB.getQuoriPOOB().getQuoridor();

    /**
	 * Save the game in a file
	 * 
	 * @param file the file to save
	 */
	public void saveFile(File file) {
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(file));
			pw.println("Two players:");
			pw.println(quoridor.getTwoPlayers());
			writeMode(pw);
			writePlayers(pw);
			writeBoard(pw);
			writeTokens(pw);
			writeBoardWalls(pw);
			writeInfo(pw);
            pw.close();
		} catch (Exception e) {
			e.printStackTrace();
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
			quoriPOOB.setMode("domain." + (String) in.readObject());
			quoriPOOB.setTime((int) in.readObject());
			// if (quoriPOOB.getMode().equals("Timed")) {
			// 	int timePlayer1 = (int) in.readObject();
			// 	int timePlayer2 = (int) in.readObject();
			// 	quoriPOOB.setTimePlayers(timePlayer1, timePlayer2);
			// }
			in.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error al abrir el archivo", JOptionPane.ERROR_MESSAGE);
		}

		QuoriPOOB.setQuoriPOOB(quoriPOOB);
		return QuoriPOOB.getQuoriPOOB();
	}

	private void writeMode(PrintWriter pw) {
		Mode mode = quoridor.getMode();

		pw.println("Mode:");
		pw.println(mode.getClass().getSimpleName());
		pw.println(mode.getTime()[0]);

		if (mode.getClass().getSimpleName().equals("Timed")) {
			mode = (Timed) mode;
			pw.println(mode.getTime()[1] + " " + mode.getTime()[2]);
		}
	}

	private void writePlayers(PrintWriter pw) {
		LinkedHashMap<Color, Player> players = quoridor.getPlayers();
		
		pw.println("Players:");
		for (Player player : players.values()) {
			pw.println(player.getColor() + " " + player.getName());
			pw.println("NormalWalls: "+ player.numberWalls().get("NormalWall"));
			pw.println("Temporary: "+ player.numberWalls().get("Temporary"));
			pw.println("LongWall: "+ player.numberWalls().get("LongWall"));
			pw.println("Allied: "+ player.numberWalls().get("Allied"));
		}
	}

	private void writeBoard(PrintWriter pw) {
		Board board = quoridor.getBoard();
		Square[][] matrixBoard = board.getMatrixBoard();

		pw.println("Board:");
		pw.println(board.getSize());
		for (int row = 0; row < matrixBoard.length; row++) {
			for (int col = 0; col < matrixBoard.length; col++) {
				Square square = matrixBoard[row][col];
				String type = square.getClass().getSimpleName();
				// boolean wallUp = (square.getWallUp() != null) ? true : false;
				// boolean wallLeft = (square.getWallLeft() != null) ? true : false;
				// boolean wallDown = (square.getWallDown() != null) ? true : false;
				// boolean wallRight = (square.getWallRight() != null) ? true : false;
				// boolean token = (square.getToken() != null) ? true : false;

				// pw.println(type + " " + row + " " + col + " " + wallUp + " " + wallLeft + " " + wallDown + " " + wallRight + " " + token);
				pw.println(type + " " + row + " " + col);
			}
		}
	}

	private void writeTokens(PrintWriter pw) {
		LinkedHashMap<Color, Token> tokens = quoridor.getTokens();

		pw.println("Tokens:");
		for (Token token : tokens.values()) {
			pw.println(token.getColor() + " " + token.getRow() + " " + token.getColumn());
			for (int[] position : token.getLastMovements()) {
				pw.println(position[0] + " " + position[1]);
			}
		}
	}

	private void writeBoardWalls(PrintWriter pw) {
		ArrayList<Wall> walls = quoridor.getBoard().getWalls();

		for (Wall wall : walls) {
			String type = wall.getClass().getSimpleName();
			int row = wall.getInicialRow();
			int col = wall.getInicialColumn();
			String squareSide = wall.getSquareSide();

			pw.println(type + " " + wall.getColor() + " " + row + " " + col + " " + squareSide);
		}
	}

	private void writeInfo(PrintWriter pw) {
		pw.println("Player playing:");
		pw.println(quoridor.getCurrentPlayer().getColor());
		pw.println("Game over:");
		pw.println(quoridor.getGameOver());

		if (quoridor.getGameOver()) {
			pw.println("Winner");
			if (quoridor.getWinner() != null) {
				pw.println(quoridor.getWinner().getColor());
			} else {
				pw.println("null");
			}
		}
	}
}