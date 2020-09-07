public class Todo extends Task {
    public Todo (String description){
        super(description);
    }

    @Override
    public String showTask(){
       return "[T][" + (isDone ? "\u2713" : "\u2718") + "] " + description;
    }
}
