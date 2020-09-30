package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a FIND command in Duke which extends from the abstract class Command.
 * A <code>Find Command</code> object corresponds to
 * any commands that finds a task from the task list using specific input.
 */
public class FindCommand extends Command{
    private String objectToFind;
    public static final int FIND_LENGTH = 4;
    /**
     * Constructor for FindCommand()
     * Initialise input to be filtered.
     *
     * @param line user input of task to find.
     */
    public FindCommand(String line){
        objectToFind = line.substring(FIND_LENGTH);
    }
    /**
     * Execute Find task commands in task list.
     *
     * @param tasks  TaskList class.
     * @param ui User Interface class .
     * @param storage Storage class.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.findSpecificTask(objectToFind,ui);
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
}
