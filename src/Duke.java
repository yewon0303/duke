import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    private static LinkedList<Task> commands = new LinkedList<>();

    private void greeting() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    private void commandHandler() {
        Scanner scn = new Scanner(System.in);
        //assume no more than 100 tasks
        boolean carryOn = true;
        while (carryOn) {
            String command = scn.nextLine();
            String[] parsed = command.split(" ");
            if (parsed.length == 1) {
                switch (command) {
                    case "bye": bye();
                                carryOn = false; //exit
                                break;
                    case "list": listAll(commands);
                                 break;
                    default: break;
                }
            } else if (parsed[0].equals("done")) {
                int doneTask = Integer.parseInt(parsed[1]);
                done(doneTask);
            } else {
                //add command to the list of commands
                Task newTask = new Task(command);
                commands.add(newTask);
                //echo the command entered by the user
                System.out.println("    ____________________________________________________________");
                System.out.println("    added: " + command);
                System.out.println("    ____________________________________________________________");
                System.out.println();
            }
        }
    }

    private void listAll(LinkedList<Task> commands) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < commands.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + commands.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    private void done(int doneTask) {
        Task now = commands.get(doneTask - 1);
        now.markDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + now);
        System.out.println("    ____________________________________________________________");
    }

    private void bye() {
        //exit when the user types bye
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.greeting();
        duke.commandHandler();
    }
}
