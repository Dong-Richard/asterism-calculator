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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.math.BigDecimal;
import java.util.Date;

public class Cartoon {
    private final BorderPane root;
    private final NightSky sky;
    private Timeline timeline;
    private Label timeLabel;
    private Date time;
    private BigDecimal masterTime;
    private final double speed;
    private int multiplierIndex;

    public Cartoon(BorderPane root) {
        this.root = root;
        this.sky = new NightSky();
        this.speed = Constants.ROTATION_DEGREE;
        this.multiplierIndex = Constants.INITIAL_MULTIPLIER;

        this.addConstellations();
        this.addControl();
        this.setupTimeline();
        this.addLabel();
    }

    /**
     * Adds the quit button, instructions, and explanation of the program's purpose to a pane at the bottom of the
     * screen.
     */
    private void addControl() {
        VBox controlPane = (VBox) this.root.getBottom();

        Button quitButton = new Button("Quit");
        controlPane.setAlignment(Pos.CENTER);
        quitButton.setOnAction((ActionEvent e) -> this.onButtonClicked(e));

        Text introduction = new Text("Welcome to the Planetarium! This program shows the exact locations from " +
                "Providence, Rhode Island of each star in the brightest Asterisms at the exact time and date displayed.");
        Text instructions = new Text("Instructions: Press space to pause, press right to increase speed, press left " +
                "to decrease speed");

        controlPane.getChildren().add(introduction);
        controlPane.getChildren().add(quitButton);
        controlPane.getChildren().add(instructions);
    }

    /**
     * Adds the dynamic label that changes with time. The text is updated using a BigDecimal and Date class to keep
     * track of time passing.
     * Because of the precession of the Earth, we need very precise measurements to track the drifting of the
     * constellations throughout the year.
     * Date keeps track of the day, month, year, and time.
     */
    private void addLabel() {
        Pane shapePane = (Pane) this.root.getCenter();

        this.time = new Date(1634616000000L);
        this.masterTime = new BigDecimal(1634616000000L);
        this.timeLabel = new Label(this.time.toString());
        this.timeLabel.setTextFill(Color.WHITE);

        shapePane.getChildren().add(this.timeLabel);

    }

    /**
     * Exit button code
     * @param e
     */
    private void onButtonClicked(ActionEvent e) {
        Button button = (Button) e.getSource();
        if (button.getText() == "Quit")
            System.exit(0);
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
     *
     * Then the master time, kept in precise digits using BigDecimal, is truncated to a definite time in seconds by the
     * Date class to display.
     */
    private void updateCartoon() {
        this.sky.rotate(this.speed * Constants.MULTIPLIERS[this.multiplierIndex]);
        double degreesPerTick = (2 * Math.PI / (-this.speed * Constants.MULTIPLIERS[this.multiplierIndex]));
        this.masterTime = this.masterTime.add(new BigDecimal(Constants.DAY_LEGNTH / degreesPerTick));//motion due to rotation of the earth
        this.masterTime = this.masterTime.subtract(new BigDecimal((Constants.DAY_LEGNTH / 365) / degreesPerTick));//motion due to orbit of the earth
        this.time.setTime(this.masterTime.longValue());
        this.timeLabel.setText(this.time.toString());
    }

    /**
     * We grab all of the Asterisms available in the night sky, and for each asterism, we add every star and line, and
     * add its respective name label.
     */
    private void addConstellations() {

        Pane shapePane = (Pane) this.root.getCenter();

        for (int i = 0; i < this.sky.getAsterisms().length; i++) {
            for (int j = 0; j < this.sky.getAsterisms()[i].getLines().length; j++) {
                shapePane.getChildren().addAll(this.sky.getAsterisms()[i].getLines()[j]);
            }
            for (int j = 0; j < this.sky.getAsterisms()[i].getStars().length; j++) {
                shapePane.getChildren().addAll(this.sky.getAsterisms()[i].getStars()[j]);
            }
            shapePane.getChildren().addAll(this.sky.getAsterisms()[i].getName());
        }

        this.root.getCenter().setFocusTraversable(true);
        this.root.setOnKeyPressed((KeyEvent e) -> this.onKeyPressed(e));
    }


    /**
     * When Right or Left is pressed, we want to speed to speed up or slow down. Thus, I keep a definite set of
     * multipliers that an index can traverse left and right across to speed up or slow down by some factor.
     *
     * Spacebar simply pauses or starts the timeline.
     * @param e
     */
    private void onKeyPressed(KeyEvent e) {
        KeyCode keyPressed = e.getCode();
        switch (keyPressed) {
            case RIGHT:
                if (this.multiplierIndex < Constants.MULTIPLIERS.length) {
                    this.multiplierIndex++;
                }
                break;
            case LEFT:
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
    }
}
