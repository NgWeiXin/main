package seedu.ultistudent.logic;

import java.nio.file.Path;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.ultistudent.commons.core.GuiSettings;
import seedu.ultistudent.logic.commands.CommandResult;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.logic.parser.exceptions.ParseException;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.ReadOnlyUltiStudent;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.note.Note;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the UltiStudent.
     *
     * @see seedu.ultistudent.model.Model#getUltiStudent()
     */
    ReadOnlyUltiStudent getUltiStudent();

    ObservableList<Homework> getFilteredHomeworkList();

    /**
     * Returns an unmodifiable view of the list of commands entered by the user.
     * The list is ordered from the least recent command to the most recent command.
     */
    ObservableList<String> getHistory();

    /**
     * Returns the user prefs' UltiStudent file path.
     */
    Path getUltiStudentFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    ReadOnlyProperty<Homework> selectedHomeworkProperty();

    // CapEntry
    /** Returns an unmodifiable view of the filtered list of cap entries */
    ObservableList<CapEntry> getFilteredCapEntryList();

    ReadOnlyProperty<CapEntry> selectedCapEntryProperty();

    // ModuleSemester
    /** Returns an unmodifiable view of the filtered list of module semesters */
    ObservableList<ModuleSemester> getFilteredModuleSemesterList();

    /**
     * Selected module semester in the filtered module semester list.
     * null if no module semester is selected.
     *
     * @see Model#selectedModuleSemesterProperty()
     */
    ReadOnlyProperty<ModuleSemester> selectedModuleSemesterProperty();

    // Note

    ReadOnlyProperty<Note> selectedNoteProperty();

    ObservableList<Note> getFilteredNoteList();

    /**
     * Sets the selected cap entry in the filtered cap entry list.
     *
     * @see seedu.ultistudent.model.Model#setSelectedCapEntry(CapEntry)
     */
    void setSelectedCapEntry(CapEntry capEntry);

    void setSelectedHomework(Homework homework);

    void setSelectedNote(Note note);

    void setSelectedModuleSemester (ModuleSemester moduleSemester);
}
