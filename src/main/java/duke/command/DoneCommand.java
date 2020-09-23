package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a DONE command in Duke which extends from the abstract class Command.
 * A <code>Done Command</code> object corresponds to
 * any commands that mark a task as done from the tasklist. e.g., <code>Done 2</code>
 */
public class DoneCommand extends Command{
    private String line;

    /**
     * Constructor for DoneCommand()
     * Initialise task to be marked as done.
     *
     * @param line task to be marked as done. e.g. Done 2
     */
    public DoneCommand(String line){
        this.line = line;
    }
    /**
     * Returns false for boolean isExit in Duke.
     * Does not exit while loop in Duke.
     *
     * @return boolean isExit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
    /**
     * Execute markAsDone task commands in tasklist.
     *
     * @param tasks  TaskList class.
     * @param ui User Interface class .
     * @param storage Storage class.
     */
    public void executeCommand(TaskList tasks, Ui ui, Storage storage){
        tasks.markTaskAsDone(line,ui,storage);
    }
}
