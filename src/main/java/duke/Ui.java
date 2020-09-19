package duke;

public class Ui {

    // Lines to be printed
    public final String GREETING_LINES =
            "Hello! I'm duke.Duke" + "\n"
            + "What can I do for you?";
    public final String BYE_LINE =
            "Bye. Hope to see you again soon!";


    // Prints a horizontal dash line
    public void printDashLine(){
        System.out.println("--------------------------------"
                + "---------------------------------------");
    }

    public void printOneLine(String line){
        if (line.isEmpty()){
            return;
        }
        printDashLine();
        System.out.println(line);
        printDashLine();
    }

    public void printPrompt(){
        System.out.print("  > ");
    }

    // Prints the greet statement
    public void printGreeting(){
        printOneLine(GREETING_LINES);
    }

    // Prints the exit statement
    public void printExitLine() {
        printOneLine(BYE_LINE);
    }

    public void printMessage(String message) {
        printOneLine(message);
    }
}
