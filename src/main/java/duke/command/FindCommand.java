package duke.command;

import duke.tasks.TaskList;

/**
 *  Represents a find command to find all tasks which have descriptions
 *          that contains the given search term
 */
public class FindCommand extends Command {

    public FindCommand(TaskList taskList, String arguments){
        super(taskList, arguments);
    }

    @Override
    public CommandResult execute() throws Exception{
        String message = taskList.findTask(arguments);

        return new CommandResult(message, false);
    }
}
