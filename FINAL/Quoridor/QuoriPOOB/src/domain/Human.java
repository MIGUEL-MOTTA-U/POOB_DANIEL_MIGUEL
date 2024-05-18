package domain;

import java.awt.*;

public class Human extends Player {
    public Human(String name, Color color) {
        super(name, color);
    }

    @Override
    protected void moveToken(String direction) throws QuoriPOOBException {
        super.moveToken(direction);
    }

    @Override
    protected void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide)
            throws QuoriPOOBException {
        super.addWallToBoard(type, initialRow, initialColumn, squareSide);
    }
}
