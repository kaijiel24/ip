package duke.exceptions;

public class DeleteFormatException extends Exception{
    public final String DELETED_FORMAT_LINE =
            ":( Oh no! Delete must follow the format: delete <index>";

    @Override
    public String getMessage(){
        return DELETED_FORMAT_LINE;
    }
}
