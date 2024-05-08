package domain;

/**
 * This square's subclass has the same behaviors that a normal square, except it
 * steals a turn
 * 
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class DoubleTurn extends Square {
    /**
     * Constructor for objects of class DoubleTurn
     * 
     * @param row    the row where the square is located
     * @param column the column where the square is located
     * @param board  the board where the square is located
     * @throws QuoriPOOBException
     */
    public DoubleTurn(int row, int column, Board board) throws QuoriPOOBException {
        super(row, column, board);
    }

    /**
     * Its the behavior of the square.
     * 
     * @throws QuoriPOOBException
     */
    @Override
    public void act() throws QuoriPOOBException {
        if (this.board == null)
            throw new QuoriPOOBException(QuoriPOOBException.BOARD_UNDEFINED);

        Player playerPlaying = this.board.getPlayerPlaying();
        this.board.setPlayerPlaying(playerPlaying);
    }
}
