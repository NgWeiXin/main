package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.note.Note;

/**
 * Save the notes after editing is done
 */
public class SaveNoteCommand extends Command {
    public static final String COMMAND_WORD = "save-note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Saves the current selected note.\n"
            + "There is no parameters for saveNote. "
            + "Error will be prompt if there's no selected note.";

    public static final String MESSAGE_SAVE_NOTE_SUCCESS = "Saved note, %s";
    public static final String MESSAGE_NOT_EDITED = "Note is not edited there is nothing to save!";
    public static final String MESSAGE_NO_NOTE_SELECTED = "There is no note being selected";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        Note editedNote = model.getSelectedNote();

        if (editedNote == null) {
            throw new CommandException(MESSAGE_NO_NOTE_SELECTED);
        }
        int indexOfOldNote = model.getFilteredNoteList().indexOf(model.getSelectedNote());
        Note oldNote = model.getFilteredNoteList().get(indexOfOldNote);
        model.setNote(oldNote, editedNote);
        model.updateFilteredNoteList(Model.PREDICATE_SHOW_ALL_NOTES);
        model.commitUltiStudent();

        return new CommandResult(String.format(MESSAGE_SAVE_NOTE_SUCCESS, editedNote));
    }
}
