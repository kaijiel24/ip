package duke.exceptions;

import java.util.concurrent.CompletableFuture;

public class DoneAlreadyException extends Exception {
    public final String COMPLETED_LINE =
            ":( Oh no! The task has already been completed.";

    @Override
    public String getMessage(){
        return COMPLETED_LINE;
    }
}
