package duke.exceptions;

/** Used when the index given in the DeleteCommand is out of range */
public class DeleteRangeException extends Exception {
    private final String OUT_OF_RANGE_LINE =
            ":( Oh no! The index given is out of the range of the number of tasks.";

    @Override
    public String getMessage(){
        return OUT_OF_RANGE_LINE;
    }
}
