public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task newTask) {
        super.carryOn = true;
        this.newTask = newTask;
    }

    private void addDone(int size) {
        System.out.println(TextUi.DIVIDER + TextUi.NEWLINE + "\tGot it. I've added this task: ");
        System.out.println("\t" + this.newTask);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        System.out.println(TextUi.DIVIDER + TextUi.NEWLINE);
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.add(this.newTask);
        addDone(taskList.getSize());
    }

}
