package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Mode implements Serializable {
    protected List<TimeObserver> observers = new ArrayList<>();
    protected Quoridor quoridor;
    protected int timeLimit;

    public Mode(Quoridor quoridor) {
        this.quoridor = quoridor;
    }

    public void setTime(int time) {
        this.timeLimit = time;
    }

    public void addObserver(TimeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TimeObserver observer) {
        observers.remove(observer);
    }

    protected void endTurn() {
        this.quoridor.nextTurn();
        notifyObservers();
    }

    public abstract void startTurn();

    private void notifyObservers() {
        for (TimeObserver observer : observers) {
            observer.timeIsUp();
        }
    }
}
