package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public boolean isExit(){
        return true;
    }
    public void executeCommand(TaskList tasks, Ui ui, Storage storage){
        ui.printByeMessage();
    }
}
