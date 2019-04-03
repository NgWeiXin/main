package seedu.ultistudent.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.ultistudent.commons.core.LogsCenter;
import seedu.ultistudent.commons.exceptions.DataConversionException;
import seedu.ultistudent.model.ReadOnlyUltiStudent;
import seedu.ultistudent.model.ReadOnlyUserPrefs;
import seedu.ultistudent.model.UserPrefs;

/**
 * Manages storage of UltiStudent data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private UltiStudentStorage ultiStudentStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(UltiStudentStorage ultiStudentStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.ultiStudentStorage = ultiStudentStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ UltiStudent methods ==============================

    @Override
    public Path getUltiStudentFilePath() {
        return ultiStudentStorage.getUltiStudentFilePath();
    }

    @Override
    public Optional<ReadOnlyUltiStudent> readUltiStudent() throws DataConversionException, IOException {
        return readUltiStudent(ultiStudentStorage.getUltiStudentFilePath());
    }

    @Override
    public Optional<ReadOnlyUltiStudent> readUltiStudent(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return ultiStudentStorage.readUltiStudent(filePath);
    }

    @Override
    public void saveUltiStudent(ReadOnlyUltiStudent ultiStudent) throws IOException {
        saveUltiStudent(ultiStudent, ultiStudentStorage.getUltiStudentFilePath());
    }

    @Override
    public void saveUltiStudent(ReadOnlyUltiStudent ultiStudent, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        ultiStudentStorage.saveUltiStudent(ultiStudent, filePath);
    }

}
