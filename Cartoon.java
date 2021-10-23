package cartoon;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Main Logic Class.
 * <p>
 * Contains Logic for:
 * - speeding up/slowing down rotation speed on button press
 * - pausing rotation on spacebar press
 * - updating the passage of time
 * - updating graphical elements per timeline tick
 * - adding all graphical elements
 */
public class Cartoon {
    private final Pane shapePane;
    private final VBox controlPane;
    private final NightSky sky;
    private Timeline timeline;
    private Label timeLabel;
    private BigDecimal masterTime;
    private int multiplierIndex;

    /**
     * Instantiated shapePane, controlPane, creates a new Night Sky, and sets the initial speed. Graphically adds
     * All asterisms, the controlPane text and buttons, sets focus and starts the timeline.
     *
     * @param shapePane   Pane in which all animated graphical elements will lie
     * @param controlPane Pane that displays instructions and quit button
     */
    public Cartoon(Pane shapePane, VBox controlPane) {
        this.shapePane = shapePane;
        this.controlPane = controlPane;
        this.sky = new NightSky();
        this.multiplierIndex = Constants.INITIAL_MULTIPLIER_INDEX;

        this.addNightSky();
        this.addControl();
        this.addTimeLabel();
        this.setupFocusAndKeyHandler();
        this.setupTimeline();
    }

    /**
     * Adds all graphical elements of the program.
     */
    private void addNightSky() {
        this.addAsterisms();
        this.addCardinalDirections();
        this.addZenithLabel();
        this.addGround();
    }

    /**
     * We grab all of the Asterisms available in the night sky, and for each asterism, we add every star and line, and
     * add its respective name label.
     */
    private void addAsterisms() {
        //add asterisms
        for (int i = 0; i < this.sky.getAsterisms().length; i++) {
            for (int j = 0; j < this.sky.getAsterisms()[i].getLines().length; j++) {
                this.shapePane.getChildren().addAll(this.sky.getAsterisms()[i].getLines()[j]);
            }
            for (int j = 0; j < this.sky.getAsterisms()[i].getStars().length; j++) {
                this.shapePane.getChildren().addAll(this.sky.getAsterisms()[i].getStars()[j]);
            }
            this.shapePane.getChildren().addAll(this.sky.getAsterisms()[i].getName());
        }
    }

    /**
     * Adds a dot and label to where the Zenith (Point directly overhead) should be.
     */
    private void addZenithLabel() {
        Ellipse zenith = new Ellipse(Constants.ZENITH_X, Constants.ZENITH_Y, 2, 2);
        zenith.setFill(Color.RED);
        Text zenithLabel = new Text("Zenith");
        zenithLabel.setX(Constants.ZENITH_X + 3);
        zenithLabel.setY(Constants.ZENITH_Y + 13);
        zenithLabel.setFill(Color.SKYBLUE);

        this.shapePane.getChildren().add(zenith);
        this.shapePane.getChildren().add(zenithLabel);
    }

    /**
     * Adds the four cardinal directions to the app. According to our definitions, North is at the bottom of the screen.
     */
    private void addCardinalDirections() {
        Text north = new Text("N");
        Text south = new Text("S");
        Text east = new Text("E");
        Text west = new Text("W");

        north.setX(Constants.ZENITH_X);
        north.setY(Constants.ZENITH_Y + Constants.ZENITH_ALTITUDE * Constants.SCALE_FACTOR);

        south.setX(Constants.ZENITH_X);
        south.setY(Constants.ZENITH_Y - Constants.ZENITH_ALTITUDE * Constants.SCALE_FACTOR + 10);

        east.setX(Constants.ZENITH_X + Constants.ZENITH_ALTITUDE * Constants.SCALE_FACTOR - 10);
        east.setY(Constants.ZENITH_Y);

        west.setX(Constants.ZENITH_X - Constants.ZENITH_ALTITUDE * Constants.SCALE_FACTOR);
        west.setY(Constants.ZENITH_Y);

        north.setFill(Color.RED);
        south.setFill(Color.RED);
        east.setFill(Color.RED);
        west.setFill(Color.RED);

        this.shapePane.getChildren().add(north);
        this.shapePane.getChildren().add(south);
        this.shapePane.getChildren().add(east);
        this.shapePane.getChildren().add(west);
    }

    /**
     * Cuts out the background of the night sky (A circle when projected onto our FOV) and adds the cutout as the
     * ground, and leaves a black circle in the middle.
     */
    private void addGround() {
        Rectangle groundCutOut = new Rectangle(Constants.APP_WIDTH*2, Constants.CARTOON_PANE_HEIGHT*2);
        Ellipse ground = new Ellipse(Constants.ZENITH_X, Constants.ZENITH_Y,
                Constants.ZENITH_ALTITUDE * Constants.SCALE_FACTOR,
                Constants.ZENITH_ALTITUDE * Constants.SCALE_FACTOR);
        Shape cutOut = Shape.subtract(groundCutOut, ground);
        cutOut.setFill(Color.DARKSLATEGRAY);

        this.shapePane.getChildren().add(cutOut);
    }

