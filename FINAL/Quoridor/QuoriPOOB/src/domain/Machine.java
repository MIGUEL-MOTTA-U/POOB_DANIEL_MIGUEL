package domain;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This players's subclass has the same behaviors that a normal player, except
 * it
 * calculates a determinated strategy to play against the player
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2023
 */
public abstract class Machine extends Player {
    /**
     * Constructor of Machine
     * 
     * @param name  the name of the machine
     * @param color the color of the machine
     */
    public Machine(String name, String color) {
        super(name, color);
    }

    /*
     * Returns the last square where the machine was
     */
    protected Square lastSquare() {
        int lastAdded = board.getTokens().get(color).getLastMovements().size() - 1;
        int[] lastCoordenates = board.getTokens().get(color).getLastMovements().get(lastAdded);
        int row = lastCoordenates[0], column = lastCoordenates[1];

        return board.getMatrixBoard()[row][column];
    }

    /*
     * Returns to move to a determinated square
     */
    protected String getDirection(Square square) throws QuoriPOOBException {
        return board.getDirection(color, square);
    }

    /*
     * This method ensures that if a special case arises,
     * the machine can move in the appropriate direction
     */
    protected void alternativeMove() {
        String direction;

        try {
            direction = getDirection(lastSquare());
            super.moveToken(direction);
        } catch (Exception e) {
            ArrayList<String> directions = new ArrayList<>(Arrays.asList("UP", "DOWN", "RIGHT", "LEFT"));
            boolean moved = false;
            int i = 0;

            while (!moved) {
                try {
                    super.moveToken(directions.get(i));
                    moved = true;
                } catch (QuoriPOOBException q) {
                    i++;
                }
            }
        }
    }
}
