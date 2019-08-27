import java.text.SimpleDateFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Date;

public class Deadline extends Task {
    //Task of type Deadline
    //to be done before a specific date/time
    protected String by;
    protected Date date;
    private SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public Deadline(String[] parsedTask) throws DukeException {
        //index 0 carry task
        //index 1 carry deadline
        super("Deadline", parsedTask[0]); //parent constructor
        this.by = parsedTask[1]; //without "/by "
        try {
            this.date = input.parse(by);
        } catch (ParseException pe) {
            throw new DukeException("Parsing error detected.");
        }
    }

    public Deadline(boolean isDone, String task, String by) throws DukeException {
        super(isDone, "Deadline", task);
        this.by = by;
        try {
            this.date = input.parse(by);
        } catch (ParseException pe) {
            throw new DukeException("Parsing error detected.");
        }
    }

    private String printDate() {
        Format formatter = new SimpleDateFormat("dd MMMM yyyy hh.mmaa");
        String dateFormatted = formatter.format(this.date);
        return dateFormatted;
    }

    @Override
    public String saveTask() {
        return "D" + super.saveTask() + " | " + this.by;
    }

    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + this.printDate() + ")";
    }
}