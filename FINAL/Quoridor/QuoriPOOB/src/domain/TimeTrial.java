package domain;

import java.util.Timer;

public class TimeTrial extends Mode {
    private TurnTimerTask task;

    /**
     * Constructor of TimeTrial
     * 
     * @param quoridor the quoridor game
     */
    public TimeTrial(Quoridor quoridor) {
        super(quoridor);
    }

    /**
     * Defines the behavior when a task is canceled.
     */
    @Override
    public void cancelTask() {
        this.task.cancel();
    }

    /**
     * Defines the behavior when a turn starts.
     */
    @Override
    public void startTurn() {
        if (task != null)
            task.cancel();
        task = new TurnTimerTask(this, timeLimit);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
}
