package seedu.ultistudent.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.ultistudent.commons.core.LogsCenter;
import seedu.ultistudent.model.cap.CapEntry;


/**
 * Panel containing the list of {@code CapEntry}.
 */
public class CapManagerMainPanel extends UiPart<Region> {
    private static final String FXML = "CapManagerMainPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CapManagerMainPanel.class);

    @FXML
    private FlowPane capEntryFlowPane;
    @FXML
    private Label cap;
    @FXML
    private ScrollPane scrollPane;

    public CapManagerMainPanel(ObservableList<CapEntry> capEntries) {
        super(FXML);

        scrollPane.setFitToWidth(true);

        capEntryFlowPane.setHgap(10);
        capEntryFlowPane.setVgap(10);
        capEntryFlowPane.prefWidthProperty().bind(scrollPane.widthProperty());
        capEntryFlowPane.prefHeightProperty().bind(scrollPane.heightProperty());
        cap.setText(CapEntry.getCapScore());

        int index = 1;
        for (CapEntry capEntry: capEntries) {
            capEntryFlowPane.getChildren().add(new CapEntryCard(capEntry, index).getRoot());
            index++;
        }

        capEntries.addListener((ListChangeListener<CapEntry>) c -> {
            capEntryFlowPane.getChildren().clear();
            int i = 1;
            for (CapEntry capEntry: capEntries) {
                capEntryFlowPane.getChildren().add(new CapEntryCard(capEntry, i).getRoot());
                i++;
            }
            cap.setText(CapEntry.getCapScore());
        });

    }

}
