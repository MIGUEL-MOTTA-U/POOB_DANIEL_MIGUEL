package domain;

import java.util.ArrayList;

public class Token {
    private Board board;
    private Square square;
    private String color;
    private int row;
    private int column;
    private int destiny;
    private ArrayList<int[]> lastMovements;

    public Token(String color) {
        this.color = color;
        lastMovements = new ArrayList<>();
    }

    public boolean checkWin() {
        return (destiny == row) && (square.checkWin());
    }

    public void moveUp() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        --this.row;
        move();
    }

    public void moveLeft() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        --this.column;
        move();
    }

    public void moveDown() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        ++this.row;
        move();
    }

    public void moveRight() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        ++this.column;
        move();
    }

    public void moveUpLeft() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        --this.row;
        --this.column;
        move();
    }

    public void moveUpRight() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        --this.row;
        ++this.column;
        move();
    }

    public void moveDownLeft() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        ++this.row;
        --this.column;
        move();
    }

    public void moveDownRight() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        ++this.row;
        ++this.column;
        move();
    }

    public void jumpTokenUp() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        this.row = this.row - 2;
        move();
    }

    public void jumpTokenUpRight() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        this.row = this.row - 2;
        this.column = this.column + 2;
        move();
    }

    public void jumpTokenUpLeft() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        this.row = this.row - 2;
        this.column = this.column - 2;
        move();
    }

    public void jumpTokenDownRight() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        this.row = this.row + 2;
        this.column = this.column + 2;
        move();
    }

    public void jumpTokenDownLeft() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        this.row = this.row + 2;
        this.column = this.column - 2;
        move();
    }

    public void jumpTokenLeft() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        this.column = this.column - 2;
        move();
    }

    public void jumpTokenDown() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        this.row = this.row + 2;
        move();
    }

    public void jumpTokenRight() throws QuoriPOOBException {
        this.lastMovements.add(new int[] { this.row, this.column });
        this.column = this.column + 2;
        move();
    }

    public void returnTwoMoves() throws QuoriPOOBException {
        if (this.lastMovements.size() <= 2){
            QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.RETURN_MOVES_NOT_POSSIBLE);
            Log.record(e);
			throw e;
        }
        int size = this.lastMovements.size();
        this.lastMovements.remove(size - 1);
        // this.lastMovements.remove(size - 2);
        size = this.lastMovements.size();

        int[] positions = this.lastMovements.get(size - 1);
        this.row = positions[0];
        this.column = positions[1];
    }

    public int getDestiny() {
        return destiny;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public String getColor() {
        return this.color;
    }

    public Square getSquare() {
        return this.square;
    }

    public ArrayList<int[]> getLastMovements() {
        return lastMovements;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public void setPosition(int row, int col) throws QuoriPOOBException {
        this.row = row;
        this.column = col;
        move();
    }

    public void setInitialRow(int initialRow, int lastRow) {
        this.row = initialRow;
        this.destiny = (initialRow == 0) ? lastRow : 0;
    }

    public void setInitialColumn(int initialColumn) {
        this.column = initialColumn;
    }

    public void setLastMovements(ArrayList<int[]> lastMoves) {
        this.lastMovements = lastMoves;
    }

    private void move() throws QuoriPOOBException {
        this.square.delToken();
        Square square = this.board.getSquare(this.row, this.column);
        setSquare(square);
        square.setToken(this);
    }
}
