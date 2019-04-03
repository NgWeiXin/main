package seedu.ultistudent.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.ultistudent.commons.exceptions.IllegalValueException;
import seedu.ultistudent.model.ReadOnlyUltiStudent;
import seedu.ultistudent.model.UltiStudent;

import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.note.Note;

/**
 * An Immutable UltiStudent that is serializable to JSON format.
 */
@JsonRootName(value = "ultistudent")
class JsonSerializableUltiStudent {

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
     * Constructs a {@code JsonSerializableUltiStudent} with the given lists.
     */
    @JsonCreator
    public JsonSerializableUltiStudent(@JsonProperty("capEntryList") List<JsonAdaptedCapEntry> capEntries,
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
     * @param source future changes to this will not affect the created {@code JsonSerializableUltiStudent}.
     */
    public JsonSerializableUltiStudent(ReadOnlyUltiStudent source) {
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
        UltiStudent ultiStudent = new UltiStudent();

        for (JsonAdaptedCapEntry jsonAdaptedCapEntry : capEntryList) {
            CapEntry capEntry = jsonAdaptedCapEntry.toModelType();
            if (ultiStudent.hasCapEntry(capEntry)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CAP_ENTRY);
            }
            ultiStudent.addCapEntry(capEntry);
        }

        for (JsonAdaptedHomeworkList jsonAdaptedHomeworkList : homeworkList) {
            Homework homework = jsonAdaptedHomeworkList.toModelType();
            if (ultiStudent.hasHomework(homework)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_HOMEWORK);
            }
            ultiStudent.addHomework(homework);
        }

        for (JsonAdaptedNote jsonAdaptedNote : noteList) {
            Note note = jsonAdaptedNote.toModelType();
            if (ultiStudent.hasNote(note)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_NOTE);
            }
            ultiStudent.addNote(note);
        }

        for (JsonAdaptedModuleSemester jsonAdaptedModuleSemester : moduleSemesterList) {
            ModuleSemester moduleSemester = jsonAdaptedModuleSemester.toModelType();
            if (ultiStudent.hasModuleSemester(moduleSemester)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE_SEMESTER);
            }
            ultiStudent.addModuleSemester(moduleSemester);
        }

        return ultiStudent;
    }

}
