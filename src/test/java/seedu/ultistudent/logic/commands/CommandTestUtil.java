package seedu.ultistudent.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULECREDITS;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_MODULEGRADE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.ultistudent.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.ultistudent.commons.core.index.Index;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.UltiStudent;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleCodeContainsKeywordsPredicate;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.homework.HomeworkNameContainsKeywordsPredicate;
import seedu.ultistudent.model.note.Note;
import seedu.ultistudent.model.note.NoteNameContainsKeywordsPredicate;
import seedu.ultistudent.model.person.NameContainsKeywordsPredicate;
import seedu.ultistudent.model.person.Person;
import seedu.ultistudent.testutil.EditCapEntryDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String VALID_MODULE_CODE_CS1001 = "CS1001";
    public static final String VALID_MODULE_CODE_CS1002 = "CS1002";
    public static final String VALID_MODULE_GRADE_CS1001 = "B-";
    public static final String VALID_MODULE_GRADE_CS1002 = "C+";
    public static final String VALID_MODULE_CREDITS_CS1001 = "4";
    public static final String VALID_MODULE_CREDITS_CS1002 = "12";
    public static final String VALID_MODULE_SEMESTER_CS1001 = "Y2S2";
    public static final String VALID_MODULE_SEMESTER_CS1002 = "Y4S1";

    public static final String MODULE_CODE_DESC_CS1001 = " " + PREFIX_MODULECODE + VALID_MODULE_CODE_CS1001;
    public static final String MODULE_CODE_DESC_CS1002 = " " + PREFIX_MODULECODE + VALID_MODULE_CODE_CS1002;
    public static final String MODULE_GRADE_DESC_CS1001 = " " + PREFIX_MODULEGRADE + VALID_MODULE_GRADE_CS1001;
    public static final String MODULE_GRADE_DESC_CS1002 = " " + PREFIX_MODULEGRADE + VALID_MODULE_GRADE_CS1002;
    public static final String MODULE_CREDITS_DESC_CS1001 = " " + PREFIX_MODULECREDITS + VALID_MODULE_CREDITS_CS1001;
    public static final String MODULE_CREDITS_DESC_CS1002 = " " + PREFIX_MODULECREDITS + VALID_MODULE_CREDITS_CS1002;
    public static final String MODULE_SEMESTER_DESC_CS1001 = " " + PREFIX_SEMESTER + VALID_MODULE_SEMESTER_CS1001;
    public static final String MODULE_SEMESTER_DESC_CS1002 = " " + PREFIX_SEMESTER + VALID_MODULE_SEMESTER_CS1002;

    // '&' not allowed in module code
    public static final String INVALID_MODULE_CODE_DESC = " " + PREFIX_MODULECODE + "CS1231&";
    // numbers' not allowed in grade
    public static final String INVALID_MODULE_GRADE_DESC = " " + PREFIX_MODULEGRADE + "911A";
    // negative credits not allowed
    public static final String INVALID_MODULE_CREDITS_DESC = " " + PREFIX_MODULECREDITS + "-50";
    // empty string not allowed for semester
    public static final String INVALID_MODULE_SEMESTER_DESC = " " + PREFIX_SEMESTER;


    public static final String VALID_NOTE_NAME_LOWER = "lowercase";
    public static final String VALID_NOTE_NAME_LOWER_WITH_SPACE = "lower case";
    public static final String VALID_NOTE_NAME_UPPER = "UPPERCASE";
    public static final String VALID_NOTE_NAME_MIXED = "UPPERcase";

    public static final String VALID_CONTENT_LOWER = "testlower";
    public static final String VALID_CONTENT_UPPER = "TESTUPPER";

    public static final EditCapEntryCommand.EditCapEntryDescriptor DESC_CS1001;
    public static final EditCapEntryCommand.EditCapEntryDescriptor DESC_CS1002;

    static {
        DESC_CS1001 = new EditCapEntryDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_CS1001)
                .withModuleGrade(VALID_MODULE_GRADE_CS1001).withModuleCredits(VALID_MODULE_CREDITS_CS1001)
                .withModuleSemester(VALID_MODULE_SEMESTER_CS1001).build();
        DESC_CS1002 = new EditCapEntryDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_CS1002)
                .withModuleGrade(VALID_MODULE_GRADE_CS1002).withModuleCredits(VALID_MODULE_CREDITS_CS1002)
                .withModuleSemester(VALID_MODULE_SEMESTER_CS1002).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            CommandResult expectedCommandResult, Model expectedModel) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandHistory, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, actualCommandHistory, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the UltiStudent, filtered person list and selected person in {@code actualModel} remain unchanged <br>
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        UltiStudent expectedAddressBook = new UltiStudent(actualModel.getUltiStudent());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());
        Person expectedSelectedPerson = actualModel.getSelectedPerson();

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAddressBook, actualModel.getUltiStudent());
            assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
            assertEquals(expectedSelectedPerson, actualModel.getSelectedPerson());
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s UltiStudent.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the note at the given {@code targetIndex} in the
     * {@code model}'s UltiStudent.
     */
    public static void showNoteAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredNoteList().size());

        Note note = model.getFilteredNoteList().get(targetIndex.getZeroBased());
        final String[] splitName = ((Note) note).getNoteName().noteName.split("\\s+");
        model.updateFilteredNoteList(new NoteNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredNoteList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the homework at the given {@code targetIndex} in the
     * {@code model}'s UltiStudent.
     */
    public static void showHomeworkAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredHomeworkList().size());

        Homework homework = model.getFilteredHomeworkList().get(targetIndex.getZeroBased());
        final String[] splitHomework = homework.getHomeworkName().value.split("\\s+");
        model.updateFilteredHomeworkList(new HomeworkNameContainsKeywordsPredicate(Arrays.asList(splitHomework[0])));

        assertEquals(1, model.getFilteredHomeworkList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the capEntry at the given {@code targetIndex} in the
     * {@code model}'s UltiStudent.
     */
    public static void showCapEntryAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCapEntryList().size());

        CapEntry capEntry = model.getFilteredCapEntryList().get(targetIndex.getZeroBased());
        final String[] splitCapEntry = capEntry.getModuleCode().value.split("\\s+");
        model.updateFilteredCapEntryList(new ModuleCodeContainsKeywordsPredicate(Arrays.asList(splitCapEntry[0])));

        assertEquals(1, model.getFilteredCapEntryList().size());
    }

    /**
     * Deletes the first person in {@code model}'s filtered list from {@code model}'s UltiStudent.
     */
    public static void deleteFirstPerson(Model model) {
        Person firstPerson = model.getFilteredPersonList().get(0);
        model.deletePerson(firstPerson);
        model.commitUltiStudent();
    }

    /**
     * Update model's selected note to the note given at targetIndex in the model's UltiStudent.
     * @param model
     * @param targetIndex
     */
    public static void selectNoteAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredNoteList().size());

        Note note = model.getFilteredNoteList().get(targetIndex.getZeroBased());
        model.setSelectedNote(note);

        assertNotNull(model.getSelectedNote());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the UltiStudent, filtered person list and selected person in {@code actualModel} remain unchanged <br>
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertNoteCommandFailure(Command command, Model actualModel,
                                                CommandHistory actualCommandHistory,
                                                String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        UltiStudent expectedAddressBook = new UltiStudent(actualModel.getUltiStudent());
        List<Note> expectedFilteredList = new ArrayList<>(actualModel.getFilteredNoteList());
        Note expectedSelectedNote = actualModel.getSelectedNote();

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAddressBook, actualModel.getUltiStudent());
            assertEquals(expectedFilteredList, actualModel.getFilteredNoteList());
            assertEquals(expectedSelectedNote, actualModel.getSelectedNote());
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }
}
