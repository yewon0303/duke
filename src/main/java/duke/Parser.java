package duke;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.ui.DukeException;

public class Parser {

    public Parser() {
    }

    public Command parse(String fullCommand) {
        String[] split = fullCommand.split(" ");
        String first = split[0];
        Command command = new Command();
        try {
            if (split.length == 1) {
                switch (fullCommand) {
                case "bye": //ByeCommend
                    command = new ByeCommand();
                    break;
                case "list": //ListCommand
                    command = new ListCommand();
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
                return command;
            } else {
                switch (first) {
                case "todo": //create Todo Task
                    Todo newTodo = new Todo(fullCommand.substring(5));
                    command = new AddCommand(newTodo);
                    break;
                case "deadline": //create Deadline Task
                    String[] parsedTask = fullCommand.substring(9).split("/by");
                    if (parsedTask.length != 2) {
                        throw new DukeException("Please enter deadline in the format /by [deadline]");
                    } else {
                        Deadline newDeadline = new Deadline(parsedTask);
                        command = new AddCommand(newDeadline);
                        break;
                    }
                case "event": //create event Task
                    String[] parsedTask1 = fullCommand.substring(6).split("/at");
                    if (parsedTask1.length != 2) {
                        throw new DukeException("Please enter date or time in the format /at [date or time]");
                    } else {
                        Event newEvent = new Event(parsedTask1);
                        command = new AddCommand(newEvent);
                        break;
                    }
                case "done": //mark event as done by the task index
                    if (split.length != 2) {
                        throw new DukeException("Please enter in the format done [task index]");
                    } else {
                        int doneTask = Integer.parseInt(split[1]) - 1;
                        command = new DoneCommand(doneTask);
                        break;
                    }
                case "delete": //delete a task by the task index
                    if (split.length != 2) {
                        throw new DukeException("Please enter in the format delete [task index]");
                    } else {
                        int wantDelete = Integer.parseInt(split[1]) - 1;
                        command = new DeleteCommand(wantDelete);
                        break;
                    }
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (DukeException de) {
            System.out.println(de);
        }
        return command;
    }
}