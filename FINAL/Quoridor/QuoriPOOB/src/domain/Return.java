package domain;

/**
 * This square's subclass has the same behaviors that a normal square, except it
 * returns the token two move if possible
 * 
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class Return extends Square {
    /**
     * Constructor for objects of class NormalSquare
     * 
     * @param row    the row where the square is located
     * @param column the column where the square is located
     * @param board  the board where the square is located
     * @throws QuoriPOOBException
     */
    public Return(int row, int column, Board board) throws QuoriPOOBException {
        super(row, column, board);
    }

    /**
     * Its the behavior of the square.
     * 
     * @throws QuoriPOOBException
     */
    @Override
    public void act() throws QuoriPOOBException {
        if (this.board == null){
            QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.BOARD_UNDEFINED);
            Log.record(e);
			throw e;
        }
        if (this.token != null) {
            this.board.returnTwoMoves(this.token.getColor());
        }
    }
}
