public class DoneCommand extends Command {
    protected int doneTaskNum;

    public DoneCommand(int doneTaskNum) {
        super.carryOn = true;
        this.doneTaskNum = doneTaskNum;
    }

    private void doneDone(Task doneTask) {
        System.out.println(TextUi.DIVIDER + TextUi.NEWLINE
                + "\tNice! I've marked this task as done: ");
        System.out.println("\t" + doneTask + TextUi.NEWLINE
                + TextUi.DIVIDER);
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
