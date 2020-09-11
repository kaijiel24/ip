public abstract class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        isDone = false;
    }

    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    // Mark a task as done based on the index
    public void markAsDone(){
        isDone = true;
    }

    // Returns String that describes task
    public abstract String showTask();

    // Returns String in the format to be saved
    public abstract String saveTask();
}