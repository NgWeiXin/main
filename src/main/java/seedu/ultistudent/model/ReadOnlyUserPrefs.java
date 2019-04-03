package seedu.ultistudent.model;

import java.nio.file.Path;

import seedu.ultistudent.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getUltiStudentFilePath();

}
