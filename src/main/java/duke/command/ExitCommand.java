package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an EXIT command in Duke which extends from the abstract class Command.
 * An <code>Exit Command</code> object corresponds to
 * any commands that exits Duke. e.g., <code>Bye</code>
 */
public class ExitCommand extends Command {
    /**
     * Returns true for boolean isExit in Duke.
     * Exits while loop in Duke and end the code.
     *
     * @return boolean isExit.
     */
    public boolean isExit(){
        return true;
    }
    /**
     * Execute Bye command in UI.
     *
     * @param tasks  TaskList class.
     * @param ui User Interface class .
     * @param storage Storage class.
     */
    public void executeCommand(TaskList tasks, Ui ui, Storage storage){
        ui.printByeMessage();
    }
}
