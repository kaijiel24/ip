package duke.tasks;

public class Todo extends Task {

    private TaskType type = TaskType.TODO;
    public Todo (String description){
        super(description);
    }

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