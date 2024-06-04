package domain;

public class ProjectException extends Exception {
    public static final String TIME_EMPTY = "The time is empty";
    public static final String TIME_ERROR = "The time is out of the range";
    public static final String COMPOSED_EMPTY = "Composed is empty";
    public static final String IMPOSSIBLE = "IMPOSSIBLE,It can't be calculated";
    public static final String UNKNOWN = "The activity does not exist";
    public static final String EXISTENT_ACTIVITY = "There is another activity in the project with the same name";
    public static final String WRONG_TYPE = "Wrong value given, follow standard type input";

    public ProjectException(String message){
        super(message);
    }
}
