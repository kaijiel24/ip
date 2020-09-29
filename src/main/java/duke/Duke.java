package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.command.ExitCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.StorageResult;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * <h1>Duke</h1>
 * The Duke program is implements an application that helps user keep track of their
 *  tasks in various forms such as Todos, Deadlines and Events.
 *
 * @author Kai Jie
 * @version 0.2
 */
public class Duke {

    private final String DUKE_TXT = "data/duke.txt";

    private TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;


    /**
     * Creates a new instance of Ui, Storage and Parser.
     * Initialises the taskList using Storage initialiseTaskList method
     * and prints message from initialisation.
     */
    public Duke(){
        ui = new Ui();
        storage = new Storage(DUKE_TXT);
        parser = new Parser();

        StorageResult result = storage.initialiseTaskList();
        taskList = result.getTaskList();
        ui.printMessage(result.getMessage());
    }


    /**
     * Executes the command and returns the result
     *
     * @param command command from parser
     * @return result from executing command
     */
    public CommandResult executeCommand(Command command) {
        // Solution below adapted from personbook
        try {
            return command.execute();
        } catch (Exception e){
            return new CommandResult(e.getMessage(), false, null);
        }
    }


    /**
     * This method is used to runDuke continuously on a loop doing the following things:
     * take in a userInput, parse the output through the Parser to get the command to run
     * and run the command. It then writes new taskList to storage if there are any changes
     * and prints the message to for the user
     * The loop is exited when the userInput is "bye"
     */
    public void runDuke(){
        ui.printGreeting();

        // Solution below adapted from personbook
        Command command;

        do {
            ui.printPrompt();
            String userInput = ui.readUserInput();

            command = parser.parseCommand(taskList, userInput);

            CommandResult commandResult = executeCommand(command);

            if (commandResult.isUpdated()){
                taskList = commandResult.getTaskList();
                String storageMessage = storage.writeToFile(taskList);

                ui.printMessage(storageMessage);
            }

            ui.printMessage(commandResult.getMessage());

        } while (!ExitCommand.isExit(command));

        ui.printExitLine();
    }


    /**
     * This is the main method which creates a new instance of Duke and uses
     *      runDuke method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke().runDuke();
    }
}
