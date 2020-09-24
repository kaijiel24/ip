package duke.exceptions;

/**
 * Used when EventCommand does not follow the format:
 *      event [description] /at [time/date]
 */
public class EventException extends Exception {
    private final String EVENT_LINE =
            ":( Oh no! Event must follow the format: <description> /at <time/date> ";

    @Override
    public String getMessage(){
        return EVENT_LINE;
    }
}
