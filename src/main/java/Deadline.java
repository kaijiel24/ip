public class Deadline extends Task {
    private String by;

    public Deadline (String description, String by){
        super(description);
        this.by = by;
    }

    public String getBy(){
        return by;
    }

    // Prints task
    public void printTask(){
        System.out.print("[D][");

        if (isDone) {
            System.out.print("\u2713");
        } else {
            System.out.print("\u2718");
        }

        System.out.println("] " + description + "(by: " + by + ")");
    }
}
