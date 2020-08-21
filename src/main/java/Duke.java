import java.util.Scanner;

public class Duke {

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
    public static void printExitLine(){
        printDashLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDashLine();
    }

    // Print list of tasks
    public static void printList(String[] tasks, int taskCount){
        printDashLine();
        for (int i = 0; i <  taskCount; ++i){
            System.out.println(i+1 + ". " + tasks[i]);
        }
        printDashLine();
    }

    public static void main(String[] args) {
        // Greets user
        printGreeting();

        // Initialise variables to get input from user
        String line;
        Scanner in = new Scanner(System.in);

        // Create array to hold the list of tasks
        // taskCount keeps track of number of tasks
        String[] tasks = new String[100];
        int taskCount = 0;


        // Get the first input from user
        printPrompt();
        line = in.nextLine();

        // While the input is not "bye", add input to array of tasks and get another input
        while (!line.equals("bye")){

            // If input is "list", print the list of tasks
            if(line.equals("list")){
                printList(tasks, taskCount);
            } else {
                // Else, add line to array of tasks and increase the task count
                tasks[taskCount] = line;
                taskCount = taskCount + 1;

                // Acknowledge input
                printDashLine();
                System.out.println("added: " + line);
                printDashLine();
            }

            // Get another input
            printPrompt();
            line = in.nextLine();
        }

        // When the input is bye and we exit from the loop, print exit line
        printExitLine();
    }
}
