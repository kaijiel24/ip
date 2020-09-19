package duke.exceptions;

public class NothingException extends Exception {
    public final String NOT_RECOGNISED_LINE =
            ":( Oh no! Command not recognised";

    @Override
    public String getMessage(){
        return NOT_RECOGNISED_LINE;
    }
}
