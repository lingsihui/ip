import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        String upperLine = line.toUpperCase();
        String[] list = new String[100];
        int i = 0;
        if (upperLine.equals("BYE")){
            System.out.println("Bye. Hope to see you again soon!\n");
        }
        else if(upperLine.equals("LIST")){
            for(int value =0 ; value < list.length; value++){
                System.out.println(i+". "+ list[i]);
            }
        }
        else{
            System.out.print("added: " +line);
            list[i] = line;
            i++;
        }

    }
}
