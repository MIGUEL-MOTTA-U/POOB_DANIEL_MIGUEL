package domain;

import java.util.ArrayList;

/**
 * The class intermediate extends Machine. This machine calculates the shortest
 * path
 * of the machine and the other player, it moves by the path if the machine path
 * is shortest,
 * puts a wall to the other player paths otherwise.
 * 
 *
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 2024-05-25
 */
public class Intermediate extends Machine {
    /**
     * Constructor of Intermediate
     * 
     * @param name  the name of the Machine
     * @param color the color of the Machine
     */
    public Intermediate(String name, String color) {
        super(name, color);
    }

    /*
     * Overrides the method moveToken() to act according to the behaviour of
     * Intermediate Machine
     */
    @Override
    protected void moveToken(String direction) throws QuoriPOOBException {
        play(direction);
    }

    /*
     * Overrides the method addWallToBoard() to act according to the behaviour of
     * Intermediate Machine
     */
    @Override
    protected void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide)
            throws QuoriPOOBException {
        play(null);
    }

    /*
     * calculates the shortest path of the machine and the other player, it moves by
     * the path if the machine path is shortest, puts a wall to the other player
     * paths otherwise.
     */
    private void play(String direction) throws QuoriPOOBException {
        ArrayList<Square> machinePath = calculateMyShorestPath();
        ArrayList<Square> humanPath = getOtherPlayer().calculateMyShorestPath();

        if (machinePath.size() <= humanPath.size() || walls.size() < 1) {
            try {
                direction = getDirection(machinePath.get(1));
                super.moveToken(direction);
            } catch (Exception e) {
                alternativeMove();
            }
        } else {
            if (!putWall(humanPath)) {
                try {
                    direction = getDirection(machinePath.get(1));
                    super.moveToken(direction);
                } catch (Exception e) {
                    alternativeMove();
                }
            }
        }
    }

    /*
     * Returns true if it put a wall false otherwise.
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
     * Returns the side to put a wall between two squares
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
