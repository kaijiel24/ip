package duke.exceptions;

/** Used when none of the tasks in the list has a description that contains the search term */
public class NotFoundException extends Exception {
    public final String NOT_FOUND_LINE =
            ":( Oh no! The search term cannot be found.";

    @Override
    public String getMessage(){
        return NOT_FOUND_LINE;
    }
}
