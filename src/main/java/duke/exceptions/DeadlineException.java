package duke.exceptions;

/**
 * Used when DeadlineCommand does not follow the format:
 *      deadline [description] /by [time/date]
 */
public class DeadlineException extends Exception {
    private final String DEADLINE_LINE =
            ":( Oh no! Deadline must follow the format: deadline <description> /by <time/date> ";

    @Override
    public String getMessage(){
        return DEADLINE_LINE;
    }
}
