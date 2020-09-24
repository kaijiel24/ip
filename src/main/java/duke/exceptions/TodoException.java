package duke.exceptions;

/** Used when TodoCommand does not follow the format:
 *      todo [description]
 */
public class TodoException extends Exception {
    private final String TODO_LINE =
            ":( Oh no! Todo must follow the format: todo <description>";

    @Override
    public String getMessage(){
        return TODO_LINE;
    }
}
