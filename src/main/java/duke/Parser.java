package duke;

import duke.command.*;
import duke.tasks.TaskList;
import duke.tasks.TaskType;

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

    public final String SPACE_REGEX = "\\s";
    public final String START_LINE_REGEX = "^";


    public Parser(){
    }


    // Reads the input of the line to determine the command and run it
    public Command parseCommand(TaskList taskList, String userInput){

        // Get command from the userInput
        final String[] splitLine = userInput.split(" ", 2);
        final String command = splitLine[0];

        // Remove command from userInput
        final String arguments = userInput.replaceAll(START_LINE_REGEX + command + SPACE_REGEX, "");

        // Check the command type then execute the commands
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
