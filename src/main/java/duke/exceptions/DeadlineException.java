package duke.exceptions;

public class DeadlineException extends Exception {
    public final String DEADLINE_LINE =
            ":( Oh no! Deadline must follow the format <description> /by <time/date> ";

    @Override
    public String getMessage(){
        return DEADLINE_LINE;
    }
}
