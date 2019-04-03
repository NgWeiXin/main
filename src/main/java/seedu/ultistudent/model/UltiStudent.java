package seedu.ultistudent.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.ultistudent.commons.util.InvalidationListenerManager;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.cap.UniqueCapEntryList;
import seedu.ultistudent.model.cap.UniqueModuleSemesterList;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.homework.UniqueHomeworkList;
import seedu.ultistudent.model.note.Note;
import seedu.ultistudent.model.note.UniqueNoteList;
import seedu.ultistudent.model.person.Person;
import seedu.ultistudent.model.person.UniquePersonList;

/**
 * Wraps all data at the ultistudent-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class UltiStudent implements ReadOnlyUltiStudent {

    private final UniqueCapEntryList capEntryList;
    private final UniquePersonList persons;
    private final UniqueHomeworkList homeworkList;
    private final UniqueModuleSemesterList moduleSemesterList;
    private final UniqueNoteList noteList;
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        capEntryList = new UniqueCapEntryList();
        homeworkList = new UniqueHomeworkList();
        noteList = new UniqueNoteList();
        moduleSemesterList = new UniqueModuleSemesterList();
    }

    public UltiStudent() {}

    /**
     * Creates an UltiStudent using the Persons in the {@code toBeCopied}
     */
    public UltiStudent(ReadOnlyUltiStudent toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
        indicateModified();
    }

    /**
     * Replaces the contents of the cap entries list with {@code capEntryList}.
     * {@code capEntryList} must not contain duplicate persons.
     */
    public void setCapEntryList(List<CapEntry> capEntryList) {
        this.capEntryList.setCapEntryList(capEntryList);
        indicateModified();
    }

    /**
     * Replaces the contents of the cap entries list with {@code capEntryList}.
     * {@code capEntryList} must not contain duplicate persons.
     */
    public void setModuleSemesterList(List<ModuleSemester> moduleSemesterList) {
        this.moduleSemesterList.setModuleSemesterList(moduleSemesterList);
        indicateModified();
    }

    /**
     * Resets the existing data of this {@code UltiStudent} with {@code newData}.
     */
    public void resetData(ReadOnlyUltiStudent newData) {
        requireNonNull(newData);
        setCapEntryList(newData.getCapEntryList());
        setPersons(newData.getPersonList());
        setHomework(newData.getHomeworkList());
        setModuleSemesterList(newData.getModuleSemesterList());
        setNote(newData.getNoteList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the UltiStudent.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a cap entry with the same identity as {@code person} exists in UltiStudent.
     */
    public boolean hasCapEntry(CapEntry capEntry) {
        requireNonNull(capEntry);
        return capEntryList.contains(capEntry);
    }

    /**
     * Returns true if a module semester with the same identity as {@code moduleSemester} exists in UltiStudent.
     */
    public boolean hasModuleSemester(ModuleSemester moduleSemester) {
        requireNonNull(moduleSemester);
        return moduleSemesterList.contains(moduleSemester);
    }

    /**
     * Adds a person to the UltiStudent.
     * The person must not already exist in the UltiStudent.
     */
    public void addPerson(Person p) {
        persons.add(p);
        indicateModified();
    }

    /**
     * Adds a cap entry to UltiStudent.
     * The cap entry must not already exist in UltiStudent.
     */
    public void addCapEntry(CapEntry c) {
        capEntryList.add(c);
        indicateModified();
    }

    /**
     * Adds a cap entry to UltiStudent.
     * The cap entry must not already exist in UltiStudent.
     */
    public void addModuleSemester(ModuleSemester moduleSemester) {
        moduleSemesterList.add(moduleSemester);
        indicateModified();
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the UltiStudent.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the UltiStudent.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
        indicateModified();
    }

    /**
     * Replaces the given cap entry {@code target} in the list with {@code editedCapEntry}.
     * {@code target} must exist in the UltiStudent.
     * The cap entry identity of {@code editedPerson} must not be the same as another existing cap entry in
     * the UltiStudent.
     */
    public void setCapEntry(CapEntry target, CapEntry editedCapEntry) {
        requireNonNull(editedCapEntry);

        capEntryList.setCapEntry(target, editedCapEntry);
        indicateModified();
    }

    /**
     * Replaces the given cap entry {@code target} in the list with {@code editedCapEntry}.
     * {@code target} must exist in the UltiStudent.
     * The cap entry identity of {@code editedPerson} must not be the same as another existing cap entry in
     * the UltiStudent.
     */
    public void setModuleSemester(ModuleSemester target, ModuleSemester editedModuleSemester) {
        requireNonNull(editedModuleSemester);

        moduleSemesterList.setModuleSemester(target, editedModuleSemester);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code UltiStudent}.
     * {@code key} must exist in the UltiStudent.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code UltiStudent}.
     * {@code key} must exist in the UltiStudent.
     */
    public void removeCapEntry(CapEntry key) {
        capEntryList.remove(key);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code UltiStudent}.
     * {@code key} must exist in the UltiStudent.
     */
    public void removeModuleSemester(ModuleSemester key) {
        moduleSemesterList.remove(key);
        indicateModified();
    }

    public void setHomework(Homework target, Homework editedHomework) {
        requireNonNull(editedHomework);

        homeworkList.setHomework(target, editedHomework);
        indicateModified();
    }

    public void setHomework(List<Homework> homework) {
        this.homeworkList.setHomework(homework);
        indicateModified();
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the UltiStudent.
     */
    public boolean hasHomework(Homework homework) {
        requireNonNull(homework);
        return this.homeworkList.contains(homework);
    }

    /**
     * Adds a person to the UltiStudent.
     * The person must not already exist in the UltiStudent.
     */
    public void addHomework(Homework homework) {
        this.homeworkList.add(homework);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code UltiStudent}.
     * {@code key} must exist in the UltiStudent.
     */
    public void removeHomework(Homework homework) {
        this.homeworkList.remove(homework);
        indicateModified();
    }

    //===== Note Manager =====
    public void setNote(Note target, Note editedNote) {
        requireNonNull(editedNote);

        noteList.setNote(target, editedNote);
        indicateModified();
    }

    public void setNote(List<Note> note) {
        this.noteList.setNotes(note);
        indicateModified();
    }

    /**
     * Returns true if a note with the same identity as {@code note} exists in
     * the UltiStudent.
     */
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return this.noteList.contains(note);
    }

    /**
     * Adds a note to the UltiStudent.
     * The note must not already exist in the UltiStudent.
     */
    public void addNote(Note note) {
        this.noteList.add(note);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code UltiStudent}.
     * {@code key} must exist in the UltiStudent.
     */
    public void removeNote(Note note) {
        this.noteList.remove(note);
        indicateModified();
    }


    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the UltiStudent has been modified.
     */
    protected void indicateModified() {
        invalidationListenerManager.callListeners(this);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<CapEntry> getCapEntryList() {
        return capEntryList.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Homework> getHomeworkList() {
        return homeworkList.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<ModuleSemester> getModuleSemesterList() {
        return moduleSemesterList.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Note> getNoteList() {
        return noteList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UltiStudent // instanceof handles nulls
                && persons.equals(((UltiStudent) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
