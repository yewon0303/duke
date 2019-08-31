package duke;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.ui.DukeException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task newTask) {
        this.taskList.add(newTask);
    }

    public Task remove(int wantToRemove) {
        return this.taskList.remove(wantToRemove);
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task get(int wantToGet) {
        return this.taskList.get(wantToGet);
    }

    public ArrayList<Task> getArrayList() {
        return this.taskList;
    }

    //from txt file to TaskList
    public TaskList decodeTaskList(ArrayList<String> fullString) throws DukeException {
        for (String line : fullString) {
            String[] parsed = line.split("|");
            Task newTask = taskDecoder(parsed);
            this.taskList.add(newTask);
        }
        return new TaskList(this.taskList);
    }

    private boolean doneCheck(String num) {
        //returns true if done (1)
        return num.equals("1");
    }

    private Task taskDecoder(String[] parsed) throws DukeException {
        String command = parsed[0];
        if (parsed.length < 3) {
            throw new DukeException("Insufficient information provided.");
        } else {
            boolean isDone = doneCheck(parsed[1]);
            if (command.equals("T") && parsed.length == 3) {
                Task todo = new Todo(isDone, parsed[2]);
                return todo;
            } else if (command.equals("D") && parsed.length == 4) {
                Task deadline = new Deadline(isDone, parsed[2], parsed[3]);
                return deadline;
            } else if (command.equals("E") && parsed.length == 4) {
                Task event = new Event(isDone, parsed[2], parsed[3]);
                return event;
            } else {
                throw new DukeException("Task not recognised by the program!");
            }
        }
    }

    @Override
    public String toString() {
        String rtn = "";
        for (Task task : this.taskList) {
            rtn += "\n" + task;
        }
        return rtn;
    }

}

