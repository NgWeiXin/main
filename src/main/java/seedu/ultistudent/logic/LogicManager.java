package seedu.ultistudent.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.ultistudent.commons.core.GuiSettings;
import seedu.ultistudent.commons.core.LogsCenter;
import seedu.ultistudent.logic.commands.Command;
import seedu.ultistudent.logic.commands.CommandResult;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.logic.parser.UltiStudentParser;
import seedu.ultistudent.logic.parser.exceptions.ParseException;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.ReadOnlyUltiStudent;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.note.Note;
import seedu.ultistudent.model.person.Person;
import seedu.ultistudent.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CommandHistory history;
    private final UltiStudentParser ultiStudentParser;
    private boolean addressBookModified;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        history = new CommandHistory();
        ultiStudentParser = new UltiStudentParser();

        // Set addressBookModified to true whenever the models' UltiStudent is modified.
        model.getUltiStudent().addListener(observable -> addressBookModified = true);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        addressBookModified = false;

        CommandResult commandResult;
        try {
            Command command = ultiStudentParser.parseCommand(commandText);
            commandResult = command.execute(model, history);
        } finally {
            history.add(commandText);
        }

        if (addressBookModified) {
            logger.info("UltiStudent modified, saving to file.");
            try {
                storage.saveUltiStudent(model.getUltiStudent());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }

        return commandResult;
    }

    @Override
    public ReadOnlyUltiStudent getAddressBook() {
        return model.getUltiStudent();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<CapEntry> getFilteredCapEntryList() {
        return model.getFilteredCapEntryList();
    }

    @Override
    public ObservableList<ModuleSemester> getFilteredModuleSemesterList() {
        return model.getFilteredModuleSemesterList();
    }

    @Override
    public ObservableList<Homework> getFilteredHomeworkList() {
        return model.getFilteredHomeworkList();
    }

    @Override
    public ObservableList<Note> getFilteredNoteList() {
        return model.getFilteredNoteList();
    }

    @Override
    public ObservableList<String> getHistory() {
        return history.getHistory();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getUltiStudentFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public ReadOnlyProperty<Person> selectedPersonProperty() {
        return model.selectedPersonProperty();
    }

    @Override
    public ReadOnlyProperty<CapEntry> selectedCapEntryProperty() {
        return model.selectedCapEntryProperty();
    }

    @Override
    public ReadOnlyProperty<ModuleSemester> selectedModuleSemesterProperty() {
        return model.selectedModuleSemesterProperty();
    }

    @Override
    public ReadOnlyProperty<Homework> selectedHomeworkProperty() {
        return model.selectedHomeworkProperty();
    }

    @Override
    public ReadOnlyProperty<Note> selectedNoteProperty() {
        return model.selectedNoteProperty();
    }

    @Override
    public void setSelectedPerson(Person person) {
        model.setSelectedPerson(person);
    }

    @Override
    public void setSelectedCapEntry(CapEntry capEntry) {
        model.setSelectedCapEntry(capEntry);
    }

    @Override
    public void setSelectedModuleSemester(ModuleSemester moduleSemester) {
        model.setSelectedModuleSemester(moduleSemester);
    }

    @Override
    public void setSelectedHomework(Homework homework) {
        model.setSelectedHomework(homework);
    }

    @Override
    public void setSelectedNote(Note note) {
        model.setSelectedNote(note);
    }
}
