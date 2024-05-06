package domain;

import java.awt.Color;

public class Teleporter extends Square {
    public Teleporter(int row, int column, Board board) throws QuoriPOOBException {
        super(row, column, board);
    }

    @Override
    public boolean blockUp(Color token) {
        return false;
    }

    @Override
    public boolean blockLeft(Color token) {
        return false;
    }

    @Override
    public boolean blockDown(Color token) {
        return false;
    }

    @Override
    public boolean blockRight(Color token) {
        return false;
    }

    @Override
    public boolean blockDiagonalMovements() {
        return false;
    }

    @Override
    public void act() throws QuoriPOOBException {

    }
}
