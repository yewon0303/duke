package duke.command;

import duke.task.Task;
import duke.TaskList;
import duke.ui.DukeException;
import duke.ui.TextUi;

import static duke.ui.TextUi.DIVIDER;
import static duke.ui.TextUi.NEWLINE;

public class DeleteCommand extends Command {
    private int wantToDelete;
    private Task deletedTask;

    public DeleteCommand(int wantToDelete) {
        super.carryOn = true;
        this.wantToDelete = wantToDelete;
    }

    private void deleteDone(int size) {
        System.out.println(DIVIDER + NEWLINE + "\tNoted. I've removed this task: ");
        System.out.println("\t\t" + this.deletedTask);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        System.out.println(DIVIDER + NEWLINE);
    }

    @Override
    public void execute(TaskList taskList) {
        try {
            this.deletedTask = taskList.remove(this.wantToDelete);;
            deleteDone(taskList.getSize());
        } catch (IndexOutOfBoundsException ex) {
            TextUi.show(new DukeException("The task you want to delete does not exist."));
        }
    }
}
