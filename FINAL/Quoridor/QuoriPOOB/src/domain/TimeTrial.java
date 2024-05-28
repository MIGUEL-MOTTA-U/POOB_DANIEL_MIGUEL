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
        timer.cancel();
    }

    /**
     * Defines the behavior when a turn starts.
     */
    @Override
    public void startTurn() {
        if (task != null)
            task.cancel();
        if (timer != null)
            timer.cancel();

        task = new TurnTimerTask(this, timeLimit);
        timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
}
