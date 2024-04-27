package domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * @autor: Daniel Diaz and Miguel Motta
 *         This class represents the Square game.
 */
public class Square {
    private String[][][] board;
    private HashMap<String, int[]> tokens;
    private HashMap<String, int[]> hollows;
    private int rows;
    private int movements;
    private int percentage;
    private int correctPlaces;
    private boolean gameOver;
    private ArrayList<String> usedColors;
    private boolean ok;

    /**
     * Constructor of Square by given number of rows.
     * 
     * @param n    is the number of rows (and columns, beacause is square).
     * @param nTok is the number of random tokens and hollows to be generated
     * @throws SquareException if the number of rows
     */
    public Square(int n, int nTok) throws SquareException {
        if (n < 1) {
            ok = false;
            throw new SquareException(SquareException.WRONG_DIMENSIONS);
        }
        else if (nTok > (Math.pow(n, 2) / 2) || nTok > 12) {
            ok = false;
            throw new SquareException(SquareException.LIMIT_TOKENS);
        }
        else {
            correctPlaces = 0;
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

        movements = 0;
        percentage = 0;
        ok = true;
        gameOver = false;
    }

     /**
      * Move the tokens to the orientation given
      *
      * @param direction is the direction where the token is moving to
      * @throws SquareException if the orientation doesn't exist
      */
    public void move(String direction) throws SquareException {
        if (!getGameOver()) {
            if (tokens.isEmpty()) {
                ok = false;
                throw new SquareException(SquareException.BOARD_UNDEFINIED);
            }
            switch (direction.toUpperCase()) {
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
                    ok = false;
                    throw new SquareException(SquareException.WRONG_DIRECTION);
            }
            ok = true;
        }
    }

    /**
     * Change the color of a token.
     * 
     * @param oldColor the color to change
     * @param newColor the new color
     * @throws SquareException if the color to change doesn´t exist or the new color already exist
     */
    public void changeTokenColor(String oldColor, String newColor) throws SquareException {
        if (tokens.isEmpty()) {
            ok = false;
            throw new SquareException(SquareException.BOARD_UNDEFINIED);
        } else if (!tokens.containsKey(oldColor)) {
            ok = false;
            throw new SquareException(SquareException.UNKNOWN_TOKEN);
        } else if (usedColors.contains(newColor)) {
            ok = false;
            throw new SquareException(SquareException.TOKEN_EXISTENT);
        } else {
            int rowOfToken = tokens.get(oldColor)[0], columnOfToken = tokens.get(oldColor)[1];
            int rowOfHollow = hollows.get(oldColor)[0], columnOfHollow = hollows.get(oldColor)[1];
            board[rowOfToken][columnOfToken][1] = newColor;
            board[rowOfHollow][columnOfHollow][1] = newColor;
            tokens.remove(oldColor);
            tokens.put(newColor, new int[] { rowOfToken, columnOfToken });
            hollows.remove(oldColor);
            hollows.put(newColor, new int[] { rowOfHollow, columnOfHollow });
            usedColors.remove(oldColor);
            usedColors.add(newColor);
            ok = true;
        }
    }

    /**
     * Convert the color rgb to a string
     * 
     * @param color the color to convert
     * @return the string of the color rgb
     */
    public String colorToString(Color color) {
        if (color != null) {
            String colorString = String.format("%d,%d,%d", color.getRed(), color.getGreen(), color.getBlue());
            return colorString;
        }
        return null;
    }

    /**
     * Convert the color string to a Color object
     * 
     * @param colorString the color string to convert
     * @return the Color object
     */
    public Color stringToColor(String colorString) {
        if (colorString != null && !colorString.isEmpty()) {
            String[] components = colorString.split(",");
            if (components.length == 3) {
                int r = Integer.parseInt(components[0]);
                int g = Integer.parseInt(components[1]);
                int b = Integer.parseInt(components[2]);
                return new Color(r, g, b);
            }
        }
        return null;
    }

    /**
     * Returns the percentage of Tokens in a hollow.
     * 
     * @return the percentage of Tokens in a hollow
     */
    public int percentage() {
        if (!tokens.isEmpty()) percentage = (int) (100 * correctPlaces / tokens.size());
        ok = true;
        return percentage;
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
     * Check if the player won
     * 
     * @return TRUE, if the player won. FALSE, otherwise
     */
    public boolean gameWon() {
        if (percentage() == 100) {
            return true;
        }
        return false;
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

    /**
     * Returns the state of the last action.
     * 
     * @return ok, the boolean of the state of Square.
     */
    public boolean ok() {
        return ok;
    }

    /*
     * Generates random Hollows to start the board
     */
    private void randomHollows(int numHollows) {
        ArrayList<String> colorsHollows = new ArrayList<>(Arrays.asList(
                "255,0,0", // RED
                "0,0,255", // BLUE
                "0,255,0", // GREEN
                "255,255,0", // YELLOW
                "0,255,255", // CYAN
                "255,0,255", // MAGENTA
                "128,128,128", // GRAY
                "192,192,192", // LIGHT_GRAY
                "64,64,64", // DARK_GRAY
                "255,200,0", // ORANGE
                "255,175,175" // PINK
        ));
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
        ArrayList<String> colorsToken = new ArrayList<>(usedColors);
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
    private void moveEast() {
        for (int i = 0; i < rows; i++) {
            for (int j = rows - 1; j >= 0; j--) {
                if (board[i][j][0].equals("T") && j != rows - 1 && !board[i][j][2].equals("TRUE")) {
                    if (board[i][j + 1][0].isEmpty()) {
                        updateTokens(i, j + 1, board[i][j][1]);
                        board[i][j + 1][0] = board[i][j][0];
                        board[i][j + 1][1] = board[i][j][1];
                        board[i][j][0] = "";
                        board[i][j][1] = "";
                    } else if (board[i][j + 1][0].equals("H") && board[i][j + 1][2].isEmpty()) {
                        if (!board[i][j + 1][1].equals(board[i][j][1])) {
                            gameOver = true;
                        } else {
                            correctPlaces++;
                            updateTokens(i, j + 1, board[i][j][1]);
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
    private void moveWest() {
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j < rows; j++) {
                if (board[i][j][0].equals("T") && !board[i][j][2].equals("TRUE")) {
                    if (board[i][j - 1][0].isEmpty()) {
                        updateTokens(i, j - 1, board[i][j][1]);
                        board[i][j - 1][0] = board[i][j][0];
                        board[i][j - 1][1] = board[i][j][1];
                        board[i][j][0] = "";
                        board[i][j][1] = "";
                    } else if (board[i][j - 1][0].equals("H") && board[i][j - 1][2].isEmpty()) {
                        if (!board[i][j - 1][1].equals(board[i][j][1])) {
                            gameOver = true;
                        } else {
                            correctPlaces++;
                            updateTokens(i, j - 1, board[i][j][1]);
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
    private void moveNorth() {
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (board[i][j][0].equals("T") && !board[i][j][2].equals("TRUE")) {
                    if (board[i - 1][j][0].isEmpty()) {
                        updateTokens(i - 1, j, board[i][j][1]);
                        board[i - 1][j][0] = board[i][j][0];
                        board[i - 1][j][1] = board[i][j][1];
                        board[i][j][0] = "";
                        board[i][j][1] = "";
                    } else if (board[i - 1][j][0].equals("H") && board[i - 1][j][2].isEmpty()) {
                        if (!board[i - 1][j][1].equals(board[i][j][1])) {
                            gameOver = true;
                        } else {
                            correctPlaces++;
                            updateTokens(i - 1, j, board[i][j][1]);
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
    private void moveSouth() {
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < rows; j++) {
                if (board[i][j][0].equals("T") && i != rows - 1 && !board[i][j][2].equals("TRUE")) {
                    if (board[i + 1][j][0].isEmpty()) {
                        updateTokens(i + 1, j, board[i][j][1]);
                        board[i + 1][j][0] = board[i][j][0];
                        board[i + 1][j][1] = board[i][j][1];
                        board[i][j][0] = "";
                        board[i][j][1] = "";
                    } else if (board[i + 1][j][0].equals("H") && board[i + 1][j][2].isEmpty()) {
                        if (!board[i + 1][j][1].equals(board[i][j][1])) {
                            gameOver = true;
                        } else {
                            correctPlaces++;
                            updateTokens(i + 1, j, board[i][j][1]);
                            board[i + 1][j][2] = "TRUE";
                            board[i][j][0] = "";
                            board[i][j][1] = "";
                        }
                    }
                }
            }
        }
    }

    /*
     * Update the HashMap of tokens when a token moves
     */
    private void updateTokens(int row, int col, String color) {
        tokens.remove(color);
        tokens.put(color, new int[] { row, col });
    }
}