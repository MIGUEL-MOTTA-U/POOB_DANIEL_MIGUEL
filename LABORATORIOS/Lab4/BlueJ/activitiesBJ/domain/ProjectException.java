package domain;

public class ProjectException extends Exception {
    public static final String TIME_EMPTY = "The time is empty";
    public static final String TIME_ERROR = "The time is out of the range";
    public static final String COMPOSED_EMPTY = "Composed is empty";
    public static final String IMPOSSIBLE = "IMPOSSIBLE,It can't be calculated";
    
    public ProjectException(String message){
        super(message);
    }
}
