package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }
    public void executeCommand (TaskList tasks, Ui ui, Storage storage){
        tasks.showList(ui);
    }
}
