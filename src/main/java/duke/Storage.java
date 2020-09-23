package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage file in Duke. A <code>Storage</code> object corresponds to
 * any methods interacting with files. e.g., <code>load files, update files</code>
 */
public class Storage {
    public static final int LENGTH_OF_DONE_SYMBOL = 4;
    public static final int LENGTH_OF_NOT_DONE_SYMBOL = 4;
    public static final String DONE_SYMBOL = "|1| ";
    public static final String NOT_DONE_SYMBOl = "|0| ";

    private String filePath;

    /**
     * Constructor.
     * Initialise filepath.
     *
     * @param filePath  File path of the file to store data.
     */
    public Storage(String filePath){
        this.filePath = filePath;
    }
    /**
     * Returns Array List of the tasks stored in the file.
     * If the files in the is not in the correct format, Duke Exception will be thrown.
     * e.g. files does not start with symbol |0| or |1|.
     * format of file : e.g. DONE_SYMBOL TASK_TYPE Description /AT Description.
     * e.g. |0| Deadline return book /by monday.
     * * e.g. files does not start with Deadline,Event or Todo.
     *
     * @return ArrayList<Task> of tasks stored in the file.
     * @throws IOException  If file has an error.
     * @throws DukeException  If file format is not correct.
     */
    public ArrayList<Task> load() throws IOException, DukeException{
        File f = new File(filePath); // create a File for the given file path
        f.getParentFile().mkdirs();
        f.createNewFile();
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> storageTasks = new ArrayList<>();
        while (s.hasNext()) {
            String line  = s.nextLine();
            if(line.contains(DONE_SYMBOL)){
                addFileTask(storageTasks,line.substring(LENGTH_OF_DONE_SYMBOL));
                storageTasks.get(storageTasks.size() - 1).markAsDone();
            } else if (line.contains(NOT_DONE_SYMBOl)){
                addFileTask(storageTasks,line.substring(LENGTH_OF_NOT_DONE_SYMBOL));
            } else{
                throw new DukeException();
            }
        }
        return storageTasks;
    }
    private void addFileTask(ArrayList<Task> storageTasks,String line) throws DukeException{
        if(line.startsWith("Deadline")){
            int slashPosition = line.indexOf("/");
            storageTasks.add(new Deadline(line ,slashPosition));
        } else if(line.startsWith("Todo")){
            storageTasks.add(new Todo(line));
        } else if (line.startsWith("Event")){
            int slashPosition = line.indexOf("/");
            storageTasks.add(new Event(line ,slashPosition));
        } else {
            throw new DukeException();
        }
    }
    /**
     * Save tasks in task list to file in the correct format.
     * If task cannot be written into file, an error message will be shown.
     * format: DONE_SYMBOL TYPE task_description at/by_description
     *
     * @param type  String type of Task.
     * @param t Class Task of the task to add.
     * @param isAppend Boolean of whether the file should overwrite or append to file.
     * @param ui  User interface to print error message.
     */
    public void saveTaskToFile(String type,Task t, boolean isAppend,Ui ui){
        try {
            writeToFile(type,t,isAppend);
        } catch (IOException e) {
            ui.printErrorToSaveTaskToFile(e);
        }
    }
    private void writeToFile(String type, Task taskToAdd,boolean isAppend) throws IOException {
        FileWriter fw = new FileWriter(this.filePath,isAppend);
        String done;
        if (taskToAdd.getIsDone()){
            done = DONE_SYMBOL;
        } else {
            done = NOT_DONE_SYMBOl;
        }
        if (type.equals("Deadline")){
            fw.write(done + type + taskToAdd.getDescription() + "/by"
                    + taskToAdd.getBy() + System.lineSeparator());
        } else if (type.equals("Event")){
            fw.write(done + type + taskToAdd.getDescription() + "/at"
                    + taskToAdd.getAt() + System.lineSeparator());
        } else if (type.equals("Todo")){
            fw.write(done + type + taskToAdd.getDescription()
                    + System.lineSeparator());
        }
        fw.close();
    }
    /**
     * Update tasks list in the file.
     *
     * @param tasks  String type of Task.
     * @param ui User interface to be passed to saveTaskToFile.
     */
    public void updateTaskToFile(ArrayList<Task> tasks,Ui ui) {
        saveTaskToFile(tasks.get(0).getType(),tasks.get(0), false, ui);
        for(int i = 1; i< tasks.size();i++){
            saveTaskToFile(tasks.get(i).getType(),tasks.get(i),true, ui);
        }
    }

}
