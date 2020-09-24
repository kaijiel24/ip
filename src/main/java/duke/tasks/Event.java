package duke.tasks;

/**
 * Represents an Event Task
 * TaskType <code>taskType</code> represents the type of task this class is
 * String <code>by</code> represents the date or time task should be happening at
 */
public class Event extends Task{
    private TaskType type = TaskType.EVENT;
    private String at;

    /** Constructor without isDone */
    public Event (String description, String at){
        super(description);
        this.at = at;
    }

    /** Constructor with isDone */
    public Event (String description, boolean isDone, String at){
        super(description, isDone);
        this.at = at;
    }

    /** Gets at */
    public String getAt(){
        return at;
    }

    @Override
    public String showTask() {
        return "[E][" + (isDone ? "\u2713" : "\u2718") + "] " + description + " (at: " + at + ")";
    }

    @Override
    public String saveTask(){
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + at;
    }
}
