import java.util.Scanner;

public class Duke {

    public static final String LIST = "list";
    public static final String BYE = "bye";


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

    // Prints the exit statement
    public static void printExitLine() {
        printDashLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDashLine();
    }

    // Adding a task to list of tasks
    public static void addTask(String line) {
        taskList[taskCount] = new Task (line);
        taskCount = taskCount + 1;

        // Acknowledge input
        printDashLine();
        System.out.println("added: " + line);
        printDashLine();
    }

    // Print list of task
    public static void printList() {
        printDashLine();

        // Print each item
        for (int i = 0; i < taskCount; ++i) {
            System.out.print(i + 1 + ".[");

            // If the the index in tasksCompleted is true, then print a tick
            // else print a cross
            if (taskList[i].isDone()) {
                System.out.print("✓");
            } else {
                System.out.print("✗");
            }

            System.out.println("] " + taskList[i].getDescription());
        }

        printDashLine();
    }

    public static void markAsDone(int index){
        // Mark the index as done
        taskList[index].markAsDone();

        // Acknowledge task is done
        printDashLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [✓] " + taskList[index].getDescription());
        printDashLine();
    }

    public static void readInput(String line){

        // If input is LIST, print the list of tasks
        if(line.equals(LIST)) {
            printList();

        } else if (line.matches("^done \\d+")){
            // Else if the line is done followed by number
            // separate the done from the number
            String[] lineSplit = line.split(" ");
            int index = Integer.parseInt(lineSplit[1]) - 1;

            markAsDone(index);

        } else {
            // Else add the input to new list
            addTask(line);
        }
    }

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

        // When the input is bye and we exit from the loop, print exit line
        printExitLine();
    }
}
