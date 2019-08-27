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
    public static final String DEFAULT_STORAGE_PATH = "../src/dukeTaskList.txt";

    public Storage() {
        //this(DEFAULT_STORAGE_PATH);
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> rtn = new ArrayList<>();
        //no prior data found in hard drive
        //if (!Files.exists(DEFAULT_STORAGE_PATH)) {
            //return the new ArrayList<Task>
        //}
        //prior data found in hard drive
        try {
            File file = new File(DEFAULT_STORAGE_PATH);
            Scanner scn = new Scanner(file);
            while (scn.hasNext()) {
                Task now = fileDecoder(scn.nextLine());
                rtn.add(now);
            }
        } catch (FileNotFoundException fnfe) {
            throw new DukeException("File cannot be found.");
        }
        return rtn;
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
            default: throw new DukeException("File is corrupt.");
        }
        return task;
    }

    private boolean checkIsDone(String num) {
        return num.equals("1"); //returns true if 1 (done)
    }

    public void update(ArrayList<Task> commands) throws IOException {
        FileWriter fw = new FileWriter(DEFAULT_STORAGE_PATH);
        String text = "";
        for (Task now : commands) {
            text += now.saveTask() + "\n";
        }
        fw.write(text);
        fw.close();
    }
}