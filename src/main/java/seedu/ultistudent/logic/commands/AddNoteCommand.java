package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_NOTE_NAME;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.note.Note;

/**
 * Adds a Note to the NotesManager.
 */
public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "add-note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note"
            + " to the UltiStudent Notes Manager. \n"
            + "Parameters: "
            + PREFIX_MODULECODE + "MODULE_CODE "
            + PREFIX_NOTE_NAME + "NOTE NAME \n"

            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULECODE + "CS2103T "
            + PREFIX_NOTE_NAME + "Week 7 Notes";

    public static final String MESSAGE_SUCCESS = "New note added: %1$s";
    public static final String MESSAGE_DUPLICATE_NOTE = "This note already exists in the UltiStudent";

    private final Note toAdd;

    /**
     * Creates an AddNoteCommand to add the specified {@code Note}
     */
    public AddNoteCommand(Note note) {
        requireNonNull(note);
        toAdd = note;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasNote(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }

        model.addNote(toAdd);
        model.commitUltiStudent();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals (Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddNoteCommand // instanceof handles nulls
                && toAdd.equals(((AddNoteCommand) other).toAdd));
    }
}
