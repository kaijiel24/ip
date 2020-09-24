package duke.exceptions;

/**
 * Used when DoneCommand does not follow the format:
 *      done [index]
 */
public class DoneFormatException extends Exception{
    private final String DONE_FORMAT_LINE =
            ":( Oh no! Done must follow the format: done <index>";

    @Override
    public String getMessage(){
        return DONE_FORMAT_LINE;
    }
}
