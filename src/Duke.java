import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    private static void greeting() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    private static void commandHandler() {
        Scanner scn = new Scanner(System.in);
        LinkedList<String> commands = new LinkedList<>();
        while (true) {
            String command = scn.nextLine();
            if (command.equals("bye")) {
                bye();
                break;
            } else if (command.equals("list")) {
                listAll(commands);
            } else {
                //add command to the list of commands
                commands.add(command);
                //echo the command entered by the user
                System.out.println("    ____________________________________________________________");
                System.out.println("    added: " + command);
                System.out.println("    ____________________________________________________________");
                System.out.println();
            }
        }
    }

    private static void bye() {
        //exit when the user types bye
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    private static void listAll(LinkedList<String> commands) {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < commands.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + commands.get(i));
        }
        System.out.println("    ____________________________________________________________");
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
