package cartoon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * My cartoon models the night sky exactly as it is in real life, simulated using javafx. Here is a quick overview
 * of my classes:
 * Cartoon: Main logic class, handles key inputs and timeline movements. Contains logic to track the time that passes
 * per tick.
 * Asterism: Handles the individual composite shapes within the bigger composite shape. Contains methods to translate
 * from alt-az coordinates to rectangular, and update the position of labels
 * Constants: Defines coordinates of all stars
 * NightSky: The main composite object, contains all asterisms, has method to rotate about the north celestial pole.
 * PaneOrganizer: Sets up the various panes.
 */

public class App extends Application {

    // Create top-level object, set up the scene, and show the stage here.
    @Override
    public void start(Stage stage) {
        PaneOrganizer paneOrganizer = new PaneOrganizer();
        Scene scene = new Scene(paneOrganizer.getRoot(), Constants.APP_WIDTH, Constants.APP_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Planetarium");
        stage.show();
    }

    /*
     * Here is the mainline! No need to change this.
     */
    public static void main(String[] argv) {
        // launch is a method inherited from Application
        launch(argv);
    }
}
