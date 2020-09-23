package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class TaskList {
    private ArrayList<Task> tasks;
    public final int MIN_DESCRIPTION_LENGTH = 2;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    public void processEvent(String line,Ui ui, Storage storage, String type) {
        try {
            if (!line.contains("/at")){
                throw new DukeException();
            }
            int slashPosition = line.indexOf("/");
            addTask(new Event(line,slashPosition),ui,storage,type);
        } catch (DukeException e) {
            System.out.println("OOPS! Invalid Event Input");
        }
    }

    public void processDeadline(String line,Ui ui, Storage storage, String type) {
        try {
            if (!line.contains("/by")){
                throw new DukeException();
            }
            int slashPosition = line.indexOf("/");
            addTask(new Deadline(line,slashPosition),ui,storage,type);
        } catch (DukeException e) {
            System.out.println("OOPS! Invalid Deadline Input");
        }
    }

    public void addTask(Task t,Ui ui,Storage storage,String type){
        try {
            if (t.getDescriptionLength() < MIN_DESCRIPTION_LENGTH) {
                throw new DukeException();
            }
            tasks.add(t);
            ui.showAddTaskMessage(t,tasks.size());
            storage.saveTaskToFile(type,t,true);
        }catch(DukeException e){
            ui.printInvalidTaskToAdd();
        }
    }

    public void deleteTask(String line,Ui ui,Storage storage){
        try {
            int taskNum = processTaskToDelete(line);
            ui.showDeleteTaskMessage(tasks,taskNum);
            tasks.remove(taskNum-1);
            storage.updateTaskToFile(tasks);
        }catch (DukeException e) {
            ui.showInvalidTaskToDelete();
        }
    }

    public void markTaskAsDone(String line,Ui ui,Storage storage) {
        try {
            int taskNum = processTaskToDelete(line);
            tasks.get(taskNum-1).markAsDone();
            ui.showMarkedTaskMessage(tasks,taskNum);
            storage.updateTaskToFile(tasks);
        } catch (DukeException e) {
            ui.showInvalidTaskToMark();
        }
    }

    public int processTaskToDelete(String line) throws DukeException {
        if(!line.contains(" ")){
            throw new DukeException();
        }
        String[] words = line.split(" ");
        if(words.length < 2){
            throw new DukeException();
        }
        int taskNum = Integer.parseInt(words[1]);
        if(taskNum > tasks.size() ){
            throw new DukeException();
        }
        return taskNum;
    }

    public void showList (Ui ui) {
        try {
            if(tasks.size() == 0){
                throw new DukeException();
            }
            ui.printTasksInList(tasks);
        }catch (DukeException e){
            ui.printListIsEmptyMessage();
        }
    }

    public void findSpecificTask(String line,Ui ui,Storage storage){
        try{
            ArrayList<Task> filteredTaskList = (ArrayList<Task>) tasks.stream()
                    .filter((t) -> t.getDescription().contains(line))
                    .collect(toList());
            if(filteredTaskList.size() == 0){
                throw new DukeException();
            }
            ui.printFilteredTaskList(filteredTaskList);
        } catch (DukeException e){
            ui.printNoMatchingTaskMessage();
        }
    }


}
