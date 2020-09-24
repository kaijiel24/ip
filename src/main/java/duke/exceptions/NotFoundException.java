package duke.exceptions;

public class NotFoundException extends Exception {
    public final String NOT_FOUND_LINE =
            ":( Oh no! The search term cannot be found.";

    @Override
    public String getMessage(){
        return NOT_FOUND_LINE;
    }
}
