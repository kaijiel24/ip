package duke.tasks;

/**
 * Represents a Task in the TaskList.
 * String <code>description</code> represents the description of the task; and
 * boolean <code>isDone</code> represents whether the task has been completed.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /** Constructor without isDone */
    public Task(String description){
        this.description = description;
        isDone = false;
    }

    /** Constructor with isDone */
    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    /** Gets description of task */
    public String getDescription() {
        return description;
    }

    /** Checks whether task is complete */
    public boolean isDone() {
        return isDone;
    }

    /** Marks task as completed */
    // Mark a task as done based on the index
    public void markAsDone(){
        isDone = true;
    }

    /**
     * Get a message about the details of the task
     * @return a String representing the details of the task in the format to be shown to user
     */
    // Returns String that describes task
    public abstract String showTask();

    /**
     * Get a formatted message to save to file
     * @return a String representing the details of the task in the format to be saved
     */
    public abstract String saveTask();
}