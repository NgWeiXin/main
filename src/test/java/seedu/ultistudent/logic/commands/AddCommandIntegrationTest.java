package seedu.ultistudent.logic.commands;

//import static seedu.ultistudent.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.ultistudent.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.ultistudent.testutil.TypicalPersons.getTypicalAddressBook;
//
//import org.junit.Before;
//import org.junit.Test;

import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.model.Model;
//import seedu.ultistudent.model.ModelManager;
//import seedu.ultistudent.model.UserPrefs;
//import seedu.ultistudent.model.person.Person;
//import seedu.ultistudent.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    //    @Before
    //    public void setUp() {
    //        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    //    }
    //
    //    @Test
    //    public void execute_newPerson_success() {
    //        Person validPerson = new PersonBuilder().build();
    //
    //        Model expectedModel = new ModelManager(model.getUltiStudent(), new UserPrefs());
    //        expectedModel.addPerson(validPerson);
    //        expectedModel.commitUltiStudent();
    //
    //        assertCommandSuccess(new AddCommand(validPerson), model, commandHistory,
    //                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    //    }
    //
    //    @Test
    //    public void execute_duplicatePerson_throwsCommandException() {
    //        Person personInList = model.getUltiStudent().getPersonList().get(0);
    //        assertCommandFailure(new AddCommand(personInList), model, commandHistory,
    //                AddCommand.MESSAGE_DUPLICATE_PERSON);
    //    }

}
