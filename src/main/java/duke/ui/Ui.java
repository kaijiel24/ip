package duke.ui;

import java.util.Scanner;

/** Displays messages to user */
public class Ui {

    private final String GREETING_LINES =
            "Hello! I'm Duke" + "\n"
            + "What can I do for you?";
    private final String BYE_LINE =
            "Bye. Hope to see you again soon!";

    private final Scanner in;

    /** Constructor */
    public Ui(){
        in = new Scanner(System.in);
    }

    /**
     * Reads user input from command line
     * @return String containing userInput
     */
    public String readUserInput(){
        return in.nextLine();
    }

    private void printDashLine(){
        System.out.println("------------------------------------------------------------");
    }

    private void printOneLine(String line){
        if (line.isEmpty()){
            return;
        }

        printDashLine();
        System.out.println(line);
        printDashLine();
    }

    /** Displays the prompt for user input */
    public void printPrompt(){
        System.out.print("  > ");
    }

    /** Displays the greeting message */
    public void printGreeting(){
        printOneLine(GREETING_LINES);
    }

    /** Displays the exit message */
    public void printExitLine() {
        printOneLine(BYE_LINE);
    }

    /**
     * Displays the message
     *
     * @param message message to be displayed to suer
     */
    public void printMessage(String message) {
        printOneLine(message);
    }
}
