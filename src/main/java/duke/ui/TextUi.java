package duke.ui;

import duke.TaskList;
import duke.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class TextUi {
    public static final String DIVIDER =  "";
    public static final String NEWLINE = "\n";

    public TextUi() {
    }

    /**
     * Prints the welcome message including the logo, Hello greeting
     * and the horizontal line before and after.
     */
    public static String greeting() {
        String rtn = "";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        rtn += "Hello from" + NEWLINE + logo
                + "Hello! I'm Duke" + NEWLINE + "What can I do for you?";
        return rtn;
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
    public static String findCommandPrint(ArrayList<Task> tasks) {
        String rtn = DIVIDER + NEWLINE + "\tHere are the matching tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            rtn +=  (i + 1) + "." + tasks.get(i) + NEWLINE;
        }
        if (tasks.isEmpty()) {
            rtn += "There is no task with the keyword in the list.";
        }
        return rtn;
    }

    public static String bye() {
        return "Bye. Hope to see you again soon!";
    }
}