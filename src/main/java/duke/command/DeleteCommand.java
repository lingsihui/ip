package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a DELETE command in Duke which extends from the abstract class Command.
 * A <code>Delete Command</code> object corresponds to
 * any commands that deletes a task from the tasklist. e.g., <code>Delete 2</code>
 */
public class DeleteCommand extends Command{
    private String line;
    /**
     * Constructor for DeleteCommand()
     * Initialise task to delete.
     *
     * @param line task to delete e.g. delete 2.
     */
    public DeleteCommand(String line){
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
     * Execute Delete task commands in tasklist.
     *
     * @param tasks  TaskList class.
     * @param ui User Interface class .
     * @param storage Storage class.
     */
    public void executeCommand(TaskList tasks, Ui ui, Storage storage){
        tasks.deleteTask(line,ui,storage);
    }
}
