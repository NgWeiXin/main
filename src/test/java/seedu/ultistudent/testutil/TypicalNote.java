package seedu.ultistudent.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.ultistudent.model.UltiStudent;
import seedu.ultistudent.model.note.Note;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalNote {

    public static final Note NOTE_FIRST = new NoteBuilder()
            .withModuleCode("CS2103T")
            .withNoteName("Demo Script Draft").withContent("")
            .build();
    public static final Note NOTE_SECOND = new NoteBuilder()
            .withModuleCode("CS2101")
            .withNoteName("Oral Presentation 1").withContent("a")
            .build();

    public static final Note NOTE_THIRD = new NoteBuilder()
            .withModuleCode("CS2103T")
            .withNoteName("Milestone2").withContent("b")
            .build();
    public static final Note NOTE_FOURTH = new NoteBuilder()
            .withModuleCode("CS2103T")
            .withNoteName("Milestone3").withContent("c")
            .build();
    public static final Note NOTE_FIFTH = new NoteBuilder()
            .withModuleCode("CS3243")
            .withNoteName("Tutorial7").withContent("d")
            .build();
    public static final Note NOTE_SIXTH = new NoteBuilder()
            .withModuleCode("CS3243")
            .withNoteName("Assignment1").withContent("e")
            .build();
    public static final Note NOTE_SEVENTH = new NoteBuilder()
            .withModuleCode("CS2101")
            .withNoteName("User Guide Peer Review").withContent("f")
            .build();

    // Manually added
    public static final Note NOTE_EIGHTH = new NoteBuilder()
            .withModuleCode("CS2103T")
            .withNoteName("Milestone 4").withContent("g")
            .build();
    public static final Note NOTE_NINETH = new NoteBuilder()
            .withModuleCode("CS2103T")
            .withNoteName("Milestone 5").withContent("h")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Note NOTE_TENTH = new NoteBuilder()
            .withModuleCode("CS2103T")
            .withNoteName("Milestone 5").withContent("i")
            .build();
    public static final Note NOTE_ELEVENTH = new NoteBuilder()
            .withModuleCode("CS2103T")
            .withNoteName("Milestone 5").withContent("j")
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalNote() {} // prevents instantiation

    /**
     * Returns an {@code UltiStudent} with all the typical homework.
     */
    public static UltiStudent getTypicalAddressBook() {
        UltiStudent ab = new UltiStudent();
        for (Note note : getTypicalNote()) {
            ab.addNote(note);
        }
        return ab;
    }

    public static List<Note> getTypicalNote() {
        return new ArrayList<>(Arrays.asList(NOTE_FIRST, NOTE_SECOND,
                NOTE_THIRD, NOTE_FOURTH, NOTE_FIFTH, NOTE_SIXTH, NOTE_SEVENTH));
    }
}
