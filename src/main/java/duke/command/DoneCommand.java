package duke.command;

import duke.task.Task;
import duke.TaskList;
import duke.ui.DukeException;

public class DoneCommand extends Command {
    protected int doneTaskNum;

    public DoneCommand(int doneTaskNum) {
        assert doneTaskNum >= 0 : "the index should not be negative";
        super.carryOn = true;
        this.doneTaskNum = doneTaskNum;
    }

    private String doneDone(Task doneTask) {
        String rtn = "Nice! I've marked this task as done: " + doneTask;
        return rtn;
    }

    @Override
    public String execute(TaskList taskList) {
        try {
            Task doneTask = taskList.get(this.doneTaskNum);
            doneTask.markIsDone();
            return doneDone(doneTask);
        } catch (IndexOutOfBoundsException ex) {
            return (new DukeException("The task you want to mark done does not exist.")).toString();
        }
    }
}
