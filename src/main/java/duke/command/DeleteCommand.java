package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command{
    private String line;

    public DeleteCommand(String line){
        this.line = line;
    }
    public boolean isExit(){
        return false;
    }
    public void executeCommand(TaskList tasks, Ui ui, Storage storage){
        tasks.deleteTask(line,ui,storage);
    }
}
