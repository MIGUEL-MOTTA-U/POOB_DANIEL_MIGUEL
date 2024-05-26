package domain;

/**
 * This square's subclass has the same behaviors that a normal square.
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2023
 */
public class NormalSquare extends Square {
    /**
     * Constructor for objects of class NormalSquare
     * 
     * @param row    the row where the square is located
     * @param column the column where the square is located
     * @param board  the board where the square is located
     * @throws QuoriPOOBException
     */
    public NormalSquare(int row, int column, Board board) throws QuoriPOOBException {
        super(row, column, board);
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
