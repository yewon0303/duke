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
import duke.ui.TextUi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String DEFAULT_STORAGE_PATH = "../java/dukeTaskList.txt";

    public Storage() {
    }

    public TaskList load() {
        ArrayList<Task> rtn = new ArrayList<>();
        try {
            File file = new File(DEFAULT_STORAGE_PATH);
            Scanner scn = new Scanner(file);
            while (scn.hasNext()) {
                Task now = fileDecoder(scn.nextLine());
                rtn.add(now);
            }
        } catch (FileNotFoundException fnfe) {
            TextUi.show(new DukeException("File cannot be found."));
        } catch (DukeException de) {
            TextUi.show(de);
        }
        return new TaskList(rtn);
    }

    private Task fileDecoder(String line) throws DukeException {
        String[] split = line.split(" \\| ");
        String first = split[0];
        Task task;
        boolean isDone = checkIsDone(split[1]);
            switch (first) {
                case ("T"): //Todo Task
                    Todo newTodo = new Todo(isDone, split[2]);
                    task = newTodo;
                    break;
                case ("D"): //Deadline Task
                    Deadline newDeadline = new Deadline(isDone, split[2], split[3]);
                    task = newDeadline;
                    break;
                case ("E"): //Event Task
                    Event newEvent = new Event(isDone, split[2], split[3]);
                    task = newEvent;
                    break;
                default: //File cannot be read
                    throw new DukeException("The file is corrupt!");
            }
        return task;
    }

    private boolean checkIsDone(String num) {
        return num.equals("1"); //returns true if 1 (done)
    }

    public void update(TaskList commands) {
        try {
            FileWriter fw = new FileWriter(DEFAULT_STORAGE_PATH);
            String text = "";
            ArrayList<Task> commandsArrayList = commands.getArrayList();
            for (Task now : commandsArrayList) {
                text += now.saveTask() + "\n";
            }
            fw.write(text);
            fw.close();
        } catch (IOException ioe) {
            TextUi.show(new DukeException("IOException detected!"));
        }

    }
}