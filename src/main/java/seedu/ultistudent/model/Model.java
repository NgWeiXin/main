package seedu.ultistudent.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.ultistudent.commons.core.GuiSettings;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.note.Note;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<CapEntry> PREDICATE_SHOW_ALL_CAP_ENTRIES = unused -> true;
    Predicate<Homework> PREDICATE_SHOW_ALL_HOMEWORK = unused -> true;
    Predicate<Note> PREDICATE_SHOW_ALL_NOTES = unused -> true;
    Predicate<ModuleSemester> PREDICATE_SHOW_ALL_MODULE_SEMESTERS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' UltiStudent file path.
     */
    Path getUltiStudentFilePath();

    /**
     * Sets the user prefs' UltiStudent file path.
     */
    void setUltiStudentFilePath(Path ultiStudentFilePath);

    /**
     * Replaces UltiStudent data with the data in {@code ultiStudent}.
     */
    void setUltiStudent(ReadOnlyUltiStudent ultiStudent);

    /**
     * Returns the UltiStudent
     */
    ReadOnlyUltiStudent getUltiStudent();

    /**
     * Returns true if the model has previous UltiStudent states to restore.
     */
    boolean canUndoUltiStudent();

    /**
     * Returns true if the model has undone UltiStudent states to restore.
     */
    boolean canRedoUltiStudent();

    /**
     * Restores the model's UltiStudent to its previous state.
     */
    void undoUltiStudent();

    /**
     * Restores the model's UltiStudent to its previously undone state.
     */
    void redoUltiStudent();

    /**
     * Saves the current UltiStudent state for undo/redo.
     */
    void commitUltiStudent();

    //=========== Cap Entry ===========================================================================

    /**
     * Returns true if a cap entry with the same identity as {@code capEntry} exists in the UltiStudent.
     */
    boolean hasCapEntry(CapEntry capEntry);

    /**
     * Deletes the given cap entry.
     * The cap entry must exist in the UltiStudent.
     */
    void deleteCapEntry(CapEntry target);

    /**
     * Adds the given cap entry.
     * {@code codeEntry} must not already exist in the UltiStudent.
     */
    void addCapEntry(CapEntry capEntry);

    /**
     * Replaces the given cap entry {@code target} with {@code editedCapEntry}.
     * {@code target} must exist in the UltiStudent.
     * The cap entry identity of {@code editedCapEntry} must not be the same as another existing cap entry
     * in the UltiStudent.
     */
    void setCapEntry(CapEntry target, CapEntry editedCapEntry);

    /**
     * Returns an unmodifiable view of the filtered cap entry list
     */
    ObservableList<CapEntry> getFilteredCapEntryList();

    /**
     * Updates the filter of the filtered cap entry list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCapEntryList(Predicate<CapEntry> predicate);

    /**
     * Selected cap entry in the filtered cap entry list.
     * null if no cap entry is selected.
     */
    ReadOnlyProperty<CapEntry> selectedCapEntryProperty();

    /**
     * Returns the selected cap entry in the filtered cap entry list.
     * null if no cap entry is selected.
     */
    CapEntry getSelectedCapEntry();

    /**
     * Sets the selected cap entry in the filtered cap entry list.
     */
    void setSelectedCapEntry(CapEntry capEntry);

    //=========== Module Semester ===========================================================================

    /**
     * Returns true if a cap entry with the same identity as {@code capEntry} exists in the UltiStudent.
     */
    boolean hasModuleSemester(ModuleSemester moduleSemester);

    /**
     * Deletes the given cap entry.
     * The cap entry must exist in the UltiStudent.
     */
    void deleteModuleSemester(ModuleSemester target);

    /**
     * Adds the given cap entry.
     * {@code codeEntry} must not already exist in the UltiStudent.
     */
    void addModuleSemester(ModuleSemester moduleSemester);

    /**
     * Replaces the given cap entry {@code target} with {@code editedCapEntry}.
     * {@code target} must exist in the UltiStudent.
     * The cap entry identity of {@code editedCapEntry} must not be the same as another existing cap entry
     * in the UltiStudent.
     */
    void setModuleSemester(ModuleSemester target, ModuleSemester editedCapEntry);

    /**
     * Returns an unmodifiable view of the filtered cap entry list
     */
    ObservableList<ModuleSemester> getFilteredModuleSemesterList();

    /**
     * Updates the filter of the filtered cap entry list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleSemesterList(Predicate<ModuleSemester> predicate);

    /**
     * Selected cap entry in the filtered cap entry list.
     * null if no cap entry is selected.
     */
    ReadOnlyProperty<ModuleSemester> selectedModuleSemesterProperty();

    /**
     * Returns the selected cap entry in the filtered cap entry list.
     * null if no cap entry is selected.
     */
    ModuleSemester getSelectedModuleSemester();

    /**
     * Sets the selected cap entry in the filtered cap entry list.
     */
    void setSelectedModuleSemester(ModuleSemester moduleSemester);

    //=========== Homework ===========================================================================

    /**
     * Returns true if a homework with the same identity as {@code homework} exists in the UltiStudent.
     */
    boolean hasHomework(Homework homework);

    /**
     * Deletes the given homework.
     * The homework must exist in the UltiStudent.
     */
    void deleteHomework(Homework target);

    /**
     * Adds the given homework.
     * {@code homework} must not already exist in the UltiStudent.
     */
    void addHomework(Homework homework);

    /**
     * Replaces the given cap entry {@code target} with {@code editedHomework}.
     * {@code target} must exist in the UltiStudent.
     * The homework identity of {@code editedHomework} must not be the same as another existing cap entry
     * in the UltiStudent.
     */
    void setHomework(Homework target, Homework editedHomework);

    /**
     * Returns an unmodifiable view of the filtered homework list
     */
    ObservableList<Homework> getFilteredHomeworkList();

    /**
     * Updates the filter of the filtered homework list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredHomeworkList(Predicate<Homework> predicate);

    /**
     * Selected homework in the filtered cap entry list.
     * null if no homework is selected.
     */
    ReadOnlyProperty<Homework> selectedHomeworkProperty();

    /**
     * Returns the selected cap entry in the filtered homework list.
     * null if no homework is selected.
     */
    Homework getSelectedHomework();

    /**
     * Sets the selected homework in the filtered homework list.
     */
    void setSelectedHomework(Homework homework);

    //=========== Notes ===================================================

    /**
     * Returns true if a Note with the same identity as {@code note} exists
     * in the UltiStudent.
     */
    boolean hasNote(Note note);

    /**
     * Deletes the given note.
     * The note must exist in the UltiStudent.
     */
    void deleteNote(Note note);

    /**
     * Adds the given note.
     * {@code Note} must not already exist in the UltiStudent.
     */
    void addNote(Note note);

    /**
     * Replaces the given cap note {@code target} with {@code editedNote}.
     * {@code target} must exist in the UltiStudent.
     * The note identity of {@code editedNote} must not be the same as
     * another note in the UltiStudent.
     */
    void setNote(Note target, Note editedNote);

    /**
     * Returns an unmodifiable view of the filtered note list
     */
    ObservableList<Note> getFilteredNoteList();

    /**
     * Updates the filter of the filtered note list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredNoteList(Predicate<Note> predicate);

    /**
     * Selected note in the filtered notes list.
     * null if no note is selected.
     */
    ReadOnlyProperty<Note> selectedNoteProperty();

    /**
     * Returns the selected note in the filtered cap entry list.
     * null if no note is selected.
     */
    Note getSelectedNote();

    /**
     * Sets the selected note in the filtered note list.
     */
    void setSelectedNote(Note note);
}
