package domain;

import java.awt.*;

public class Human extends Player {
    public Human(String name, Color color) {
        super(name, color);
    }

    @Override
    public void moveToken(Color color, String direction) throws QuoriPOOBException {
        switch (direction.toUpperCase()) {
            case "UP":
                board.moveTokenUp(this.color);
                break;
            case "DOWN":
                board.moveTokenDown(this.color);
                break;
            case "LEFT":
                board.moveTokenLeft(this.color);
                break;
            case "RIGHT":
                board.moveTokenRight(this.color);
                break;
            case "UPLEFT":
                board.moveTokenUpLeft(this.color);
                break;
            case "UPRIGHT":
                board.moveTokenUpRight(this.color);
                break;
            case "DOWNLEFT":
                board.moveTokenDownLeft(this.color);
                break;
            case "DOWNRIGHT":
                board.moveTokenDownRight(this.color);
                break;
            default:
                throw new QuoriPOOBException(QuoriPOOBException.TOKEN_WRONG_DIRECTION);
        }

    }

    @Override
    public void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide) {
        Wall wallToPut = null;
        for (Wall w : this.walls) {
            if (w.getClass().getSimpleName().toUpperCase().equals(type.toUpperCase())) {
                wallToPut = w;
                break;
            }
        }

        board.addWallToBoard(wallToPut, initialRow, initialColumn, squareSide);
        delWall(wallToPut);
    }
}
