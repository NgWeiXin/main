package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.UltiStudent;

/**
 * Clears the UltiStudent.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "UltiStudent has been cleared!";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.setUltiStudent(new UltiStudent());
        model.commitUltiStudent();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
