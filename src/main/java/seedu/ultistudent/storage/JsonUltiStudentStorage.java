package seedu.ultistudent.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.ultistudent.commons.core.LogsCenter;
import seedu.ultistudent.commons.exceptions.DataConversionException;
import seedu.ultistudent.commons.exceptions.IllegalValueException;
import seedu.ultistudent.commons.util.FileUtil;
import seedu.ultistudent.commons.util.JsonUtil;
import seedu.ultistudent.model.ReadOnlyUltiStudent;

/**
 * A class to access UltiStudent data stored as a json file on the hard disk.
 */
public class JsonUltiStudentStorage implements UltiStudentStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUltiStudentStorage.class);

    private Path filePath;

    public JsonUltiStudentStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getUltiStudentFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyUltiStudent> readUltiStudent() throws DataConversionException {
        return readUltiStudent(filePath);
    }

    /**
     * Similar to {@link #readUltiStudent()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyUltiStudent> readUltiStudent(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableUltiStudent> jsonUltiStudent = JsonUtil.readJsonFile(
                filePath, JsonSerializableUltiStudent.class);
        if (!jsonUltiStudent.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonUltiStudent.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveUltiStudent(ReadOnlyUltiStudent ultiStudent) throws IOException {
        saveUltiStudent(ultiStudent, filePath);
    }

    /**
     * Similar to {@link #saveUltiStudent(ReadOnlyUltiStudent)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveUltiStudent(ReadOnlyUltiStudent ultiStudent, Path filePath) throws IOException {
        requireNonNull(ultiStudent);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableUltiStudent(ultiStudent), filePath);
    }

}
