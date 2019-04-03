package seedu.ultistudent.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.ultistudent.commons.exceptions.DataConversionException;
import seedu.ultistudent.model.ReadOnlyUltiStudent;
import seedu.ultistudent.model.UltiStudent;

/**
 * Represents a storage for {@link UltiStudent}.
 */
public interface UltiStudentStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getUltiStudentFilePath();

    /**
     * Returns UltiStudent data as a {@link ReadOnlyUltiStudent}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyUltiStudent> readUltiStudent() throws DataConversionException, IOException;

    /**
     * @see #getUltiStudentFilePath()
     */
    Optional<ReadOnlyUltiStudent> readUltiStudent(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyUltiStudent} to the storage.
     * @param ultiStudent cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUltiStudent(ReadOnlyUltiStudent ultiStudent) throws IOException;

    /**
     * @see #saveUltiStudent(ReadOnlyUltiStudent)
     */
    void saveUltiStudent(ReadOnlyUltiStudent ultiStudent, Path filePath) throws IOException;

}
