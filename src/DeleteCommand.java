public class DeleteCommand extends Command {
    private int wantToDelete;
    private Task deletedTask;

    public DeleteCommand(int wantToDelete) {
        super.carryOn = true;
        this.wantToDelete = wantToDelete;
    }

    private void deleteDone(int size) {
        System.out.println(TextUi.DIVIDER + TextUi.NEWLINE + "\tNoted. I've removed this task: ");
        System.out.println("\t\t" + this.deletedTask);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        System.out.println(TextUi.DIVIDER + TextUi.NEWLINE);
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
