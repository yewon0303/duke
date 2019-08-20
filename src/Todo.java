public class Todo extends Task {
    //Task of type Todo
    //without any date/time attached to it

    public Todo(String task) {
        super("Todo", task);
    }

    @Override
    public String toString() {
        return " [T]" + super.toString();
    }
}