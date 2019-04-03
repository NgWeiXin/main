package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;

/**
 * Reverts the {@code model}'s UltiStudent to its previously undone state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo success!";
    public static final String MESSAGE_FAILURE = "No more commands to redo!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoUltiStudent()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.redoUltiStudent();
        model.updateFilteredHomeworkList(Model.PREDICATE_SHOW_ALL_HOMEWORK);
        model.updateFilteredNoteList(Model.PREDICATE_SHOW_ALL_NOTES);
        model.updateFilteredModuleSemesterList(Model.PREDICATE_SHOW_ALL_MODULE_SEMESTERS);
        model.updateFilteredCapEntryList(Model.PREDICATE_SHOW_ALL_CAP_ENTRIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
