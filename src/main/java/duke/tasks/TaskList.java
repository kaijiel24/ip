package duke.tasks;

import duke.exceptions.*;

import java.util.ArrayList;

public class TaskList {// duke.tasks.Task variables
    public ArrayList<Task> tasks = new ArrayList<>();

    public final String LIST_INTRO_LINE =
            "Here are the tasks in your list:";
    public final String TASK_ADDED_LINE =
            "Got it. I've added this task:";
    public final String TASK_DELETED_LINE =
            "Noted. I've removed this task:";
    public final String TASK_DONE_LINE =
            "Nice! I've marked this task as done:";


    public final String GET_DESCRIPTION_REGEX = "/.+";
    public final String GET_AT_REGEX = ".+/at ";
    public final String GET_BY_REGEX = ".+/by ";
    public final String DIGITS_REGEX = "\\d+";
    public final String DEADLINE_REGEX = ".+/by.+";
    public final String EVENT_REGEX = ".+/at.+";

    public TaskList() {
    }

    // Print acknowledgement of task added/ deleted depending on line
    public String getAcknowledgement(String line, int index){
        return line + "\n  " + tasks.get(index).showTask();
    }
    // Print number of tasks left
    public String getNumOfTask() {
        return "Now you have " + tasks.size() + " tasks in the list";
    }

    // Print list of task
    public String getList() throws EmptyListException {
        if (tasks.size() == 0) {
            throw new EmptyListException();
        }
        String fullList = LIST_INTRO_LINE;

        // Add each item to listLines
        for (int i = 1; i <= tasks.size(); ++i) {
            fullList = fullList + "\n" +  i + ". " + tasks.get(i - 1).showTask();
        }

        return fullList;
    }

    // Adding a duke.tasks.Todo to list of tasks
    public String addTodo(String userInput) throws TodoException {

        if (userInput.equals("todo") || userInput.equals("")){
            throw new TodoException();
        }
        // Create new duke.tasks.Todo instance an add it to end taskList
        tasks.add(new Todo(userInput));

        return getAcknowledgement(TASK_ADDED_LINE, tasks.size()-1)
            + "\n" + getNumOfTask();
    }

    // Adding a duke.tasks.Deadline to list of tasks
    public String addDeadline(String userInput) throws DeadlineException {
        // Check if line follows the format "<description> /by <time/date>"
        if (!userInput.matches(DEADLINE_REGEX)){
            throw new DeadlineException();
        }

        // get description and by from line
        String description = userInput.replaceAll(GET_DESCRIPTION_REGEX, "");
        String by = userInput.replaceAll(GET_BY_REGEX, "");

        // Create new duke.tasks.Deadline instance an add it to end taskList
        tasks.add(new Deadline(description, by));

        return getAcknowledgement(TASK_ADDED_LINE, tasks.size()-1)
                + "\n" + getNumOfTask();
    }

    // Adding an duke.tasks.Event to list of tasks
    public String addEvent(String userInput) throws EventException {
        // Check if userInput follows the format "<description> /at <time/date>"
        if (!userInput.matches(EVENT_REGEX)){
            throw new EventException();
        }

        // get description and by from userInput
        String description = userInput.replaceAll(GET_DESCRIPTION_REGEX, "");
        String at = userInput.replaceAll(GET_AT_REGEX, "");


        // Create new duke.tasks.Event instance an add it to end taskList
        tasks.add(new Event(description, at));

        return getAcknowledgement(TASK_ADDED_LINE, tasks.size()-1)
                + "\n" + getNumOfTask();
    }

    public void doNothing () throws NothingException {
        throw new NothingException();
    }

    // Delete task
    public String deleteTask(String userInput)
            throws DeleteFormatException, DeleteRangeException {

        if (!userInput.matches(DIGITS_REGEX)){
            throw new DeleteFormatException();
        }

        int index = Integer.parseInt(userInput) - 1;

        if (index >= tasks.size()) {
            throw new DeleteRangeException();
        }


        String acknowledgement = getAcknowledgement(TASK_DELETED_LINE, index);

        tasks.remove(index);

        return acknowledgement + "\n" + getNumOfTask();
    }



    // Mark the task at the given index as done
    public String markAsDone(String userInput)
            throws DoneFormatException, DoneAlreadyException, DoneRangeException{

        // Check if the command is done and is followed by a number
        // and if the index is within the range of number of tasks
        if (!userInput.matches(DIGITS_REGEX)){
            throw new DoneFormatException();
        }

        int index = Integer.parseInt(userInput) - 1;
        if (index >= tasks.size()){
            throw new DoneRangeException();
        }
        // Check if task is already done
        if (tasks.get(index).isDone()){
            throw new DoneAlreadyException();
        }

        // Mark the index as done
        tasks.get(index).markAsDone();

        // Acknowledge task is done
        return getAcknowledgement(TASK_DONE_LINE, index);

    }

    public void clearList(){
        tasks.clear();
    }
}