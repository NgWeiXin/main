package seedu.ultistudent.ui;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.ultistudent.commons.core.LogsCenter;
import seedu.ultistudent.model.note.Note;

/**
 * Panel containing the list of {@code Note}.
 */
public class NotesManagerSubPanel extends UiPart<Region> {
    private static final String FXML = "NotesManagerSubPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(NotesManagerSubPanel.class);

    @FXML
    private ListView<Note> notesListView;

    public NotesManagerSubPanel(ObservableList<Note> notesList,
                                ObservableValue<Note> selectedNote,
                                Consumer<Note> onSelectedNoteChange) {
        super(FXML);
        notesListView.setItems(notesList);
        notesListView.setCellFactory(listView -> new NotesListViewCell());
        notesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            logger.fine("Selection in note list panel changed to : '" + newValue + "'");
            onSelectedNoteChange.accept(newValue);
        });
        selectedNote.addListener((observable, oldValue, newValue) -> {
            logger.fine("Selected note changed to: " + newValue);

            // Don't modify selection if we are already selecting the selected note,
            // otherwise we would have an infinite loop.
            if (Objects.equals(notesListView.getSelectionModel().getSelectedItem(), newValue)) {
                return;
            }

            if (newValue == null) {
                notesListView.getSelectionModel().clearSelection();
            } else {
                int index = notesListView.getItems().indexOf(newValue);
                notesListView.scrollTo(index);
                notesListView.getSelectionModel().clearAndSelect(index);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Note} using a {@code NoteCard}.
     */
    class NotesListViewCell extends ListCell<Note> {
        @Override
        protected void updateItem(Note note, boolean empty) {
            super.updateItem(note, empty);

            if (empty || note == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NoteCard(note, getIndex() + 1).getRoot());
            }
        }
    }

}
