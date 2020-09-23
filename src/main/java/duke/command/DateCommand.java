package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DateCommand extends Command{
    public static final int DATE_LENGTH = 5;
    private String date;

    public DateCommand(String line){
        this.date = line.substring(DATE_LENGTH);
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.showDateList(ui,date);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
