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
    public final String FIND_INTRO_LINE =
            "Here are the tasks in your list that contains '";

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
    public String addTodo(String arguments) throws TodoException {

        if (arguments.equals("todo") || arguments.equals("")){
            throw new TodoException();
        }
        // Create new duke.tasks.Todo instance an add it to end taskList
        tasks.add(new Todo(arguments));

        return getAcknowledgement(TASK_ADDED_LINE, tasks.size()-1)
            + "\n" + getNumOfTask();
    }

    // Adding a duke.tasks.Deadline to list of tasks
    public String addDeadline(String arguments) throws DeadlineException {
        // Check if line follows the format "<description> /by <time/date>"
        if (!arguments.matches(DEADLINE_REGEX)){
            throw new DeadlineException();
        }

        // get description and by from line
        String description = arguments.replaceAll(GET_DESCRIPTION_REGEX, "");
        String by = arguments.replaceAll(GET_BY_REGEX, "");

        // Create new duke.tasks.Deadline instance an add it to end taskList
        tasks.add(new Deadline(description, by));

        return getAcknowledgement(TASK_ADDED_LINE, tasks.size()-1)
                + "\n" + getNumOfTask();
    }

    // Adding an duke.tasks.Event to list of tasks
    public String addEvent(String arguments) throws EventException {
        // Check if arguments follows the format "<description> /at <time/date>"
        if (!arguments.matches(EVENT_REGEX)){
            throw new EventException();
        }

        // get description and by from arguments
        String description = arguments.replaceAll(GET_DESCRIPTION_REGEX, "");
        String at = arguments.replaceAll(GET_AT_REGEX, "");


        // Create new duke.tasks.Event instance an add it to end taskList
        tasks.add(new Event(description, at));

        return getAcknowledgement(TASK_ADDED_LINE, tasks.size()-1)
                + "\n" + getNumOfTask();
    }

    public void doNothing () throws NothingException {
        throw new NothingException();
    }

    // Delete task
    public String deleteTask(String arguments)
            throws DeleteFormatException, DeleteRangeException {

        if (!arguments.matches(DIGITS_REGEX)){
            throw new DeleteFormatException();
        }

        int index = Integer.parseInt(arguments) - 1;

        if (index >= tasks.size()) {
            throw new DeleteRangeException();
        }


        String acknowledgement = getAcknowledgement(TASK_DELETED_LINE, index);

        tasks.remove(index);

        return acknowledgement + "\n" + getNumOfTask();
    }



    // Mark the task at the given index as done
    public String markAsDone(String arguments)
            throws DoneFormatException, DoneAlreadyException, DoneRangeException{

        // Check if the command is done and is followed by a number
        // and if the index is within the range of number of tasks
        if (!arguments.matches(DIGITS_REGEX)){
            throw new DoneFormatException();
        }

        int index = Integer.parseInt(arguments) - 1;
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

    // Print list of task
    public String findTask(String arguments) throws NotFoundException {
        String foundList = FIND_INTRO_LINE + arguments + "'";

        int numFound = 0;

        // Add each item to foundList
        for (int i = 1; i <= tasks.size(); ++i) {
            if (tasks.get(i - 1).getDescription().toLowerCase().contains(arguments.toLowerCase())) {
                numFound += 1;
                foundList = foundList + "\n" + i + ". " + tasks.get(i - 1).showTask();
            }
        }

        if (numFound == 0){
            throw new NotFoundException();
        }
        return foundList;
    }

    public void clearList(){
        tasks.clear();
    }
}