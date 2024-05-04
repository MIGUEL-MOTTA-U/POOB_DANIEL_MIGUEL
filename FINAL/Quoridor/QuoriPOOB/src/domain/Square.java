package domain;

public abstract class Square {
    protected Board board;
    protected int row;
    protected int column;
    protected Wall wallLeft;
    protected Wall wallRight;
    protected Wall wallUp;
    protected Wall wallDown;

    public Square(int row, int column, Board board) {
        this.row = row;
        this.column = column;
        this.board = board;
        this.wallDown = null;
        this.wallLeft = null;
        this.wallRight = null;
        this.wallUp = null;
    }
}
