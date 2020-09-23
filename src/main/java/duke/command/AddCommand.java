package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

/**
 * Represents an ADD command in Duke which extends from the abstract class Command.
 * An <code>Add Command</code> object corresponds to
 * any commands that add a task to the tasklist. e.g., <code>Event,Deadline</code>
 */
public class AddCommand extends Command {
    private String type;
    private String line;

    /**
     * Constructor for AddCommand()
     * Initialise type of task to add and task description.
     *
     * @param type  type of task.
     * @param line task description.
     */
    public AddCommand(String type, String line){
        this.type = type;
        this.line = line;
    }
    /**
     * Returns false for boolean isExit in Duke.
     * Does not exit while loop in Duke.
     *
     * @return boolean isExit.
     */
    public boolean isExit(){
        return false;
    }
    /**
     * Execute Add task commands to tasklist.
     *
     * @param tasks  TaskList class.
     * @param ui User Interface class .
     * @param storage Storage class.
     */
    public void executeCommand(TaskList tasks, Ui ui, Storage storage){
        if (type.equals("Todo")){
            tasks.addTask(new Todo(line),ui,storage,type);
        } else if (type.equals("Event")){
            tasks.processEvent(line,ui,storage,type);
        } else if (type.equals("Deadline")){
            tasks.processDeadline(line,ui,storage,type);
        }
    }
}
