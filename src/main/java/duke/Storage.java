package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final int LENGTH_OF_DONE_SYMBOL = 4;
    public static final int LENGTH_OF_NOT_DONE_SYMBOL = 4;
    public static final String DONE_SYMBOL = "|1| ";
    public static final String NOT_DONE_SYMBOl = "|0| ";

    private String filePath;

    //Constructor
    public Storage(String filePath){
        this.filePath = filePath;
    }

    //Return an Array List of the tasks in file
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

    //add File task to ArrayList
    public void addFileTask(ArrayList<Task> storageTasks,String line) throws DukeException{
        if(line.startsWith("Deadline")){
            int slashPosition = line.indexOf("/");
            storageTasks.add(new Deadline(line ,slashPosition));
        } else if(line.startsWith("Todo")){
            storageTasks.add(new Todo(line));
        } else if (line.startsWith("Event")){
            int slashPosition = line.indexOf("/");
            storageTasks.add(new Event(line ,slashPosition));;
        } else {
            throw new DukeException();
        }
    }

    public void saveTaskToFile(String type,Task t, boolean isAppend){
        try {
            writeToFile(type,t,isAppend);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void writeToFile(String type, Task taskToAdd,boolean isAppend) throws IOException {
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

    public void updateTaskToFile(ArrayList<Task> tasks) {
        saveTaskToFile(tasks.get(0).getType(),tasks.get(0), false);
        for(int i = 1; i< tasks.size();i++){
            saveTaskToFile(tasks.get(i).getType(),tasks.get(i),true);
        }
    }

}
