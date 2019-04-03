package seedu.ultistudent.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.ultistudent.model.UltiStudent;
import seedu.ultistudent.model.homework.Homework;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalHomework {

    public static final Homework HOMEWORK_FIRST = new HomeworkBuilder().withModuleCode("CS2103T")
            .withHomeworkName("Demo Script Draft").withDeadline("15/03/2019")
            .build();
    public static final Homework HOMEWORK_SECOND = new HomeworkBuilder().withModuleCode("CS2101")
            .withHomeworkName("Oral Presentation 1").withDeadline("17/03/2019")
            .build();

    public static final Homework HOMEWORK_THIRD = new HomeworkBuilder().withModuleCode("CS2103T")
            .withHomeworkName("Milestone2").withDeadline("19/03/2019")
            .build();
    public static final Homework HOMEWORK_FOURTH = new HomeworkBuilder().withModuleCode("CS2103T")
            .withHomeworkName("Milestone3").withDeadline("02/04/2019")
            .build();
    public static final Homework HOMEWORK_FIFTH = new HomeworkBuilder().withModuleCode("CS3243")
            .withHomeworkName("Tutorial7").withDeadline("21/03/2019")
            .build();
    public static final Homework HOMEWORK_SIXTH = new HomeworkBuilder().withModuleCode("CS3243")
            .withHomeworkName("Assignment1").withDeadline("22/03/2019")
            .build();
    public static final Homework HOMEWORK_SEVENTH = new HomeworkBuilder().withModuleCode("CS2101")
            .withHomeworkName("User Guide Peer Review").withDeadline("23/03/2019")
            .build();

    // Manually added
    public static final Homework HOMEWORK_EIGHTH = new HomeworkBuilder().withModuleCode("CS2103T")
            .withHomeworkName("Milestone 4").withDeadline("03/04/2019")
            .build();
    public static final Homework HOMEWORK_NINETH = new HomeworkBuilder().withModuleCode("CS2103T")
            .withHomeworkName("Milestone 5").withDeadline("04/05/2019")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Homework HOMEWORK_TENTH = new HomeworkBuilder().withModuleCode("CS2103T")
            .withHomeworkName("Milestone 5").withDeadline("04/05/2019")
            .build();
    public static final Homework HOMEWORK_ELEVENTH = new HomeworkBuilder().withModuleCode("CS2103T")
            .withHomeworkName("Milestone 5").withDeadline("04/05/2019")
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalHomework() {} // prevents instantiation

    /**
     * Returns an {@code UltiStudent} with all the typical homework.
     */
    public static UltiStudent getTypicalAddressBook() {
        UltiStudent ab = new UltiStudent();
        for (Homework homework : getTypicalHomework()) {
            ab.addHomework(homework);
        }
        return ab;
    }

    public static List<Homework> getTypicalHomework() {
        return new ArrayList<>(Arrays.asList(HOMEWORK_FIRST, HOMEWORK_SECOND, HOMEWORK_THIRD,
                HOMEWORK_FOURTH, HOMEWORK_FIFTH, HOMEWORK_SIXTH, HOMEWORK_SEVENTH));
    }
}
