public class Deadline extends Task {
    //Task of type Deadline
    //to be done before a specific date/time
    public String by;

    public Deadline(String[] parsedTask) {
        //index 0 carry task
        //index 1 carry deadline
        super("Deadline", parsedTask[0]); //parent constructor
        this.by = parsedTask[1].substring(3); //without "/by "
    }

    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + this.by + ")";
    }
}