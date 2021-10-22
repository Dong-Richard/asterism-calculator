package cartoon;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Top-Level graphical class. Handles the creation of all panes in the program.
 */
public class PaneOrganizer {
    private final BorderPane root;


    /**
     * Creates the overall borderpane, and adds the shape pane and control pane to its center and bottom.
     */
    public PaneOrganizer() {
        this.root = new BorderPane();

        Pane shapePane = new Pane();
        shapePane.setStyle("-fx-background-color: black");


        VBox controlPane = new VBox();
        controlPane.setStyle("-fx-background-color: grey");

        this.root.setCenter(shapePane);
        this.root.setBottom(controlPane);

        new Cartoon(shapePane, controlPane);
    }

    public BorderPane getRoot() {
        return this.root;
    }
}
