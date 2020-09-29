package duke.storage;

import duke.exceptions.EntryFormatError;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Reads and writes list of task to file
 */
public class Storage {

    private final String FILE_NOT_FOUND_LINE = "File not found";
    private final String NEW_FILE_CREATED_LINE = "New file data/duke.txt created";
    private final String CREATE_FILE_ERROR_LINE = "Error creating new file";
    private final String FILE_FORMAT_ERROR_LINE = "File formatting error";
    private final String SPLIT_REGEX = "\\s\\|\\s";
    private final String TODO_REGEX = "T\\s\\|\\s[01]\\s\\|.+";
    private final String EVENT_DEADLINE_REGEX = "[ED]\\s\\|\\s[01]\\s\\|.+\\|.+";

    private final String WRITE_ERROR_LINE = "Error writing to file";

    private final String filePath;
    private final File f;

    /** Constructor */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.f = new File(filePath);
    }

    /**
     * Loads user data from file into <code>taskList</code>
     *
     * @param taskList List of task to be loaded with data from file
     * @throws FileNotFoundException When file does not exist
     * @throws EntryFormatError When the entry format is wrong
     */
    public void loadData(TaskList taskList)
            throws FileNotFoundException, EntryFormatError {

        Scanner s = new Scanner(f);
        while (s.hasNext()){
            String entry = s.nextLine();

            if(!entry.matches(TODO_REGEX)
                    && !entry.matches(EVENT_DEADLINE_REGEX)) {
                throw new EntryFormatError();
            }

            String[] entrySplit= entry.split(SPLIT_REGEX, 4);
            String taskType = entrySplit[0];
            boolean isDone = entrySplit[1].equals("1");
            String description = entrySplit[2];
            String atBy = "";

            if (entrySplit.length == 4) {
                atBy = entrySplit[3];
            }

            if (taskType.equals("T")){
                taskList.tasks.add(new Todo(description, isDone));

            } else if (taskType.equals("D")){
                taskList.tasks.add(new Deadline(description, isDone, atBy));

            } else if (taskType.equals("E")){
                taskList.tasks.add(new Event(description, isDone, atBy));

            }
        }
    }

    /**
     * Creates a data file and its parent directory if it does not already exists
     *
     * @throws IOException When the file path is not available
     */
    public void createFile() throws IOException {
        Path pathToFile = Paths.get(filePath);
        Files.createDirectories(pathToFile.getParent());
        f.createNewFile();
    }

    /**
     * Initialises a new list of task and load data into file if file exists,
     *      else create new file to write data into later on
     *
     * @return StorageResult containing <code>taskList</code> created
     *      and String representing message to be sent back to user
     */
    public StorageResult initialiseTaskList(){
        TaskList taskList = new TaskList();
        String message = "";

        try {
            loadData(taskList);
        } catch (FileNotFoundException e){
            message = message + FILE_NOT_FOUND_LINE;
            try {
                createFile();
                message = message + "\n" + NEW_FILE_CREATED_LINE;

            } catch (IOException e1){
                message = message + "\n" + CREATE_FILE_ERROR_LINE;
            }

        } catch (EntryFormatError e){
            message = message + "\n" + FILE_FORMAT_ERROR_LINE;
            taskList.clearList();
        }

        return new StorageResult(taskList, message);
    }

    private void _writeToFile(TaskList taskList) throws IOException{
        FileWriter fw = new FileWriter(f);
        for (Task task : taskList.tasks){
            fw.write(task.saveTask() + "\n");
        }
        fw.close();
    }

    /**
     * Writes list of task to file
     *
     * @param taskList list of task to write to data
     * @return String representing message to be shown to user
     */
    public String writeToFile(TaskList taskList){
        try {
            _writeToFile(taskList);
        } catch (IOException e) {
            return WRITE_ERROR_LINE;
        }

        return "";
    }
}
