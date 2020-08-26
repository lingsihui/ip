import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Greetings
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        //Reading inputs
        int exit = 0;
        int numOfList = 0;
        Task list[] = new Task [100] ;

        do{
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String upperLine = line.toUpperCase();
            //user enter bye
            if (upperLine.equals("BYE")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                exit = 1;
            }
            else {
                if (upperLine.equals("LIST")) {
                    for (int i = 0; i < numOfList;i++) {
                        System.out.println(i+1 + "." + list[i].getStatusIcon() + " " + list[i].description);
                    }
                }
                else if(upperLine.contains("DONE")){
                    String[] words = line.split(" ");
                    int taskNum = Integer.parseInt(words[1]);
                    list[taskNum-1].markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(list[taskNum-1].getStatusIcon() + " " + list[taskNum-1].description);
                }
                else {
                    System.out.println("added: " + line);
                    list[numOfList] = new Task(line);
                    numOfList++;
                }

            }
        } while ( exit == 0);
    }
}
