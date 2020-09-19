package duke.command;

import duke.tasks.TaskList;

public class CommandResult {
    private String feedbackMessage;
    private TaskList taskList;
    private boolean updated;

    public CommandResult(String feedbackMessage, boolean updated){
        this.updated = updated;
        this.feedbackMessage = feedbackMessage;
        taskList = null;
    }

    public CommandResult(String feedbackMessage, boolean updated, TaskList taskList){
        this.updated = updated;
        this.feedbackMessage = feedbackMessage;
        this.taskList = taskList;
    }

    public String getFeedbackMessage(){
        return feedbackMessage;
    }

    public boolean isUpdated(){
        return updated;
    }

    public TaskList getTaskList(){
        return taskList;
    }
}
