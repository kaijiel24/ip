package duke.command;

import duke.tasks.TaskList;

/** Represents a delete command where an existing task is deleted from the list */
public class DeleteCommand extends Command{

    /** Constructor */
    public DeleteCommand(TaskList taskList, String arguments){
        super(taskList, arguments);
    }

    @Override
    public CommandResult execute() throws Exception{
        String message = taskList.deleteTask(arguments);

        return new CommandResult(message,true, taskList);
    }
}
