package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.DateTimeException;

import static java.util.stream.Collectors.toList;

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
            String formattedLine = formatDate(line,slashPosition);
            addTask(new Event(formattedLine,slashPosition),ui,storage,type);
        } catch (DukeException e) {
            ui.printInvalidEventOrDeadlineMessage("Event");
        } catch (DateTimeException e){
            ui.printInvalidDateMessage();
        }
    }

    public String formatDate(String line, int slashPosition) throws DateTimeException{
        String  dateLine = line.substring(slashPosition);
        String[] dates = dateLine.split(" ");
        String oldDate;
        for (String s : dates) {
            if (s.contains("-")) {
                oldDate = s;
                LocalDate date = LocalDate.parse(oldDate);
                String newDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                return line.replace(oldDate, newDate);
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
            String formattedLine = formatDate(line, slashPosition);
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

    public void showDateList (Ui ui, String date){
        try {
            String formattedDate = formatDate(date,0);
            ArrayList<Task> filteredDateList;
            filteredDateList = (ArrayList<Task>) tasks.stream()
                    .filter((t) -> t.getBy().contains(formattedDate) || t.getAt().contains(formattedDate))
                    .collect(toList());
            if (filteredDateList.size() == 0) {
                throw new DukeException();
            }
            ui.printTasksInList(filteredDateList);
        } catch (DukeException e) {
            ui.printListIsEmptyMessage();
        } catch (DateTimeException e){
            ui.printInvalidDateMessage();
        }
    }

}
