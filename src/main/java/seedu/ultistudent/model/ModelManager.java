package seedu.ultistudent.model;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.ultistudent.commons.core.GuiSettings;
import seedu.ultistudent.commons.core.LogsCenter;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.cap.exceptions.CapEntryNotFoundException;
import seedu.ultistudent.model.cap.exceptions.ModuleSemesterNotFoundException;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.homework.exceptions.HomeworkNotFoundException;
import seedu.ultistudent.model.note.Note;
import seedu.ultistudent.model.note.exceptions.NoteNotFoundException;
import seedu.ultistudent.model.person.Person;
import seedu.ultistudent.model.person.exceptions.PersonNotFoundException;

/**
 * Represents the in-memory model of the UltiStudent data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedUltiStudent versionedAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final SimpleObjectProperty<Person> selectedPerson = new SimpleObjectProperty<>();
    private final FilteredList<CapEntry> filteredCapEntryList;
    private final SimpleObjectProperty<CapEntry> selectedCapEntry = new SimpleObjectProperty<>();
    private final FilteredList<Homework> filteredHomeworkList;
    private final SimpleObjectProperty<Homework> selectedHomework = new SimpleObjectProperty<>();
    private final FilteredList<Note> filteredNoteList;
    private final SimpleObjectProperty<Note> selectedNote = new SimpleObjectProperty<>();
    private final FilteredList<ModuleSemester> filteredModuleSemesterList;
    private final SimpleObjectProperty<ModuleSemester> selectedModuleSemester = new SimpleObjectProperty<>();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyUltiStudent addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with UltiStudent: " + addressBook + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedUltiStudent(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(versionedAddressBook.getPersonList());
        filteredPersons.addListener(this::ensureSelectedPersonIsValid);
        filteredCapEntryList = new FilteredList<>(versionedAddressBook.getCapEntryList());
        filteredCapEntryList.addListener(this::ensureSelectedCapEntryIsValid);
        filteredHomeworkList = new FilteredList<>(versionedAddressBook.getHomeworkList());
        filteredHomeworkList.addListener(this::ensureSelectedHomeworkIsValid);
        filteredNoteList = new FilteredList<>(versionedAddressBook.getNoteList());
        filteredNoteList.addListener(this::ensureSelectedNoteIsValid);
        filteredModuleSemesterList = new FilteredList<>(versionedAddressBook.getModuleSemesterList());
        filteredModuleSemesterList.addListener(this::ensureSelectedModuleSemesterIsValid);
    }

    public ModelManager() {
        this(new UltiStudent(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getUltiStudentFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setUltiStudentFilePath(Path ultiStudentFilePath) {
        requireNonNull(ultiStudentFilePath);
        userPrefs.setAddressBookFilePath(ultiStudentFilePath);
    }

    //=========== UltiStudent ================================================================================

    @Override
    public void setUltiStudent(ReadOnlyUltiStudent ultiStudent) {
        versionedAddressBook.resetData(ultiStudent);
    }

    @Override
    public ReadOnlyUltiStudent getUltiStudent() {
        return versionedAddressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return versionedAddressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        versionedAddressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        versionedAddressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        versionedAddressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoUltiStudent() {
        return versionedAddressBook.canUndo();
    }

    @Override
    public boolean canRedoUltiStudent() {
        return versionedAddressBook.canRedo();
    }

    @Override
    public void undoUltiStudent() {
        versionedAddressBook.undo();
    }

    @Override
    public void redoUltiStudent() {
        versionedAddressBook.redo();
    }

    @Override
    public void commitUltiStudent() {
        versionedAddressBook.commit();
    }

    //=========== Selected person ===========================================================================

    @Override
    public ReadOnlyProperty<Person> selectedPersonProperty() {
        return selectedPerson;
    }

    @Override
    public Person getSelectedPerson() {
        return selectedPerson.getValue();
    }

    @Override
    public void setSelectedPerson(Person person) {
        if (person != null && !filteredPersons.contains(person)) {
            throw new PersonNotFoundException();
        }
        selectedPerson.setValue(person);
    }


    /**
     * Ensures {@code selectedPerson} is a valid person in {@code filteredPersons}.
     */
    private void ensureSelectedPersonIsValid(ListChangeListener.Change<? extends
            Person> change) {
        while (change.next()) {
            if (selectedPerson.getValue() == null) {
                // null is always a valid selected person, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedPersonReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedPerson.getValue());
            if (wasSelectedPersonReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedPerson.getValue());
                selectedPerson.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedPersonRemoved = change.getRemoved().stream()
                    .anyMatch(removedPerson -> selectedPerson.getValue().isSamePerson(removedPerson));
            if (wasSelectedPersonRemoved) {
                // Select the person that came before it in the list,
                // or clear the selection if there is no such person.
                selectedPerson.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    //====================== CapManager ========================================

    @Override
    public boolean hasCapEntry(CapEntry capEntry) {
        requireNonNull(capEntry);
        return versionedAddressBook.hasCapEntry(capEntry);
    }

    @Override
    public void deleteCapEntry(CapEntry target) {
        versionedAddressBook.removeCapEntry(target);
    }

    @Override
    public void addCapEntry(CapEntry capEntry) {
        versionedAddressBook.addCapEntry(capEntry);
        updateFilteredCapEntryList(PREDICATE_SHOW_ALL_CAP_ENTRIES);
    }

    @Override
    public void setCapEntry(CapEntry target, CapEntry editedCapEntry) {
        requireAllNonNull(target, editedCapEntry);

        versionedAddressBook.setCapEntry(target, editedCapEntry);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<CapEntry> getFilteredCapEntryList() {
        return filteredCapEntryList;
    }

    @Override
    public void updateFilteredCapEntryList(Predicate<CapEntry> predicate) {
        requireNonNull(predicate);
        filteredCapEntryList.setPredicate(predicate);
    }

    @Override
    public ReadOnlyProperty<CapEntry> selectedCapEntryProperty() {
        return selectedCapEntry;
    }

    @Override
    public CapEntry getSelectedCapEntry() {
        return selectedCapEntry.getValue();
    }

    @Override
    public void setSelectedCapEntry(CapEntry capEntry) {
        if (capEntry != null && !filteredCapEntryList.contains(capEntry)) {
            throw new CapEntryNotFoundException();
        }
        selectedCapEntry.setValue(capEntry);
    }

    /**
     * Ensures {@code selectedCapEntry} is a valid cap entry in {@code filteredCapEntryList}.
     */
    private void ensureSelectedCapEntryIsValid(ListChangeListener.Change<? extends CapEntry> change) {
        while (change.next()) {
            if (selectedCapEntry.getValue() == null) {
                // null is always a valid selected person, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedCapEntryReplaced = change.wasReplaced() && change.getAddedSize()
                    == change.getRemovedSize() && change.getRemoved().contains(selectedCapEntry.getValue());
            if (wasSelectedCapEntryReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedCapEntry.getValue());
                selectedCapEntry.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedCapEntryRemoved = change.getRemoved().stream()
                    .anyMatch(removedCapEntry -> selectedCapEntry.getValue().isSameCapEntry(removedCapEntry));
            if (wasSelectedCapEntryRemoved) {
                // Select the person that came before it in the list,
                // or clear the selection if there is no such person.
                selectedCapEntry.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }
    //====================== Module Semester List ========================================

    @Override
    public boolean hasModuleSemester(ModuleSemester moduleSemester) {
        requireNonNull(moduleSemester);
        return versionedAddressBook.hasModuleSemester(moduleSemester);
    }

    @Override
    public void deleteModuleSemester(ModuleSemester target) {
        versionedAddressBook.removeModuleSemester(target);
    }

    @Override
    public void addModuleSemester(ModuleSemester moduleSemester) {
        versionedAddressBook.addModuleSemester(moduleSemester);
        updateFilteredModuleSemesterList(PREDICATE_SHOW_ALL_MODULE_SEMESTERS);
    }

    @Override
    public void setModuleSemester(ModuleSemester target, ModuleSemester editedModuleSemester) {
        requireAllNonNull(target, editedModuleSemester);

        versionedAddressBook.setModuleSemester(target, editedModuleSemester);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<ModuleSemester> getFilteredModuleSemesterList() {
        return filteredModuleSemesterList;
    }

    @Override
    public void updateFilteredModuleSemesterList(Predicate<ModuleSemester> predicate) {
        requireNonNull(predicate);
        filteredModuleSemesterList.setPredicate(predicate);
    }

    @Override
    public ReadOnlyProperty<ModuleSemester> selectedModuleSemesterProperty() {
        return selectedModuleSemester;
    }

    @Override
    public ModuleSemester getSelectedModuleSemester() {
        return selectedModuleSemester.getValue();
    }

    @Override
    public void setSelectedModuleSemester(ModuleSemester moduleSemester) {
        if (moduleSemester != null && !filteredModuleSemesterList.contains(moduleSemester)) {
            throw new ModuleSemesterNotFoundException();
        }
        selectedModuleSemester.setValue(moduleSemester);
    }

    /**
     * Ensures {@code selectedCapEntry} is a valid cap entry in {@code filteredCapEntryList}.
     */
    private void ensureSelectedModuleSemesterIsValid(ListChangeListener.Change<? extends ModuleSemester> change) {
        while (change.next()) {
            if (selectedModuleSemester.getValue() == null) {
                // null is always a valid selected person, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedModuleSemesterReplaced = change.wasReplaced() && change.getAddedSize()
                    == change.getRemovedSize() && change.getRemoved().contains(selectedModuleSemester.getValue());
            if (wasSelectedModuleSemesterReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedModuleSemester.getValue());
                selectedModuleSemester.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedModuleSemsterRemoved = change.getRemoved().stream()
                    .anyMatch(removedModuleSemester -> selectedModuleSemester.getValue().equals(removedModuleSemester));
            if (wasSelectedModuleSemsterRemoved) {
                // Select the person that came before it in the list,
                // or clear the selection if there is no such person.
                selectedModuleSemester.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1)
                        : null);
            }
        }
    }


    //=============================================== HomeworkManager ===============================================//

    @Override
    public boolean hasHomework(Homework homework) {
        requireNonNull(homework);
        return versionedAddressBook.hasHomework(homework);
    }

    @Override
    public void deleteHomework(Homework target) {
        versionedAddressBook.removeHomework(target);
    }

    @Override
    public void addHomework(Homework homework) {
        versionedAddressBook.addHomework(homework);
        updateFilteredHomeworkList(PREDICATE_SHOW_ALL_HOMEWORK);
    }

    @Override
    public void setHomework(Homework target, Homework editedHomework) {
        requireAllNonNull(target, editedHomework);

        versionedAddressBook.setHomework(target, editedHomework);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Homework> getFilteredHomeworkList() {
        return filteredHomeworkList;
    }

    @Override
    public void updateFilteredHomeworkList(Predicate<Homework> predicate) {
        requireNonNull(predicate);
        filteredHomeworkList.setPredicate(predicate);
    }

    @Override
    public ReadOnlyProperty<Homework> selectedHomeworkProperty() {
        return selectedHomework;
    }

    @Override
    public Homework getSelectedHomework() {
        return selectedHomework.getValue();
    }

    @Override
    public void setSelectedHomework(Homework homework) {
        if (homework != null && !filteredHomeworkList.contains(homework)) {
            throw new HomeworkNotFoundException();
        }
        selectedHomework.setValue(homework);
    }

    /**
     * Ensures {@code selectedPerson} is a valid person in {@code filteredPersons}.
     */
    private void ensureSelectedHomeworkIsValid(ListChangeListener.Change<? extends Homework> change) {
        while (change.next()) {
            if (selectedHomework.getValue() == null) {
                // null is always a valid selected person, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedHomeworkReplaced = change.wasReplaced() && change.getAddedSize()
                    == change.getRemovedSize() && change.getRemoved().contains(selectedHomework.getValue());
            if (wasSelectedHomeworkReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedHomework.getValue());
                selectedHomework.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedHomeworkRemoved = change.getRemoved().stream()
                    .anyMatch(removedHomework -> selectedHomework.getValue().equals(removedHomework));
            if (wasSelectedHomeworkRemoved) {
                // Select the person that came before it in the list,
                // or clear the selection if there is no such person.
                selectedHomework.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    // ======================= Notes Manager ==================================
    @Override
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return versionedAddressBook.hasNote(note);
    }

    @Override
    public void deleteNote(Note target) {
        versionedAddressBook.removeNote(target);
    }

    @Override
    public void addNote(Note note) {
        versionedAddressBook.addNote(note);
        updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
    }

    @Override
    public void setNote(Note target, Note editedNote) {
        requireAllNonNull(target, editedNote);

        versionedAddressBook.setNote(target, editedNote);
    }

    //=========== Filtered Note List Accessors =================================

    /**
     * Returns an unmodifiable view of the list of {@code Note} backed by the
     * internal list of
     * {@code versionedAddressBook}
     */

    @Override
    public ObservableList<Note> getFilteredNoteList() {
        return filteredNoteList;
    }

    @Override
    public void updateFilteredNoteList(Predicate<Note> predicate) {
        requireNonNull(predicate);
        filteredNoteList.setPredicate(predicate);
    }

    //=========== Selected note ==============================================

    @Override
    public ReadOnlyProperty<Note> selectedNoteProperty() {
        return selectedNote;
    }

    @Override
    public Note getSelectedNote() {
        return selectedNote.getValue();
    }

    @Override
    public void setSelectedNote(Note note) {
        if (note != null && !filteredNoteList.contains(note)) {
            throw new NoteNotFoundException();
        }
        selectedNote.setValue(note);
    }

    /**
     * Ensures {@code selectedNote} is a valid note in {@code
     * filteredNoteList}.
     */
    private void ensureSelectedNoteIsValid(ListChangeListener.Change<? extends Note> change) {
        while (change.next()) {
            if (selectedNote.getValue() == null) {
                // null is always a valid selected note, so we do not need to
                // check that it is valid anymore.
                return;
            }

            boolean wasSelectedNoteReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedNote.getValue());
            if (wasSelectedNoteReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedNote.getValue
                        ());
                selectedNote.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedNoteRemoved = change.getRemoved().stream()
                    .anyMatch(removedNote -> selectedNote.getValue()
                            .isSameNote(removedNote));
            if (wasSelectedNoteRemoved) {
                // Select the person that came before it in the list,
                // or clear the selection if there is no such person.
                selectedNote.setValue(change.getFrom() > 0 ? change.getList()
                        .get(change.getFrom() - 1) : null);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedAddressBook.equals(other.versionedAddressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredCapEntryList.equals(other.filteredCapEntryList)
                && filteredHomeworkList.equals(other.filteredHomeworkList)
                && Objects.equals(selectedPerson.get(), other.selectedPerson.get());
    }
}
