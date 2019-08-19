import java.util.Scanner;

public class Duke {
    private static void greeting() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    private static void commandHandler() {
        Scanner scn = new Scanner(System.in);

        while (true) {
            String command = scn.next();
            if (command.equals("bye")) {
                //exit when the user types bye
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                System.out.println();
                break;
            } else {
                //echo the command entered by the user
                System.out.println("    ____________________________________________________________");
                System.out.println("    " + command);
                System.out.println("    ____________________________________________________________");
                System.out.println();
            }
        }


    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greeting();
        commandHandler();
    }
}
