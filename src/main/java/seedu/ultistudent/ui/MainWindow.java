package seedu.ultistudent.ui;

import static seedu.ultistudent.logic.parser.CliSyntax.CAP_MANAGER;
import static seedu.ultistudent.logic.parser.CliSyntax.HOMEWORK_MANAGER;
import static seedu.ultistudent.logic.parser.CliSyntax.NOTES_MANAGER;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.ultistudent.commons.core.GuiSettings;
import seedu.ultistudent.commons.core.LogsCenter;
import seedu.ultistudent.logic.Logic;
import seedu.ultistudent.logic.commands.CommandResult;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container

    private HomeworkManagerMainPanel homeworkManagerMainPanel;
    private NotesManagerMainPanel notesManagerMainPanel;
    private CapManagerMainPanel capManagerMainPanel;

    private NotesManagerSubPanel notesManagerSubPanel;
    private CapManagerSubPanel capManagerSubPanel;

    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private IconListPanel iconListPanel;
    private StatusBarFooter statusBarFooter;

    @FXML
    private StackPane mainPanelPlaceholder;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane subPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane iconListPanelPlaceholder;

    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        iconListPanel = new IconListPanel();
        iconListPanelPlaceholder.getChildren().add(iconListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        statusBarFooter = new StatusBarFooter(HOMEWORK_MANAGER, logic.getUltiStudent());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand, logic.getHistory());
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        handleSwitchToHomeworkManager();
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.ultistudent.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isChangeManager()) {
                String [] selectedManager = commandResult.getFeedbackToUser().split(" ");
                logger.info(selectedManager[0]);
                switch (selectedManager[0]) {
                case HOMEWORK_MANAGER:
                    handleSwitchToHomeworkManager();
                    break;
                case NOTES_MANAGER:
                    handleSwitchToNotesManager();
                    break;
                case CAP_MANAGER:
                    handleSwitchToCapsManager();
                    break;
                default:
                    break;
                }
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    /**
     * Handles the view for Homework Manager.
     */
    @FXML
    public void handleSwitchToHomeworkManager() {
        //TODO: handles the setting up of HomeworkManager view
        dimAllIcons();
        iconListPanel.setHomeworkManagerIconBrightness(0.8);

        statusBarFooter.setCurrentManagerText(HOMEWORK_MANAGER);

        clearDisplay();
        homeworkManagerMainPanel = new HomeworkManagerMainPanel(logic.getFilteredHomeworkList(),
                                                              logic.selectedHomeworkProperty(),
                                                              logic::setSelectedHomework);
        mainPanelPlaceholder.getChildren().add(homeworkManagerMainPanel.getRoot());
    }

    /**
     * Handles the view for Notes Manager.
     */
    @FXML
    public void handleSwitchToNotesManager() {
        //TODO: handles the setting up of NotesManager view
        dimAllIcons();
        iconListPanel.setNotesManagerIconBrightness(0.8);

        statusBarFooter.setCurrentManagerText(NOTES_MANAGER);

        clearDisplay();
        notesManagerMainPanel = new NotesManagerMainPanel(logic.selectedNoteProperty());
        notesManagerSubPanel = new NotesManagerSubPanel(logic.getFilteredNoteList(),
                                                        logic.selectedNoteProperty(),
                                                        logic::setSelectedNote);
        mainPanelPlaceholder.getChildren().add(notesManagerMainPanel.getRoot());
        subPanelPlaceholder.getChildren().add(notesManagerSubPanel.getRoot());
    }

    /**
     * Handles the view for Caps Manager.
     */
    @FXML
    public void handleSwitchToCapsManager() {
        //TODO: handles the setting up of CapCalculator view
        dimAllIcons();
        iconListPanel.setCapManagerIconBrightness(0.8);

        statusBarFooter.setCurrentManagerText(CAP_MANAGER);

        clearDisplay();
        capManagerMainPanel = new CapManagerMainPanel(logic.getFilteredCapEntryList());
        capManagerSubPanel = new CapManagerSubPanel(logic.getFilteredModuleSemesterList(),
                                                    logic.selectedModuleSemesterProperty(),
                                                    logic::setSelectedModuleSemester);
        mainPanelPlaceholder.getChildren().add(capManagerMainPanel.getRoot());
        subPanelPlaceholder.getChildren().add(capManagerSubPanel.getRoot());
    }

    private void clearDisplay() {
        mainPanelPlaceholder.getChildren().clear();
        subPanelPlaceholder.getChildren().clear();
    }

    private void dimAllIcons() {
        iconListPanel.setHomeworkManagerIconBrightness(0.4);
        iconListPanel.setNotesManagerIconBrightness(0.4);
        iconListPanel.setCapManagerIconBrightness(0.4);
    }

}
