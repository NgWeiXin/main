package seedu.ultistudent.storage;

//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static seedu.ultistudent.testutil.TypicalPersons.ALICE;
import static seedu.ultistudent.testutil.TypicalPersons.HOON;
import static seedu.ultistudent.testutil.TypicalPersons.IDA;
import static seedu.ultistudent.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import seedu.ultistudent.commons.exceptions.DataConversionException;
import seedu.ultistudent.model.UltiStudent;
import seedu.ultistudent.model.ReadOnlyUltiStudent;

public class JsonUltiStudentStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonUltiStudentStorageTest");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        readAddressBook(null);
    }

    private java.util.Optional<ReadOnlyUltiStudent> readAddressBook(String filePath) throws Exception {
        return new JsonUltiStudentStorage(Paths.get(filePath)).readUltiStudent(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() throws Exception {

        thrown.expect(DataConversionException.class);
        readAddressBook("notJsonFormatAddressBook.json");

        // IMPORTANT: Any code below an exception-throwing line (like the one above) will be ignored.
        // That means you should not have more than one exception test in one method
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() throws Exception {
        thrown.expect(DataConversionException.class);
        readAddressBook("invalidPersonAddressBook.json");
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() throws Exception {
        thrown.expect(DataConversionException.class);
        readAddressBook("invalidAndValidPersonAddressBook.json");
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.getRoot().toPath().resolve("TempAddressBook.json");
        UltiStudent original = getTypicalAddressBook();
        JsonUltiStudentStorage jsonAddressBookStorage = new JsonUltiStudentStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveUltiStudent(original, filePath);
        ReadOnlyUltiStudent readBack = jsonAddressBookStorage.readUltiStudent(filePath).get();
        //assertEquals(original, new UltiStudent(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveUltiStudent(original, filePath);
        readBack = jsonAddressBookStorage.readUltiStudent(filePath).get();
        //assertEquals(original, new UltiStudent(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonAddressBookStorage.saveUltiStudent(original); // file path not specified
        readBack = jsonAddressBookStorage.readUltiStudent().get(); // file path not specified
        //assertEquals(original, new UltiStudent(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        saveAddressBook(null, "SomeFile.json");
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyUltiStudent addressBook, String filePath) {
        try {
            new JsonUltiStudentStorage(Paths.get(filePath))
                    .saveUltiStudent(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        saveAddressBook(new UltiStudent(), null);
    }
}
