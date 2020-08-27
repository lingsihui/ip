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
        int isExited = 0;
        int numOfList = 0;
        Task[] List = new Task [100] ;

        do{
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String upperLine = line.toUpperCase();
            if (upperLine.equals("BYE")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                isExited = 1;
            } else {
                if (upperLine.equals("LIST")) {
                    for (int i = 0; i < numOfList;i++) {
                        System.out.println(i+1 + "." + List[i].getStatusIcon() + " " + List[i].description);
                    }
                } else if(upperLine.contains("DONE")){
                    String[] words = line.split(" ");
                    int taskNum = Integer.parseInt(words[1]);
                    List[taskNum-1].markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(List[taskNum-1].getStatusIcon() + " " + List[taskNum-1].description);
                } else {
                    System.out.println("added: " + line);
                    List[numOfList] = new Task(line);
                    numOfList++;
                }

            }
        } while (isExited == 0);
    }
}
