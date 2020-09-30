package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a Date command in Duke which extends from the abstract class Command.
 * A <code>Date Command</code> object corresponds to
 * any commands that filters out a task from the tasklist using specific date.
 */
public class DateCommand extends Command{
    public static final int DATE_LENGTH = 4;
    private String date;
    /**
     * Constructor for DateCommand()
     * Initialise specific date to filter.
     *
     * @param line date.
     */
    public DateCommand(String line){
        this.date = line.substring(DATE_LENGTH);
    }
    /**
     * Execute Date task commands in tasklist.
     *
     * @param tasks  TaskList class.
     * @param ui User Interface class .
     * @param storage Storage class.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.showDateList(ui,date);
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
