package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Duke {
    public static final int TASK_SIZE = 100;
    public static final int MIN_DESCRIPTION_LENGTH = 2;

    private static Task[] tasks = new Task[TASK_SIZE];
    private static int numOfTask = 0;

    public static void main(String[] args) {
        printGreetingMessage();
        loadTaskFile();
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

    public static void loadTaskFile() {
        try {
            loadFileContents("data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (DukeException e) {
            System.out.println("Invalid File content");
        }
    }

    private static void loadFileContents(String filePath) throws FileNotFoundException, DukeException{
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line  = s.nextLine();
            if(line.contains("|1|")){
                addFileTask(line.substring(4));
                tasks[numOfTask].markAsDone();
            } else if (line.contains("|0|")){
                addFileTask(line.substring(4));
            } else{
                throw new DukeException();
            }
            numOfTask++;
        }
        showList();
    }

    private static void addFileTask(String line) throws DukeException{
        if(line.startsWith("Deadline")){
            int slashPosition = line.indexOf("/");
            tasks[numOfTask] = new Deadline(line ,slashPosition);
        } else if(line.startsWith("Todo")){
            tasks[numOfTask] = new Todo(line);
        } else if (line.startsWith("Event")){
            int slashPosition = line.indexOf("/");
            tasks[numOfTask] = new Event(line ,slashPosition);
        } else {
            throw new DukeException();
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
            tasks[numOfTask] = t;
            numOfTask++;
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + t);
            System.out.println("Now you have " + numOfTask + " task in the list.");
            saveTaskToFile("data/duke.txt",t,true);
        }catch(DukeException e){
            t.printInvalid();
        }
    }

    private static void saveTaskToFile(String filepath, Task t, boolean isAppend){
        try {
            writeToFile(filepath,t,isAppend);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void writeToFile(String filePath, Task taskToAdd,boolean isAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath,isAppend);
        String done;
        if (taskToAdd.getIsDone()){
            done = "|1| ";
        } else {
            done = "|0| ";
        }
        String taskType = taskToAdd.getTaskType();
        if (taskType.equals("Deadline")){
            fw.write(done + taskToAdd.getTaskType() + taskToAdd.getDescription() + "/by"
                    + taskToAdd.getBy() + System.lineSeparator());
        } else if (taskType.equals("Event")){
            fw.write(done + taskToAdd.getTaskType() + taskToAdd.getDescription() + "/at"
                    + taskToAdd.getAt() + System.lineSeparator());
        } else if (taskType.equals("Todo")){
            fw.write(done + taskToAdd.getTaskType() + taskToAdd.getDescription()
                    + System.lineSeparator());
        }
        fw.close();
    }

    public static void markTaskAsDone(String line) {
        try {
            int taskNum = processTaskToMark(line);
            tasks[taskNum - 1].markAsDone();
            updateTaskToFile();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t" + tasks[taskNum - 1]);
        } catch (DukeException e) {
            System.out.println("OOPS! Invalid task to mark!");
        } catch (IOException e){
            System.out.println("OOPS! An error had occurred");
        }
    }

    public static void updateTaskToFile() throws IOException{
        saveTaskToFile("data/duke.txt",tasks[0], false);
        for(int i = 1; i< numOfTask;i++){
            saveTaskToFile("data/duke.txt",tasks[i],true);
        }
    }


    public static int processTaskToMark(String line) throws DukeException {
        if(!line.contains(" ")){
            throw new DukeException();
        }
        String[] words = line.split(" ");
        if(words.length < 2){
            throw new DukeException();
        }
        int taskNum = Integer.parseInt(words[1]);
        if(taskNum > numOfTask ){
            throw new DukeException();
        }
        return taskNum;
    }

    public static void showList () {
        try {
            if(numOfTask == 0){
                throw new DukeException();
            }
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < numOfTask; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }catch (DukeException e){
            System.out.println("Your list is empty! :0");
        }
    }

    public static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

}