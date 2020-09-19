package duke.exceptions;

public class DoneFormatException extends Exception{
    public final String DONE_FORMAT_LINE =
            ":( Oh no! Done must follow the format: done <index>";

    @Override
    public String getMessage(){
        return DONE_FORMAT_LINE;
    }
}
