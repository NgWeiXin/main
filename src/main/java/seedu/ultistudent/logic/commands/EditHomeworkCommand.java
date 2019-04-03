package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.ultistudent.model.Model.PREDICATE_SHOW_ALL_HOMEWORK;

import java.util.List;
import java.util.Optional;

import seedu.ultistudent.commons.core.Messages;
import seedu.ultistudent.commons.core.index.Index;
import seedu.ultistudent.commons.util.CollectionUtil;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.homework.Date;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.homework.HomeworkName;
import seedu.ultistudent.model.modulecode.ModuleCode;

/**
 * Edits the details of a homework entry in the HomeworkManager.
 */
public class EditHomeworkCommand extends Command {

    public static final String COMMAND_WORD = "edit-hw";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edi"
            + "ts the details of the homework "
            + "by the index number used in the displayed homework list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MODULECODE + "MODULECODE] "
            + "[" + PREFIX_HOMEWORK + "HOMEWORK] "
            + "[" + PREFIX_DEADLINE + "DEADLINE] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MODULECODE + "CS2103T "
            + PREFIX_HOMEWORK + "Tutorial 5";

    public static final String MESSAGE_EDIT_HOMEWORK_SUCCESS = "Edited homework: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_HOMEWORK = "This homework already exists in the UltiStudent.";

    private final Index index;
    private final EditHomeworkDescriptor editHomeworkDescriptor;

    /**
     * @param index of the homework in the filtered homework list to edit
     * @param editHomeworkDescriptor details to edit the homework with
     */
    public EditHomeworkCommand(Index index, EditHomeworkDescriptor editHomeworkDescriptor) {
        requireNonNull(index);
        requireNonNull(editHomeworkDescriptor);

        this.index = index;
        this.editHomeworkDescriptor = new EditHomeworkDescriptor(editHomeworkDescriptor);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Homework> lastShownList = model.getFilteredHomeworkList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX);
        }

        Homework homeworkToEdit = lastShownList.get(index.getZeroBased());
        Homework editedHomework = createEditedHomework(homeworkToEdit, editHomeworkDescriptor);

        if (!homeworkToEdit.equals(editedHomework) && model.hasHomework(editedHomework)) {
            throw new CommandException(MESSAGE_DUPLICATE_HOMEWORK);
        }

        model.setHomework(homeworkToEdit, editedHomework);
        model.updateFilteredHomeworkList(PREDICATE_SHOW_ALL_HOMEWORK);
        model.commitUltiStudent();
        return new CommandResult(String.format(MESSAGE_EDIT_HOMEWORK_SUCCESS, editedHomework));
    }

    /**
     * Creates and returns a {@code Homework} with the details of {@code homeworkToEdit}
     * edited with {@code editHomeworkDescriptor}.
     */
    private static Homework createEditedHomework(Homework homeworkToEdit,
                                                 EditHomeworkDescriptor editHomeworkDescriptor) {
        assert homeworkToEdit != null;

        ModuleCode updatedModuleCode = editHomeworkDescriptor.getModuleCode().orElse(homeworkToEdit.getModuleCode());
        HomeworkName updatedHomeworkName = editHomeworkDescriptor.getHomeworkName()
                                                                 .orElse(homeworkToEdit.getHomeworkName());
        Date updatedDeadline = editHomeworkDescriptor.getDeadline().orElse(homeworkToEdit.getDeadline());

        return new Homework(updatedModuleCode, updatedHomeworkName, updatedDeadline);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditHomeworkCommand e = (EditHomeworkCommand) other;
        return index.equals(e.index)
                && editHomeworkDescriptor.equals(e.editHomeworkDescriptor);
    }

    /**
     * Stores the details to edit the homework with. Each non-empty field value will replace the
     * corresponding field value of the homework.
     */
    public static class EditHomeworkDescriptor {
        private ModuleCode moduleCode;
        private HomeworkName homeworkName;
        private Date deadline;

        public EditHomeworkDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditHomeworkDescriptor(EditHomeworkDescriptor toCopy) {
            setModuleCode(toCopy.moduleCode);
            setHomeworkName(toCopy.homeworkName);
            setDeadline(toCopy.deadline);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleCode, homeworkName, deadline);
        }

        public void setModuleCode(ModuleCode moduleCode) {
            this.moduleCode = moduleCode;
        }

        public Optional<ModuleCode> getModuleCode() {
            return Optional.ofNullable(moduleCode);
        }

        public void setHomeworkName(HomeworkName homeworkName) {
            this.homeworkName = homeworkName;
        }

        public Optional<HomeworkName> getHomeworkName() {
            return Optional.ofNullable(homeworkName);
        }

        public void setDeadline(Date deadline) {
            this.deadline = deadline;
        }

        public Optional<Date> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditHomeworkDescriptor)) {
                return false;
            }

            // state check
            EditHomeworkDescriptor e = (EditHomeworkDescriptor) other;

            return getModuleCode().equals(e.getModuleCode())
                    && getHomeworkName().equals(e.getHomeworkName())
                    && getDeadline().equals(e.getDeadline());
        }
    }
}
