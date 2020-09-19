package duke.exceptions;

public class EventException extends Exception {
    public final String EVENT_LINE =
            ":( Oh no! duke.tasks.Event must follow the format <description> /at <time/date> ";

    @Override
    public String getMessage(){
        return EVENT_LINE;
    }
}
