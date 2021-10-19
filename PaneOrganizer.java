package cartoon;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PaneOrganizer {
    private final BorderPane root;

    public PaneOrganizer() {
        this.root = new BorderPane();

        Pane shapePane = new Pane();
        shapePane.setStyle("-fx-background-color: black");

        VBox controlPane = new VBox();
        controlPane.setStyle("-fx-background-color: grey");

        this.root.setCenter(shapePane);
        this.root.setBottom(controlPane);

        new Cartoon(this.root);
    }

    public BorderPane getRoot() {
        return this.root;
    }
}
