package duke.tasks;

import duke.exceptions.DeadlineException;
import duke.exceptions.DeleteFormatException;
import duke.exceptions.DeleteRangeException;
import duke.exceptions.DoneAlreadyException;
import duke.exceptions.DoneFormatException;
import duke.exceptions.DoneRangeException;
import duke.exceptions.EmptyListException;
import duke.exceptions.EventException;
import duke.exceptions.NotFoundException;
import duke.exceptions.NothingException;
import duke.exceptions.TodoException;

import java.util.ArrayList;

/**
 * Represents a list of Task
 * The ArrayList of Task <code>tasks</code> represents the list of all the tasks available
 */
public class TaskList {
    public ArrayList<Task> tasks = new ArrayList<>();

    private final String LIST_INTRO_LINE =
            "Here are the tasks in your list:";
    private final String TASK_ADDED_LINE =
            "Got it. I've added this task:";
    private final String TASK_DELETED_LINE =
            "Got it. I've removed this task:";
    private final String TASK_DONE_LINE =
            "Nice! I've marked this task as done:";
    public final String FIND_INTRO_LINE =
            "Here are the tasks in your list that contains '";

    private final String GET_DESCRIPTION_REGEX = "/.+";
    private final String GET_AT_REGEX = ".+/at ";
    private final String GET_BY_REGEX = ".+/by ";
    private final String DIGITS_REGEX = "\\d+";
    private final String DEADLINE_REGEX = ".+/by.+";
    private final String EVENT_REGEX = ".+/at.+";

    /** Constructor */
    public TaskList() {
    }

    /**
     * Gets acknowledgement of task added/ deleted depending on line
     *
     * @param line Message that a task has been successfully added/ deleted
     * @param index Index of the task that was added/ deleted
     * @return a String representing an acknowledgement message
     */
    public String getAcknowledgement(String line, int index){
        return line + "\n  " + tasks.get(index).showTask();
    }

    /**
     * Gets the current number of tasks in a list
     *
     * @return a String representing a message about then current number of tasks in the list
     */
    public String getNumOfTask() {
        return "Now you have " + tasks.size() + " tasks in the list";
    }


    /**
     * Gets the list of tasks
     *
     * @return a String representing a message about the list of tasks
     * @throws EmptyListException If list.size() == 0
     */
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

    /**
     * Adds a Todo task to the list
     *
     * @param arguments Arguments of the user's input
     * @return a String representing the full message to return to user in the case of a successful addition
     * @throws TodoException when the task description is empty
     */
    public String addTodo(String arguments) throws TodoException {

        if (arguments.equals("")){
            throw new TodoException();
        }
        tasks.add(new Todo(arguments));

        return getAcknowledgement(TASK_ADDED_LINE, tasks.size()-1)
            + "\n" + getNumOfTask();
    }

    /**
     * Adds a Deadline task to the list
     *
     * @param arguments Arguments of the user's input
     * @return a String representing the full message to return to user in the case of a successful addition
     * @throws DeadlineException when the arguments do not follow the format of
     *      description /by time/date
     */
    public String addDeadline(String arguments) throws DeadlineException {
        if (!arguments.matches(DEADLINE_REGEX)){
            throw new DeadlineException();
        }

        String description = arguments.replaceAll(GET_DESCRIPTION_REGEX, "");
        String by = arguments.replaceAll(GET_BY_REGEX, "");

        tasks.add(new Deadline(description, by));

        return getAcknowledgement(TASK_ADDED_LINE, tasks.size()-1)
                + "\n" + getNumOfTask();
    }

    /**
     * Adds a Event task to the list
     *
     * @param arguments Arguments of the user's input
     * @return a String representing the full message to return to user in the case of a successful addition
     * @throws EventException when the arguments do not follow the format of
     *      description /at time/date
     */
    public String addEvent(String arguments) throws EventException {
        if (!arguments.matches(EVENT_REGEX)){
            throw new EventException();
        }

        String description = arguments.replaceAll(GET_DESCRIPTION_REGEX, "");
        String at = arguments.replaceAll(GET_AT_REGEX, "");


        tasks.add(new Event(description, at));

        return getAcknowledgement(TASK_ADDED_LINE, tasks.size()-1)
                + "\n" + getNumOfTask();
    }

    /**
     * Does nothing other than throw exception. Used in cases where command not recognised
     *
     * @throws NothingException when command not recognised
     */
    public void doNothing () throws NothingException {
        throw new NothingException();
    }

    /**
     * Deletes a task from the list
     *
     * @param arguments Arguments of the user's input
     * @return a String representing the full message to return to user in the case of a successful deletion
     * @throws DeleteFormatException when the arguments do not follow the format of a single integer
     * @throws DeleteRangeException when the integer from the argument is not within the range of the
     *      number of tasks in the list
     */
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

    /**
     * Marks a task in the list as done
     *
     * @param arguments Arguments of the user's input
     * @return a String representing the full message to return to user in the case where the task is
     *      successfully marked as done
     * @throws DoneFormatException when the arguments do not follow the format of a single integer
     * @throws DoneRangeException when the integer from the argument is not within the range of the
     * @throws DoneAlreadyException when the task specified has already been previously marked as done
     */
    public String markAsDone(String arguments)
            throws DoneFormatException, DoneAlreadyException, DoneRangeException{

        if (!arguments.matches(DIGITS_REGEX)){
            throw new DoneFormatException();
        }

        int index = Integer.parseInt(arguments) - 1;
        if (index >= tasks.size()){
            throw new DoneRangeException();
        }
        if (tasks.get(index).isDone()){
            throw new DoneAlreadyException();
        }

        tasks.get(index).markAsDone();

        return getAcknowledgement(TASK_DONE_LINE, index);
    }

    /**
     * Find tasks which have description containing search term
     *
     * @param arguments Search term to find within tasks' description
     * @return a String representing the list of items that contain the search term
     * @throws NotFoundException when task containg search term is not found
     */
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

    /** Clears the list of task */
    public void clearList(){
        tasks.clear();
    }
}