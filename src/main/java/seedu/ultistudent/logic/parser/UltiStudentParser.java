package seedu.ultistudent.logic.parser;

import static seedu.ultistudent.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ultistudent.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.ultistudent.logic.parser.CliSyntax.CAP_MANAGER;
import static seedu.ultistudent.logic.parser.CliSyntax.HOMEWORK_MANAGER;
import static seedu.ultistudent.logic.parser.CliSyntax.NOTES_MANAGER;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.ultistudent.logic.commands.AddCapEntryCommand;
import seedu.ultistudent.logic.commands.AddCommand;
import seedu.ultistudent.logic.commands.AddHomeworkCommand;
import seedu.ultistudent.logic.commands.AddNoteCommand;
import seedu.ultistudent.logic.commands.ClearCommand;
import seedu.ultistudent.logic.commands.Command;
import seedu.ultistudent.logic.commands.DeleteCapEntryCommand;
import seedu.ultistudent.logic.commands.DeleteCommand;
import seedu.ultistudent.logic.commands.DeleteHomeworkCommand;
import seedu.ultistudent.logic.commands.DeleteNoteCommand;
import seedu.ultistudent.logic.commands.EditCapEntryByModuleCodeCommand;
import seedu.ultistudent.logic.commands.EditCapEntryCommand;
import seedu.ultistudent.logic.commands.EditCommand;
import seedu.ultistudent.logic.commands.EditHomeworkCommand;
import seedu.ultistudent.logic.commands.EditNoteCommand;
import seedu.ultistudent.logic.commands.ExitCommand;
import seedu.ultistudent.logic.commands.FindCommand;
import seedu.ultistudent.logic.commands.FindModuleCommand;
import seedu.ultistudent.logic.commands.FindNoteCommand;
import seedu.ultistudent.logic.commands.FindSemesterCommand;
import seedu.ultistudent.logic.commands.HelpCommand;
import seedu.ultistudent.logic.commands.HistoryCommand;
import seedu.ultistudent.logic.commands.ListCapEntryCommand;
import seedu.ultistudent.logic.commands.ListCommand;
import seedu.ultistudent.logic.commands.ListHomeworkCommand;
import seedu.ultistudent.logic.commands.ListNoteCommand;
import seedu.ultistudent.logic.commands.OpenCommand;
import seedu.ultistudent.logic.commands.RedoCommand;
import seedu.ultistudent.logic.commands.SaveNoteCommand;
import seedu.ultistudent.logic.commands.SelectCommand;
import seedu.ultistudent.logic.commands.UndoCommand;
import seedu.ultistudent.logic.parser.exceptions.ParseException;
import seedu.ultistudent.ui.StatusBarFooter;

/**
 * Parses user input.
 */
public class UltiStudentParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        //===== Add commands in UltiStudent =====//
        case AddCommand.COMMAND_WORD:
            return new AddCommand();

        case AddCapEntryCommand.COMMAND_WORD:
            return new AddCapEntryCommandParser().parse(arguments);

        case AddHomeworkCommand.COMMAND_WORD:
            return new AddHomeworkCommandParser().parse(arguments);

        case AddNoteCommand.COMMAND_WORD:
            return new AddNoteCommandParser().parse(arguments);

        //===== DeleteCommand in UltiStudent =====//
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand();

        case DeleteHomeworkCommand.COMMAND_WORD:
            return new DeleteHomeworkCommandParser().parse(arguments);

        case DeleteCapEntryCommand.COMMAND_WORD:
            return new DeleteCapEntryCommandParser().parse(arguments);

        case DeleteNoteCommand.COMMAND_WORD:
            return new DeleteNoteCommandParser().parse(arguments);

        //===== Edit Commands in UltiStudent =====//
        case EditCommand.COMMAND_WORD:
            return new EditCommand();

        case EditHomeworkCommand.COMMAND_WORD:
            if (!StatusBarFooter.getCurrentManagerStatus().equals(HOMEWORK_MANAGER)) {
                throw new ParseException("Please open Homework Manager before using 'edit-hw' command.");
            }
            return new EditHomeworkCommandParser().parse(arguments);

        case EditCapEntryByModuleCodeCommand.COMMAND_WORD:
            if (!StatusBarFooter.getCurrentManagerStatus().equals(CAP_MANAGER)) {
                throw new ParseException("Please open Cap Manager before using 'edit-cap-mc' command.");
            }
            return new EditCapEntryByModuleCodeCommandParser().parse(arguments);

        case EditCapEntryCommand.COMMAND_WORD:
            if (!StatusBarFooter.getCurrentManagerStatus().equals(CAP_MANAGER)) {
                throw new ParseException("Please open Cap Manager before using 'edit-cap' command.");
            }
            return new EditCapEntryCommandParser().parse(arguments);

        case EditNoteCommand.COMMAND_WORD:
            if (!StatusBarFooter.getCurrentManagerStatus().equals(NOTES_MANAGER)) {
                throw new ParseException("Please open Notes Manager before using 'edit-note' command.");
            }
            return new EditNoteCommandParser().parse(arguments);

        //===== Other useful Commands in UltiStudent =====//
        case SaveNoteCommand.COMMAND_WORD:
            return new SaveNoteCommand();

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindSemesterCommand.COMMAND_WORD:
            return new FindSemesterCommandParser().parse(arguments);

        case FindModuleCommand.COMMAND_WORD:
            return new FindModuleCommandParser().parse(arguments);

        case FindNoteCommand.COMMAND_WORD:
            return new FindNoteCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListNoteCommand.COMMAND_WORD:
            return new ListNoteCommand();

        case ListCapEntryCommand.COMMAND_WORD:
            return new ListCapEntryCommand();

        case ListHomeworkCommand.COMMAND_WORD:
            return new ListHomeworkCommand();

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case OpenCommand.COMMAND_WORD:
            return new OpenCommandParser().parse(arguments);


        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
