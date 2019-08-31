package duke.task;

import duke.ui.DukeException;

import java.text.SimpleDateFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Date;

public class Event extends Task {
    //Task of type Event
    //starts at a specific time and ends at a specific time
    protected String at;
    protected Date date;
    private SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public Event(String[] parsedTask) throws DukeException {
        //index 0 carry task
        //index 1 carry date/time
        super("Event", parsedTask[0]); //parent constructor
        this.at = parsedTask[1]; //without "/at "
        try {
            this.date = input.parse(at);
        } catch (ParseException pe) {
            throw new DukeException("Parsing error detected.");
        }
    }

    public Event(boolean isDone, String task, String at) throws DukeException {
        super(isDone, "Event", task);
        this.at = at;
        try {
            this.date = input.parse(at);
        } catch (ParseException pe) {
            throw new DukeException("Parsing error detected.");
        }
    }

    public String printDate() {
        Format formatter = new SimpleDateFormat("dd MMMM yyyy hh.mmaa");
        String dateFormatted = formatter.format(this.date);
        return dateFormatted;
    }

    @Override
    public String saveTask() {
        return "E" + super.saveTask() + " | " + this.at;
    }

    @Override
    public String toString() {
        return " [E]" + super.toString() + " (at: " + this.printDate() + ")";
    }
}