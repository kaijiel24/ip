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
    public static void printExitLine() {
        printDashLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDashLine();
    }

    public static void main(String[] args) {
        // Greets user
        printGreeting();

        // Initialise variables to get input from user
        String line;
        Scanner in = new Scanner(System.in);

        // Initialize an instance of class Task to keep track of tasks
        Task taskList = new Task();

        // Get the first input from user
        printPrompt();
        line = in.nextLine();

        // While the input is not "bye", add input to array of tasks and get another input
        while (!line.equals("bye")){

            // If input is "list", print the list of tasks
            if(line.equals("list")) {
                printDashLine();
                taskList.printList();
                printDashLine();
            } else if (line.matches("^done \\d+")){
                // Else if the line is done followed by number
                // separate the done from the number
                String[] lineSplit = line.split(" ");
                int index = Integer.parseInt(lineSplit[1]);

                // Mark the index as done
                taskList.markAsDone(index);

                // Acknowledge task is done
                printDashLine();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [✓] " + taskList.getTask(index));
                printDashLine();
            } else {
                // Else add the input to new list
                taskList.addTask(line);

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
