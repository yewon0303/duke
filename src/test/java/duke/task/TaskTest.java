package duke.task;

import duke.ui.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {

    @Test
    public void displayIsDone_Test_success() {
        assertEquals("[✓] ", new Todo(true, "return book").displayIsDone());
        assertEquals("[✘] ", new Todo(false, "return book").displayIsDone());
    }

    @Test
    public void saveTask_Test_success() throws DukeException {
        assertEquals("T | 0 | eat dinner",
                new Todo(false, "eat dinner").saveTask());
        assertEquals("D | 1 | play tennis | 02/02/2019 2000",
                new Deadline(true, "play tennis", "02/02/2019 2000").saveTask());
    }
}
