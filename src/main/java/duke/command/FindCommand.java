package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command{
    public static final int FIND_LENGTH = 5;
    private String objectToFind;

    public FindCommand(String line){
        objectToFind = line.substring(FIND_LENGTH);
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.findSpecificTask(objectToFind,ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
