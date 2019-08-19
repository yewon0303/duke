public class Task {
    public String task;
    private boolean done;

    public Task(String task) {
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