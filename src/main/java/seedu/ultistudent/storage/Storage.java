package seedu.ultistudent.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.ultistudent.commons.exceptions.DataConversionException;
import seedu.ultistudent.model.ReadOnlyUltiStudent;
import seedu.ultistudent.model.ReadOnlyUserPrefs;
import seedu.ultistudent.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends UltiStudentStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getUltiStudentFilePath();

    @Override
    Optional<ReadOnlyUltiStudent> readUltiStudent() throws DataConversionException, IOException;

    @Override
    void saveUltiStudent(ReadOnlyUltiStudent ultiStudent) throws IOException;

}
