package duke.command;

import duke.tasks.TaskList;

public class ListCommand extends Command{

    public ListCommand(TaskList taskList){
        super(taskList, "");
    }

    public CommandResult execute() throws Exception{
        String message = taskList.getList();

        return new CommandResult(message, false, taskList);
    }
}
