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
import seedu.ultistudent.testutil.HomeworkBuilder;

public class AddHomeworkCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullHomework_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddHomeworkCommand(null);
    }

    @Test
    public void execute_homeworkAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingHomeworkAdded modelStub = new ModelStubAcceptingHomeworkAdded();
        Homework validHomework = new HomeworkBuilder().build();

        CommandResult commandResult = new AddHomeworkCommand(validHomework).execute(modelStub, commandHistory);

        assertEquals(String.format(AddHomeworkCommand.MESSAGE_SUCCESS, validHomework),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validHomework), modelStub.homeworksAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateHomework_throwsCommandException() throws Exception {
        Homework validHomework = new HomeworkBuilder().build();
        AddHomeworkCommand addHomeworkCommand = new AddHomeworkCommand(validHomework);
        ModelStub modelStub = new ModelStubWithHomework(validHomework);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddHomeworkCommand.MESSAGE_DUPLICATE_HOMEWORK);
        addHomeworkCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Homework draft = new HomeworkBuilder().withHomeworkName("User Guide Draft 1").build();
        Homework milestone = new HomeworkBuilder().withHomeworkName("Milestone 1").build();
        AddHomeworkCommand addDraftCommand = new AddHomeworkCommand(draft);
        AddHomeworkCommand addMileStoneCommand = new AddHomeworkCommand(milestone);

        // same object -> returns true
        assertTrue(addDraftCommand.equals(addDraftCommand));

        // same values -> returns true
        AddHomeworkCommand addDraftCommandCopy = new AddHomeworkCommand(draft);
        assertTrue(addDraftCommand.equals(addDraftCommandCopy));

        // different types -> returns false
        assertFalse(addDraftCommand.equals(1));

        // null -> returns false
        assertFalse(addDraftCommand.equals(null));

        // different person -> returns false
        assertFalse(addDraftCommand.equals(addMileStoneCommand));
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
        public void setModuleSemester(ModuleSemester target, ModuleSemester editedCapEntry) {
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
     * A Model stub that contains a homework person.
     */
    private class ModelStubWithHomework extends ModelStub {
        private final Homework homework;

        ModelStubWithHomework(Homework homework) {
            requireNonNull(homework);
            this.homework = homework;
        }

        @Override
        public boolean hasHomework(Homework homework) {
            requireNonNull(homework);
            return this.homework.equals(homework);
        }
    }

    /**
     * A Model stub that always accept the homework being added.
     */
    private class ModelStubAcceptingHomeworkAdded extends ModelStub {
        final ArrayList<Homework> homeworksAdded = new ArrayList<>();

        @Override
        public boolean hasHomework(Homework homework) {
            requireNonNull(homework);
            return homeworksAdded.stream().anyMatch(homework::equals);
        }

        @Override
        public void addHomework(Homework homework) {
            requireNonNull(homework);
            homeworksAdded.add(homework);
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
