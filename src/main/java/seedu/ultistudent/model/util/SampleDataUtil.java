package seedu.ultistudent.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.ultistudent.model.UltiStudent;
import seedu.ultistudent.model.ReadOnlyUltiStudent;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleCredits;
import seedu.ultistudent.model.cap.ModuleGrade;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.homework.Date;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.homework.HomeworkName;
import seedu.ultistudent.model.modulecode.ModuleCode;
import seedu.ultistudent.model.note.Content;
import seedu.ultistudent.model.note.Note;
import seedu.ultistudent.model.note.NoteName;
import seedu.ultistudent.model.tag.Tag;

/**
 * Contains utility methods for populating {@code UltiStudent} with sample data.
 */
public class SampleDataUtil {


    public static Homework[] getSampleHomework() {
        return new Homework[] {
            new Homework(new ModuleCode("CS2030"), new HomeworkName("Tutorial 1"), new Date("20/04/2019"))
            //TODO: add 2 or 3 more homeworks
        };
    }

    public static Note[] getSampleNote() {
        return new Note[] {
            new Note(new ModuleCode("CS2103T"), new NoteName("My First Lesson"), new Content("Hello World"))
            //TODO: add 2 or 3 more notes
        };
    }

    public static CapEntry[] getSampleCapEntry() {
        return new CapEntry[] {
            new CapEntry(new ModuleCode("CS1101S"), new ModuleGrade("A"), new ModuleCredits("4"),
                    new ModuleSemester("Y1S1"))
                    //TODO: add 2 or 3 more cap entries
        };
    }

    public static ReadOnlyUltiStudent getSampleUltiStudent() {
        UltiStudent sampleUltiStudent = new UltiStudent();
        for (Homework sampleHomework : getSampleHomework()) {
            sampleUltiStudent.addHomework(sampleHomework);
        }
        for (Note sampleNote : getSampleNote()) {
            sampleUltiStudent.addNote(sampleNote);
        }
        for (CapEntry sampleCapEntry : getSampleCapEntry()) {
            sampleUltiStudent.addCapEntry(sampleCapEntry);
        }
        return sampleUltiStudent;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
