package duke.command;

import duke.tasks.TaskList;

/** Represents a list command to show all current tasks in list */
public class ListCommand extends Command{

    /** Constructor */
    public ListCommand(TaskList taskList){
        super(taskList, "");
    }

    @Override
    public CommandResult execute() throws Exception{
        String message = taskList.getList();

        return new CommandResult(message, false);
    }
}
