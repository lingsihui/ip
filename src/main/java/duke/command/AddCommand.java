package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.Task;

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
            int slashPosition = tasks.processEvent(line);
            tasks.addTask(new Event(line,slashPosition),ui,storage,type);
        } else if (type.equals("Deadline")){
            int slashPosition = tasks.processDeadline(line);
            tasks.addTask(new Deadline(line,slashPosition),ui,storage,type);
        }
    }
}
