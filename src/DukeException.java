public class DukeException extends Exception {
    //class to represent exceptions specific to Duke

    private String errorMessage;

    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "     ☹ OOPS!!! " + this.errorMessage;
    }
}