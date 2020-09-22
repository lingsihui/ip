package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command{
    private String line;

    public DoneCommand(String line){
        this.line = line;
    }
    @Override
    public boolean isExit() {
        return false;
    }
    public void executeCommand(TaskList tasks, Ui ui, Storage storage){
        tasks.markTaskAsDone(line,ui,storage);
    }
}
