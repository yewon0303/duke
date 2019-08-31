package duke.task;

import duke.ui.DukeException;

public class Task {
    public String type; //Todo, Deadline, Event
    public String task;
    private boolean isDone;

    public Task(String type, String task) {
        this.type = type;
        this.task = task;
        this.isDone = false;
    }

    public Task(boolean isDone, String type, String task) {
        this.isDone = isDone;
        this.type = type;
        this.task = task;
    }

    public void markIsDone() {
        this.isDone = true;
    }

    public boolean checkIsDone() {
        return this.isDone;
    }

    public String displayIsDone() {
        return this.isDone
                ? "[" + "\u2713" + "] " //done
                : "[" + "\u2718" + "] "; //not done
    }

    private String isDoneInt(boolean isDone) {
        return isDone ? "1" : "0";
    }

    //for saving the task into hard disc
    public String saveTask() {
        return String.format(" | " + isDoneInt(this.isDone) +
                " | " + this.task);
    }

    @Override
    public String toString() {
            return String.format(this.displayIsDone() + this.task);
    }
}