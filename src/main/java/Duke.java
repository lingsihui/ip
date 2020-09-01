import java.util.Scanner;

public class Duke {
    public static final int DEADLINE_LENGTH = 8;
    public static final int EVENT_LENGTH = 5;

    private static Task[] tasks = new Task[100];
    private static int numOfTask = 0;


    public static void main(String[] args) {
        printGreetingMessage();
        boolean isExited = false;
        Scanner in = new Scanner(System.in);
        while (!isExited) {
            String line;
            line = readInput(in);
            isExited = evaluateInput(line);
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

    public static boolean evaluateInput (String line){
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
            printInvalidInputMessage();
            return false;
        }
    }

    public static void printInvalidInputMessage() {
        System.out.println("Invalid Input");
    }

    public static void markTaskAsDone(String line) {
        String[] words = line.split(" ");
        int taskNum = Integer.parseInt(words[1]);
        tasks[taskNum - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + tasks[taskNum - 1]);
    }

    public static void addEvent(String line) {
        int slashPosition  =  line.indexOf("/");
        String at = line.substring(slashPosition + 1);
        String description = line.substring(EVENT_LENGTH,slashPosition);
        addTask(new Event(description,at));
    }

    public static void addDeadline(String line) {
        int slashPosition  =  line.indexOf("/");
        String by = line.substring(slashPosition + 1);
        String description = line.substring(DEADLINE_LENGTH,slashPosition);
        addTask(new Deadline(description,by));
    }

    public static void addTask (Task t){
        System.out.println("Got it. I've added this task:");
        tasks[numOfTask] = t;
        numOfTask++;
        System.out.println("\t" + t);
        System.out.println("Now you have " + numOfTask + " task in the list.");
    }

    public static void showList () {
        int numbering = 0;
        System.out.println("Here are the tasks in your list:");
        for (Task t: tasks){
            numbering++;
            if(t == null){
                break;
            }
            System.out.println(numbering + ". " + t);
        }
    }

    public static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

}

