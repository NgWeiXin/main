package seedu.ultistudent.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.ultistudent.commons.exceptions.IllegalValueException;
import seedu.ultistudent.model.cap.ModuleSemester;

/**
 * Jackson-friendly version of {@link seedu.ultistudent.model.cap.ModuleSemester}.
 */
public class JsonAdaptedModuleSemester {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "ModuleSemester's %s field is missing!";

    private final String moduleSemester;

    /**
     * Constructs a {@code JsonAdaptedModuleSemester} with the given module semester details.
     */
    @JsonCreator
    public JsonAdaptedModuleSemester(@JsonProperty("moduleSemester") String moduleSemester) {
        this.moduleSemester = moduleSemester;
    }

    /**
     * Converts a given {@code ModuleSemester} into this class for Jackson use.
     */
    public JsonAdaptedModuleSemester(ModuleSemester source) {
        moduleSemester = source.value;
    }

    /**
     * Converts this Jackson-friendly adapted ModuleSemester object into the model's {@code ModuleSemester} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module semester.
     */
    public ModuleSemester toModelType() throws IllegalValueException {

        if (moduleSemester == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleSemester.class.getSimpleName()));
        }
        if (!ModuleSemester.isValidModuleSemester(moduleSemester)) {
            throw new IllegalValueException(ModuleSemester.MESSAGE_CONSTRAINTS);
        }
        final ModuleSemester modelModuleSemester = new ModuleSemester(moduleSemester);

        return modelModuleSemester;
    }
}
