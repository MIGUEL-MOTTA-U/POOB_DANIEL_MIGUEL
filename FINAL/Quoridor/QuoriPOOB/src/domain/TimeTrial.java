package domain;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class TimeTrial extends Mode {
    TurnTimerTask task;

    public TimeTrial(Quoridor quoridor) {
        super(quoridor);
    }

    @Override
    public void startTurn() {
        if (task != null) task.cancel();
        task = new TurnTimerTask(timeLimit);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    private class TurnTimerTask extends TimerTask {
        private int remainingTime;
    
        public TurnTimerTask(int timeLimit) {
            this.remainingTime = timeLimit;
        }
    
        @Override
        public void run() {
            try {
                if (remainingTime <= 0) {
                    endTurn();
                } else {
                    updateTime(remainingTime);
                    remainingTime--;
                }
            } catch (QuoriPOOBException e) {
                JOptionPane.showConfirmDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateTime(int time) {
        for (TimeObserver observer : observers) {
            observer.updateTime(time);
        }
    }
}
