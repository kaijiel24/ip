public abstract class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        isDone = false;
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
}