package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.homework.*;

public class JsonAdaptedHomeworkList {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Homework's %s field is missing!";

    private final String moduleCode;
    private final String homeworkName;
    private final String day;
    private final String year;
    private final String month;
    //private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedHomeworkList(@JsonProperty("homeworkName") String homeworkName,
                                   @JsonProperty("moduleCode") String moduleCode,
                                   @JsonProperty("day") String day,
                                   @JsonProperty("month") String month,
                                   @JsonProperty("year") String year) {
        this.moduleCode = moduleCode;
        this.homeworkName = homeworkName;
        this.month = month;
        this.day = day;
        this.year = year;
    }
    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedHomeworkList(Homework source) {
        this.moduleCode = source.getModuleCode().value;
        this.homeworkName = source.getHomeworkName().value;
        this.day = source.getDeadline().getDay().value;
        this.year = source.getDeadline().getYear().value;
        this.month = source.getDeadline().getMonth().name();

    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Homework toModelType() throws IllegalValueException {
        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modCode = new ModuleCode(moduleCode);

        if (homeworkName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, HomeworkName.class.getSimpleName()));
        }
        if (!HomeworkName.isValidHomeworkName(homeworkName)) {
            throw new IllegalValueException(HomeworkName.MESSAGE_CONSTRAINTS);
        }
        final HomeworkName hwName = new HomeworkName(homeworkName);

        if (day == null || year == null || month == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Day.isValidDay(day)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        if (!Year.isValidYear(year)) {
            throw new IllegalValueException(Year.MESSAGE_CONSTRAINTS);
        }
        final Date deadline = new Date(new Day(day), Month.JAN, new Year(year));

        return new Homework(modCode, hwName, deadline);
    }

}
