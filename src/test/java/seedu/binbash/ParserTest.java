package seedu.binbash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import seedu.binbash.command.Command;
import seedu.binbash.command.DeleteCommand;
import seedu.binbash.command.SearchCommand;
import seedu.binbash.command.ListCommand;
import seedu.binbash.command.ByeCommand;
import seedu.binbash.exceptions.InvalidCommandException;
import seedu.binbash.exceptions.InvalidArgumentException;
import seedu.binbash.exceptions.InvalidFormatException;

public class ParserTest {
    private ItemList itemList;
    private Parser parser;

    @BeforeEach
    public void setUp() {
        itemList = new ItemList(new ArrayList<>());
        parser = new Parser(itemList);
    }

    @Test
    public void testParseCommand_validCommandBye_returnsByeCommand() {
        try {
            Command command = parser.parseCommand("bye");
            assertTrue(command instanceof ByeCommand);
        } catch (InvalidCommandException e) {
            fail("Unexpected InvalidCommandException: " + e.getMessage());
        }
    }

    @Test
    public void testParseCommand_invalidCommand_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> parser.parseCommand("invalid"));
    }

    @Test
    public void testParseCommand_validCommandDelete_returnsDeleteCommand() throws InvalidCommandException {
        itemList.addItem("Test Item", "Test Description", 5, "2024-12-31", 10.5, 7.5);
        Command command = parser.parseCommand("delete Test Item");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void testParseCommand_validCommandList_returnsListCommand() throws InvalidCommandException {
        Command command = parser.parseCommand("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void testParseCommand_validCommandSearch_returnsSearchCommand() throws InvalidCommandException {
        Command command = parser.parseCommand("search keyword");
        assertTrue(command instanceof SearchCommand);
    }

    @Test
    public void testParseCommand_invalidAddCommand_throwsInvalidFormatException() {
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand("add invalid format"));
    }

    @Test
    public void testParseCommand_invalidDeleteCommand_throwsInvalidArgumentException() {
        assertThrows(InvalidArgumentException.class, () -> parser.parseCommand("delete -1"));
    }

    @Test
    public void testParseCommand_invalidSearchCommand_throwsInvalidFormatException() {
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand("search"));
    }
}
