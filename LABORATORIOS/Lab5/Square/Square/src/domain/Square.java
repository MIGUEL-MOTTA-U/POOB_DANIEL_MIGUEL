package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * @autor: Daniel Diaz and Miguel Motta
 *         This class represents the Square game
 */
public class Square {
    private String[][][] board;
    private HashMap<String, int[]> tokens;
    private HashMap<String, int[]> hollows;
    private int rows;
    private int movements;
    private int percentage;
    private boolean gameOver;
    private ArrayList<String> usedColors;

    /**
     * Constructor of Square by given number of rows.
     * 
     * @param n    is the number of rows (and columns, beacause is square).
     * @param nTok is the number of random tokens and hollows to be generated
     * @throws SquareException if the number of rows
     */
    public Square(int n, int nTok) throws SquareException {
        if (n < 1)
            throw new SquareException(SquareException.WRONG_DIMENSIONS);
        else if (nTok > (Math.pow(n, 2) / 2) || nTok > 16)
            throw new SquareException(SquareException.LIMIT_TOKENS);
        else {
            board = new String[n][n][3];
            usedColors = new ArrayList<>();
            tokens = new HashMap<>();
            hollows = new HashMap<>();
            rows = n;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j][0] = ""; 
                    board[i][j][1] = ""; 
                    board[i][j][2] = "";
                }
            }
            randomHollows(nTok);
        }
        gameOver = false;
    }

    /**
     * Move the tokens to the orientation given
     * 
     * @param direction is the direction where the token is moving to
     */
    public void move(String direction) throws SquareException {
        switch (direction) {
            case "NORTH":
                moveNorth();
                movements++;
                break;
            case "EAST":
                moveEast();
                movements++;
                break;
            case "SOUTH":
                moveSouth();
                movements++;
                break;
            case "WEST":
                moveWest();
                movements++;
                break;
            default:
                throw new SquareException(SquareException.WRONG_DIRECTION);
        }
    }

    /**
     * Change the color of a token
     * 
     * @param oldColor the color to change
     * @param newColor the new color
     * @throws SquareException
     */
    public void changeTokenColor(String oldColor, String newColor) throws SquareException {
        if (!tokens.containsKey(oldColor))
            throw new SquareException(SquareException.UNKNOWN_TOKEN);
        else if (usedColors.contains(newColor))
            throw new SquareException(SquareException.TOKEN_EXISTENT);
        else {
            int rowOfToken = tokens.get(oldColor)[0], columnOfToken = tokens.get(oldColor)[1];
            int rowOfHollow = hollows.get(oldColor)[0], columnOfHollow = hollows.get(oldColor)[1];
            board[rowOfToken][columnOfToken][1] = newColor;
            board[rowOfHollow][columnOfHollow][1] = newColor;
            // ME falta hacer el test
            tokens.remove(oldColor);
            tokens.put(newColor, new int[] { rowOfToken, columnOfToken });
            hollows.remove(oldColor);
            hollows.put(newColor, new int[] { rowOfHollow, columnOfHollow });
        }
    }

    /**
     * Saves the game.
     */
    public void save() {

    }

    /**
     * Returns the percentage of Tokens in a hollow.
     * 
     * @return the percentage of Tokens in a hollow
     */
    public int percentage() {
        return 0;
    }

    /**
     * Counts the movements in the board and returns it.
     * 
     * @return the movements in the board.
     */
    public int movements() {
        return movements;
    }

    /**
     * Returns the board of the Square
     * 
     * @return the board
     */
    public String[][][] getBoard() {
        return board;
    }

    /**
     * Returns the tokens in the board
     * 
     * @return all the tokens
     */
    public HashMap<String, int[]> getTokens() {
        return tokens;
    }

    /**
     * Returns the hollows in the board
     * 
     * @return all the hollows
     */
    public HashMap<String, int[]> getHollows() {
        return hollows;
    }

    /**
     * Return if the player lose
     * 
     * @return TRUE if the player lose. FALSE, otherwise
     */
    public boolean getGameOver() {
        return gameOver;
    }

    /*
     * Generates random Hollows to start the board
     */
    private void randomHollows(int numHollows) {
        ArrayList<String> colorsHollows = new ArrayList<>(Arrays.asList(
                "RED", "BLUE", "GREEN", "YELLOW", "CYAN", "MAGENTA", "BLACK", "GRAY", "LIGHT_GRAY", "DARK_GRAY",
                "ORANGE", "PINK"));
        Random random = new Random();
        ArrayList<Integer> possibleRows = new ArrayList<>();
        ArrayList<Integer> possibleColumns = new ArrayList<>();
        for (int k = 0; k < rows; k++) {
            possibleRows.add(k);
            possibleColumns.add(k);
        }

        int hollowsPlaced = 0;
        while (hollowsPlaced < numHollows) {
            int randomRow = random.nextInt(rows);
            int randomCol = random.nextInt(rows);
            String randomColor = colorsHollows.get(random.nextInt(colorsHollows.size()));
            if (board[randomRow][randomCol][0].isEmpty()) { 
                board[randomRow][randomCol][0] = "H"; 
                board[randomRow][randomCol][1] = randomColor;
                usedColors.add(randomColor); 
                colorsHollows.remove(randomColor);
                hollows.put(randomColor, new int[] { randomRow, randomCol });
                hollowsPlaced++;
            }
        }

        randomTokens(usedColors, possibleRows, possibleColumns, numHollows);
    }

    /*
     * Generates rando Tokens to start the board
     */
    private void randomTokens(ArrayList<String> pColors, ArrayList<Integer> possibleR, ArrayList<Integer> possibleC,
            int numHollows) {
        Random random = new Random();
        ArrayList<String> colorsToken = usedColors;
        int hollowsPlaced = 0;
        while (hollowsPlaced < numHollows) {
            int randomRow = random.nextInt(rows);
            int randomCol = random.nextInt(rows);
            String randomColor = colorsToken.get(random.nextInt(colorsToken.size()));
            if (board[randomRow][randomCol][0].isEmpty()) { 
                board[randomRow][randomCol][0] = "T"; 
                board[randomRow][randomCol][1] = randomColor; 
                colorsToken.remove(randomColor);
                tokens.put(randomColor, new int[] { randomRow, randomCol });

                hollowsPlaced++;
            }
        }
    }

    /*
     * Moves all the possible tokens to the East
     */
    private void moveEast() throws SquareException {
        for (int i = 0; i < rows; i++) {
            for (int j = rows - 1; j >= 0; j--) {
                if (board[i][j][0].equals("T") && j != rows - 1 && !board[i][j][2].equals("TRUE")) {
                    if (board[i][j + 1][0].isEmpty()) {
                        board[i][j + 1][0] = board[i][j][0];
                        board[i][j + 1][1] = board[i][j][1];
                        board[i][j][0] = "";
                        board[i][j][1] = "";
                    } else if (board[i][j + 1][0].equals("H") && board[i][j + 1][2].isEmpty()) {
                        if (!board[i][j + 1][1].equals(board[i][j][1])) {
                            gameOver = true;
                        } else {
                            board[i][j + 1][2] = "TRUE";
                            board[i][j][0] = "";
                            board[i][j][1] = "";
                        }
                    }
                }
            }
        }
    }

    /*
     * Moves all the possible tokens to the West
     */
    private void moveWest() throws SquareException {
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j < rows; j++) {
                if (board[i][j][0].equals("T") && !board[i][j][2].equals("TRUE")) {
                    if (board[i][j - 1][0].isEmpty()) {
                        board[i][j - 1][0] = board[i][j][0];
                        board[i][j - 1][1] = board[i][j][1];
                        board[i][j][0] = "";
                        board[i][j][1] = "";
                    } else if (board[i][j - 1][0].equals("H") && board[i][j - 1][2].isEmpty()) {
                        if (!board[i][j - 1][1].equals(board[i][j][1])) {
                            gameOver = true;
                        } else {
                            board[i][j - 1][2] = "TRUE";
                            board[i][j][0] = "";
                            board[i][j][1] = "";
                        }
                    }
                }
            }
        }
    }

    /*
     * Moves all the possible tokens to the North
     */
    private void moveNorth() throws SquareException {
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (board[i][j][0].equals("T") && !board[i][j][2].equals("TRUE")) {
                    if (board[i - 1][j][0].isEmpty()) {
                        board[i - 1][j][0] = board[i][j][0];
                        board[i - 1][j][1] = board[i][j][1];
                        board[i][j][0] = "";
                        board[i][j][1] = "";
                    } else if (board[i - 1][j][0].equals("H") && board[i - 1][j][2].isEmpty()) {
                        if (!board[i - 1][j][1].equals(board[i][j][1])) {
                            gameOver = true;
                        } else {
                            board[i - 1][j][2] = "TRUE";
                            board[i][j][0] = "";
                            board[i][j][1] = "";
                        }
                    }
                }
            }
        }
    }

    /*
     * Moves all the possible tokens to the West
     */
    private void moveSouth() throws SquareException {
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < rows; j++) {
                if (board[i][j][0].equals("T") && i != rows - 1 && !board[i][j][2].equals("TRUE")) {
                    if (board[i + 1][j][0].isEmpty()) {
                        board[i + 1][j][0] = board[i][j][0];
                        board[i + 1][j][1] = board[i][j][1];
                        board[i][j][0] = "";
                        board[i][j][1] = "";
                    } else if (board[i + 1][j][0].equals("H") && board[i + 1][j][2].isEmpty()) {
                        if (!board[i + 1][j][1].equals(board[i][j][1])) {
                            gameOver = true;
                        } else {
                            board[i + 1][j][2] = "TRUE";
                            board[i][j][0] = "";
                            board[i][j][1] = "";
                        }
                    }
                }
            }
        }
    }

}