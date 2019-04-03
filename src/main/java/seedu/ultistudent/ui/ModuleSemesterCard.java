package seedu.ultistudent.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.ultistudent.model.cap.ModuleSemester;

/**
 * An UI component that displays information of a {@code ModuleSemester}.
 */
public class ModuleSemesterCard extends UiPart<Region> {

    private static final String FXML = "ModuleSemesterCard.fxml";

    public final ModuleSemester moduleSemester;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label moduleSemesterLabel;

    public ModuleSemesterCard(ModuleSemester moduleSemester) {
        super(FXML);
        this.moduleSemester = moduleSemester;
        moduleSemesterLabel.setText(moduleSemester.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleSemesterCard)) {
            return false;
        }

        // state check
        ModuleSemesterCard card = (ModuleSemesterCard) other;
        return id.getText().equals(card.id.getText())
                && moduleSemester.equals(card.toString());
    }
}
