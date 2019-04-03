package seedu.ultistudent.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.ultistudent.commons.exceptions.IllegalValueException;
import seedu.ultistudent.model.modulecode.ModuleCode;
import seedu.ultistudent.model.note.Content;
import seedu.ultistudent.model.note.Note;
import seedu.ultistudent.model.note.NoteName;

/**
 * Jackson-friendly version of {@link Note}.
 */
public class JsonAdaptedNote {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Note's %s field is missing!";

    private final String moduleCode;
    private final String noteName;
    private final String content;

    /**
     * Constructs a {@code JsonAdaptedNote} with the given note details.
     */
    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("modulecode") String moduleCode,
                           @JsonProperty("noteName") String noteName,
                           @JsonProperty("content") String content) {
        this.moduleCode = moduleCode;
        this.noteName = noteName;
        this.content = content;
    }

    /**
     * Converts a given {@code Note} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        moduleCode = source.getModuleCode().value;
        noteName = source.getNoteName().noteName;
        content = source.getContent().content;
    }

    /**
     * Converts this Jackson-friendly adapted Note object into the model's {@code Note} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted note.
     */
    public Note toModelType() throws IllegalValueException {

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        if (noteName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NoteName.class.getSimpleName()));
        }
        if (!NoteName.isValidNoteName(noteName)) {
            throw new IllegalValueException(NoteName.MESSAGE_CONSTRAINTS);
        }
        final NoteName modelNoteName = new NoteName(noteName);

        if (content == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Content.class.getSimpleName()));
        }

        final Content modelContent = new Content(content);

        return new Note(modelModuleCode, modelNoteName, modelContent);
    }
}






