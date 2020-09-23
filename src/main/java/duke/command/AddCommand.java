package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

public class AddCommand extends Command {
    private String type;
    private String line;

    public AddCommand(String type, String line){
        this.type = type;
        this.line = line;
    }
    public boolean isExit(){
        return false;
    }
    public void executeCommand(TaskList tasks, Ui ui, Storage storage){
        if (type.equals("Todo")){
            tasks.addTask(new Todo(line),ui,storage,type);
        } else if (type.equals("Event")){
            tasks.processEvent(line,ui,storage,type);
        } else if (type.equals("Deadline")){
            tasks.processDeadline(line,ui,storage,type);
        }
    }
}
