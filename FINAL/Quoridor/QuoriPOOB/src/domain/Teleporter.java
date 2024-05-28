package domain;

/**
 * This square's subclass has the same behaviors that a normal square, except it
 * allows the token to move around regardless of walls (including diagonal
 * movements)
 * 
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class Teleporter extends Square {
    /**
     * Constructor for objects of class Teleporter
     * 
     * @param row    the row where the square is located
     * @param column the column where the square is located
     * @param board  the board where the square is located
     * @throws QuoriPOOBException
     */
    public Teleporter(int row, int column, Board board) throws QuoriPOOBException {
        super(row, column, board);
    }

    /**
     * Check if the there is a wall above the square blocking the passage to the
     * token
     * 
     * @param token the token to pass
     * @return TRUE, if the there is a wall blocking the tocken. FALSE, otherwise;
     */
    @Override
    public boolean blockUp(String token) {
        return false;
    }

    /**
     * Check if the there is a wall to the left of the square blocking the passage
     * to the
     * token
     * 
     * @param token the token to pass
     * @return TRUE, if the there is a wall blocking the tocken. FALSE, otherwise;
     */
    @Override
    public boolean blockLeft(String token) {
        return false;
    }

    /**
     * Check if the there is a wall below the square blocking the passage to the
     * token
     * 
     * @param token the token to pass
     * @return TRUE, if the there is a wall blocking the tocken. FALSE, otherwise;
     */
    @Override
    public boolean blockDown(String token) {
        return false;
    }

    /**
     * Check if the there is a wall to the right of the square blocking the passage
     * to the
     * token
     * 
     * @param token the token to pass
     * @return TRUE, if the there is a wall blocking the tocken. FALSE, otherwise;
     */
    @Override
    public boolean blockRight(String token) {
        return false;
    }

    /**
     * Check if the square block the diagonal movements of the token
     * 
     * @return TRUE, if the square block the diagonal movements. FALSE, otherwise;
     */
    @Override
    public boolean blockDiagonalMovements() {
        return false;
    }

    /**
     * Its the behavior of the square.
     * 
     * @throws QuoriPOOBException
     */
    @Override
    public void act() throws QuoriPOOBException {

    }
}
