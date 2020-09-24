package duke.exceptions;

/** Used when task in MarkAsDoneCommand is already completed */
public class DoneAlreadyException extends Exception {
    private final String COMPLETED_LINE =
            ":( Oh no! The task has already been completed.";

    @Override
    public String getMessage(){
        return COMPLETED_LINE;
    }
}
