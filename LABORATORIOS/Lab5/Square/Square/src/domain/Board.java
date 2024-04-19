package domain;
import java.util.HashMap;
public class Board {
    private int columns;
    private int rows;
    private int movements;
    private String[][][] matrixBoard;


    public Board(int n, int m){
        // Inicializar todas las casillas con valores predeterminados
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrixBoard[i][j][0] = ""; // String 1
                matrixBoard[i][j][1] = ""; // String 2
                matrixBoard[i][j][2] = "false"; // booleano representado como String
            }
        }
        randomHollows();
        randomTokens();
        columns = m;
        rows = n;
    }
    

    /**
     * Try to add a Hollow by given coordenates and color
     * @param color The color of the Hollow
     * @param row The row where is the Hollow
     * @param column The column where is the Hollow
     */
    public void addHollow(String color, int row, int column) {
        
    }
    

    /**
     * Try to add a Token by given coordenates and color
     * @param color The color of the Token
     * @param row The row where is the Token
     * @param column The column where is the Token
     */
    public void addToken(String color, int row, int column) {
        
    }
    

    /**
      * Try to Move the given token
      * @param token is the token that will move on the board
      * @param direction is the direction where the token is moving to
      */
    public void move(String token, String direction) {
        
    }

    /**
     * This method checks if the given Token is on a Hollow of the same Color.
     * @param token The given token that checks if It is on a Hollow of the same Color.
     * @return true if the token is on a Hollow of the same Color, otherwise returns false
     */
    public boolean isTokenInMatchingHollow(String token) {
        return false; // Cambiar esto según la implementación
    }

    /**
     * Saves the game.
     */
    public void save(){
    }

    /**
     * Returns the percentage of Tokens in a hollow.
     * @return the percentage of Tokens in a hollow
     */
    public int percentage(){
        return 0;
    }

    /**
     * Counts the movements in the board and returns it.
     * @return the movements in the board.
     */
    public int movements(){
        return movements;
    }

    /**
     * Changes the color of a token
     * @param color is the color of the given Token
     */
    public void changeTokenColor(String color){
    }

    /*
     * It generates random Tokens to start the board
     */
    private void randomTokens() {
        
    }
    
    /*
     * It generates random Hollows to start the board
     */
    private void randomHollows() {
        
    }

}