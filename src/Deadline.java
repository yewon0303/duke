public class Deadline extends Task {
    //Task of type Deadline
    //to be done before a specific date/time
    protected String by;

    public Deadline(String[] parsedTask) {
        //index 0 carry task
        //index 1 carry deadline
        super("Deadline", parsedTask[0]); //parent constructor
        this.by = parsedTask[1].substring(3); //without "/by "
    }

    public Deadline(boolean isDone, String task, String by) {
        super(isDone, "Deadline", task);
        this.by = by;
    }

    @Override
    public String saveTask() {
        return "D" + super.saveTask() + " | " + this.by;
    }

    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + this.by + ")";
    }
}