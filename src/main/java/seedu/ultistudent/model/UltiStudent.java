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

/**
 * Wraps all data at the ultistudent level
 */
public class UltiStudent implements ReadOnlyUltiStudent {

    private final UniqueCapEntryList capEntryList;
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
        capEntryList = new UniqueCapEntryList();
        homeworkList = new UniqueHomeworkList();
        noteList = new UniqueNoteList();
        moduleSemesterList = new UniqueModuleSemesterList();
    }

    public UltiStudent() {}

    /**
     * Creates an UltiStudent using capEntryList, homeworkList, noteList and moduleSemesterList
     * in the {@code toBeCopied}
     */
    public UltiStudent(ReadOnlyUltiStudent toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //===== list overwrite operations =====//

    /**
     * Replaces the contents of the cap entries list with {@code capEntryList}.
     * {@code capEntryList} must not contain duplicate cap entry.
     */
    public void setCapEntryList(List<CapEntry> capEntryList) {
        this.capEntryList.setCapEntryList(capEntryList);
        indicateModified();
    }

    /**
     * Replaces the contents of the ModuleSemesterlist with {@code moduleSemesterList}.
     * {@code moduleSemesterList} must not contain duplicate ModuleSemester.
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
        setHomework(newData.getHomeworkList());
        setModuleSemesterList(newData.getModuleSemesterList());
        setNote(newData.getNoteList());
    }

    //===== CapManager's operations =====//
    /**
     * Returns true if a cap entry with the same identity as {@code capEntry} exists in UltiStudent.
     */
    public boolean hasCapEntry(CapEntry capEntry) {
        requireNonNull(capEntry);
        return capEntryList.contains(capEntry);
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
     * Replaces the given cap entry {@code target} in the list with {@code editedCapEntry}.
     * {@code target} must exist in the UltiStudent.
     * The cap entry identity of {@code editedCapEntry} must not be the same as another existing cap entry in
     * the UltiStudent.
     */
    public void setCapEntry(CapEntry target, CapEntry editedCapEntry) {
        requireNonNull(editedCapEntry);

        capEntryList.setCapEntry(target, editedCapEntry);
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
     * Adds a moduleSemester to UltiStudent.
     * The moduleSemester must not already exist in UltiStudent.
     */
    public void addModuleSemester(ModuleSemester moduleSemester) {
        moduleSemesterList.add(moduleSemester);
        indicateModified();
    }

    /**
     * Returns true if a module semester with the same identity as {@code moduleSemester} exists in UltiStudent.
     */
    public boolean hasModuleSemester(ModuleSemester moduleSemester) {
        requireNonNull(moduleSemester);
        return moduleSemesterList.contains(moduleSemester);
    }

    /**
     * Replaces the given ModuleSemester {@code target} in the list with {@code editedModuleSemester}.
     * {@code target} must exist in the UltiStudent.
     * The ModuleSemester identity of {@code editedModuleSemester} must not be the same as another
     * existing ModuleSemester in the UltiStudent.
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
    public void removeModuleSemester(ModuleSemester key) {
        moduleSemesterList.remove(key);
        indicateModified();
    }

    //===== HomeworkManager's operations =====//
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
     * Returns true if a homework with the same identity as {@code homework} exists in the UltiStudent.
     */
    public boolean hasHomework(Homework homework) {
        requireNonNull(homework);
        return this.homeworkList.contains(homework);
    }

    /**
     * Adds a homework to the UltiStudent.
     * The homework must not already exist in the UltiStudent.
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

    //===== NotesManager's operations =====//
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

    //===== util methods =====//

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
                && capEntryList.equals(((UltiStudent) other).capEntryList)
                && homeworkList.equals(((UltiStudent) other).homeworkList)
                && noteList.equals(((UltiStudent) other).noteList)
                && moduleSemesterList.equals(((UltiStudent) other).moduleSemesterList));
    }
}
