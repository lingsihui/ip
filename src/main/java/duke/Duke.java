package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final int MIN_DESCRIPTION_LENGTH = 2;
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printGreetingMessage();
        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        while (!isExit) {
            try {
                String line;
                line = readInput(in);
                isExit = evaluateInput(line);
            }catch(DukeException e){
                printInvalidInputMessage();
                isExit = false;
            }
        }
    }

    public static void printGreetingMessage () {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static String readInput (Scanner in) {
        return in.nextLine();
    }

    public static boolean evaluateInput (String line) throws DukeException{
        String upperLine = line.toUpperCase();
        if (upperLine.startsWith("TODO")) {
            addTask(new Todo(line));
            return false;
        } else if (upperLine.startsWith("LIST")) {
            showList();
            return false;
        } else if (upperLine.startsWith("DEADLINE")) {
            addDeadline(line);
            return false;
        } else if (upperLine.startsWith("EVENT")) {
            addEvent(line);
            return false;
        } else if (upperLine.startsWith("BYE")) {
            printByeMessage();
            return true;
        } else if (upperLine.startsWith("DONE")) {
            markTaskAsDone(line);
            return false;
        } else if (upperLine.startsWith("DELETE")){
            deleteTask(line);
            return false;
        } else {
            throw new DukeException();
        }
    }

    public static void addEvent(String line) {
        try {
            if (!line.contains("/at")){
                throw new DukeException();
            }
            int slashPosition = line.indexOf("/");
            addTask(new Event(line,slashPosition));
        } catch (DukeException e) {
            System.out.println("OOPS! Invalid Event Input");
        }
    }

    public static void addDeadline(String line) {
        try {
            if (!line.contains("/by")){
                throw new DukeException();
            }
            int slashPosition = line.indexOf("/");
            addTask(new Deadline(line,slashPosition));
        } catch (DukeException e) {
            System.out.println("OOPS! Invalid Deadline Input");
        }
    }

    public static void printInvalidInputMessage() {
        System.out.println("Opps! Sorry I don't know what you mean! :(");
    }

    public static void addTask(Task t){
        try {
            if (t.getDescriptionLength() < MIN_DESCRIPTION_LENGTH) {
                throw new DukeException();
            }
            tasks.add(t);
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + t);
            System.out.println("Now you have " + tasks.size() + " task in the list.");

        }catch(DukeException e){
            t.printInvalid();
        }
    }

    public static void markTaskAsDone(String line) {
        try {
            int taskNum = processTask(line);
            tasks.get(taskNum-1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t" + tasks.get(taskNum - 1));
        } catch (DukeException e) {
            System.out.println("OOPS! Invalid task to mark!");
        }
    }

    public static void deleteTask(String line){
        try {
            int taskNum = processTask(line);
            System.out.println("Noted I have removed this task!");
            System.out.println("\t"+tasks.get(taskNum-1));
            tasks.remove(taskNum-1);
            System.out.println("Now you have " + tasks.size() + " task in the list.");
        }catch (DukeException e) {
            System.out.println("OOPS! Invalid task to delete!");
        }
    }

    public static int processTask(String line) throws DukeException {
        if(!line.contains(" ")){
            throw new DukeException();
        }
        String[] words = line.split(" ");
        if(words.length < 2){
            throw new DukeException();
        }
        int taskNum = Integer.parseInt(words[1]);
        if(taskNum > tasks.size() ){
            throw new DukeException();
        }
        return taskNum;
    }

    public static void showList () {
        try {
            if(tasks.size() == 0){
                throw new DukeException();
            }
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }catch (DukeException e){
            System.out.println("Your list is empty! :0");
        }
    }

    public static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

}

