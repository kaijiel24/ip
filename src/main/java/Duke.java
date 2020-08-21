public class Duke {

    // Prints a horizontal dash line
    public static void printDashLine(){
        System.out.println("-----------------------------------------------");
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
        System.out.println("Bye. Hope to see you again soon!");
        printDashLine();
    }

    public static void main(String[] args) {
        // Greets user
        printGreeting();

        // When the input is bye and we exit from the loop, print exit line
        printExitLine();
    }
}
