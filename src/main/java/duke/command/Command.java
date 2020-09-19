package duke.command;

import duke.tasks.TaskList;

public abstract class Command {
    protected TaskList taskList;
    protected String arguments;

    protected Command(TaskList taskList, String arguments){
        this.taskList = taskList;
        this.arguments = arguments;
    }

    public abstract CommandResult execute() throws Exception;

}
