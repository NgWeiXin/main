package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.ultistudent.commons.core.Messages;
import seedu.ultistudent.commons.core.index.Index;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.homework.Homework;


/**
 * Deletes a homework identified using it's displayed index from the UltiStudent.
 */
public class DeleteHomeworkCommand extends Command {

    public static final String COMMAND_WORD = "delete-hw";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the homework entry identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_HOMEWORK_SUCCESS = "Deleted Homework: %1$s";

    private final Index targetIndex;

    public DeleteHomeworkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Homework> lastShownList = model.getFilteredHomeworkList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX);
        }

        Homework homeworkToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteHomework(homeworkToDelete);
        model.commitUltiStudent();
        return new CommandResult(String.format(MESSAGE_DELETE_HOMEWORK_SUCCESS, homeworkToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteHomeworkCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteHomeworkCommand) other).targetIndex)); // state check
    }
}
