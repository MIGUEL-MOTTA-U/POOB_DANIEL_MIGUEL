package domain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
//import java.util.HashMap;
import java.util.Random;
public class Square {
    private int rows;
    private int movements;
    private String[][][] board; // i, j, (Type(H,T,""), Color or "")
    private HashMap<String, int[]> tokens;
    private HashMap<String, int[]> hollows;
    private ArrayList<String> usedColors;

    /**
     * Constructor of Square by given number of rows, the max number of rows is 16 by the momment.
     * @param n is the number of rows (and columns, beacause is square).
     * @throws SquareException if the number of rows 
     */
    public Square(int n) throws SquareException{
        if (n<1||n>16) throw new SquareException(SquareException.WRONG_DIMENSIONS);
        board = new String[n][n][3];
        usedColors = new ArrayList<>();
        tokens = new HashMap<>();
        hollows = new HashMap<>();
        rows = n;
        // La llena de espacios vacios
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j][0] = ""; // String 1 --> vacioS
                board[i][j][1] = ""; // String 2 -->
                board[i][j][2] = "";
            }
        }
        randomHollows();
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
    public void move(String direction) throws SquareException{
        switch (direction) {
            case "NORTH":
                moveNorth();
                break;
            case "EAST":
                moveEast();
                break;
            case "SOUTH":
                moveSouth();
                break;
            case "WEST":
                moveWest();    
            break;
            default:
                throw new SquareException(SquareException.WRONG_DIRECTION);
        } 
    }


    private void moveEast()throws SquareException{
        for(int i = 0;i<rows;i++){
            for(int j=rows-1 ; j>=0 ;j--){
                if(board[i][j][0].equals("T")&& j!= rows-1 && !board[i][j][0].equals("TRUE")){
                    if(board[i][j+1][0].isEmpty()){
                        board[i][j+1][0] = board[i][j][0];
                        board[i][j+1][1] = board[i][j][1];
                        board[i][j][0] = "";
                        board[i][j][1] = "";
                        movements++;
                    } else if(board[i][j+1][0].equals("H")&&board[i][j+1][2].isEmpty()){
                        if(!board[i][j+1][1].equals(board[i][j][1])) throw new SquareException(SquareException.LOSER);
                        else{
                            board[i][j+1][2] = "TRUE";
                            board[i][j][0] = "";
                            board[i][j][1] = "";
                            movements++;
                        }
                    }
                }
            }
        }
    }

    private void moveWest()throws SquareException{
        for(int i = 0;i<rows;i++){
            for(int j=1 ; j<rows ;j++){
                if(board[i][j][0].equals("T") && !board[i][j][0].equals("TRUE")){
                    if(board[i][j-1][0].isEmpty()){
                        board[i][j-1][0] = board[i][j][0];
                        board[i][j-1][1] = board[i][j][1];
                        board[i][j][0] = "";
                        board[i][j][1] = "";
                    } else if(board[i][j-1][0].equals("H")){
                        if(!board[i][j-1][1].equals(board[i][j][1])&& ) throw new SquareException(SquareException.LOSER);
                        else 
                    }
                }
            }
        }
    }

    private void moveNorth()throws SquareException{
        for(int i = 1;i<rows;i++){
            for(int j=0 ; j<rows ;j++){
                if(board[i][j][0].equals("T") && !board[i][j][0].equals("TRUE")){
                    if(board[i-1][j][0].isEmpty()){
                        board[i-1][j][0] = board[i][j][0];
                        board[i-1][j][1] = board[i][j][1];
                        board[i][j][0] = "";
                        board[i][j][1] = "";
                    } else if(board[i-1][j][0].equals("H")){
                        if(!board[i-1][j][1].equals(board[i][j][1])&& ) throw new SquareException(SquareException.LOSER);
                        else 
                    }
                }
            }
        }
    }

    private void moveSouth()throws SquareException{
        for(int i = rows-1;i>=0;i--){
            for(int j=0 ; j<rows ;j++){
                if(board[i][j][0].equals("T")&& i!= rows-1 && !board[i][j][0].equals("TRUE")){
                    if(board[i+1][j][0].isEmpty()){
                        board[i+1][j][0] = board[i][j][0];
                        board[i+1][j][1] = board[i][j][1];
                        board[i][j][0] = "";
                        board[i][j][1] = "";
                    } else if(board[i+1][j][0].equals("H")){
                        if(!board[i+1][j][1].equals(board[i][j][1])&& ) throw new SquareException(SquareException.LOSER);
                        else 
                    }
                }
            }
        }
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
    private void randomHollows() {
        ArrayList<String> colorsHollows = new ArrayList<>(Arrays.asList(
        "red", "blue", "green", "yellow", "orange", "purple", 
        "white", "black", "gray", "pink", "light green", "brown",
        "sky blue", "violet", "dark yellow"
        ));
        Random random = new Random();
        int numHollows = rows - 1;
        ArrayList<Integer> possibleRows = new ArrayList<>();
        ArrayList<Integer> possibleColumns = new ArrayList<>();
        for (int k = 0; k < rows;k++){
            possibleRows.add(k);
            possibleColumns.add(k);
        }

        int hollowsPlaced = 0;
        while (hollowsPlaced < numHollows){
            int randomRow = random.nextInt(rows);
            int randomCol = random.nextInt(rows);
            String randomColor = colorsHollows.get(random.nextInt(colorsHollows.size()));
            if (board[randomRow][randomCol][0].isEmpty()){ // && board[randomRow][randomCol][1].isEmpty()) { --> Inecesario
                board[randomRow][randomCol][0] = "H"; // Type
                board[randomRow][randomCol][1] = randomColor; // Random Color
                usedColors.add(randomColor); // Lo agrega a los colores para crear los tokens
                colorsHollows.remove(randomColor);
                hollows.put(randomColor, new int[] {randomRow,randomCol});
                hollowsPlaced++;
            }
        }
        
        
        randomTokens(usedColors, possibleRows, possibleColumns, numHollows);
    }
    
    /*
     * It generates rando Tokens to start the board
     * @param: colors, nTokens, possibleR, possibleC
     */
    private void randomTokens(ArrayList<String> pColors, ArrayList<Integer> possibleR, ArrayList<Integer>  possibleC, int numHollows){
        Random random = new Random();
        ArrayList<String> colorsToken = usedColors;
        int hollowsPlaced = 0;
        while (hollowsPlaced < numHollows){
            int randomRow = random.nextInt(rows);
            int randomCol = random.nextInt(rows);
            String randomColor = colorsToken.get(random.nextInt(colorsToken.size()));
            if (board[randomRow][randomCol][0].isEmpty()){ // && board[randomRow][randomCol][1].isEmpty()) { --> Inecesario
                board[randomRow][randomCol][0] = "T"; // Type
                board[randomRow][randomCol][1] = randomColor; // Random Color
                colorsToken.remove(randomColor);
                tokens.put(randomColor, new int[] {randomRow,randomCol});

                hollowsPlaced++;
            }
        }
    }

    /**
     * Returns the board of the Square
     * @return board, the attribute board of Square
     */
    public String[][][] getBoard(){
        return board;
    }

    /**
     * Returns the tokens in a HashMap
     * @return tokens, the attribute tokens of Square
     */
    public HashMap<String, int[]> getTokens(){
        return tokens;
    }

    /**
     * Returns the hollows in a HashMap
     * @return hollows, the attribute hollows of Square
     */
    public HashMap<String, int[]> getHollows(){
        return hollows;
    }
}