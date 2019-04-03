package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;

/**
 * Reverts the {@code model}'s UltiStudent to its previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo success!";
    public static final String MESSAGE_FAILURE = "No more commands to undo!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoUltiStudent()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undoUltiStudent();
        model.updateFilteredCapEntryList(Model.PREDICATE_SHOW_ALL_CAP_ENTRIES);
        model.updateFilteredModuleSemesterList(Model.PREDICATE_SHOW_ALL_MODULE_SEMESTERS);
        model.updateFilteredHomeworkList(Model.PREDICATE_SHOW_ALL_HOMEWORK);
        model.updateFilteredNoteList(Model.PREDICATE_SHOW_ALL_NOTES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
