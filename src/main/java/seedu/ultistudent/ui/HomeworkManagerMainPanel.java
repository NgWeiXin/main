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
import seedu.ultistudent.model.homework.Homework;

/**
 * Panel containing the list of {@code Homework}.
 */
public class HomeworkManagerMainPanel extends UiPart<Region> {
    private static final String FXML = "HomeworkManagerMainPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(HomeworkManagerMainPanel.class);

    @FXML
    private ListView<Homework> homeworkListView;

    public HomeworkManagerMainPanel(ObservableList<Homework> homeworkList,
                                    ObservableValue<Homework> selectedHomework,
                                    Consumer<Homework> onSelectedHomeworkChange) {
        super(FXML);
        homeworkListView.setItems(homeworkList);
        homeworkListView.setCellFactory(listView -> new HomeworkListViewCell());
        homeworkListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            logger.fine("Selection in homework list panel changed to : '" + newValue + "'");
            onSelectedHomeworkChange.accept(newValue);
        });
        selectedHomework.addListener((observable, oldValue, newValue) -> {
            logger.fine("Selected homework changed to: " + newValue);

            // Don't modify selection if we are already selecting the selected homework,
            // otherwise we would have an infinite loop.
            if (Objects.equals(homeworkListView.getSelectionModel().getSelectedItem(), newValue)) {
                return;
            }

            if (newValue == null) {
                homeworkListView.getSelectionModel().clearSelection();
            } else {
                int index = homeworkListView.getItems().indexOf(newValue);
                homeworkListView.scrollTo(index);
                homeworkListView.getSelectionModel().clearAndSelect(index);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Homework} using a {@code HomeworkCard}.
     */
    class HomeworkListViewCell extends ListCell<Homework> {
        @Override
        protected void updateItem(Homework homework, boolean empty) {
            super.updateItem(homework, empty);

            if (empty || homework == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new HomeworkCard(homework, getIndex() + 1).getRoot());
            }
        }
    }

}
