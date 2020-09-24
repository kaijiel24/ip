package duke.exceptions;

/** Used when list to be shown is empty */
public class EmptyListException extends Exception {
    private final String EMPTY_LIST_LINE =
            ":( Oh no! List is empty.";

    @Override
    public String getMessage(){
        return EMPTY_LIST_LINE;
    }
}
