package duke.command;

import duke.task.Task;
import duke.TaskList;
import duke.ui.DukeException;

import static duke.ui.TextUi.DIVIDER;
import static duke.ui.TextUi.NEWLINE;

public class DoneCommand extends Command {
    protected int doneTaskNum;

    public DoneCommand(int doneTaskNum) {
        super.carryOn = true;
        this.doneTaskNum = doneTaskNum;
    }

    private void doneDone(Task doneTask) {
        System.out.println(DIVIDER + NEWLINE
                + "\tNice! I've marked this task as done: ");
        System.out.println("\t" + doneTask + NEWLINE + DIVIDER);
    }

    @Override
    public void execute(TaskList taskList) {
        try {
            Task doneTask = taskList.get(this.doneTaskNum);
            doneTask.markIsDone();
            doneDone(doneTask);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(new DukeException("The task you want to mark done does not exist."));
        }
    }
}
