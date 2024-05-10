package domain;

import java.awt.Color;
import java.util.ArrayList;

public class Token {
    private Board board;
    private Square square;
    private Color color;
    private int row;
    private int column;
    private ArrayList<int[]> lastMovements;

    public Token(Color color) {
        this.color = color;
        lastMovements = new ArrayList<>();
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public void setInitialRow(int initialRow) {
        this.row = initialRow;
    }

    public void setInitialColumn(int initialColumn) {
        this.column = initialColumn;
    }

    public void moveUp() throws QuoriPOOBException {
        this.lastMovements.add(new int[]{this.row, this.column});
        --this.row;
        move();
    }

    public void moveLeft() throws QuoriPOOBException {
        this.lastMovements.add(new int[]{this.row, this.column});
        --this.column;   
        move();
    }

    public void moveDown() throws QuoriPOOBException {
        this.lastMovements.add(new int[]{this.row, this.column});
        ++this.row;
        move();
    }

    public void moveRight() throws QuoriPOOBException {
        this.lastMovements.add(new int[]{this.row, this.column});
        ++this.column;
        move();
    }

    public void moveUpLeft() throws QuoriPOOBException {
        this.lastMovements.add(new int[]{this.row, this.column});
        --this.row;
        --this.column;
        move();
    }
    
    public void moveUpRight() throws QuoriPOOBException {
        this.lastMovements.add(new int[]{this.row, this.column});
        --this.row;
        ++this.column;
        move();
    }
    
    public void moveDownLeft() throws QuoriPOOBException {
        this.lastMovements.add(new int[]{this.row, this.column});
        ++this.row;
        --this.column;
        move();
    }
    
    public void moveDownRight() throws QuoriPOOBException {
        this.lastMovements.add(new int[]{this.row, this.column});
        ++this.row;
        ++this.column;
        move();
    }

    public void jumpTokenUp() throws QuoriPOOBException {
        this.lastMovements.add(new int[]{this.row, this.column});
        this.row = this.row - 2;
        move();
    }

    public void jumpTokenLeft() throws QuoriPOOBException {
        this.lastMovements.add(new int[]{this.row, this.column});
        this.column = this.column - 2;
        move();
    }

    public void jumpTokenDown() throws QuoriPOOBException {
        this.lastMovements.add(new int[]{this.row, this.column});
        this.row = this.row + 2;
        move();
    }

    public void jumpTokenRight() throws QuoriPOOBException {
        this.lastMovements.add(new int[]{this.row, this.column});
        this.column = this.column + 2;
        move();
    }

    public void returnTwoMoves() throws QuoriPOOBException {
        if (this.lastMovements.size() <= 2) throw new QuoriPOOBException(QuoriPOOBException.RETURN_MOVES_NOT_POSSIBLE);

        int size = this.lastMovements.size();
        this.lastMovements.remove(size - 1);
        this.lastMovements.remove(size - 2);
        size = this.lastMovements.size();

        int[] positions = this.lastMovements.get(size - 1);
        this.row = positions[0];
        this.column = positions[1];
    }

    //Getters & setters

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public Color getColor() {
        return this.color;
    }

    public Square getSquare() {
        return this.square;
    }

    private void move() throws QuoriPOOBException {
        this.square.delToken();
        Square square = this.board.getSquare(this.row, this.column);
        setSquare(square);
        square.setToken(this);
    }
}
