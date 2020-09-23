package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command in Duke. A <code>Command</code> object corresponds to
 * any commands with the two abstract method executeCommand and isExit.
 */
public abstract class Command {

    /**
     * Execute corresponding task commands based on the command class.
     *
     * @param tasks  TaskList class.
     * @param ui User Interface class .
     * @param storage Storage class.
     */
    public abstract void executeCommand(TaskList tasks, Ui ui, Storage storage);
    /**
     * Returns either true or false for boolean isExit in Duke.
     * Does not exit while loop in Duke when return false.
     * Exit while loop in Duke when return true.
     *
     * @return boolean isExit.
     */
    public abstract boolean isExit();
}
