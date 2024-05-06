package domain;

import java.awt.Color;

public abstract class Square {
    protected Board board;
    protected int row;
    protected int column;
    protected Wall wallLeft;
    protected Wall wallRight;
    protected Wall wallUp;
    protected Wall wallDown;

    public Square(int row, int column, Board board) throws QuoriPOOBException {
        if (row > board.getSize() || column > board.getSize() || row < 0 || column < 0)
            throw new QuoriPOOBException(QuoriPOOBException.SQUARE_OUT_OF_RANGE);

        this.row = row;
        this.column = column;
        this.board = board;
        this.wallDown = null;
        this.wallLeft = null;
        this.wallRight = null;
        this.wallUp = null;
    }

    public void addWallUp(Wall wall) {
        this.wallUp = wall;
    }

    public void addWallLeft(Wall wall) {
        this.wallLeft = wall;
    }

    public void addWallDown(Wall wall) {
        this.wallDown = wall;
    }

    public void addWallRight(Wall wall) {
        this.wallRight = wall;
    }

    public void delWallUp() {
        this.wallUp = null;
    }

    public void delWallLeft() {
        this.wallLeft = null;
    }

    public void delWallDown() {
        this.wallDown = null;
    }

    public void delWallRight() {
        this.wallRight = null;
    }

    public boolean blockUp(Color token) {
        boolean block = false;
        if (this.wallUp != null) {
            block = this.wallUp.blockToken(token);
        }

        return block;
    }

    public boolean blockLeft(Color token) {
        boolean block = false;
        if (this.wallLeft != null) {
            block = this.wallLeft.blockToken(token);
        }

        return block;
    }

    public boolean blockDown(Color token) {
        boolean block = false;
        if (this.wallDown != null) {
            block = this.wallDown.blockToken(token);
        }

        return block;
    }

    public boolean blockRight(Color token) {
        boolean block = false;
        if (this.wallRight != null) {
            block = this.wallRight.blockToken(token);
        }

        return block;
    }

    // Getters y Setters
    public int[] getCoordenates(){
        return new int[] {row, column};
    }

    public Wall getWallUp() {
        return this.wallUp;
    }

    public Wall getWallLeft() {
        return this.wallUp;
    }

    public Wall getWallDown() {
        return this.wallUp;
    }

    public Wall getWallRight() {
        return this.wallUp;
    }

    public abstract void act() throws QuoriPOOBException;
}
