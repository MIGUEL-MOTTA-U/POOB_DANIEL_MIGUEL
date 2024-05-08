package domain;

import java.awt.Color;

/**
 * Create a allied wall.
 * 
 * @author Daniel Diaz && Miguel Motta
 * @version (a version number or a date)
 */
public class Allied extends Wall {
    /**
	 * Constructor for objects of class TemporaryWall
	 * 
	 * @param color the color of the wall
	 */
    public Allied(Color color) {
        super(color);
    }

    /**
	 * Check if the wall is blocking the way to the token
	 * 
	 * @param token the token to pass
	 * @return TRUE, if the token and wall are of the same color. FALSE, otherwise
	 */
    @Override
    public boolean blockToken(Color token) {
        return (token.equals(this.color)) ? false : true;
    }

    /**
     * Its the behavior of the wall.
     */
    @Override
    public void act() {

    }
}
