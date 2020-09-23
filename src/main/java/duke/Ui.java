package duke;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static final String LINE = "========================================================";
    private  Scanner in;

    //Constructor
    public Ui(){
        this.in = new Scanner(System.in);
    }

    public void printGreetingMessage () {
        showLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public String readInput(){
        return this.in.nextLine();
    }

    public void showLine(){
        System.out.println(LINE);
    }

    public void printInvalidInputMessage() {
        System.out.println("Opps! Sorry I don't know what you mean! :(");
    }

    public void showFileNotFoundError(){
        System.out.println("File not found");
    }

    public void showLoadingError(){
        System.out.println("Invalid File content");
    }

    public void showAddTaskMessage(Task t, int size){
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + t);
        System.out.println("Now you have " + size + " task in the list.");
    }

    public void printInvalidTaskToAdd(){
        System.out.println("OOPS! Description cannot be empty!");
    }

    public void showDeleteTaskMessage(ArrayList<Task> tasks, int taskNum){
        System.out.println("Noted I have removed this task!");
        System.out.println("\t"+ tasks.get(taskNum-1));
        System.out.println("Now you have " + tasks.size() + " task in the list.");
    }

    public void showInvalidTaskToDelete(){
        System.out.println("OOPS! Invalid task to delete!");
    }

    public void showMarkedTaskMessage(ArrayList<Task> tasks,int taskNum){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t"+ tasks.get(taskNum-1));
    }
    public void showInvalidTaskToMark(){
        System.out.println("OOPS! Invalid task to Mark!");
    }
    public void printTasksInList(ArrayList<Task> tasks){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    public void printListIsEmptyMessage(){
        System.out.println("Your list is empty! :0");
    }

    public void printInvalidDateMessage(){
        System.out.println("OOPS! Invalid Date Input");
        System.out.println("Input Date in this format YYYY-MM-DD");
    }

    public void printInvalidEventOrDeadlineMessage(String type){
        System.out.println("OOPS! Invalid " + type + " Input");
    }

}
