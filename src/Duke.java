import java.util.Scanner;
import java.util.ArrayList;
import java.text.ParseException;

public class Duke {
    private static ArrayList<Task> commands = new ArrayList<>(100);
    private static String horizontalLine =  "    ____________________________________________________________";

    private void greeting() {
        System.out.println(horizontalLine);
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println(horizontalLine + "\n");
    }

    private void commandHandler() {
        Scanner scn = new Scanner(System.in);
        //assume no more than 100 tasks
        boolean carryOn = true;
            while (carryOn) {
                String command = scn.nextLine();
                String[] parsed = command.split(" ");
                String first = parsed[0];
                try {
                if (parsed.length == 1) {
                    switch (command) {
                        case "bye": bye();
                            carryOn = false; //exit
                            break;
                        case "list": listAll(commands);
                            break;
                        case "todo": throw new DukeException("The description of a todo cannot be empty.");
                        case "deadline": throw new DukeException("The description of a deadline cannot be empty.");
                        default: throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                } else {
                    //new task created or marked done
                    switch(first) {
                        case "todo": //create todo Task
                            Todo newTodo = new Todo(command.substring(5));
                            addNow(newTodo);
                            break;
                        case "deadline": //create deadline Task
                            String[] parsedTask = command.substring(9).split("/by");
                            if (parsedTask.length != 2) {
                                throw new DukeException("Please enter deadline in the format /by [deadline]");
                            } else {
                                Deadline newDeadline = new Deadline(parsedTask);
                                addNow(newDeadline);
                                break;
                            }
                        case "event": //create event Task
                            String[] parsedTask1 = command.substring(6).split("/at");
                            if (parsedTask1.length != 2) {
                                throw new DukeException("Please enter date or time in the format /at [date or time]");
                            } else {
                                Event newEvent = new Event(parsedTask1);
                                addNow(newEvent);
                                break;
                            }
                        case "done": //mark event as done by the task index
                            if (parsed.length != 2) {
                                throw new DukeException("Please enter in the format done [task index]");
                            } else {
                                int doneTask = Integer.parseInt(parsed[1]);
                                done(doneTask);
                                break;
                            }
                        case "delete": //delete a task by the task index
                                    if (parsed.length!= 2) {
                                        throw new DukeException("Please enter in the format delete [task index]");
                                    } else {
                                        int wantDelete = Integer.parseInt(parsed[1]) - 1;
                                        deleteTask(wantDelete);
                                        break;
                                    }
                        default: throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                }
            }
            catch (DukeException dukeE) {
                System.out.println(horizontalLine);
                System.out.println(dukeE);
                System.out.println(horizontalLine + "\n");
                continue;
            }
        }
    }

    private void addNow(Task newTask) {
        commands.add(newTask); //add new Task to the list of tasks
        //echo the command entered by the user
        System.out.println(horizontalLine + "\n     Got it. I've added this task:");
        System.out.println("       " + newTask);
        System.out.println("     Now you have " + commands.size() + " tasks in the list.");
        System.out.println(horizontalLine + "\n");
    }

    private void listAll(ArrayList<Task> commands) {
        System.out.println(horizontalLine);
        System.out.println("    Here are the tasks in your list: ");
        for (int i = 0; i < commands.size(); i++) {
            System.out.println("    " + (i + 1) + "." + commands.get(i));
        }
        System.out.println(horizontalLine);
    }

    private void done(int doneTask) throws DukeException {
        try {
            Task now = commands.get(doneTask - 1);
            now.markDone();
            System.out.println(horizontalLine);
            System.out.println("    Nice! I've marked this task as done: ");
            System.out.println("    " + now);
            System.out.println(horizontalLine);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("The task you want to mark done does not exist.");
        }
    }

    private void deleteTask(int wantDelete) throws DukeException {
        try {
            Task taskToDelete = commands.remove(wantDelete);
            System.out.println(horizontalLine + "\n     Noted. I've removed this task:");
            System.out.println("       " + taskToDelete);
            System.out.println("     Now you have " + commands.size() + " tasks in the list.");
            System.out.println(horizontalLine + "\n");
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("The task you want to delete does not exist.");
        }
    }

    private void bye() {
        //exit when the user types bye
        System.out.println(horizontalLine);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
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
