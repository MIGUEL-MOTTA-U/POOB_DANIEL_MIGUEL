package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Mode implements Serializable {
    protected List<TimeObserver> observers;
    protected Quoridor quoridor;
    protected int timeLimit;

    public Mode(Quoridor quoridor) {
        this.quoridor = quoridor;
        this.observers = new ArrayList<>();
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

    public void endTurn() throws QuoriPOOBException {
        this.quoridor.nextTurn();
        notifyObservers();
    }

    public void updateTime(int time) {
        for (TimeObserver observer : observers) {
            observer.updateTime(time);
        }
    }

    public abstract void startTurn();
    public abstract void cancelTask();

    protected void notifyObservers() throws QuoriPOOBException {
        for (TimeObserver observer : observers) {
            observer.timeIsUp();
        }
    }
}
