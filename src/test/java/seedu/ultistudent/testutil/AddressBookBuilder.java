package seedu.ultistudent.testutil;

import seedu.ultistudent.model.UltiStudent;
import seedu.ultistudent.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code UltiStudent ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private UltiStudent addressBook;

    public AddressBookBuilder() {
        addressBook = new UltiStudent();
    }

    public AddressBookBuilder(UltiStudent addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code UltiStudent} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public UltiStudent build() {
        return addressBook;
    }
}
