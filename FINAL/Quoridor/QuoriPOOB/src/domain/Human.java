package domain;

/**
 * The Human class represents a human player in a game.
 * It extends the Player class and provides implementations for moving tokens
 * and adding walls to the game board.
 *
 * @author Daniel Diaz and Miguel MOtta
 * @version 1.0
 * @since 2024-05-25
 */
public class Human extends Player {
    /**
     * comstructor of Human
     * 
     * @param name  the player name
     * @param color the player color
     */
    public Human(String name, String color) {
        super(name, color);
    }

    /*
     * Constructs a new Human instance with the specified name and color.
     *
     */
    @Override
    protected void moveToken(String direction) throws QuoriPOOBException {
        super.moveToken(direction);
    }

    /**
     * Moves the player's token in the specified direction on the game board.
     */
    @Override
    protected void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide)
            throws QuoriPOOBException {
        super.addWallToBoard(type, initialRow, initialColumn, squareSide);
    }
}
