package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULECODE;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.homework.Homework;


/**
 * Adds a Homework to the HomeworkManager.
 */
public class AddHomeworkCommand extends Command {

    public static final String COMMAND_WORD = "add-hw";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a homework to UltiStudent's homework manager.\n"
            + "Parameters: "
            + PREFIX_MODULECODE + "MODULECODE "
            + PREFIX_HOMEWORK + "HOMEWORK "
            + PREFIX_DEADLINE + "DEADLINE\n"

            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULECODE + "CS2103T "
            + PREFIX_HOMEWORK + "User Guide Draft 1 "
            + PREFIX_DEADLINE + "dd/mm/yyyy";

    public static final String MESSAGE_SUCCESS = "New homework added: %1$s";
    public static final String MESSAGE_DUPLICATE_HOMEWORK = "This homework already exists in UltiStudent";

    private final Homework toAdd;

    /**
     * Creates an AddHomeworkCommand to add the specified {@code Homework}
     */
    public AddHomeworkCommand(Homework homework) {
        requireNonNull(homework);
        toAdd = homework;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasHomework(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_HOMEWORK);
        }

        model.addHomework(toAdd);
        model.commitUltiStudent();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddHomeworkCommand // instanceof handles nulls
                && toAdd.equals(((AddHomeworkCommand) other).toAdd));
    }
}
