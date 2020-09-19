package duke.command;

import duke.tasks.TaskList;

public class EmptyCommand extends Command{

    public EmptyCommand(TaskList taskList) {
        super(taskList, "");
    }

    @Override
    public CommandResult execute() throws Exception {
        taskList.doNothing();
        return new CommandResult("", false, taskList);
    }
}
