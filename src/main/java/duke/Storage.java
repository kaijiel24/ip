package duke;

import duke.exceptions.FileFormatException;
import duke.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {

    public static final String SPLIT_REGEX = "\\s\\|\\s";
    private final String TODO_REGEX = "T\\s\\|\\s[01]\\s\\|.+";
    private final String EVENT_DEADLINE_REGEX = "[ED]\\s\\|\\s[01]\\s\\|.+\\|.+";

    private final String filePath;
    private final File f;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.f = new File(filePath);
    }


    public void loadData(TaskList taskList)
            throws FileNotFoundException, FileFormatException {

        Scanner s = new Scanner(f);
        while (s.hasNext()){
            String entry = s.nextLine();

            // Check if entry is in the correct format
            if(!entry.matches(TODO_REGEX)
                    && !entry.matches(EVENT_DEADLINE_REGEX)) {
                throw new FileFormatException();
            }

            String[] entrySplit= entry.split(SPLIT_REGEX);
            String taskType = entrySplit[0];
            boolean isDone = entrySplit[1].equals("1");
            String description = entrySplit[2];
            String atBy = entrySplit.length >= 4 ? entrySplit[3] : "";


            if (taskType.equals("T")){
                taskList.tasks.add(new Todo(description, isDone));

            } else if (taskType.equals("D")){
                taskList.tasks.add(new Deadline(description, isDone, atBy));

            } else if (taskType.equals("E")){
                taskList.tasks.add(new Event(description, isDone, atBy));

            }
        }
    }

    public void createFile() throws IOException {
        Path pathToFile = Paths.get(filePath);
        Files.createDirectories(pathToFile.getParent());
        f.createNewFile();
    }

    public TaskListResult initialiseTaskList(){
        TaskList taskList = new TaskList();
        String message = "";

        try {
            loadData(taskList);
        } catch (FileNotFoundException e){
            message = message + "File not found";
            try {
                createFile();
                message = message + "\n" + "New file data/duke.txt created";

            } catch (IOException e1){
                message = message + "\n" + "Error creating new file";
            }

        } catch (FileFormatException e){
            message = message + "\n" + "File formatting error";
            taskList.clearList();
        }

        return new TaskListResult(taskList, message);
    }

    private void _writeToFile(TaskList taskList) throws IOException{
        FileWriter fw = new FileWriter(f);
        for (Task task : taskList.tasks){
            fw.write(task.saveTask() + "\n");
        }
        fw.close();
    }

    public String writeToFile(TaskList taskList){
        try {
            _writeToFile(taskList);
        } catch (IOException e) {
            return "Error writing to file";
        }

        return "";
    }
}
