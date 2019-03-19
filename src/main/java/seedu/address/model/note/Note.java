package seedu.address.model.note;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Note in UltiStudent - Notes Keeping.
 */
public class Note {

    // Notes fields
    private String moduleName;
    private String noteName;

    // Data fields
    // private File content;
    // AL of AL

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    // TODO: change to validate according to input string
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    /**
     * Every field must be present and not null.
     */
    public Note(String moduleName, String noteName) {
        requireAllNonNull(moduleName, noteName);
        this.moduleName = moduleName;
        this.noteName = noteName;
        // content = new File(new );
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    /**
     * Returns a String containing the note name.
     */
    public String toString() {
        return moduleName + " " + noteName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && moduleName.equals(((Note) other).moduleName) // state check
                && noteName.equals(((Note) other).noteName)); // state check
    }

    @Override
    public int hashCode() {
        return noteName.hashCode();
    }
}
