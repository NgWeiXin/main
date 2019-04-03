package seedu.ultistudent.testutil;

import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_CREDITS_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_CREDITS_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_GRADE_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_GRADE_CS1002;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_SEMESTER_CS1001;
import static seedu.ultistudent.logic.commands.CommandTestUtil.VALID_MODULE_SEMESTER_CS1002;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.ultistudent.model.UltiStudent;
import seedu.ultistudent.model.cap.CapEntry;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalCapEntry {

    public static final CapEntry CS2101 = new CapEntryBuilder().withModuleCode("CS2101")
            .withModuleGrade("A+").withModuleCredits("4")
            .withModuleSemester("Y1S1")
            .build();

    public static final CapEntry CS2102 = new CapEntryBuilder().withModuleCode("CS2102")
            .withModuleGrade("B").withModuleCredits("6")
            .withModuleSemester("Y2S2")
            .build();

    public static final CapEntry CS2103 = new CapEntryBuilder().withModuleCode("CS2103")
            .withModuleGrade("A-").withModuleCredits("3")
            .withModuleSemester("Y3S2")
            .build();

    public static final CapEntry CS2104 = new CapEntryBuilder().withModuleCode("CS2104")
            .withModuleGrade("C+").withModuleCredits("9")
            .withModuleSemester("Y1S2")
            .build();

    public static final CapEntry CS2105 = new CapEntryBuilder().withModuleCode("CS2105")
            .withModuleGrade("B+").withModuleCredits("7")
            .withModuleSemester("Y3S1")
            .build();

    public static final CapEntry CS2106 = new CapEntryBuilder().withModuleCode("CS2106")
            .withModuleGrade("B-").withModuleCredits("14")
            .withModuleSemester("Y5S2")
            .build();

    public static final CapEntry CS2107 = new CapEntryBuilder().withModuleCode("CS2107")
            .withModuleGrade("C").withModuleCredits("12")
            .withModuleSemester("Y4S2")
            .build();

    // Manually added - Cap Entry's details found in {@code CommandTestUtil}
    public static final CapEntry CS1001 = new CapEntryBuilder().withModuleCode(VALID_MODULE_CODE_CS1001)
            .withModuleGrade(VALID_MODULE_GRADE_CS1001)
            .withModuleCredits(VALID_MODULE_CREDITS_CS1001).withModuleSemester(VALID_MODULE_SEMESTER_CS1001).build();
    public static final CapEntry CS1002 = new CapEntryBuilder().withModuleCode(VALID_MODULE_CODE_CS1002)
            .withModuleGrade(VALID_MODULE_GRADE_CS1002)
            .withModuleCredits(VALID_MODULE_CREDITS_CS1002).withModuleSemester(VALID_MODULE_SEMESTER_CS1002).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalCapEntry() {} // prevents instantiation

    /**
     * Returns an {@code UltiStudent} with all the typical cap entries.
     */
    public static UltiStudent getTypicalAddressBook() {
        UltiStudent ab = new UltiStudent();
        for (CapEntry capEntry : getTypicalCapEntryList()) {
            ab.addCapEntry(capEntry);
        }
        return ab;
    }

    public static List<CapEntry> getTypicalCapEntryList() {
        return new ArrayList<>(Arrays.asList(CS2101, CS2102, CS2103, CS2104, CS2105, CS2106, CS2107));
    }
}
