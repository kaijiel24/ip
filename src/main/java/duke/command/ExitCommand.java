package duke.command;

import duke.tasks.TaskList;

public class ExitCommand extends Command{
    public ExitCommand(TaskList taskList){
        super(taskList, "");
    }

    @Override
    public CommandResult execute(){
        return new CommandResult("", false);
    }

    /** Checks if command given is and exit command */
    public static boolean isExit(Command command){
        return command instanceof ExitCommand;
    }
}
