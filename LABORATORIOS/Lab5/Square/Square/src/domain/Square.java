package domain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
//import java.util.HashMap;
import java.util.Random;
public class Square {
    private int columns;
    private int rows;
    private int movements;
    private String[][][] board; // i, j, (Type(H,T,""), Color or "", Boolean or "")
    private HashMap<String, int[]> tokens;
    private HashMap<String, int[]> hollows;
    private ArrayList<String> COLORS = new ArrayList<>(Arrays.asList(
        "red", "blue", "green", "yellow", "orange", "purple", 
        "white", "black", "gray", "pink", "light green", "brown",
        "sky blue", "violet", "dark yellow"
    ));

    // Que pasa si n <= 0 or m <= 0 
    public Square(int n, int m){
        board = new String[n][m][3];
        columns = m;
        rows = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j][0] = ""; // String 1 --> vacio
                board[i][j][1] = ""; // String 2 -->
                board[i][j][2] = ""; // booleano representado como String
            }
        }
        randomTokens();
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
    public void move(String direction) {
        
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
     * It generates random Hollows to start the board
     */
    private void randomTokens() {
        int numHollows = rows - 1;
        ArrayList<String> usedColors = new ArrayList<>(numHollows);
        Random random = new Random();
        int hollowsPlaced = 0;
        while (hollowsPlaced < numHollows){
            int randomRow = random.nextInt(rows);
            int randomCol = random.nextInt(columns);
            String ramdomColor = COLORS.get(random.nextInt(COLORS.size()));
            if (board[randomRow][randomCol][0].isEmpty()){ // && board[randomRow][randomCol][1].isEmpty()) { --> Inecesario
                board[randomRow][randomCol][0] = "H"; // Type
                board[randomRow][randomCol][1] = ramdomColor; // Random Color
                board[randomRow][randomCol][2] = ""; // Booleano representando true para hueco --> Aun no se cómo implementarlo pues creo que sobra
                COLORS.remove(ramdomColor); // Lo elimina de los colores disponibles
                usedColors.add(ramdomColor); // Lo agrega a los colores para crear los tokens
                hollows.put(ramdomColor, new int[] {randomRow,randomCol});
                // Incrementar el contador de huecos colocados
                hollowsPlaced++; // Para poder implementar el segundo orden de harishem
            }
        }
        randomTokens();
    }
    

}