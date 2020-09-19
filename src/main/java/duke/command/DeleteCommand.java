package duke.command;

import duke.tasks.TaskList;

public class DeleteCommand extends Command{

    public DeleteCommand(TaskList taskList, String arguments){
        super(taskList, arguments);
    }

    @Override
    public CommandResult execute() throws Exception{
        String message = taskList.deleteTask(arguments);

        return new CommandResult(message,true, taskList);
    }
}
