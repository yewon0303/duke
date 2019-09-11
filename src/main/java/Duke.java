import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.command.Command;
import duke.ui.TextUi;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;


public class Duke {
    private static TextUi ui;
    private static Storage storage;
    private static TaskList taskList;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/olivia.jpg"));
    private Image Duke = new Image(this.getClass().getResourceAsStream("/images/dukeOrsino.png"));

    public Duke() {
        this.ui = new TextUi();
        this.storage = new Storage();
        this.taskList = storage.load();
    }

    String getResponse(String input) {
        String nextCommand = input;
        Parser parser = new Parser();
        Command command = parser.parse(nextCommand);
        String rtn = command.execute(this.taskList);
        this.storage.update(this.taskList);
        return rtn;
    }

}
