import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.command.Command;
import duke.ui.TextUi;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class Duke extends Application {
    public TextUi ui;
    public Storage storage;
    public TaskList taskList;

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
/*
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run(args);
    }*/

    /**
     * Runs the main method and exits the program once the end is reached.
     */
    /*
    public void run(String[] args) {
        //go(args);
        commandHandler();
        exit();
    }

    private String go() {
        this.ui = new TextUi();
        this.storage = new Storage();
        this.taskList = storage.load();
        return ui.greeting();
    }*/

    /**
     * Handles the commands entered by the user and executes relevant commands
     * until the end of the program.
     */
    /*
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
    }*/

    private void exit() {
        ui.bye();
        System.exit(0);
    }

    @Override
    public void start(Stage stage) {

        //Step 1. Setting up required components
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setPadding(new Insets(20, 10, 20, 10));
        dialogContainer.setBackground(new Background(
                        new BackgroundFill(Color.web("#c0c0c0"),
                                            new CornerRadii(5),
                                            Insets.EMPTY)));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
//    private Label getDialogLabel(String text) {
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));

        //Add padding to each of the Labels
        userText.setPadding(new Insets(10, 8, 10, 8));
        dukeText.setPadding(new Insets(10, 8, 10, 8));

        //Add background color to each of the dialog boxes
        userText.setBackground(new Background(
                new BackgroundFill(
                        Color.web("#e1c1c1"),
                        new CornerRadii(10),
                        new Insets(5, 0, 10, 0))));
        dukeText.setBackground(new Background(
                new BackgroundFill(
                        Color.web("#e0f0e0"),
                        new CornerRadii(10),
                        new Insets(5, 0, 10, 0))));

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText.toString(), user),
                DialogBox.getDukeDialog(dukeText.toString(), Duke)
        );
        userInput.clear();
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
