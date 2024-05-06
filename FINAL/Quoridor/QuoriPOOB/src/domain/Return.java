package domain;

import java.awt.Color;

public class Return extends Square{
    public Return(int row, int column, Board board) throws QuoriPOOBException {
        super(row, column, board);
    }

    @Override
    public void act() throws QuoriPOOBException {
        if (this.board == null) throw new QuoriPOOBException(QuoriPOOBException.BOARD_UNDEFINED);

        Player playerPlaying = this.board.getPlayerPlaying();
        Color colorToken = playerPlaying.getColor();
        this.board.returnTwoMoves(colorToken);
    }
}
