package seedu.ultistudent.logic.commands;

import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.ultistudent.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ultistudent.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ultistudent.logic.commands.CommandTestUtil.showHomeworkAtIndex;
import static seedu.ultistudent.testutil.TypicalHomework.getTypicalAddressBook;
import static seedu.ultistudent.testutil.TypicalIndexes.INDEX_FIRST_HOMEWORK;
import static seedu.ultistudent.testutil.TypicalIndexes.INDEX_SECOND_HOMEWORK;

import org.junit.Test;

import seedu.ultistudent.commons.core.Messages;
import seedu.ultistudent.commons.core.index.Index;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.ModelManager;
import seedu.ultistudent.model.UserPrefs;
import seedu.ultistudent.model.homework.Homework;


/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteHomeworkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Homework homeworkToDelete = model.getFilteredHomeworkList().get(INDEX_FIRST_HOMEWORK.getZeroBased());
        DeleteHomeworkCommand deleteHomeworkCommand = new DeleteHomeworkCommand(INDEX_FIRST_HOMEWORK);

        String expectedMessage = String.format(DeleteHomeworkCommand.MESSAGE_DELETE_HOMEWORK_SUCCESS, homeworkToDelete);

        ModelManager expectedModel = new ModelManager(model.getUltiStudent(), new UserPrefs());
        expectedModel.deleteHomework(homeworkToDelete);
        expectedModel.commitUltiStudent();

        assertCommandSuccess(deleteHomeworkCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredHomeworkList().size() + 1);
        DeleteHomeworkCommand deleteHomeworkCommand = new DeleteHomeworkCommand(outOfBoundIndex);

        assertCommandFailure(deleteHomeworkCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showHomeworkAtIndex(model, INDEX_FIRST_HOMEWORK);

        Homework homeworkToDelete = model.getFilteredHomeworkList().get(INDEX_FIRST_HOMEWORK.getZeroBased());
        DeleteHomeworkCommand deleteHomeworkCommand = new DeleteHomeworkCommand(INDEX_FIRST_HOMEWORK);

        String expectedMessage = String.format(DeleteHomeworkCommand.MESSAGE_DELETE_HOMEWORK_SUCCESS, homeworkToDelete);

        Model expectedModel = new ModelManager(model.getUltiStudent(), new UserPrefs());
        expectedModel.deleteHomework(homeworkToDelete);
        expectedModel.commitUltiStudent();
        showNoHomework(expectedModel);

        assertCommandSuccess(deleteHomeworkCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showHomeworkAtIndex(model, INDEX_FIRST_HOMEWORK);

        Index outOfBoundIndex = INDEX_SECOND_HOMEWORK;
        // ensures that outOfBoundIndex is still in bounds of UltiStudent list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUltiStudent().getHomeworkList().size());

        DeleteHomeworkCommand deleteHomeworkCommand = new DeleteHomeworkCommand(outOfBoundIndex);

        assertCommandFailure(deleteHomeworkCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Homework homeworkToDelete = model.getFilteredHomeworkList().get(INDEX_FIRST_HOMEWORK.getZeroBased());
        DeleteHomeworkCommand deleteHomeworkCommand = new DeleteHomeworkCommand(INDEX_FIRST_HOMEWORK);
        Model expectedModel = new ModelManager(model.getUltiStudent(), new UserPrefs());
        expectedModel.deleteHomework(homeworkToDelete);
        expectedModel.commitUltiStudent();

        // delete -> first person deleted
        deleteHomeworkCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered person list to show all persons
        expectedModel.undoUltiStudent();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first person deleted again
        expectedModel.redoUltiStudent();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredHomeworkList().size() + 1);
        DeleteHomeworkCommand deleteHomeworkCommand = new DeleteHomeworkCommand(outOfBoundIndex);

        // execution failed -> UltiStudent state not added into model
        assertCommandFailure(deleteHomeworkCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX);

        // single UltiStudent state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    //    /**
    //     * 1. Deletes a {@code Homework} from a filtered list.
    //     * 2. Undo the deletion.
    //     * 3. The unfiltered list should be shown now. Verify that the index of the previously deleted homework in the
    //     * unfiltered list is different from the index at the filtered list.
    //     * 4. Redo the deletion. This ensures {@code RedoCommand} deletes the homework object regardless of indexing.
    //     */
    //    @Test
    //    public void executeUndoRedo_validIndexFilteredList_sameHomeworkDeleted() throws Exception {
    //        DeleteHomeworkCommand deleteHomeworkCommand = new DeleteHomeworkCommand(INDEX_FIRST_HOMEWORK);
    //        Model expectedModel = new ModelManager(model.getUltiStudent(), new UserPrefs());
    //
    //        showHomeworkAtIndex(model, INDEX_SECOND_HOMEWORK);
    //        Homework homeworkToDelete = model.getFilteredHomeworkList().get(INDEX_FIRST_HOMEWORK.getZeroBased());
    //        expectedModel.deleteHomework(homeworkToDelete);
    //        expectedModel.commitUltiStudent();
    //
    //        // delete -> deletes second homework in unfiltered homework list / first homework in
    //        filtered homework list
    //        deleteHomeworkCommand.execute(model, commandHistory);
    //
    //        // undo -> reverts addressbook back to previous state and filtered homework list to show all homework
    //        expectedModel.undoUltiStudent();
    //        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS,
    //        expectedModel);
    //
    //        assertNotEquals(homeworkToDelete, model.getFilteredHomeworkList().
    //        get(INDEX_FIRST_HOMEWORK.getZeroBased()));
    //        // redo -> deletes same second homework in unfiltered homework list
    //        expectedModel.redoUltiStudent();
    //        assertCommandSuccess(new RedoCommand(), model, commandHistory,
    //        RedoCommand.MESSAGE_SUCCESS, expectedModel);
    //    }

    @Test
    public void equals() {
        DeleteHomeworkCommand deleteFirstHomeworkCommand = new DeleteHomeworkCommand(INDEX_FIRST_HOMEWORK);
        DeleteHomeworkCommand deleteSecondHomeworkCommand = new DeleteHomeworkCommand(INDEX_SECOND_HOMEWORK);

        // same object -> returns true
        assertTrue(deleteFirstHomeworkCommand.equals(deleteFirstHomeworkCommand));

        // same values -> returns true
        DeleteHomeworkCommand deleteFirstHomeworkCommandCopy = new DeleteHomeworkCommand(INDEX_FIRST_HOMEWORK);
        assertTrue(deleteFirstHomeworkCommand.equals(deleteFirstHomeworkCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstHomeworkCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstHomeworkCommand.equals(null));

        // different homework -> returns false
        assertFalse(deleteFirstHomeworkCommand.equals(deleteSecondHomeworkCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoHomework(Model model) {
        model.updateFilteredHomeworkList(p -> false);

        assertTrue(model.getFilteredHomeworkList().isEmpty());
    }
}
