package seedu.ultistudent.model.homework;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Date for a deadline in a homework entry in UltiStudent.
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS =
            "The format for the date should follow DD/MM/YYYY. "
                    + "It is possible to use single digits for the DD and MM fields. "
                    + "Invalid dates such as 30 Feb 2019 or 29 Feb on non-leap years are not accepted.";

    //regex reference to https://stackoverflow.com/questions/15491894/regex-to-validate-date-format-dd-mm-yyyy
    //credits to: Ofir Luzon
    public static final String VALIDATION_REGEX = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)"
            + "(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)"
            + "?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])"
            + "(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    // Identity fields
    private String value;

    /**
     * Every field must be present and not null.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.value = date;
    }

    public String getDate () {
        return value;
    }

    /**
     * Returns true if it is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both homework have the same deadline.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Date)) {
            return false;
        }

        return value.equals(((Date) other).value);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
