package domain;

import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 * this class represents the actions to be executed during a player's turn
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public class TurnTimerTask extends TimerTask {
    private Mode mode;
    private int remainingTime;

    /**
     * Constructor of TurnTimerTask
     * 
     * @param mode      the game mode
     * @param timeLimit the time limit of the game
     */
    public TurnTimerTask(Mode mode, int timeLimit) {
        this.mode = mode;
        this.remainingTime = timeLimit;
    }

    /**
     * Return the time remaining
     * 
     * @return the time remaining
     */
    public int getRemainingTime() {
        return this.remainingTime;
    }

    /**
     * the action to be taken when time is running out
     */
    @Override
    public void run() {
        try {
            if (remainingTime <= 0) {
                this.mode.endTurn();
            } else {
                this.mode.updateTime(remainingTime);
                remainingTime--;
            }
        } catch (QuoriPOOBException e) {
            JOptionPane.showConfirmDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Log.record(e);
        }
    }
}
