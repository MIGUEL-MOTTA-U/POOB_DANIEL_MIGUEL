package domain;

public abstract class Square {
    protected int row;
    protected int column;
    protected Wall wallLeft;
    protected Wall wallRight;
    protected Wall wallUp;
    protected Wall wallDown;

    public Square(int row, int column){
        this.row = row;
        this.column = column;
        wallDown = null;
        wallLeft = null;
        wallRight = null;
        wallUp = null;
    }
}
