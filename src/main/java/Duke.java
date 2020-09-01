import java.util.Scanner;

public class Duke {

    // Commands Constant
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DONE = "done";

    // Regex Constants
    public static final String GET_DESCRIPTION_REGEX = "/.+";
    public static final String GET_AT_REGEX = ".+/at ";
    public static final String GET_BY_REGEX = ".+/by ";
    public static final String SPACE_REGEX = "\\s";
    public static final String START_LINE_REGEX = "^";
    public static final String DIGITS_REGEX = "\\d+";
    public static final String DEADLINE_REGEX = ".+/by.+";
    public static final String EVENT_REGEX = ".+/at.+";

    // Task variables
    public static Task[] taskList= new Task[100];
    public static int taskCount = 0;

    // Prints a horizontal dash line
    public static void printDashLine(){
        System.out.println("-----------------------------------------------");
    }

    // Prints the prompt before every user input
    public static void printPrompt(){
        System.out.print("  > ");
    }

    // Prints the greet statement
    public static void printGreeting(){
        printDashLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printDashLine();
    }

    // Prints acknowledgement after adding task
    public static void printAddTask(){
        printDashLine();
        System.out.print("Got it. I've added this task:\n  ");
    }

    // Print number of tasks
    public static void printNumOfTask(){
        System.out.println("Now you have " + taskCount + " tasks in the list");
        printDashLine();
    }

    // Prints error statement
    public static void printError() {
        printDashLine();
        System.out.println("Command not recognised");
        printDashLine();
    }

    // Prints the exit statement
    public static void printExitLine() {
        printDashLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDashLine();
    }

    // Acknowledge input
    public static void printAcknowledgement(){
        printAddTask();
        taskList[taskCount-1].printTask();
        printNumOfTask();
    }

    // Adding a Todo to list of tasks
    public static void addTodo(String line) {
        // Create new Todo instance an add it to end taskList
        taskList[taskCount] = new Todo (line);
        taskCount = taskCount + 1;

        printAcknowledgement();
    }

    // Adding a Deadline to list of tasks
    public static void addDeadline(String line) {
        // get description and by from line
        String description = line.replaceAll(GET_DESCRIPTION_REGEX, "");
        String by = line.replaceAll(GET_BY_REGEX, "");

        // Create new Deadline instance an add it to end taskList
        taskList[taskCount] = new Deadline (description, by);
        taskCount = taskCount + 1;

        printAcknowledgement();
    }

    // Adding an Event to list of tasks
    public static void addEvent(String line) {
        // get description and by from line
        String description = line.replaceAll(GET_DESCRIPTION_REGEX, "");
        String at = line.replaceAll(GET_AT_REGEX, "");

        // Create new Event instance an add it to end taskList
        taskList[taskCount] = new Event (description, at);
        taskCount = taskCount + 1;

        printAcknowledgement();
    }

    // Print list of task
    public static void printList() {
        printDashLine();

        System.out.println("Here are the tasks in your list");

        // Print each item
        for (int i = 0; i < taskCount; ++i) {
            System.out.print(i + 1 + ".");
            taskList[i].printTask();
        }

        printDashLine();
    }

    // Mark the task at the given index as done
    public static void markAsDone(int index){
        // Check if task is already done
        if (taskList[index].isDone()){
            printDashLine();
            System.out.println("The task has already been completed.");
            printDashLine();
            return;
        }

        // Mark the index as done
        taskList[index].markAsDone();

        // Acknowledge task is done
        printDashLine();
        System.out.print("Nice! I've marked this task as done:\n  ");
        taskList[index].printTask();
        printDashLine();
    }

    // Reads the input of the line to determine the command and run it
    public static void readInput(String line){

        // Get command from the line
        String[] splitLine = line.split(" ", 2);
        String command = splitLine[0];


        // Remove command from line
        line = line.replaceAll(START_LINE_REGEX + command + SPACE_REGEX, "");

        // Check the command type then execute the commands
        if(line.equals(LIST)) {
            printList();
        } else if (command.equals(DONE) && line.matches(DIGITS_REGEX)){
            // Check if the command is done and is followed by a number
            int index = Integer.parseInt(line) - 1;

            // Only do markAsDone if the index is within the range of number of tasks
            if (index < taskCount){
                markAsDone(index);
            } else {
                printError();
            }
        } else if (command.equals(TODO)){
            addTodo(line);
        } else if (command.equals(DEADLINE) && line.matches(DEADLINE_REGEX)){
            // Check if line contains "/by"
            addDeadline(line);
        } else if (command.equals(EVENT)&& line.matches(EVENT_REGEX)){
            // Check if line contains "/at"
            addEvent(line);
        } else {
            //  Print Error if the input does not fulfil any of the functions
            printError();
        }
    }

    // Continuously take in input and running the commands until input is BYE then exit
    public static void runDuke(){
        // Initialise variables to get input from user
        String line;
        Scanner in = new Scanner(System.in);

        // Get the first input from user
        printPrompt();
        line = in.nextLine();

        // While the input is not "bye", add input to array of tasks and get another input
        while (!line.equals(BYE)){

            readInput(line);

            // Get another input
            printPrompt();
            line = in.nextLine();
        }
    }

    public static void main(String[] args) {
        // Greets user
        printGreeting();

        // Run Duke until it exits
        runDuke();

        // When the input is BYE and we exit from the loop, print exit line
        printExitLine();
    }
}
