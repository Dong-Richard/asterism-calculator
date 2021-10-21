package cartoon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Here's cartoon! Your first JavaFX assignment!
 * Before you start coding your cartoon, take a look at
 * the lecture slides and JavaFX Guide for all the
 * information you'll need (and more!).
 * <p>
 * Please put your overall comments for the project here.
 * <p>
 * My cartoon models the night sky exactly as it is in real life, simulated using javafx. Here is a quick overview
 * of my classes:
 * <p>
 * Cartoon: Main logic class, handles key inputs and timeline movements. Contains logic to track the time that passes
 * per tick.
 * Asterism: Handles the individual composite shapes within the bigger composite shape. Contains methods to translate
 * from alt-az coordinates to rectangular, and update the position of labels
 * Constants: Defines coordinates of all stars
 * NightSky: The main composite object, contains all asterisms, has method to rotate about the north celestial pole.
 * PaneOrganizer: Sets up the various panes.
 */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Create top-level object, set up the scene, and show the stage here.
        PaneOrganizer paneOrganizer = new PaneOrganizer();
        Scene scene = new Scene(paneOrganizer.getRoot(), Constants.APP_WIDTH, Constants.APP_HEIGHT + Constants.CONTROL_PANE_OFFSET);
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
