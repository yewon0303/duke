public class Command {
    protected boolean carryOn;
    protected TaskList taskList;

    public Command() {
    }

    public void execute(TaskList taskList) {
    }

    public boolean getCarryOn() {
        return this.carryOn;
    }

}