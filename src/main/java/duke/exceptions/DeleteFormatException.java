package duke.exceptions;

/**
 * Used when DeleteCommand does not follow the format:
 *      delete [index]
 */
public class DeleteFormatException extends Exception{
    private final String DELETED_FORMAT_LINE =
            ":( Oh no! Delete must follow the format: delete <index>";

    @Override
    public String getMessage(){
        return DELETED_FORMAT_LINE;
    }
}
