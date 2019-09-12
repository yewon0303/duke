package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.ui.DukeException;

public class Parser {

    public Parser() {
    }

    /**
     * Parses input by user into Command, and other relevant details
     * such as the deadline, event date, or delete task index.
     *
     * @param fullCommand entered by the user
     * @return Command
     */
    public Command parse(String fullCommand) {
        String[] split = fullCommand.split(" ");
        String first = split[0];
        Command command = new Command();
        try {
            if (split.length == 1) {
                return oneWordCommandParser(fullCommand);
            } else {
                return multWordsCommandParser(fullCommand, split);
            }
        } catch (DukeException de) {
            System.out.println(de);
        }
        return command;
    }

    /**
     * Parses one word command input by user such as Undo, Bye, List.
     *
     * @param command
     * @return command to be returned to main parse function
     * @throws DukeException
     */
    private Command oneWordCommandParser(String command) throws DukeException {
        Command returnCommand = new Command();
        switch (command) {
            case "undo":
                returnCommand = new UndoCommand();
                break;
            case "bye":
                returnCommand = new ByeCommand();
                break;
            case "list":
                returnCommand = new ListCommand();
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means.");
        }
        return returnCommand;
    }

    /**
     * Parses commands consisting of additional details, such as
     * "find findIndex", "view viewIndex", "todo Task", "deadline Task /by deadline", "event Task /at date",
     * "done doneIndex", "delete deleteIndex".
     *
     * @param fullCommand
     * @param split
     * @return command to be returned to the main parse function
     * @throws DukeException
     */
    private Command multWordsCommandParser(String fullCommand, String[] split) throws DukeException {
        String keyword = split[0];
        Command returnCommand = new Command();
        switch(keyword) {
            case "find": //find all tasks containing keyword
                String searchWord = split[1];
                returnCommand = new FindCommand(searchWord);
                break;
            case "view":
                int searchTask = Integer.parseInt(split[1]);
                returnCommand = new ViewCommand(searchTask);
                break;
            case "todo": //create Todo Task
                returnCommand = addCommandParser("todo", fullCommand, split);
                break;
            case "deadline": //create Deadline Task
                returnCommand = addCommandParser("deadline", fullCommand, split);
                break;
            case "event": //create Event Task
                returnCommand = addCommandParser("event", fullCommand, split);
                break;
            case "done": //mark Task done
                if (split.length != 2) {
                    throw new DukeException("Please enter in the format done [task index]");
                } else {
                    TaskList.addCommand(CommandEnum.Commands.DONE);
                    int doneTask = Integer.parseInt(split[1]) - 1;
                    returnCommand = new DoneCommand(doneTask);
                    break;
                }
            case "delete": //delete Task
                if (split.length != 2) {
                    throw new DukeException("Please enter in the format delete [task index]");
                } else {
                    TaskList.addCommand(CommandEnum.Commands.DELETE);
                    int wantDelete = Integer.parseInt(split[1]) - 1;
                    returnCommand = new DeleteCommand(wantDelete);
                    break;
                }
            default: throw new DukeException("I'm sorry, I don't understand you.");
        }
        return returnCommand;
    }

    /**
     * Parses commands input by user that results in addition to tehe current TaskList.
     *
     * @param type
     * @param fullCommand
     * @param split
     * @return command to be returned to the main parse function
     * @throws DukeException
     */
    private Command addCommandParser(String type, String fullCommand, String[] split) throws DukeException {
        Command returnCommand = new Command();
        switch (type) {
            case "todo": //Todo Task
                TaskList.addCommand(CommandEnum.Commands.ADD);
                Todo newTodo = new Todo(fullCommand.substring(5));
                returnCommand = new AddCommand(newTodo);
                break;
            case "deadline": //Deadline Task
                String[] parsedTask = fullCommand.substring(9).split("/by");
                if (parsedTask.length != 2) {
                    throw new DukeException("Please enter deadline in the format /by [deadline]");
                } else {
                    TaskList.addCommand(CommandEnum.Commands.ADD);
                    Deadline newDeadline = new Deadline(parsedTask);
                    returnCommand = new AddCommand(newDeadline);
                    break;
                }
            case "event": //Event Task
                String[] parsedTask1 = fullCommand.substring(6).split("/at");
                if (parsedTask1.length != 2) {
                    throw new DukeException("Please enter details in the format /at [date or time]");
                } else {
                    TaskList.addCommand(CommandEnum.Commands.ADD);
                    Event newEvent = new Event(parsedTask1);
                    returnCommand = new AddCommand(newEvent);
                    break;
                }
            default: throw new DukeException("I'm sorry, I don't understand you.");
        }
        return returnCommand;
    }
}