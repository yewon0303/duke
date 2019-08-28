package duke.task;

import duke.ui.DukeException;

public class Todo extends Task {
    //Task of type Todo
    //without any date/time attached to it

    public Todo(String task) {
        super("Todo", task);
    }

    public Todo(boolean isDone, String task) {
        super(isDone, "Todo", task);
    }

    @Override
    public String saveTask() {
        return "T" + super.saveTask();
    }

    @Override
    public String toString() {
        return " [T]" + super.toString();
    }
}