    /**
     * Adds the quit button, instructions, and explanation of the program's purpose to a pane at the bottom of the
     * screen.
     */
    private void addControl() {
        Button quitButton = new Button(Constants.QUIT_BUTTON);
        this.controlPane.setAlignment(Pos.CENTER);
        quitButton.setOnAction((ActionEvent e) -> this.onButtonClicked(e));

        Text introduction = new Text(Constants.INTRODUCTION_STRING);
        Text instructions = new Text(Constants.INSTRUCTION_STRING);
        instructions.setFill(Color.BLUE);

        this.controlPane.getChildren().add(introduction);
        this.controlPane.getChildren().add(instructions);
        this.controlPane.getChildren().add(quitButton);
    }

    /**
     * Exit button code
     *
     * @param e
     */
    private void onButtonClicked(ActionEvent e) {
        Button button = (Button) e.getSource();
        if (button.getText().equals(Constants.QUIT_BUTTON))
            System.exit(0);
    }

    /**
     * Adds the dynamic label that changes with time. The text is updated using a BigDecimal and Date class to keep
     * track of time passing.
     * Because of the precession of the Earth, we need very precise measurements to track the drifting of the
     * constellations throughout the year.
     * Date keeps track of the day, month, year, and time.
     */
    private void addTimeLabel() {

        this.masterTime = new BigDecimal(1634616000000L); //Number is the offset in milliseconds of 10/19/21 from 1900.
        this.timeLabel = new Label(new Date(this.masterTime.longValue()).toString());
        this.timeLabel.setTextFill(Color.WHITE);
        this.timeLabel.setStyle("-fx-font-size: 18");

        this.shapePane.getChildren().add(this.timeLabel);

    }

    /**
     * Sets up timeline according to defined keyframe.
     */
    private void setupTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.KEYFRAME_DURATION), (ActionEvent e) -> this.updateCartoon());
        this.timeline = new Timeline(kf);
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }

    /**
     * Rotates the sky (movement due to Earth's motion), then adds the appropriate amount of time that must have passed
     * per degrees that the Earth has rotated. An additional small portion of time is subtracted to keep track of the
     * orbit of the earth around the sun, since the stars in the North Celestial Dome changes as Earth's position changes.
     * <p>
     * Then the master time, kept in precise digits using BigDecimal, is truncated to a definite time in seconds by the
     * Date class to display.
     */
    private void updateCartoon() {
        this.sky.rotate(Constants.ONE_RADIAN_CCW * Constants.MULTIPLIERS[this.multiplierIndex]);
        double timeFractionPerTick = 2 * Math.PI / (-Constants.ONE_RADIAN_CCW * Constants.MULTIPLIERS[this.multiplierIndex]);
        //motion due to rotation of the earth
        this.masterTime = this.masterTime.add(new BigDecimal(Constants.DAY_LENGTH_MILLIS / timeFractionPerTick));
        //motion due to orbit of the earth
        this.masterTime = this.masterTime.subtract(new BigDecimal((Constants.DAY_LENGTH_MILLIS / 365.0) / timeFractionPerTick));
        this.timeLabel.setText(new Date(this.masterTime.longValue()).toString());
    }

    /**
     * Sets the focus to the shapePane.
     */
    private void setupFocusAndKeyHandler() {
        this.shapePane.requestFocus();
        this.shapePane.setFocusTraversable(true);
        this.shapePane.setOnKeyPressed((KeyEvent e) -> this.onKeyPressed(e));
    }

    /**
     * When Right or Left is pressed, we want to speed to speed up forwards or backwards in time. Thus, I keep a
     * definite set of multipliers that an index can traverse left and right across to speed up or slow down by
     * some factor.
     * <p>
     * Spacebar simply pauses or starts the timeline.
     *
     * @param e
     */
    private void onKeyPressed(KeyEvent e) {
        KeyCode keyPressed = e.getCode();
        switch (keyPressed) {
            case RIGHT:
                if (this.timeline.getStatus() == Animation.Status.PAUSED) return;
                if (this.multiplierIndex < Constants.MULTIPLIERS.length - 1) {
                    this.multiplierIndex++;
                }
                break;
            case LEFT:
                if (this.timeline.getStatus() == Animation.Status.PAUSED) return;
                if (this.multiplierIndex > 0) {
                    this.multiplierIndex--;
                }
                break;
            case SPACE:
                if (this.timeline.getStatus() == Animation.Status.PAUSED)
                    this.timeline.play();
                else
                    this.timeline.pause();
                break;
            default:
                break;
        }
        e.consume();
    }
}
