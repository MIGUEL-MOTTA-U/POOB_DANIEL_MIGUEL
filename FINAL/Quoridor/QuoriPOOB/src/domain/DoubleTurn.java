package domain;

public class DoubleTurn extends Square{
    public DoubleTurn(int row, int column, Board board) throws QuoriPOOBException {
        super(row, column, board);
    }

    @Override
    public void act() throws QuoriPOOBException {
        if (this.board == null) throw new QuoriPOOBException(QuoriPOOBException.BOARD_UNDEFINED);

        Player playerPlaying = this.board.getPlayerPlaying();
        this.board.setPlayerPlaying(playerPlaying);
    }
}
