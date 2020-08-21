public class Task {
    private String[] tasks;
    private boolean[] tasksCompleted;
    private int taskCount;

    public Task(){
        this.tasks = new String[100];
        this.tasksCompleted = new boolean[100];
        taskCount = 0;
    }

    // Get the number of tasks
    public int getTaskCount(){
        return taskCount;
    }

    // Adding a task to list of tasks
    public void addTask(String line){
        // Add line to array of tasks, set newly added index in tasksCompleted as false
        // and increase the task count
        tasks[taskCount] = line;
        tasksCompleted[taskCount] = false;
        taskCount = taskCount + 1;
    }

    // Get the task based on the index
    public String getTask(int index){
        return tasks[index-1];
    }

    // Mark a task as done based on the index
    public void markAsDone(int index){
        // Mark the index as tasksCompleted
        tasksCompleted[index-1] = true;
    }

    // Print the list of tasks
    public void printList(){

        // Print each item
        for (int i = 0; i < taskCount; ++i){
            System.out.print(i+1 + ".[");

            // If the the index in tasksCompleted is true, then print a tick
            // else print a cross
            if (tasksCompleted[i]){
                System.out.print("✓");
            } else {
                System.out.print("✗");
            }

            System.out.println("] " + tasks[i]);
        }
    }
}