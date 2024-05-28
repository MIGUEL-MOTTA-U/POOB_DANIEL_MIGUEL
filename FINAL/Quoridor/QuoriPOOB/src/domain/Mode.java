package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * An abstract class representing a game mode in Quoridor.
 * It implements the Serializable interface.
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2023
 */
public abstract class Mode {
    protected List<TimeObserver> observers;
    protected Quoridor quoridor;
    protected int timeLimit;
    protected Timer timer;

    /**
     * Constructor for the Mode class.
     *
     * @param quoridor The Quoridor game instance.
     */
    public Mode(Quoridor quoridor) {
        this.quoridor = quoridor;
        this.observers = new ArrayList<>();
    }

    /**
     * Sets the time limit for the game mode.
     *
     * @param time The time limit in seconds.
     */
    public void setTime(int time) {
        this.timeLimit = time;
    }

    /**
     * return the time mode
     * 
     * @return the time mode
     */
    public int[] getTime() {
        int[] time = new int[1];
        time[0] = this.timeLimit;

        return time;
    }

    /**
     * Adds a TimeObserver to the list of observers.
     *
     * @param observer The TimeObserver to be added.
     */
    public void addObserver(TimeObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes a TimeObserver from the list of observers.
     *
     * @param observer The TimeObserver to be removed.
     */
    public void removeObserver(TimeObserver observer) {
        observers.remove(observer);
    }

    /**
     * Ends the current turn, moves to the next turn, and notifies observers.
     *
     * @throws QuoriPOOBException if an error occurs during the next turn.
     */
    public void endTurn() throws QuoriPOOBException {
        this.quoridor.nextTurn();
        notifyObservers();
    }

    /**
     * Updates the time for all observers in the list.
     *
     * @param time The new time value.
     */
    public void updateTime(int time) {
        for (TimeObserver observer : observers) {
            observer.updateTime(time);
        }
    }

    /**
     * Abstract method to be implemented by concrete subclasses.
     * Defines the behavior when a turn starts.
     */
    public abstract void startTurn();

    /**
     * Abstract method to be implemented by concrete subclasses.
     * Defines the behavior when a task is canceled.
     */
    public abstract void cancelTask();

    /*
     * Notifies all observers that the time is up.
     */
    protected void notifyObservers() throws QuoriPOOBException {
        for (TimeObserver observer : observers) {
            observer.timeIsUp();
        }
    }
}
