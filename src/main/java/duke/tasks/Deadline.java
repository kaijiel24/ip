package duke.tasks;

/**
 * Represents a Deadline Task
 * TaskType <code>taskType</code> represents the type of task this class is
 * String <code>by</code> represents the date or time task should be completed by
 */
public class Deadline extends Task {
    private TaskType type = TaskType.DEADLINE;
    private String by;

    /** Constructor without isDone */
    public Deadline (String description, String by){
        super(description);
        this.by = by;
    }

    /** Constructor with isDone */
    public Deadline (String description, boolean isDone, String by){
        super(description, isDone);
        this.by = by;
    }

    /** Gets by*/
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
