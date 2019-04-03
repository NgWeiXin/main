package seedu.ultistudent.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.ultistudent.commons.exceptions.IllegalValueException;
import seedu.ultistudent.model.UltiStudent;
import seedu.ultistudent.model.ReadOnlyUltiStudent;

import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.note.Note;

/**
 * An Immutable UltiStudent that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_CAP_ENTRY = "Cap Entry list contains duplicate cap entry(s).";
    public static final String MESSAGE_DUPLICATE_HOMEWORK = "Homework list contains duplicate homework";
    public static final String MESSAGE_DUPLICATE_NOTE = "Notes contains duplicate.";
    public static final String MESSAGE_DUPLICATE_MODULE_SEMESTER = "Module Semester list contains duplicate module "
            + "semester(s).";

    private final List<JsonAdaptedCapEntry> capEntryList = new ArrayList<>();
    private final List<JsonAdaptedHomeworkList> homeworkList = new ArrayList<>();
    private final List<JsonAdaptedNote> noteList = new ArrayList<>();
    private final List<JsonAdaptedModuleSemester> moduleSemesterList = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given cap.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("capEntryList") List<JsonAdaptedCapEntry> capEntries,
                                       @JsonProperty("homeworkList") List<JsonAdaptedHomeworkList> homeworkList,
                                       @JsonProperty("noteList") List<JsonAdaptedNote> notes,
                                       @JsonProperty("moduleSemesterList") List<JsonAdaptedModuleSemester>
                                                   moduleSemesterList) {
        this.capEntryList.addAll(capEntries);
        this.homeworkList.addAll(homeworkList);
        this.noteList.addAll(notes);
        this.moduleSemesterList.addAll(moduleSemesterList);
    }

    /**
     * Converts a given {@code ReadOnlyUltiStudent} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyUltiStudent source) {
        capEntryList.addAll(source.getCapEntryList().stream().map(JsonAdaptedCapEntry::new)
                .collect(Collectors.toList()));
        homeworkList.addAll(source.getHomeworkList().stream().map(JsonAdaptedHomeworkList::new)
                .collect(Collectors.toList()));
        noteList.addAll(source.getNoteList().stream().map(JsonAdaptedNote::new).collect(Collectors.toList()));
        moduleSemesterList.addAll(source.getModuleSemesterList().stream().map(JsonAdaptedModuleSemester::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this UltiStudent into the model's {@code UltiStudent} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UltiStudent toModelType() throws IllegalValueException {
        UltiStudent addressBook = new UltiStudent();

        for (JsonAdaptedCapEntry jsonAdaptedCapEntry : capEntryList) {
            CapEntry capEntry = jsonAdaptedCapEntry.toModelType();
            if (addressBook.hasCapEntry(capEntry)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CAP_ENTRY);
            }
            addressBook.addCapEntry(capEntry);
        }

        for (JsonAdaptedHomeworkList jsonAdaptedHomeworkList : homeworkList) {
            Homework homework = jsonAdaptedHomeworkList.toModelType();
            if (addressBook.hasHomework(homework)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_HOMEWORK);
            }
            addressBook.addHomework(homework);
        }

        for (JsonAdaptedNote jsonAdaptedNote : noteList) {
            Note note = jsonAdaptedNote.toModelType();
            if (addressBook.hasNote(note)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_NOTE);
            }
            addressBook.addNote(note);
        }

        for (JsonAdaptedModuleSemester jsonAdaptedModuleSemester : moduleSemesterList) {
            ModuleSemester moduleSemester = jsonAdaptedModuleSemester.toModelType();
            if (addressBook.hasModuleSemester(moduleSemester)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE_SEMESTER);
            }
            addressBook.addModuleSemester(moduleSemester);
        }

        return addressBook;
    }

}
