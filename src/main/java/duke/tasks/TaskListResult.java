package duke.tasks;

import duke.tasks.TaskList;

public class TaskListResult {
    public TaskList taskList;
    public String message;

    public TaskListResult(TaskList taskList, String message){
        this.taskList = taskList;
        this.message = message;
    }
}
