package duke.exceptions;

/** Used when commands are not recognised */
public class NothingException extends Exception {
    private final String NOT_RECOGNISED_LINE =
            ":( Oh no! Command not recognised";

    @Override
    public String getMessage(){
        return NOT_RECOGNISED_LINE;
    }
}
