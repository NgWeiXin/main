package seedu.ultistudent.model;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code UltiStudent} that keeps track of its own history.
 */
public class VersionedUltiStudent extends UltiStudent {

    private final List<ReadOnlyUltiStudent> ultiStudentStateList;
    private int currentStatePointer;

    public VersionedUltiStudent(ReadOnlyUltiStudent initialState) {
        super(initialState);

        ultiStudentStateList = new ArrayList<>();
        ultiStudentStateList.add(new UltiStudent(initialState));
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code UltiStudent} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    public void commit() {
        removeStatesAfterCurrentPointer();
        ultiStudentStateList.add(new UltiStudent(this));
        currentStatePointer++;
        indicateModified();
    }

    private void removeStatesAfterCurrentPointer() {
        ultiStudentStateList.subList(currentStatePointer + 1, ultiStudentStateList.size()).clear();
    }

    /**
     * Restores the UltiStudent to its previous state.
     */
    public void undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        currentStatePointer--;
        resetData(ultiStudentStateList.get(currentStatePointer));
    }

    /**
     * Restores the UltiStudent to its previously undone state.
     */
    public void redo() {
        if (!canRedo()) {
            throw new NoRedoableStateException();
        }
        currentStatePointer++;
        resetData(ultiStudentStateList.get(currentStatePointer));
    }

    /**
     * Returns true if {@code undo()} has UltiStudent states to undo.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if {@code redo()} has UltiStudent states to redo.
     */
    public boolean canRedo() {
        return currentStatePointer < ultiStudentStateList.size() - 1;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedUltiStudent)) {
            return false;
        }

        VersionedUltiStudent otherVersionedUltiStudent = (VersionedUltiStudent) other;

        // state check
        return super.equals(otherVersionedUltiStudent)
                && ultiStudentStateList.equals(otherVersionedUltiStudent.ultiStudentStateList)
                && currentStatePointer == otherVersionedUltiStudent.currentStatePointer;
    }

    /**
     * Thrown when trying to {@code undo()} but can't.
     */
    public static class NoUndoableStateException extends RuntimeException {
        private NoUndoableStateException() {
            super("Current state pointer at start of ultiStudentState list, unable to undo.");
        }
    }

    /**
     * Thrown when trying to {@code redo()} but can't.
     */
    public static class NoRedoableStateException extends RuntimeException {
        private NoRedoableStateException() {
            super("Current state pointer at end of ultiStudentState list, unable to redo.");
        }
    }
}
