package domain;

import java.awt.Color;

/**
 * Create a temporary wall.
 * 
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class Temporary extends Wall {
    private int turn;

    /**
	 * Constructor for objects of class TemporaryWall
	 * 
	 * @param color the color of the wall
	 */
    public Temporary(Color color) {
        super(color);
        this.turn = 0;
    }

    /**
     * Its the behavior of the wall. After 4 turns, the wall is removed form the board
     */
    @Override
    public void act() throws QuoriPOOBException {
        if (this.board != null) {
            this.turn++;
            if (this.turn == 4) delWallFromBoard();
        }
    }

    /**
	 * Delete the wall from the board
	 * 
	 * @throws QuoriPOOBException
	 */
    @Override
    public void delWallFromBoard() throws QuoriPOOBException {
        super.delWallFromBoard();
        this.turn = 0;
    }
}
