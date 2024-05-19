package domain;

import java.util.TimerTask;
import javax.swing.JOptionPane;

public class TurnTimerTask extends TimerTask{
    private Mode mode;
    private int remainingTime;
    
    public TurnTimerTask(Mode mode, int timeLimit) {
        this.mode = mode;
        this.remainingTime = timeLimit;
    }

    public int getRemainingTime() {
        return this.remainingTime;
    }

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
        }
    }
}
