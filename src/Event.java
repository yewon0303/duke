public class Event extends Task {
    //Task of type Event
    //starts at a specific time and ends at a specific time
    public String at;

    public Event(String[] parsedTask) {
        //index 0 carry task
        //index 1 carry date/time
        super("Event", parsedTask[0]); //parent constructor
        this.at = parsedTask[1].substring(3); //without "/at "
    }

    @Override
    public String toString() {
        return " [E]" + super.toString() + " (at: " + this.at + ")";
    }
}