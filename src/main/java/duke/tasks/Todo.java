package duke.tasks;

/**
 * Represents a Todo Task
 * TaskType <code>taskType</code> represents the type of task this class is
 */
public class Todo extends Task {

    /** Constructor without isDone */
    public Todo (String description){
        super(description);
    }

    /** Constructor with isDone */
    public Todo (String description, boolean isDone){
        super(description, isDone);
    }

    @Override
    public String showTask(){
       return "[T][" + (isDone ? "\u2713" : "\u2718") + "] " + description;
    }

    @Override
    public String saveTask(){
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}
