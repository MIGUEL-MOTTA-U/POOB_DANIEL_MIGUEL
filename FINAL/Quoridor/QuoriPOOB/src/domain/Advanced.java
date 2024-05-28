package domain;

import java.util.ArrayList;

/**
 * This class extends the Machine class, evaluates the shortest path, and first
 * seeks to
 * obstruct the opponent's path. When there are no more bridges, it advances."
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public class Advanced extends Machine {
    /**
     * comstructor of Advanceed
     * 
     * @param name  the player name
     * @param color the player color
     */
    public Advanced(String name, String color) {
        super(name, color);
    }

    @Override
    /**
     * This method takes the order to move the Token
     * and gives the order to tha Advanced machine to play.
     * 
     * @throws QuoriPOOBException if it is not possible to for the machine.
     */
    protected void moveToken(String direction) throws QuoriPOOBException {
        play(direction);
    }

    @Override
    /**
     * This method takes the order to add a wall to board
     * and gives the order to tha Advanced machine to play.
     * 
     * @throws QuoriPOOBException if it is not possible to for the machine.
     */
    protected void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide)
            throws QuoriPOOBException {
        play(null);
    }

    /*
     * Calculates the next action to do for the token
     * maybe jump of put a wall.
     */
    private void play(String direction) throws QuoriPOOBException {
        ArrayList<Square> machinePath = calculateMyShorestPath();
        ArrayList<Square> humanPath = getOtherPlayer().calculateMyShorestPath();

        if (walls.size() < 1 || !putWall(humanPath)) {
            try {
                direction = getDirection(machinePath.get(1));
                super.moveToken(direction);
            } catch (Exception e) {
                alternativeMove();
            }
        }
    }

    /*
     * Puts a wall in a given path if it is possible.
     */
    private boolean putWall(ArrayList<Square> squares) {
        int initialRow, initialColumn;
        String side;

        for (int i = 0; i < squares.size() - 1; i++) {
            side = getWallSide(squares.get(i), squares.get(i + 1));
            initialRow = squares.get(i).getCoordenates()[0];
            initialColumn = squares.get(i).getCoordenates()[1];
            String type = walls.get(0).getClass().getSimpleName();

            try {
                super.addWallToBoard(type, initialRow, initialColumn, side);
                return true;
            } catch (QuoriPOOBException e) {
                try {
                    if (side.equals("UP") || side.equals("DOWN")) {
                        initialColumn -= 1;
                    } else {
                        initialRow -= 1;
                    }

                    super.addWallToBoard(type, initialRow, initialColumn, side);
                    return true;
                } catch (QuoriPOOBException q) {
                }
            }
        }

        return false;
    }

    /*
     * Returns the side of the given square to put a wall
     */
    private String getWallSide(Square square, Square nextSquare) {
        String res;
        int[] coordenatesSquare = square.getCoordenates();
        int[] coordenatesNext = nextSquare.getCoordenates();

        if (coordenatesSquare[0] == coordenatesNext[0]) {
            res = (coordenatesSquare[1] - coordenatesNext[1] > 0) ? "LEFT" : "RIGHT";
        } else {
            res = (coordenatesSquare[0] - coordenatesNext[0] > 0) ? "UP" : "DOWN";
        }

        return res;
    }
}
