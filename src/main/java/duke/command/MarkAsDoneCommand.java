package duke.command;

import duke.tasks.TaskList;

/**
 * Represents a "mark as done" command and changes the status of
 * of an existing, undone task to done
 */
public class MarkAsDoneCommand extends Command {

    /** Constructor */
    public MarkAsDoneCommand(TaskList taskList, String arguments){
        super(taskList, arguments);
    }

    @Override
    public CommandResult execute() throws Exception{
        String message = taskList.markAsDone(arguments);

        return new CommandResult(message, true, taskList);
    }
}
