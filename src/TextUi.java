import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class TextUi {
    public static final String DIVIDER =  "\t____________________________________________________________";
    public static final String NEWLINE = "\n";

    public TextUi() {
    }

    public void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from" + NEWLINE + logo);
        System.out.println(DIVIDER);
        System.out.println("\tHello! I'm Duke" + NEWLINE + "\tWhat can I do for you?");
        System.out.println(DIVIDER + NEWLINE);
    }

    public String readCommand() {
        Scanner scn = new Scanner(System.in);
        return scn.nextLine();
    }

    public static void show(DukeException de) {
        System.out.println(de);
    }

    public void bye() {
        //exit when the user types bye
        System.out.println(DIVIDER);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(DIVIDER + NEWLINE);
    }
}