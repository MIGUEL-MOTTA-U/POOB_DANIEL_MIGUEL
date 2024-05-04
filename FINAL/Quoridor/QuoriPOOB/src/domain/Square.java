package domain;

public abstract class Square {
    protected Board board;
    protected int row;
    protected int column;
    protected Wall wallLeft;
    protected Wall wallRight;
    protected Wall wallUp;
    protected Wall wallDown;

    public Square(int row, int column, Board board) throws QuoriPOOBException {
        if (row > board.getSize() || column > board.getSize()) throw new QuoriPOOBException(QuoriPOOBException.SQUARE_OUT_OF_RANGE);
        
        this.row = row;
        this.column = column;
        this.board = board;
        this.wallDown = null;
        this.wallLeft = null;
        this.wallRight = null;
        this.wallUp = null;
    }
}
