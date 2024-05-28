package domain;

import java.util.*;

/**
 * his class extends the Machine class and exhibits random behavior,
 * in which it will either place a wall or advance randomly
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public class Beginner extends Machine {
    /**
     * comstructor of Beginner
     * 
     * @param name  the player name
     * @param color the player color
     */
    public Beginner(String name, String color) {
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
     * The logic of put a random wall or jump
     */
    private void play(String direction) throws QuoriPOOBException {
        Random random = new Random();
        int option = random.nextInt(2);

        if (option == 0) {
            jump();
        } else {
            if (this.walls.size() > 0) {
                putWall();
            } else {
                jump();
            }
        }
    }

    /*
     * Puts a random wall in a random position.
     */
    private void putWall() {
        ArrayList<String> types = new ArrayList<>(Arrays.asList("Temporary", "NormalWall", "Allied"));
        ArrayList<String> orientation = new ArrayList<>(Arrays.asList("UP", "DOWN", "LEFT", "RIGHT"));
        int type, oriented, row, column;
        boolean created = false;
        Random random = new Random();

        while (!created) {
            type = random.nextInt(types.size());
            oriented = random.nextInt(orientation.size());
            row = random.nextInt(board.getSize());
            column = random.nextInt(board.getSize());

            try {
                super.addWallToBoard(types.get(type), row, column, orientation.get(oriented));
                created = true;
            } catch (QuoriPOOBException e) {
            }
        }
    }

    /*
     * Moves the token to a random position.
     */
    private void jump() throws QuoriPOOBException {
        ArrayList<String> possibilities = new ArrayList<>(Arrays.asList("UP", "DOWN", "LEFT", "RIGHT"));
        int option;
        boolean moved = false;
        Random random = new Random();

        while (possibilities.size() > 0 && !moved) {
            option = random.nextInt(possibilities.size());

            try {
                super.moveToken(possibilities.get(option));
                moved = true;
            } catch (QuoriPOOBException e) {
                possibilities.remove(option);
            }
        }

        if (possibilities.size() == 0 && !moved){
            QuoriPOOBException e = new QuoriPOOBException(QuoriPOOBException.RETURN_MOVES_NOT_POSSIBLE);
            Log.record(e);
            throw e;
        }
            
    }
}
