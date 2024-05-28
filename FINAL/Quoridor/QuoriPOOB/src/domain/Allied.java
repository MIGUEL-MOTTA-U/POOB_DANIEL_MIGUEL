package domain;

/**
 * This wall's subclass has the same behaviors that a normal wall, except it
 * allows to pass to the player who put the wall.
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public class Allied extends Wall {
    /**
     * Constructor for objects of class TemporaryWall
     * 
     * @param color the color of the wall
     */
    public Allied(String color) {
        super(color);
    }

    /**
     * Check if the wall is blocking the way to the token
     * 
     * @param token the token to pass
     * @return TRUE, if the token and wall are of the same color. FALSE, otherwise
     */
    @Override
    public boolean blockToken(String token) {
        return (token.equals(this.color)) ? false : true;
    }

    /**
     * Its the behavior of the wall.
     */
    @Override
    public void act() {

    }
}
