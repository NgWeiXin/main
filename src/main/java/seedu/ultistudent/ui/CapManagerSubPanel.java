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
import seedu.ultistudent.model.cap.ModuleSemester;

/**
 * Panel containing the list of {@code ModuleSemester}.
 */
public class CapManagerSubPanel extends UiPart<Region> {
    private static final String FXML = "CapManagerSubPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CapManagerSubPanel.class);

    @FXML
    private ListView<ModuleSemester> moduleSemesterListView;

    public CapManagerSubPanel(ObservableList<ModuleSemester> moduleSemesters,
                              ObservableValue<ModuleSemester> selectedModuleSemester,
                              Consumer<ModuleSemester> onSelectedModuleSemesterChange) {
        super(FXML);
        moduleSemesterListView.setItems(moduleSemesters);
        moduleSemesterListView.setCellFactory(listView -> new ModuleSemesterListViewCell());
        moduleSemesterListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    logger.fine("Selection in ModuleSemester list panel changed to : '" + newValue + "'");
                    onSelectedModuleSemesterChange.accept(newValue);
                });
        selectedModuleSemester.addListener((observable, oldValue, newValue) -> {
            logger.fine("Selected ModuleSemester changed to: " + newValue);

            // Don't modify selection if we are already selecting the selected ModuleSemester,
            // otherwise we would have an infinite loop.
            if (Objects.equals(moduleSemesterListView.getSelectionModel().getSelectedItem(), newValue)) {
                return;
            }

            if (newValue == null) {
                moduleSemesterListView.getSelectionModel().clearSelection();
            } else {
                int index = moduleSemesterListView.getItems().indexOf(newValue);
                moduleSemesterListView.scrollTo(index);
                moduleSemesterListView.getSelectionModel().clearAndSelect(index);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ModuleSemester} using a {@code ModuleSemesterCard}.
     */
    class ModuleSemesterListViewCell extends ListCell<ModuleSemester> {
        @Override
        protected void updateItem(ModuleSemester moduleSemester, boolean empty) {
            super.updateItem(moduleSemester, empty);

            if (empty || moduleSemester == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ModuleSemesterCard(moduleSemester).getRoot());
            }
        }
    }

}
