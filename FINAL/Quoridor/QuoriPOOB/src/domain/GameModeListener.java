package domain;

/**
 * this class represents the game mode listeners
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public interface GameModeListener {
    void onGameModeUpdated();

    void checkGameFinished();
}
