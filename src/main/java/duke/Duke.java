package duke;

import duke.exceptions.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {

    // Lines to be printed
    public static final String[] GREETING_LINES = {
            "Hello! I'm duke.Duke",
            "What can I do for you?"};
    public static final String BYE_LINE =
            "Bye. Hope to see you again soon!";
    public static final String EMPTY_LIST_LINE =
            "List is empty.";
    public static final String TASK_DONE_LINE =
            "Nice! I've marked this task as done:\n  ";
    public static final String TASK_DELETED_LINE =
            "Noted. I've removed this task:";
    public static final String LIST_INTRO_LINE =
            "Here are the tasks in your list";
    public static final String NOT_RECOGNISED_LINE =
            ":( Oh no! Command not recognised";
    public static final String DONE_FORMAT_LINE =
            ":( Oh no! Done must follow the format: done <index>";
    public static final String DELETED_FORMAT_LINE =
            ":( Oh no! Delete must follow the format: delete <index>";
    public static final String OUT_OF_RANGE_LINE =
            ":( Oh no! The index given is out of the range of the number of tasks.";
    public static final String COMPLETED_LINE =
            ":( Oh no! The task has already been completed.";
    public static final String TODO_LINE =
            ":( Oh no! Description of todo cannot be empty";
    public static final String DEADLINE_LINE =
            ":( Oh no! duke.tasks.Deadline must follow the format <description> /by <time/date> ";
    public static final String EVENT_LINE =
            ":( Oh no! duke.tasks.Event must follow the format <description> /at <time/date> ";

    // Commands Constant
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    // Regex Constants
    public static final String GET_DESCRIPTION_REGEX = "/.+";
    public static final String GET_AT_REGEX = ".+/at ";
    public static final String GET_BY_REGEX = ".+/by ";
    public static final String SPACE_REGEX = "\\s";
    public static final String START_LINE_REGEX = "^";
    public static final String DIGITS_REGEX = "\\d+";
    public static final String DEADLINE_REGEX = ".+/by.+";
    public static final String EVENT_REGEX = ".+/at.+";
    public static final String TASK_ADDED_LINE = "Got it. I've added this task:";

    // Path to file data/duke.txt
    public static final String DUKE_TXT = "data/duke.txt";

    // duke.tasks.Task variables
    public static ArrayList<Task> taskList = new ArrayList<>();


    // Data file variable
    public static File f = new File(DUKE_TXT);

    public static void loadData()
            throws FileNotFoundException, FileFormatException{

        Scanner s = new Scanner(f);
        while (s.hasNext()){
            String entry = s.nextLine();
//            System.out.println(entry);

            // Check if entry is in the correct format
            if(!entry.matches("T\\s\\|\\s[01]\\s\\|.+")
                && !entry.matches("[ED]\\s\\|\\s[01]\\s\\|.+\\|.+")) {
                throw new FileFormatException();
            }

            String[] entrySplit= entry.split("\\s\\|\\s");
//            System.out.println(Arrays.toString(entrySplit));
            String taskType = entrySplit[0];
            boolean isDone = entrySplit[1].equals("1");
            String description = entrySplit[2];
            String atBy = entrySplit.length >= 4 ? entrySplit[3] : "";


            if (taskType.equals("T")){
                taskList.add(new Todo(description, isDone));

            } else if (taskType.equals("D")){
                taskList.add(new Deadline(description, isDone, atBy));

            } else {
                taskList.add(new Event(description, isDone, atBy));

            }
        }
    }

    public static void createFile() throws IOException{
        Path pathToFile = Paths.get(DUKE_TXT);
        Files.createDirectories(pathToFile.getParent());
        f.createNewFile();
    }

    public static void initialiseTaskList(){
        try {
            loadData();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
            try {
                createFile();

            } catch (IOException e1){
                System.out.println("Error creating new file");
            }
            System.out.println("New file data/duke.txt created");

        } catch (FileFormatException e){
            System.out.println("File formatting error");
            taskList.clear();
        }
    }

    public static void _writeToFile() throws IOException{
        FileWriter fw = new FileWriter(DUKE_TXT);
        for (Task task : taskList){
            fw.write(task.saveTask() + "\n");
        }
        fw.close();
    }

    public static void writeToFile(){
        try {
            _writeToFile();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }


    // Prints a horizontal dash line
    public static void printDashLine(){
        System.out.println("--------------------------------"
                + "---------------------------------------");
    }

    public static void printOneLine(String line){
        printDashLine();
        System.out.println(line);
        printDashLine();
    }

    public static void printMultiLine(String[] lines){
        printDashLine();
        for (String line : lines){
            System.out.println(line);
        }
        printDashLine();
    }

    // Prints the prompt before every user input
    public static void printPrompt(){
        System.out.print("  > ");
    }

    // Prints the greet statement
    public static void printGreeting(){
        printMultiLine(GREETING_LINES);
    }

    // Prints the exit statement
    public static void printExitLine() {
        printOneLine(BYE_LINE);
    }

    // Print list of task
    public static void printList() throws EmptyListException {
        if (taskList.size() == 0) {
            throw new EmptyListException();
        }
        String[] listLines = new String[taskList.size() + 1];

        listLines[0] = LIST_INTRO_LINE;

        // Add each item to listLines
        for (int i = 1; i <= taskList.size(); ++i) {
            listLines[i] = i + ". " + taskList.get(i - 1).showTask();
        }

        printMultiLine(listLines);
    }

    // Print number of tasks left
    public static void printNumOfTask(){
        printOneLine("Now you have " + taskList.size() + " tasks in the list");
    }


    // Print acknowledgement of task added/ deleted depending on line
    public static void printAcknowledgement(String line, int index){
        String[] acknowledgeTaskLines = {
                line,
                "  " + taskList.get(index).showTask(),

        };
        printMultiLine(acknowledgeTaskLines);
    }

    // Adding a duke.tasks.Todo to list of tasks
    public static void addTodo(String userInput) throws TodoException{

        if (userInput.equals("todo") || userInput.equals("")){
            throw new TodoException();
        }
        // Create new duke.tasks.Todo instance an add it to end taskList
        taskList.add(new Todo(userInput));

        printAcknowledgement(TASK_ADDED_LINE, taskList.size()-1);
        printNumOfTask();

        writeToFile();
    }

    // Adding a duke.tasks.Deadline to list of tasks
    public static void addDeadline(String userInput) throws DeadlineException {
        // Check if line follows the format "<description> /by <time/date>"
        if (!userInput.matches(DEADLINE_REGEX)){
            throw new DeadlineException();
        }

        // get description and by from line
        String description = userInput.replaceAll(GET_DESCRIPTION_REGEX, "");
        String by = userInput.replaceAll(GET_BY_REGEX, "");

        // Create new duke.tasks.Deadline instance an add it to end taskList
        taskList.add(new Deadline (description, by));

        printAcknowledgement(TASK_ADDED_LINE, taskList.size()-1);
        printNumOfTask();

        writeToFile();
    }

    // Adding an duke.tasks.Event to list of tasks
    public static void addEvent(String userInput) throws EventException{
        // Check if userInput follows the format "<description> /at <time/date>"
        if (!userInput.matches(EVENT_REGEX)){
            throw new EventException();
        }

        // get description and by from userInput
        String description = userInput.replaceAll(GET_DESCRIPTION_REGEX, "");
        String at = userInput.replaceAll(GET_AT_REGEX, "");


        // Create new duke.tasks.Event instance an add it to end taskList
        taskList.add(new Event(description, at));

        printAcknowledgement(TASK_ADDED_LINE, taskList.size()-1);
        printNumOfTask();

        writeToFile();
    }

    public static void addNothing() throws NothingException{
        throw new NothingException();
    }

    // Delete task
    public static void deleteTask(String userInput)
            throws DeleteFormatException, DeleteRangeException {

        if (!userInput.matches(DIGITS_REGEX)){
            throw new DeleteFormatException();
        }

        int index = Integer.parseInt(userInput) - 1;

        if (index >= taskList.size()) {
            throw new DeleteRangeException();
        }


        printAcknowledgement(TASK_DELETED_LINE, index);

        taskList.remove(index);

        printNumOfTask();
        writeToFile();
    }



    // Mark the task at the given index as done
    public static void markAsDone(String userInput)
            throws DoneFormatException, DoneAlreadyException, DoneRangeException{

        // Check if the command is done and is followed by a number
        // and if the index is within the range of number of tasks
        if (!userInput.matches(DIGITS_REGEX)){
            throw new DoneFormatException();
        }

        int index = Integer.parseInt(userInput) - 1;
        if (index >= taskList.size()){
            throw new DoneRangeException();
        }
        // Check if task is already done
        if (taskList.get(index).isDone()){
            throw new DoneAlreadyException();
        }

        // Mark the index as done
        taskList.get(index).markAsDone();

        // Acknowledge task is done
        printAcknowledgement(TASK_DONE_LINE, index);

        writeToFile();
    }

    // Reads the input of the line to determine the command and run it
    public static void readInput(String userInput){

        // Get command from the userInput
        String[] splitLine = userInput.split(" ", 2);
        String command = splitLine[0];

        // Remove command from userInput
        userInput = userInput.replaceAll(START_LINE_REGEX + command + SPACE_REGEX, "");

        // Check the command type then execute the commands
        if(userInput.equals(LIST)) {
            try{
                printList();
            } catch (EmptyListException e){
                printOneLine(EMPTY_LIST_LINE);
            }

        } else if (command.equals(TODO)){
            try {
                addTodo(userInput);
            } catch (TodoException e){
                printOneLine(TODO_LINE);
            }

        } else if (command.equals(DEADLINE)){
            try{
                addDeadline(userInput);
            } catch (DeadlineException e){
                printOneLine(DEADLINE_LINE);
            }

        } else if (command.equals(EVENT)){
            try{
                addEvent(userInput);
            } catch (EventException e){
                printOneLine(EVENT_LINE);
            }

        } else if (command.equals(DELETE) ){
            try{
                deleteTask(userInput);
            } catch (DeleteFormatException e) {
                printOneLine(DELETED_FORMAT_LINE);
            } catch (DeleteRangeException e){
                printOneLine(OUT_OF_RANGE_LINE);
            }

        } else if (command.equals(DONE) ){
            try{
                markAsDone(userInput);
            } catch (DoneFormatException e) {
                printOneLine(DONE_FORMAT_LINE);
            } catch (DoneRangeException e){
                printOneLine(OUT_OF_RANGE_LINE);
            } catch (DoneAlreadyException e){
                printOneLine(COMPLETED_LINE);
            }

        } else {
            try{
                addNothing();
            } catch (NothingException e){
                printOneLine(NOT_RECOGNISED_LINE);
            }
        }
    }

    // Continuously take in input and running the commands until input is BYE then exit
    public static void runDuke(){
        // Initialise variables to get input from user
        String userInput;
        Scanner in = new Scanner(System.in);

        // Get the first input from user
        printPrompt();
        userInput = in.nextLine();

        // While the input is not "bye", add input to array of tasks and get another input
        while (!userInput.equals(BYE)){

            readInput(userInput);

            // Get another input
            printPrompt();
            userInput = in.nextLine();
        }
    }

    public static void main(String[] args) {
        // Initialise taskList from any previous saved file
        initialiseTaskList();

        // Greets user
        printGreeting();

        // Run duke.Duke until it exits
        runDuke();

        // When the input is BYE and we exit from the loop, print exit line
        printExitLine();
    }
}
