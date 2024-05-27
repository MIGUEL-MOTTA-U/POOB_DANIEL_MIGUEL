package domain;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;

public class Persistence {
	Quoridor quoridor;

	public Persistence() {
		this.quoridor = QuoriPOOB.getQuoriPOOB().getQuoridor();
	}

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
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error saving the game", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void writeMode(PrintWriter pw) {
		Mode mode = quoridor.getMode();

		pw.println("Mode:");
		pw.println(mode.getClass().getSimpleName());
		pw.println(mode.getTime()[0]);

		if (mode.getClass().getSimpleName().equals("Timed")) {
			pw.println(mode.getTime()[1] + " " + mode.getTime()[2] + " " + ((Timed)mode).getPlayerOnePlaying());
		}
	}

	private void writePlayers(PrintWriter pw) {
		LinkedHashMap<Color, Player> players = quoridor.getPlayers();
		
		pw.println("Players:");
		for (Player player : players.values()) {
			String type = player.getClass().getSimpleName();
			pw.println(type + " " + player.getColor() + " " + player.getName());
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
		      	pw.println(type + " " + row + " " + col);
			}
		}
	}

	private void writeTokens(PrintWriter pw) {
		LinkedHashMap<Color, Token> tokens = quoridor.getTokens();

		pw.println("Tokens:");
		for (Token token : tokens.values()) {
			pw.println(token.getColor() + " " + token.getRow() + " " + token.getColumn());
			pw.println(token.getLastMovements().size());
			for (int[] position : token.getLastMovements()) {
				pw.println(position[0] + " " + position[1]);
			}
		}
	}

	private void writeBoardWalls(PrintWriter pw) {
		ArrayList<Wall> walls = quoridor.getBoard().getWalls();

		pw.println("Board walls:");
		pw.println(walls.size());
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

	/**
	 * Open a file given by the user
	 * 
	 * @param file the file to open
	 * @return the garden saved in the file
	 * @throws QuoriPOOBException
	 */
	public static Quoridor openFile(File file) throws QuoriPOOBException {
		Quoridor quoridor = new Quoridor();

		try {
			BufferedReader bIn = new BufferedReader(new FileReader(file));
			readNumberPlayers(bIn, quoridor);
			readMode(bIn, quoridor);
			readPlayers(bIn, quoridor);
			readBoard(bIn, quoridor);
			readTokens(bIn, quoridor);
			readBoardWalls(bIn, quoridor);
			readInfo(bIn, quoridor);
            bIn.close();
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error opening the game", JOptionPane.ERROR_MESSAGE);
		}

		return quoridor;
	}

	private static void readNumberPlayers(BufferedReader bIn, Quoridor quoridor) {
		try {
			bIn.readLine();
			boolean twoPlayers = Boolean.parseBoolean(bIn.readLine());
			if (twoPlayers) {
				quoridor.setTwoPlayers();
			} else {
				quoridor.setOnePlayer();
			}
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error loading number players", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void readMode(BufferedReader bIn, Quoridor quoridor) {
		try {
			bIn.readLine();
			String type = "domain." + bIn.readLine();
			quoridor.setMode(type);
			int time = Integer.parseInt(bIn.readLine());
			quoridor.setTime(time);

			if (type.equals("domain.Timed")) {
				String[] info = bIn.readLine().split(" ");
				int time1 = Integer.parseInt(info[0]);
				int time2 = Integer.parseInt(info[1]);
				boolean playerOnePlaying = Boolean.parseBoolean(info[2]);

				((Timed)quoridor.getMode()).setTimePlayers(time2, time1, playerOnePlaying);
			}
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error loading mode", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void readPlayers(BufferedReader bIn, Quoridor quoridor) {
		try {
			bIn.readLine();
			
			for (int i = 0; i < 2; i++) {
				String[] info = bIn.readLine().split(" ");
				String type = "domain." + info[0];
				Color color = parseColor(info[1]);
				
				if (type.equals("domain.Human")) {
					quoridor.createPlayerHuman(info[2], color);
				} else {
					quoridor.createPlayerMachine(color, type);
				}

				int normal = Integer.parseInt(bIn.readLine().split(": ")[1]);
				int temporary = Integer.parseInt(bIn.readLine().split(": ")[1]);
				int longWalls = Integer.parseInt(bIn.readLine().split(": ")[1]);
				int allied = Integer.parseInt(bIn.readLine().split(": ")[1]);

				quoridor.addWallsPlayer(color, normal, temporary, longWalls, allied);
			}
			
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error loading players", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void readBoard(BufferedReader bIn, Quoridor quoridor) {
		try {
			bIn.readLine();
			int size = Integer.parseInt(bIn.readLine());
			quoridor.createBoard(size, null);

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					String[] info = bIn.readLine().split(" ");
					String type = "domain." + info[0];
					int row = Integer.parseInt(info[1]);
					int col = Integer.parseInt(info[2]);

					Square square = createSquare(type, row, col, quoridor.getBoard());
					quoridor.getBoard().setSquare(square, row, col);
				}
			}

			quoridor.getBoard().setPlayers(quoridor.getPlayers());
			quoridor.getBoard().setTokens(quoridor.getTokens());
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error loading board", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void readTokens(BufferedReader bIn, Quoridor quoridor) {
		try {
			bIn.readLine();

			for (int i = 0; i < 2; i++) {
				String[] info = bIn.readLine().split(" ");
				Color color = parseColor(info[0]);
				int row = Integer.parseInt(info[1]);
				int col = Integer.parseInt(info[2]);

				quoridor.getToken(color).setPosition(row, col);

				ArrayList<int[]> lastMovements = new ArrayList<>();
				int numberMovements = Integer.parseInt(bIn.readLine());

				for (int j = 0; j < numberMovements; j++) {
					info = bIn.readLine().split(" ");
					row = Integer.parseInt(info[0]);
					col = Integer.parseInt(info[1]);
					int[] position = {row, col};

					lastMovements.add(position);
				}

				quoridor.getToken(color).setLastMovements(lastMovements);
			}
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error loading tokens", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void readBoardWalls(BufferedReader bIn, Quoridor quoridor) {
		try {
			bIn.readLine();
			int numberWalls = Integer.parseInt(bIn.readLine());

			for (int i = 0; i < numberWalls; i++) {
				String[] info = bIn.readLine().split(" ");
				String type = "domain." + info[0];
				Color color = parseColor(info[1]);
				int row = Integer.parseInt(info[2]);
				int col = Integer.parseInt(info[3]);

				Wall wall = createWall(type, color);
				wall.addWallToBoard(row, col, info[4], quoridor.getBoard());
			}
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error loading board walls", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void readInfo(BufferedReader bIn, Quoridor quoridor) {
		try {
			bIn.readLine();
			Color color = parseColor(bIn.readLine());
			quoridor.getBoard().setPlayerPlaying(color);

			bIn.readLine();
			boolean gameOver = Boolean.parseBoolean(bIn.readLine());

			if (!gameOver) {
				quoridor.setGameOver(gameOver);
			} else {
				bIn.readLine();
				if (quoridor.getWinner() != null) {
					color = parseColor(bIn.readLine());
					quoridor.setWinner(color);
				}
			}
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error loading the information game", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static Color parseColor(String colorString) {
        colorString = colorString.replace("java.awt.Color[", "").replace("]", "");
        String[] components = colorString.split(",");
        int r = 0, g = 0, b = 0;

        for (String component : components) {
            String[] keyValue = component.split("=");
            String key = keyValue[0].trim();
            int value = Integer.parseInt(keyValue[1].trim());

            switch (key) {
                case "r":
                    r = value;
                    break;
                case "g":
                    g = value;
                    break;
                case "b":
                    b = value;
                    break;
            }
        }

        return new Color(r, g, b);
    }

	/*
	 * Creates an square by the given type in the given coordenates
	 */
	private static Square createSquare(String type, int row, int column, Board board) throws QuoriPOOBException {
		Square square = null;

		try {
			Class<?> cls = Class.forName(type);
			Constructor<?> constructor = cls.getDeclaredConstructor(int.class, int.class, Board.class);
			constructor.setAccessible(true);
			square = (Square) constructor.newInstance(row, column, board);
		} catch (Exception e) {
			throw new QuoriPOOBException(QuoriPOOBException.SQUARE_NOT_EXIST);
		}

		return square;
	}

	/*
	 * Creates a wall for the player by the given type
	 */
	private static Wall createWall(String type, Color color) throws QuoriPOOBException {
		Wall wall = null;

		try {
			Class<?> cls = Class.forName(type);
			Constructor<?> constructor = cls.getDeclaredConstructor(Color.class);
			constructor.setAccessible(true);
			wall = (Wall) constructor.newInstance(color);
		} catch (Exception e) {
			throw new QuoriPOOBException(QuoriPOOBException.WALL_NOT_EXIST);
		}

		return wall;
	}
}