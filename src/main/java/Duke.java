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
        String[] list = new String[100];

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
                        System.out.println(i+1 + ". " + list[i]);
                    }
                } else {
                    System.out.println("added: " + line);
                    list[numOfList] = line;
                    numOfList++;
                }

            }
        } while ( exit == 0);
    }
}
