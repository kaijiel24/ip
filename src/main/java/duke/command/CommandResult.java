package duke.command;

import duke.tasks.TaskList;

/**
 * Represents the results after executing a command
 * String <code>message</code> represents the message to be shown to the user
 * TaskList <code>taskList</code> represents the list of task after executing the command
 * boolean <code>updated</code> represents whether the list of task has been updated
 */
public class CommandResult {
    private String message;
    private TaskList taskList;
    private boolean updated;

    /** Constructor without taskList */
    public CommandResult(String message, boolean updated){
        this.updated = updated;
        this.message = message;
    }

    /** Constructor with taskList */
    public CommandResult(String message, boolean updated, TaskList taskList){
        this.updated = updated;
        this.message = message;
        this.taskList = taskList;
    }

    /** Gets message */
    public String getMessage(){
        return message;
    }

    /** Checks whether list of tasks is updated */
    public boolean isUpdated(){
        return updated;
    }

    /** Gets list of task */
    public TaskList getTaskList(){
        return taskList;
    }
}
