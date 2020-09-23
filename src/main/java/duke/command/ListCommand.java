package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a LIST command in Duke which extends from the abstract class Command.
 * A <code>List Command</code> object corresponds to
 * any commands that shows the tasks in the tasklist. e.g., <code>List</code>
 */
public class ListCommand extends Command {
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
     * Execute command to show the list in taskList.
     *
     * @param tasks  TaskList class.
     * @param ui User Interface class .
     * @param storage Storage class.
     */
    public void executeCommand (TaskList tasks, Ui ui, Storage storage){
        tasks.showList(ui);
    }
}
