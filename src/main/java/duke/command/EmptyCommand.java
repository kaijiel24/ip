package duke.command;

import duke.tasks.TaskList;

/** Represents an empty command to be executed when the command is not recognised */
public class EmptyCommand extends Command{

    /** Constructor */
    public EmptyCommand(TaskList taskList) {
        super(taskList, "");
    }

    @Override
    public CommandResult execute() throws Exception {
        taskList.doNothing();
        return new CommandResult("", false);
    }
}
