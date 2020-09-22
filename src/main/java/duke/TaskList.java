package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public final int MIN_DESCRIPTION_LENGTH = 1;


    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    public int processEvent(String line) {
        try {
            if (!line.contains("/at")){
                throw new DukeException();
            }
            return line.indexOf("/");
        } catch (DukeException e) {
            System.out.println("OOPS! Invalid Event Input");
        }
        return -1;
    }

    public int processDeadline(String line) {
        try {
            if (!line.contains("/by")){
                throw new DukeException();
            }
            return line.indexOf("/");
        } catch (DukeException e) {
            System.out.println("OOPS! Invalid Deadline Input");
        }
        return -1;
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


}
