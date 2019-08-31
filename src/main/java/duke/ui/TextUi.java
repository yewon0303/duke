package duke.ui;

import duke.TaskList;
import duke.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class TextUi {
    public static final String DIVIDER =  "\t____________________________________________________________";
    public static final String NEWLINE = "\n";

    public TextUi() {
    }

    /**
     * Prints the welcome message including the logo, Hello greeting
     * and the horizontal line before and after.
     */
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

    /**
     * Prints the farewell message when the user wishes to end the program.
     */
    public static void findCommandPrint(ArrayList<Task> tasks) {
        System.out.println(DIVIDER);
        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }
        if (tasks.isEmpty()) {
            System.out.println("\tThere is no task with the keyword in the list.");
        }
        System.out.println(DIVIDER + NEWLINE);
    }

    public void bye() {
        System.out.println(DIVIDER);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(DIVIDER + NEWLINE);
    }
}