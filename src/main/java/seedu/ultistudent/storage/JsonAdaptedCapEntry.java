package seedu.ultistudent.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.ultistudent.commons.exceptions.IllegalValueException;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleCredits;
import seedu.ultistudent.model.cap.ModuleGrade;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.modulecode.ModuleCode;

/**
 * Jackson-friendly version of {@link CapEntry}.
 */
public class JsonAdaptedCapEntry {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Cap Entry's %s field is missing!";

    private final String moduleCode;
    private final String moduleGrade;
    private final String moduleCredits;
    private final String moduleSemester;

    /**
     * Constructs a {@code JsonAdaptedCapEntry} with the given cap entry details.
     */
    @JsonCreator
    public JsonAdaptedCapEntry(@JsonProperty("modulecode") String moduleCode,
                               @JsonProperty("moduleGrade") String moduleGrade,
                               @JsonProperty("moduleCredits") String moduleCredits,
                               @JsonProperty("moduleSemester") String moduleSemester) {
        this.moduleCode = moduleCode;
        this.moduleGrade = moduleGrade;
        this.moduleCredits = moduleCredits;
        this.moduleSemester = moduleSemester;
    }

    /**
     * Converts a given {@code CapEntry} into this class for Jackson use.
     */
    public JsonAdaptedCapEntry(CapEntry source) {
        moduleCode = source.getModuleCode().value;
        moduleGrade = source.getModuleGrade().value;
        moduleCredits = source.getModuleCredits().value;
        moduleSemester = source.getModuleSemester().value;
    }

    /**
     * Converts this Jackson-friendly adapted CapEntry object into the model's {@code CapEntry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted cap entry.
     */
    public CapEntry toModelType() throws IllegalValueException {
        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        if (moduleGrade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleGrade.class.getSimpleName()));
        }
        if (!ModuleGrade.isValidModuleGrade(moduleGrade)) {
            throw new IllegalValueException(ModuleGrade.MESSAGE_CONSTRAINTS);
        }
        final ModuleGrade modelModuleGrade = new ModuleGrade(moduleGrade);

        if (moduleCredits == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCredits.class.getSimpleName()));
        }
        if (!ModuleCredits.isValidModuleCredits(moduleCredits)) {
            throw new IllegalValueException(ModuleCredits.MESSAGE_CONSTRAINTS);
        }
        final ModuleCredits modelModuleCredits = new ModuleCredits(moduleCredits);

        if (moduleSemester == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleSemester.class.getSimpleName()));
        }
        if (!ModuleSemester.isValidModuleSemester(moduleSemester)) {
            throw new IllegalValueException(ModuleSemester.MESSAGE_CONSTRAINTS);
        }
        final ModuleSemester modelModuleSemester = new ModuleSemester(moduleSemester);

        return new CapEntry(modelModuleCode, modelModuleGrade, modelModuleCredits, modelModuleSemester);
    }
}






