package duke.storage;

import duke.tasks.TaskList;

/**
 * Represents the result of getting a taskList from file
 * TaskList <code>taskList</code> represents the list of task received from the user
 * String <code>message</code> represents the message to be sent back to the user about the result
 */
public class StorageResult {
    private TaskList taskList;
    private String message;

    /**
     * Constructor
     */
    public StorageResult(TaskList taskList, String message){
        this.taskList = taskList;
        this.message = message;
    }

    /**
     * Gets taskList
     */
    public TaskList getTaskList(){
        return taskList;
    }

    /**
     * Gets message
     */
    public String getMessage(){
        return message;
    }
}