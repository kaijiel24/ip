package duke.exceptions;

public class TodoException extends Exception {
    public final String TODO_LINE =
            ":( Oh no! Todo must be followed by <description>";

    @Override
    public String getMessage(){
        return TODO_LINE;
    }
}
