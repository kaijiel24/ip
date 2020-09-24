package duke.parser;

import duke.command.*;
import duke.tasks.TaskList;
import duke.tasks.TaskType;

/** Parses user commands */
public class Parser {

    // Commands Constant
    public final String BYE = "bye";
    public final String LIST = "list";
    public final String DONE = "done";
    public final String DELETE = "delete";
    public final String TODO = "todo";
    public final String DEADLINE = "deadline";
    public final String EVENT = "event";
    public final String FIND = "find";


    // Regex Constants

    private final String SPACE_REGEX = "\\s";
    private final String START_LINE_REGEX = "^";


    /** Constructor */
    public Parser(){
    }


    /**
     * Parses userInput and returns a command to be executed
     *
     * @param taskList List of task to execute command on
     * @param userInput Input from the user to determin command
     * @return Command to be executed
     */
    public Command parseCommand(TaskList taskList, String userInput){

        final String[] splitLine = userInput.split(" ", 2);
        final String command = splitLine[0];

        final String arguments = userInput.replaceAll(START_LINE_REGEX + command + SPACE_REGEX, "");

        if(arguments.equals(LIST)) {
            return new ListCommand(taskList);
        }

        if (command.equals(TODO)){
            return new AddCommand(taskList, arguments, TaskType.TODO);
        }
        if (command.equals(DEADLINE)){
            return new AddCommand(taskList, arguments, TaskType.DEADLINE);
        }
        if (command.equals(EVENT)){
            return new AddCommand(taskList, arguments, TaskType.EVENT);
        }
        if (command.equals(DELETE) ){
            return new DeleteCommand(taskList, arguments);
        }
        if (command.equals(DONE) ) {
            return new MarkAsDoneCommand(taskList, arguments);
        }
        if (command.equals(FIND)){
            return new FindCommand(taskList, arguments);
        }
        return new EmptyCommand(taskList);
    }
}
