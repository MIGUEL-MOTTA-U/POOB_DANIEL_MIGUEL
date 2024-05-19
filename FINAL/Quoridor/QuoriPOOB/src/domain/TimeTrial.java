package domain;

import java.util.Timer;

public class TimeTrial extends Mode {
    private TurnTimerTask task;

    public TimeTrial(Quoridor quoridor) {
        super(quoridor);
    }

    public void cancelTask() {
        this.task.cancel();
    }

    @Override
    public void startTurn() {
        if (task != null) task.cancel();
        task = new TurnTimerTask(this, timeLimit);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
}
