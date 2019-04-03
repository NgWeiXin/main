package seedu.ultistudent.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    //===== HomeworkManager =====//
    public static final Prefix PREFIX_MODULECODE = new Prefix("mc/");
    public static final Prefix PREFIX_HOMEWORK = new Prefix("hw/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("d/");

    //===== CapManager =====//
    public static final Prefix PREFIX_MODULEGRADE = new Prefix("g/");
    public static final Prefix PREFIX_MODULECREDITS = new Prefix("mcs/");
    public static final Prefix PREFIX_SEMESTER = new Prefix("sem/");

    //===== NotesManager =====/
    public static final Prefix PREFIX_NOTE_NAME = new Prefix("n/");

    //===== UI =====//
    public static final String HOMEWORK_MANAGER = "HomeworkManager";
    public static final String NOTES_MANAGER = "NotesManager";
    public static final String CAP_MANAGER = "CapManager";
}
