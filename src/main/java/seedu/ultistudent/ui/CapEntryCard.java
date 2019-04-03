package seedu.ultistudent.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.ultistudent.model.cap.CapEntry;

/**
 * An UI component that displays information of a {@code CapEntry}.
 */
public class CapEntryCard extends UiPart<Region> {

    private static final String FXML = "CapEntryCard.fxml";

    public final CapEntry capEntry;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label moduleCode;
    @FXML
    private Label moduleGrade;
    @FXML
    private Label moduleCredits;
    @FXML
    private Label taken;

    public CapEntryCard(CapEntry capEntry, int displayedIndex) {
        super(FXML);
        this.capEntry = capEntry;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(capEntry.getModuleCode().toString());
        moduleGrade.setText("Grade: " + capEntry.getModuleGrade().toString());
        moduleCredits.setText("MC: " + capEntry.getModuleCredits().toString());
        taken.setText("Taken: " + capEntry.getModuleSemester());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CapEntryCard)) {
            return false;
        }

        // state check
        CapEntryCard card = (CapEntryCard) other;
        return id.getText().equals(card.id.getText())
                && capEntry.equals(card.capEntry);
    }
}
