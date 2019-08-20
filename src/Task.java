public class Task {
    public String type; //Todo, Deadline, Event
    public String task;
    private boolean done;

    public Task(String type, String task) {
        this.type = type;
        this.task = task;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public boolean checkDone() {
        return this.done;
    }

    private String displayDone() {
        return this.done ? "[" + "\u2713" + "] " //done
                         : "[" + "\u2718" + "] "; //not done
    }

    @Override
    public String toString() {
            return String.format(this.displayDone() + this.task);
    }
}