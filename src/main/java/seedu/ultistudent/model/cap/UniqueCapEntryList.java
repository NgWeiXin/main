package seedu.ultistudent.model.cap;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.ultistudent.model.cap.exceptions.CapEntryNotFoundException;
import seedu.ultistudent.model.cap.exceptions.DuplicateCapEntryException;


/**
 * A list of capEntry that enforces uniqueness between its elements and does not allow nulls.
 * A capEntry is considered unique by comparing using {@code CapEntry#isSameCapEntry(CapEntry)}. As such, adding and
 * updating of cap entries uses CapEntry#isSameCapEntry(CapEntry) for equality so as to ensure that the cap entry being
 * added or updated is unique in terms of identity in the UniqueCapEntryList. However, the removal of a cap entry uses
 * CapEntry#equals(Object) so as to ensure that the cap entry with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see CapEntry#isSameCapEntry(CapEntry)
 */

public class UniqueCapEntryList implements Iterable<CapEntry> {

    private final ObservableList<CapEntry> internalList = FXCollections.observableArrayList();
    private final ObservableList<CapEntry> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent cap entry as the given argument.
     */
    public boolean contains(CapEntry toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCapEntry);
    }

    /**
     * Adds a capEntry to the list.
     * The capEntry must not already exist in the list.
     */
    public void add(CapEntry toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCapEntryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the cap entry {@code target} in the list with {@code editedCapEntry}.
     * {@code target} must exist in the list.
     * The cap entry identity of {@code editedCapEntry} must not be the same as another existing entry in the list.
     */
    public void setCapEntry(CapEntry target, CapEntry editedCapEntry) {
        requireAllNonNull(target, editedCapEntry);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CapEntryNotFoundException();
        }

        if (!target.isSameCapEntry(editedCapEntry) && contains(editedCapEntry)) {
            throw new DuplicateCapEntryException();
        }

        internalList.set(index, editedCapEntry);
    }

    /**
     * Removes the equivalent cap entry from the list.
     * The cap entry must exist in the list.
     */
    public void remove(CapEntry toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CapEntryNotFoundException();
        }
    }

    public void setCapEntryList(UniqueCapEntryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code capEntries}.
     * {@code capEntries} must not contain duplicate cap entries.
     */
    public void setCapEntryList(List<CapEntry> capEntryList) {
        requireAllNonNull(capEntryList);
        if (!capEntryListIsUnique(capEntryList)) {
            throw new DuplicateCapEntryException();
        }

        internalList.setAll(capEntryList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<CapEntry> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<CapEntry> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueCapEntryList // instanceof handles nulls
                && internalList.equals(((UniqueCapEntryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code capEntryList} contains only unique cap entries.
     */
    private boolean capEntryListIsUnique(List<CapEntry> capEntryList) {
        for (int i = 0; i < capEntryList.size() - 1; i++) {
            for (int j = i + 1; j < capEntryList.size(); j++) {
                if (capEntryList.get(i).isSameCapEntry(capEntryList.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

