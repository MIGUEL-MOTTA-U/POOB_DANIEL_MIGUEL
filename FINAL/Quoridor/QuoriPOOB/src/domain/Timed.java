package domain;

import java.util.Timer;

public class Timed extends Mode {
    private TurnTimerTask taskPlayer1;
    private TurnTimerTask taskPlayer2;
    private int timePlayer1;
    private int timePlayer2;
    public boolean playerOnePlaying;

    public Timed(Quoridor quoridor) {
        super(quoridor);
        this.playerOnePlaying = true;
    }

    @Override
    public void setTime(int time) {
        super.setTime(time);
        this.timePlayer1 = time;
        this.timePlayer2 = time;
    }

    @Override
    public int[] getTime() {
        int[] time = new int[3];
        time[0] = this.timeLimit;
        time[1] = this.timePlayer1;
        time[2] = this.timePlayer2;

        return time;
    }

    @Override
    public void startTurn() {
        setPlayerPlaying();
        Timer timer = new Timer();

        if (playerOnePlaying) {
            taskPlayer1 = new TurnTimerTask(this, timePlayer1);
            timer.scheduleAtFixedRate(taskPlayer1, 0, 1000);
        } else {
            taskPlayer2 = new TurnTimerTask(this, timePlayer2);
            timer.scheduleAtFixedRate(taskPlayer2, 0, 1000);
        }

    }

    @Override
    public void cancelTask() {
        this.taskPlayer1.cancel();
        this.taskPlayer2.cancel();
    }

    private void setPlayerPlaying() {
        if (this.playerOnePlaying) {
            this.playerOnePlaying = false;
            if (this.taskPlayer1 != null) {
                this.timePlayer1 = taskPlayer1.getRemainingTime();
                taskPlayer1.cancel();
            }
        } else {
            playerOnePlaying = true;
            if (this.taskPlayer2 != null) {
                this.timePlayer2 = taskPlayer2.getRemainingTime();
                taskPlayer2.cancel();
            }
        }
    }

    @Override
    public void endTurn() throws QuoriPOOBException {
        if (timePlayer1 == 0 & timePlayer2 == 0) {
            this.quoridor.finishGame();
            notifyObservers();
        } else {
            super.endTurn();
        }
    }
}
