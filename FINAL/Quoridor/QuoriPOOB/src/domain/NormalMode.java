package domain;

/**
 * This Mode's subclass has the behavior of a normal mode,
 * with no time conditions
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2023
 */
public class NormalMode extends Mode {
    /**
     * Constructor of normal mode
     * 
     * @param quoridor the quoridor game
     */
    public NormalMode(Quoridor quoridor) {
        super(quoridor);
    }

    /**
     * Start the turn for the player
     */
    @Override
    public void startTurn() {

    }

    /**
     * there are not tasks created
     */
    @Override
    public void cancelTask() {

    }
}
