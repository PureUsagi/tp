package seedu.binbash.ui;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.EndOfFileException;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import seedu.binbash.logger.BinBashLogger;

import java.io.IOException;

/**
 * The user facing text interface of the program.
 */
public class Ui {
    private static final String NEWLINE = System.lineSeparator();
    private static final String LOGO = "  ____  _       ____            _" + NEWLINE +
            " | __ )(_)_ __ | __ )  __ _ ___| |__" + NEWLINE +
            " |  _ \\| | '_ \\|  _ \\ / _` / __| '_ \\" + NEWLINE +
            " | |_) | | | | | |_) | (_| \\__ \\ | | |" + NEWLINE +
            " |____/|_|_| |_|____/ \\__,_|___/_| |_|" + NEWLINE + NEWLINE;
    private static final String WELCOME_MESSAGE = "Welcome to BinBash!";
    private static final String LINE_DIVIDER = "-------------------------------------------------------------";
    private static final BinBashLogger UILOGGER = new BinBashLogger(Ui.class.getName());

    private static LineReader inputReader;
    private static boolean isUserActive;

    /**
     * Note this constructor need only be called once as there should be no more than a single
     * instance of UI per program run.
     *
     * @return The current instance of Ui.
     * @throws RuntimeException If fails to get an instance of the system terminal.
     */
    public Ui() {
        System.setProperty("org.jline.terminal.exec.redirectPipeCreationMode", "native");
        try {
            Terminal userTerminal = TerminalBuilder.builder()
                .system(true)
                .dumb(true)
                .build();
            inputReader = LineReaderBuilder.builder()
                .terminal(userTerminal)
                .completer(new CommandCompleter())
                .build();
        } catch (IOException e) {
            UILOGGER.info("failed to get system terminal!");
            throw new RuntimeException(e);
        }
        isUserActive = true;
    }

    public boolean isUserActive() {
        return isUserActive;
    }

    public void setUserAsInactive() {
        isUserActive = false;
    }

    /**
     * Returns a string received by the user.
     *
     * @return "bye" if end of file or program termination detected, a string read from standard input otherwise.
     */
    public String readUserCommand() {
        assert isUserActive();
        try {
            String userInput = inputReader.readLine("binbash> ");
            UILOGGER.info("received raw user input: " + userInput);
            return userInput;
        } catch (EndOfFileException | UserInterruptException e) {
            UILOGGER.info("received EOF / interrupt exception");
            return "bye";
        }
    }

    /**
     * Prints a greeting message to standard output.
     */
    public void greet() {
        talk(NEWLINE + LOGO + WELCOME_MESSAGE);
    }

    /**
     * Prints text to standard output as an explicit response or acknowledgement of some user command.
     *
     * @param line The text to print.
     */
    public void talk(String line) {
        System.out.println(LINE_DIVIDER + NEWLINE + line + NEWLINE + LINE_DIVIDER);
    }
}
