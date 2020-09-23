package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a Task list A <code>Task List</code> object contains the task list
 * and has operations to add/delete/update/show tasks in the list
 */
public class TaskList {
    private ArrayList<Task> tasks;
    public final int MIN_DESCRIPTION_LENGTH = 2;

    /**
     * Constructor.
     * Initialise Array list tasks to the Array List of the storage.
     *
     * @param tasks  Array list of the tasks in the file.
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    /**
     * Constructor.
     * Create a new Array list tasks.
     */
    public TaskList(){
        this.tasks = new ArrayList<>();
    }
    /**
     * Process the user input of an Event task to task description and at description.
     * If the event task has a missing /at, a Duke Exception is thrown.
     *
     * @param line  user input of event.
     * @param ui User interface to print error.
     * @param storage Storage parameter to be passed to addTask().
     */
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
    /**
     * Process the user input of a Deadline task to task description and by description.
     * If the deadline task has a missing /by, a Duke Exception is thrown.
     *
     * @param line  user input of event.
     * @param ui User interface to print error.
     * @param storage Storage parameter to be passed to addTask().
     */
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
    /**
     * Adds a task to the task list "tasks".
     * Updates the added task to the file.
     * If the description length is empty, a DukeException is thrown, and prints Invalid Task message.
     *
     * @param t  Task to be added.
     * @param ui User interface to show the task added.
     * @param storage Storage to update file.
     * @param type Type of the task to add.
     */
    public void addTask(Task t,Ui ui,Storage storage,String type){
        try {
            if (t.getDescriptionLength() < MIN_DESCRIPTION_LENGTH) {
                throw new DukeException();
            }
            tasks.add(t);
            ui.showAddTaskMessage(t,tasks.size());
            storage.saveTaskToFile(type,t,true,ui);
        }catch(DukeException e){
            ui.printInvalidTaskToAdd();
        }
    }
    /**
     * Deletes a task in the task list "tasks".
     * Updates the deleted task in the file.
     * If the task to be deleted is invalid, a DukeException is thrown,
     * and prints Invalid Task to delete message.
     * Format : Delete 2
     * Invalid format includes: Missing spaces, task Number to be deleted larger than task size.
     *
     * @param line TaskNumber of the task be deleted.
     * @param ui User interface to show the task deleted.
     * @param storage Storage to update file.
     */
    public void deleteTask(String line,Ui ui,Storage storage){
        try {
            int taskNum = processTaskToDelete(line);
            ui.showDeleteTaskMessage(tasks,taskNum);
            tasks.remove(taskNum-1);
            storage.updateTaskToFile(tasks,ui);
        }catch (DukeException e) {
            ui.showInvalidTaskToMarkAndDelete("Delete");
        }
    }
    /**
     * Marks a task in the task list "tasks" as done.
     * Updates the marked task in the file.
     * If the task to be marked is invalid, a DukeException is thrown,
     * and prints Invalid Task to mark message.
     * Format : Done 2
     * Invalid format includes: Missing spaces, task Number to be marked larger than task size.
     *
     * @param line TaskNumber of the task to be marked.
     * @param ui User interface to show the task marked.
     * @param storage Storage to update file.
     */
    public void markTaskAsDone(String line,Ui ui,Storage storage) {
        try {
            int taskNum = processTaskToDelete(line);
            tasks.get(taskNum-1).markAsDone();
            ui.showMarkedTaskMessage(tasks,taskNum);
            storage.updateTaskToFile(tasks,ui);
        } catch (DukeException e) {
            ui.showInvalidTaskToMarkAndDelete("Mark");
        }
    }
    private int processTaskToDelete(String line) throws DukeException {
        if(!line.contains(" ")){
            throw new DukeException();
        }
        String[] words = line.split(" ");
        int taskNum = Integer.parseInt(words[1]);
        if(taskNum > tasks.size() ){
            throw new DukeException();
        }
        return taskNum;
    }
    /**
     * Shows the list in the task.
     * If the list is empty, DukeException is thrown and List is empty message will be shown.
     *
     * @param ui  User Interface to show the list and error message.
     */
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
