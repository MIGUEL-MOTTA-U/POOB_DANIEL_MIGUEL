package domain;
//import java.util.HashMap;
import java.util.Random;
public class Board {
    private int columns;
    private int rows;
    private int movements;
    private String[][][] matrixBoard; // i, j, (Color, Type, Boolean)
    private String[] usedColors;
    private static final String[] COLORS = {
        "Red", "Blue", "Green", "Yellow", "Orange", "Purple", 
        "White", "Black", "Gray", "Pink", "Light Green", "Brown",
        "Sky Blue", "Violet", "Dark Yellow"
    };

    // Que pasa si n <= 0 or m <= 0 
    public Board(int n, int m){
        matrixBoard = new String[n][m][3];
        columns = m;
        rows = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrixBoard[i][j][0] = ""; // String 1 --> vacio
                matrixBoard[i][j][1] = ""; // String 2 -->
                matrixBoard[i][j][2] = ""; // booleano representado como String
            }
        }
        randomHollows();
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
        int totalSpaces = rows * columns;
        
        // Calcular la cantidad de huecos a generar (1/4 parte del total de espacios)
        int numHollows = totalSpaces / 4;
        usedColors = new String[numHollows];
        
        // Generar huecos aleatorios
        Random random = new Random();
        int hollowsPlaced = 0;
        while (hollowsPlaced < numHollows){
            int randomRow = random.nextInt(rows);
            int randomCol = random.nextInt(columns);
            String ramdomColor = COLORS[random.nextInt(COLORS.length)];
            if (matrixBoard[randomRow][randomCol][0].isEmpty()){ // && matrixBoard[randomRow][randomCol][1].isEmpty()) {
                matrixBoard[randomRow][randomCol][0] = "H"; // Color
                matrixBoard[randomRow][randomCol][1] = ramdomColor; // Random Color
                matrixBoard[randomRow][randomCol][2] = ""; // Booleano representando true para hueco
                
                // Incrementar el contador de huecos colocados
                hollowsPlaced++; // Para poder implementar el segundo orden de harishem
            }
        }
    }
    

}