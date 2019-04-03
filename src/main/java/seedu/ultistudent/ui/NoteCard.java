package seedu.ultistudent.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.ultistudent.model.note.Note;

/**
 * An UI component that displays information of a {@code Note}.
 */
public class NoteCard extends UiPart<Region> {

    private static final String FXML = "NoteListCard.fxml";

    public final Note note;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label noteName;
    @FXML
    private Label moduleCode;

    public NoteCard(Note note, int displayedIndex) {
        super(FXML);
        this.note = note;
        id.setText(displayedIndex + ". ");
        noteName.setText(note.getNoteName().toString());
        moduleCode.setText("Module: " + note.getModuleCode().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteCard)) {
            return false;
        }

        // state check
        NoteCard card = (NoteCard) other;
        return id.getText().equals(card.id.getText())
                && note.equals(card.note);
    }
}
