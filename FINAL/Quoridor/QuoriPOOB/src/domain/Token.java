package domain;

import java.awt.Color;
import java.util.ArrayList;

public class Token {
    private Square square;
    private Color color;
    private int row;
    private int column;
    private ArrayList<int[]> lastMovements;

    public Token(Color color, int row, int column) {
        this.color = color;
        this.row = row;
        this.column = column;
    }

    public void moveUp() {
        this.row--;
    }

    public void moveLeft() {
        this.column--;
    }

    public void moveDown() {
        this.row++;
    }

    public void moveRight() {
        this.column++;
    }

    public void moveUpLeft() {
        this.row--;
        this.column--;
    }
    
    public void moveUpRight() {
        this.row--;
        this.column++;
    }
    
    public void moveDownLeft() {
        this.row++;
        this.column--;
    }
    
    public void moveDownRight() {
        this.row++;
        this.column++;
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
}
