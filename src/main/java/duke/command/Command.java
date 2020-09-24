package duke.command;

import duke.tasks.TaskList;

/**
 * Represents a command
 * TaskList <code>taskList</code> represents the list of task the command works on
 * String <code>arguments</code> represents the arguments for the command
 */
public abstract class Command {
    protected TaskList taskList;
    protected String arguments;

    /** Constructor */
    protected Command(TaskList taskList, String arguments){
        this.taskList = taskList;
        this.arguments = arguments;
    }

    /**
     * Executes command based on the given arguments on the list of tasks
     *
     * @return CommandResult containing the final taskList, whether the taskList
     *      has been updated through the command and the message to be shown to
     *      the user
     * @throws Exception Various Exceptions when arguments do not fit the command
     *      specification
     */
    public abstract CommandResult execute() throws Exception;

}
