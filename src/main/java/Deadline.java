public class Deadline extends Task {
    private String by;

    public Deadline (String description, String by){
        super(description);
        this.by = by;
    }

    public Deadline (String description, boolean isDone, String by){
        super(description, isDone);
        this.by = by;
    }

    public String getBy(){
        return by;
    }

    @Override
    public String showTask(){
        return "[D][" + (isDone ? "\u2713" : "\u2718") + "] " + description + " (by: " + by + ")";
    }

    @Override
    public String saveTask(){
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + by;
    }
}
