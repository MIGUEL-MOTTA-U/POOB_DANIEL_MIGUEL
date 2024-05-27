package domain;

/**
 * this class represents the game mode pbservers
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public interface GameModeObserver {
    void onGameModeUpdated();

    void checkGameFinished();
}
