package domain;

import java.util.Timer;

public class Timed extends Mode {
    private TurnTimerTask taskPlayer1;
    private TurnTimerTask taskPlayer2;
    private int timePlayer1;
    private int timePlayer2;
    public boolean playerOnePlaying;

    /**
     * Constructor of Timed
     * 
     * @param quoridor the quoridor game
     */
    public Timed(Quoridor quoridor) {
        super(quoridor);
        this.playerOnePlaying = true;
    }

    /**
     * Sets the time limit for the game mode.
     *
     * @param time The time limit in seconds.
     */
    @Override
    public void setTime(int time) {
        super.setTime(time);
        this.timePlayer1 = time;
        this.timePlayer2 = time;
    }

    /**
     * Set the time of each player
     * 
     * @param timePlayer1      the time of the player 1
     * @param timePlayer2      the time of the player 2
     * @param playerOnePlaying TRUE, if the player 1 is playing. FALSE, otherwise
     */
    public void setTimePlayers(int timePlayer1, int timePlayer2, boolean playerOnePlaying) {
        this.timePlayer1 = timePlayer1;
        this.timePlayer2 = timePlayer2;
        this.playerOnePlaying = playerOnePlaying;
    }

    /**
     * Return if the player 1 is playing
     * 
     * @return TRUE, if the player 1 is playing. FALSE, otherwise
     */
    public boolean getPlayerOnePlaying() {
        return this.playerOnePlaying;
    }

    /**
     * return the time mode
     * 
     * @return the time mode
     */
    @Override
    public int[] getTime() {
        int[] time = new int[3];
        time[0] = this.timeLimit;
        time[1] = this.timePlayer1;
        time[2] = this.timePlayer2;

        return time;
    }

    /**
     * Defines the behavior when a turn starts.
     */
    @Override
    public void startTurn() {
        setPlayerPlaying();
        timer = new Timer();

        if (playerOnePlaying) {
            taskPlayer1 = new TurnTimerTask(this, timePlayer1);
            timer.scheduleAtFixedRate(taskPlayer1, 0, 1000);
        } else {
            taskPlayer2 = new TurnTimerTask(this, timePlayer2);
            timer.scheduleAtFixedRate(taskPlayer2, 0, 1000);
        }

    }

    /**
     * Defines the behavior when a task is canceled.
     */
    @Override
    public void cancelTask() {
        this.taskPlayer1.cancel();
        this.taskPlayer2.cancel();
        timer.cancel();
    }

    /**
     * Ends the current turn, moves to the next turn, and notifies observers.
     *
     * @throws QuoriPOOBException if an error occurs during the next turn.
     */
    @Override
    public void endTurn() throws QuoriPOOBException {
        if (timePlayer1 == 0 & timePlayer2 == 0) {
            this.quoridor.finishGame();
            notifyObservers();
        } else {
            super.endTurn();
        }
    }

    /*
     * Set the player playing
     */
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

        if (timer != null)
            timer.cancel();
    }
}
