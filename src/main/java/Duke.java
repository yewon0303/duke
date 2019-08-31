import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.command.Command;
import duke.ui.TextUi;

public class Duke {
    private TextUi ui;
    private Storage storage;
    private TaskList taskList;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run(args);
    }

    public void run(String[] args) {
        start(args);
        commandHandler();
        exit();
    }

    private void start(String[] args) {
        this.ui = new TextUi();
        this.storage = new Storage();
        this.taskList = storage.load();
        ui.greeting();
    }

    private void commandHandler() {
        boolean carryOn = true;
        while (carryOn) {
            String nextCommand = ui.readCommand();
            Parser parser = new Parser();
            Command command = parser.parse(nextCommand);
            command.execute(this.taskList);
            storage.update(this.taskList);
            carryOn = command.getCarryOn();
        }
    }

    private void exit() {
        ui.bye();
        System.exit(0);
    }
}
