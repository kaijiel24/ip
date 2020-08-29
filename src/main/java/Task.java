public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    // Mark a task as done based on the index
    public void markAsDone(){
        isDone = true;
    }
}