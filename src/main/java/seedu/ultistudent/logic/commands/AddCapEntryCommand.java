package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULECREDITS;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULEGRADE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_SEMESTER;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleSemester;

/**
 * Adds a Cap Entry to the CapManager.
 */
public class AddCapEntryCommand extends Command {

    public static final String COMMAND_WORD = "add-cap";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a CAP Entry to UltiStudent's CAP Manager.\n"
            + "Parameters: "
            + PREFIX_MODULECODE + "MODULE_CODE "
            + PREFIX_MODULEGRADE + "MODULE_GRADE "
            + PREFIX_MODULECREDITS + "MODULE_CREDITS "
            + PREFIX_SEMESTER + "MODULE_SEMESTER\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULECODE + "CS2103T "
            + PREFIX_MODULEGRADE + "A+ "
            + PREFIX_MODULECREDITS + "4 "
            + PREFIX_SEMESTER + "Y2S2 ";

    public static final String MESSAGE_SUCCESS = "New module added to CAP Manager: %1$s";
    public static final String MESSAGE_DUPLICATE_CAP_ENTRY = "This module already exists in the CAP Manager";

    private final CapEntry toAdd;

    /**
     * Creates an AddCAPEntryCommand to add the specified {@code CapEntry}
     */
    public AddCapEntryCommand(CapEntry capEntry) {
        requireNonNull(capEntry);
        toAdd = capEntry;
    }


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasCapEntry(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CAP_ENTRY);
        }

        //updates module semester
        ModuleSemester toAddModuleSemester = toAdd.getModuleSemester();
        if (!model.hasModuleSemester(toAddModuleSemester)) {
            model.addModuleSemester(toAddModuleSemester);
        }

        model.addCapEntry(toAdd);

        model.commitUltiStudent();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals (Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCapEntryCommand // instanceof handles nulls
            && toAdd.equals(((AddCapEntryCommand) other).toAdd));
    }
}
