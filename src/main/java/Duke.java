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

    public static void main(String[] args) {
        // Greets user
        printGreeting();

        // Initialise variables to get input from user
        String line;
        Scanner in = new Scanner(System.in);

        // Get the first input from user
        printPrompt();
        line = in.nextLine();

        // While the input is not "bye", print out the input and get another input
        while (!line.equals("bye")){
            printDashLine();
            System.out.println(line);
            printDashLine();
            printPrompt();
            line = in.nextLine();
        }

        // When the input is bye and we exit from the loop, print exit line
        printExitLine();
    }
}
