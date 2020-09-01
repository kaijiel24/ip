public class Event extends Task{
    private String at;

    public Event (String description, String at){
        super(description);
        this.at = at;
    }

    public String getAt(){
        return at;
    }

    // Prints task
    public void printTask() {
        System.out.print("[E][");

        if (isDone) {
            System.out.print("\u2713");
        } else {
            System.out.print("\u2718");
        }

        System.out.println("] " + description + "(at: " + at + ")");
    }
}
