package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
//import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.ultistudent.commons.core.GuiSettings;
import seedu.ultistudent.logic.CommandHistory;
//import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.UltiStudent;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.ReadOnlyUltiStudent;
import seedu.ultistudent.model.ReadOnlyUserPrefs;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.note.Note;
import seedu.ultistudent.model.person.Person;
//import seedu.ultistudent.testutil.PersonBuilder;

public class AddCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    //    @Test
    //    public void constructor_nullPerson_throwsNullPointerException() {
    //        thrown.expect(NullPointerException.class);
    //        new AddCommand(null);
    //    }
    //
    //    @Test
    //    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
    //        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
    //        Person validPerson = new PersonBuilder().build();
    //
    //        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub, commandHistory);
    //
    //        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
    //        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    //        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    //    }
    //
    //    @Test
    //    public void execute_duplicatePerson_throwsCommandException() throws Exception {
    //        Person validPerson = new PersonBuilder().build();
    //        AddCommand addCommand = new AddCommand(validPerson);
    //        ModelStub modelStub = new ModelStubWithPerson(validPerson);
    //
    //        thrown.expect(CommandException.class);
    //        thrown.expectMessage(AddCommand.MESSAGE_DUPLICATE_PERSON);
    //        addCommand.execute(modelStub, commandHistory);
    //    }
    //
    //    @Test
    //    public void equals() {
    //        Person alice = new PersonBuilder().withName("Alice").build();
    //        Person bob = new PersonBuilder().withName("Bob").build();
    //        AddCommand addAliceCommand = new AddCommand(alice);
    //        AddCommand addBobCommand = new AddCommand(bob);
    //
    //        // same object -> returns true
    //        assertTrue(addAliceCommand.equals(addAliceCommand));
    //
    //        // same values -> returns true
    //        AddCommand addAliceCommandCopy = new AddCommand(alice);
    //        assertTrue(addAliceCommand.equals(addAliceCommandCopy));
    //
    //        // different types -> returns false
    //        assertFalse(addAliceCommand.equals(1));
    //
    //        // null -> returns false
    //        assertFalse(addAliceCommand.equals(null));
    //
    //        // different person -> returns false
    //        assertFalse(addAliceCommand.equals(addBobCommand));
    //    }

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
            throw new AssertionError("This method should not be called.");
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
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public void commitUltiStudent() {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public ReadOnlyUltiStudent getUltiStudent() {
            return new UltiStudent();
        }
    }

}
