public class Todo extends Task {
    public Todo (String description){
        super(description);
    }

    // Prints task
    public void printTask(){
        System.out.print("[T][");

        if (isDone) {
            System.out.print("\u2713");
        } else {
            System.out.print("\u2718");
        }

        System.out.println("] " + description);
    }
}
