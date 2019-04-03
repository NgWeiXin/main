package seedu.ultistudent.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.ultistudent.commons.exceptions.IllegalValueException;
import seedu.ultistudent.model.homework.Date;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.homework.HomeworkName;
import seedu.ultistudent.model.modulecode.ModuleCode;


/**
 * Jackson-friendly version of {@link Homework}.
 */
public class JsonAdaptedHomeworkList {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Homework's %s field is missing!";

    private final String moduleCode;
    private final String homeworkName;
    private final String date;
    //private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedHomeworkList} with the given homework details.
     */
    @JsonCreator
    public JsonAdaptedHomeworkList(@JsonProperty("homeworkName") String homeworkName,
                                   @JsonProperty("modulecode") String moduleCode,
                                   @JsonProperty("date") String date) {
        this.moduleCode = moduleCode;
        this.homeworkName = homeworkName;
        this.date = date;
    }
    /**
     * Converts a given {@code Homework} into this class for Jackson use.
     */
    public JsonAdaptedHomeworkList(Homework source) {
        this.moduleCode = source.getModuleCode().value;
        this.homeworkName = source.getHomeworkName().value;
        this.date = source.getDeadline().getDate();
    }

    /**
     * Converts this Jackson-friendly adapted homework object into the model's {@code Homework} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted homework.
     */
    public Homework toModelType() throws IllegalValueException {
        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modCode = new ModuleCode(moduleCode);

        if (homeworkName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    HomeworkName.class.getSimpleName()));
        }
        if (!HomeworkName.isValidHomeworkName(homeworkName)) {
            throw new IllegalValueException(HomeworkName.MESSAGE_CONSTRAINTS);
        }
        final HomeworkName hwName = new HomeworkName(homeworkName);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date deadline = new Date(date);

        return new Homework(modCode, hwName, deadline);
    }

}
