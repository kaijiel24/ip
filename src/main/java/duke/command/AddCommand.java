package duke.command;

import duke.tasks.TaskList;
import duke.tasks.TaskType;

/**
 * Represents an add command where a new task is added to the existing list of task
 * TaskType <code>type</code> represents the type of the task to be added
 */
public class AddCommand extends Command{
    private TaskType type;

    /** Constructor */
    public AddCommand(TaskList taskList, String arguments, TaskType type){
        super(taskList, arguments);
        this.type = type;
    }

    @Override
    public CommandResult execute() throws Exception {

        String message = "";

        if (type == TaskType.TODO) {
            message = taskList.addTodo(arguments);

        } else if (type == TaskType.DEADLINE) {
            message = taskList.addDeadline(arguments);

        } else if (type == TaskType.EVENT) {
            message = taskList.addEvent(arguments);
        }

        return new CommandResult(message, true, taskList);
    }
}
