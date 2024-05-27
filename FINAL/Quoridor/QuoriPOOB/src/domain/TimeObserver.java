package domain;

/**
 * this class represents the time observers
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public interface TimeObserver {
    public void timeIsUp() throws QuoriPOOBException;

    public void updateTime(int time);
}
