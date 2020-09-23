package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.DateTimeException;

public class TaskList {
    private ArrayList<Task> tasks;
    public final int MIN_DESCRIPTION_LENGTH = 2;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    public void processEvent(String line,Ui ui, Storage storage, String type) {
        try {
            if (!line.contains("/at")){
                throw new DukeException();
            }
            int slashPosition = line.indexOf("/");
            String formattedLine = formatDateAndTime(line,slashPosition);
            addTask(new Event(formattedLine,slashPosition),ui,storage,type);
        } catch (DukeException e) {
            ui.printInvalidEventOrDeadlineMessage("Event");
        } catch (DateTimeException e){
            ui.printInvalidDateMessage();
        }
    }

    public String formatDateAndTime (String line, int slashPosition) throws  DateTimeException, DukeException{
        String  dateLine = line.substring(slashPosition);
        String[] dates = dateLine.split(" ");
        String oldDate = dates[0];
        for(int i = 0 ; i < dates.length ;i++){
            if (dates[i].contains("-")){
                oldDate = dates[i];
                LocalDate date = LocalDate.parse(oldDate);
                String newDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                return line.replace(oldDate,newDate);
            }
        }
        return line;
    }

    public void processDeadline (String line, Ui ui, Storage storage, String type){
        try {
            if (!line.contains("/by")) {
                throw new DukeException();
            }
            int slashPosition = line.indexOf("/");
            String formattedLine = formatDateAndTime(line, slashPosition);
            addTask(new Deadline(formattedLine, slashPosition), ui, storage, type);
        } catch (DukeException e) {
            ui.printInvalidEventOrDeadlineMessage("Deadline");
        } catch (DateTimeException e){
            ui.printInvalidDateMessage();
        }
    }

    public void addTask (Task t, Ui ui, Storage storage, String type){
        try {
            if (t.getDescriptionLength() < MIN_DESCRIPTION_LENGTH) {
                throw new DukeException();
            }
            tasks.add(t);
            ui.showAddTaskMessage(t, tasks.size());
            storage.saveTaskToFile(type, t, true);
        } catch (DukeException e) {
            ui.printInvalidTaskToAdd();
        }
    }

    public void deleteTask (String line, Ui ui, Storage storage){
        try {
            int taskNum = processTaskToDelete(line);
            ui.showDeleteTaskMessage(tasks, taskNum);
            tasks.remove(taskNum - 1);
            storage.updateTaskToFile(tasks);
        } catch (DukeException e) {
            ui.showInvalidTaskToDelete();
        }
    }

    public void markTaskAsDone (String line, Ui ui, Storage storage){
        try {
            int taskNum = processTaskToDelete(line);
            tasks.get(taskNum - 1).markAsDone();
            ui.showMarkedTaskMessage(tasks, taskNum);
            storage.updateTaskToFile(tasks);
        } catch (DukeException e) {
            ui.showInvalidTaskToMark();
        }
    }

    public int processTaskToDelete (String line) throws DukeException {
        if (!line.contains(" ")) {
            throw new DukeException();
        }
        String[] words = line.split(" ");
        if (words.length < 2) {
            throw new DukeException();
        }
        int taskNum = Integer.parseInt(words[1]);
        if (taskNum > tasks.size()) {
            throw new DukeException();
        }
        return taskNum;
    }

    public void showList (Ui ui){
        try {
            if (tasks.size() == 0) {
                throw new DukeException();
            }
            ui.printTasksInList(tasks);
        } catch (DukeException e) {
            ui.printListIsEmptyMessage();
        }
    }

}
