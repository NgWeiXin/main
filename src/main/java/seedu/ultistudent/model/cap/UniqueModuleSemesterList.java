package seedu.ultistudent.model.cap;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.ultistudent.model.cap.exceptions.DuplicateModuleSemesterException;
import seedu.ultistudent.model.cap.exceptions.ModuleSemesterNotFoundException;

/**
 * A list of moduleSemester that enforces uniqueness between its elements and does not allow nulls.
 * A moduleSemester is considered unique by comparing using {@code ModuleSemester#equals(ModuleSemester)}. As such,
 * adding and updating of module semesters uses ModuleSemester#equals(ModuleSemester) for equality so as to ensure that
 * the module semester being added or updated is unique in terms of identity in the UniqueModuleSemesterList. Similarly,
 * the removal of a cap entry uses ModuleSemester#equals(Object) so as to ensure that the cap entry with exactly the
 * same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see CapEntry#isSameCapEntry(CapEntry)
 */

public class UniqueModuleSemesterList implements Iterable<ModuleSemester> {

    private final ObservableList<ModuleSemester> internalList = FXCollections.observableArrayList();
    private final ObservableList<ModuleSemester> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent cap entry as the given argument.
     */
    public boolean contains(ModuleSemester toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a capEntry to the list.
     * The capEntry must not already exist in the list.
     */
    public void add(ModuleSemester toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateModuleSemesterException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the cap entry {@code target} in the list with {@code editedCapEntry}.
     * {@code target} must exist in the list.
     * The cap entry identity of {@code editedCapEntry} must not be the same as another existing entry in the list.
     */
    public void setModuleSemester(ModuleSemester target, ModuleSemester editedModuleSemester) {
        requireAllNonNull(target, editedModuleSemester);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ModuleSemesterNotFoundException();
        }

        if (!target.equals(editedModuleSemester) && contains(editedModuleSemester)) {
            throw new DuplicateModuleSemesterException();
        }

        internalList.set(index, editedModuleSemester);
    }

    /**
     * Removes the equivalent cap entry from the list.
     * The cap entry must exist in the list.
     */
    public void remove(ModuleSemester toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleSemesterNotFoundException();
        }
    }

    public void setModuleSemesterList(UniqueModuleSemesterList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code capEntries}.
     * {@code capEntries} must not contain duplicate cap entries.
     */
    public void setModuleSemesterList(List<ModuleSemester> moduleSemesterList) {
        requireAllNonNull(moduleSemesterList);
        if (!moduleSemesterListIsUnique(moduleSemesterList)) {
            throw new DuplicateModuleSemesterException();
        }

        internalList.setAll(moduleSemesterList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ModuleSemester> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<ModuleSemester> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueModuleSemesterList // instanceof handles nulls
            && internalList.equals(((UniqueModuleSemesterList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code capEntryList} contains only unique cap entries.
     */
    private boolean moduleSemesterListIsUnique(List<ModuleSemester> moduleSemesterList) {
        for (int i = 0; i < moduleSemesterList.size() - 1; i++) {
            for (int j = i + 1; j < moduleSemesterList.size(); j++) {
                if (moduleSemesterList.get(i).equals(moduleSemesterList.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


}
