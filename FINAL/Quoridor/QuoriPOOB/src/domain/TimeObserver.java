package domain;

public interface TimeObserver {
    public void timeIsUp() throws QuoriPOOBException;
    public void updateTime(int time);
}
