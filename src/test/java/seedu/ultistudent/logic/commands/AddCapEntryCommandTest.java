package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.ultistudent.commons.core.GuiSettings;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.UltiStudent;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.ReadOnlyUltiStudent;
import seedu.ultistudent.model.ReadOnlyUserPrefs;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.note.Note;
import seedu.ultistudent.model.person.Person;
import seedu.ultistudent.testutil.CapEntryBuilder;

public class AddCapEntryCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullCapEntry_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddCapEntryCommand(null);
    }

    @Test
    public void execute_capEntryAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCapEntryAdded modelStub = new ModelStubAcceptingCapEntryAdded();
        CapEntry validCapEntry = new CapEntryBuilder().build();

        CommandResult commandResult = new AddCapEntryCommand(validCapEntry).execute(modelStub, commandHistory);

        assertEquals(String.format(AddCapEntryCommand.MESSAGE_SUCCESS, validCapEntry), commandResult
                .getFeedbackToUser());
        assertEquals(Arrays.asList(validCapEntry), modelStub.capEntriesAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateCapEntry_throwsCommandException() throws Exception {
        CapEntry validCapEntry = new CapEntryBuilder().build();
        AddCapEntryCommand addCapEntryCommand = new AddCapEntryCommand(validCapEntry);
        ModelStub modelStub = new ModelStubWithCapEntry(validCapEntry);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddCapEntryCommand.MESSAGE_DUPLICATE_CAP_ENTRY);
        addCapEntryCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        CapEntry capEntryForCs2101 = new CapEntryBuilder().withModuleCode("CS2101").build();
        CapEntry capEntryForCs1231 = new CapEntryBuilder().withModuleCode("CS1231").build();
        AddCapEntryCommand addCapCS2101Command = new AddCapEntryCommand(capEntryForCs2101);
        AddCapEntryCommand addCapCS1231Command = new AddCapEntryCommand(capEntryForCs1231);

        // same object -> returns true
        assertTrue(addCapCS2101Command.equals(addCapCS2101Command));

        // same values -> returns true
        AddCapEntryCommand addCapCS2101CommandCopy = new AddCapEntryCommand(capEntryForCs2101);
        assertTrue(addCapCS2101Command.equals(addCapCS2101CommandCopy));

        // different types -> returns false
        assertFalse(addCapCS2101Command.equals(1));

        // null -> returns false
        assertFalse(addCapCS2101Command.equals(null));

        // different person -> returns false
        assertFalse(addCapCS2101Command.equals(addCapCS1231Command));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError(
                    "This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getUltiStudentFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUltiStudentFilePath(Path ultiStudentFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUltiStudent(ReadOnlyUltiStudent ultiStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUltiStudent getUltiStudent() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoUltiStudent() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoUltiStudent() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoUltiStudent() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoUltiStudent() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitUltiStudent() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Person> selectedPersonProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getSelectedPerson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }


        //============================================= Cap Entry =============================================//

        @Override
        public boolean hasCapEntry(CapEntry capEntry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCapEntry(CapEntry target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCapEntry(CapEntry capEntry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCapEntry(CapEntry target, CapEntry editedCapEntry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<CapEntry> getFilteredCapEntryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCapEntryList(Predicate<CapEntry> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<CapEntry> selectedCapEntryProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public CapEntry getSelectedCapEntry() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedCapEntry(CapEntry capEntry) {
            throw new AssertionError("This method should not be called.");
        }

        //============================================= Module Semester =============================================//

        @Override
        public boolean hasModuleSemester(ModuleSemester moduleSemester) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModuleSemester(ModuleSemester target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModuleSemester(ModuleSemester moduleSemester) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleSemester(ModuleSemester target, ModuleSemester editedModuleSemester) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<ModuleSemester> getFilteredModuleSemesterList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleSemesterList(Predicate<ModuleSemester> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<ModuleSemester> selectedModuleSemesterProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ModuleSemester getSelectedModuleSemester() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedModuleSemester(ModuleSemester moduleSemester) {
            throw new AssertionError("This method should not be called.");
        }

        //============================================= Homework Manager =============================================//

        @Override
        public boolean hasHomework(Homework homework) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteHomework(Homework homework) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addHomework(Homework homework) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHomework(Homework target, Homework editedHomework) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Homework> getFilteredHomeworkList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredHomeworkList(Predicate<Homework> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Homework> selectedHomeworkProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Homework getSelectedHomework() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedHomework(Homework homework) {
            throw new AssertionError("This method should not be called.");
        }

        //======= NotesManager ===========//
        @Override
        public boolean hasNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNote(Note target, Note editedNote) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Note> getFilteredNoteList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredNoteList(Predicate<Note> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Note> selectedNoteProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Note getSelectedNote() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

    }
    /**
     * A Model stub that contains a single cap entry.
     */
    private class ModelStubWithCapEntry extends ModelStub {
        private final CapEntry capEntry;

        ModelStubWithCapEntry(CapEntry capEntry) {
            requireNonNull(capEntry);
            this.capEntry = capEntry;
        }

        @Override
        public boolean hasCapEntry(CapEntry capEntry) {
            requireNonNull(capEntry);
            return this.capEntry.isSameCapEntry(capEntry);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingCapEntryAdded extends ModelStub {
        final ArrayList<CapEntry> capEntriesAdded = new ArrayList<>();
        final ArrayList<ModuleSemester> capEntriesSemesterAdded = new ArrayList<>();

        @Override
        public boolean hasCapEntry(CapEntry capEntry) {
            requireNonNull(capEntry);
            return capEntriesAdded.stream().anyMatch(capEntry::isSameCapEntry);
        }

        @Override
        public void addCapEntry(CapEntry capEntry) {
            requireNonNull(capEntry);
            capEntriesAdded.add(capEntry);
        }

        @Override
        public boolean hasModuleSemester(ModuleSemester moduleSemester) {
            requireNonNull(moduleSemester);
            return capEntriesSemesterAdded.stream().anyMatch(moduleSemester::equals);
        }

        @Override
        public void addModuleSemester(ModuleSemester moduleSemester) {
            requireNonNull(moduleSemester);
            capEntriesSemesterAdded.add(moduleSemester);
        }

        @Override
        public void commitUltiStudent() {
            // called by {@code AddCapEntryCommand#execute()}
        }

        @Override
        public ReadOnlyUltiStudent getUltiStudent() {
            return new UltiStudent();
        }
    }

}
