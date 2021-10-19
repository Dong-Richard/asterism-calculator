package cartoon;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Asterism {
    /**
     * Each asterism has stars, lines that connect the stars, and a name
     */
    private Ellipse[] stars;
    private Line[] lines;
    private final Text name;

    /**
     * Creates the lines and stars graphically and logically, as well as transforming their Alt-az coordinates to the
     * one defined in the Constants Class comment.
     *
     * @param starLocs Locations of all of the stars in the asterism
     * @param lines Definitions for all of the lines connecting those stars
     * @param name Name of the asterism
     */
    public Asterism(Point2D[] starLocs, Line[] lines, String name) {
        this.generateLines(lines);
        this.generateStars(starLocs);
        this.name = new Text(name);
        this.name.setFill(Color.SKYBLUE);
        this.updateLabelPos();
    }

    /**
     * Helper method to generate all Lines of the asterism graphically and logically.
     * @param lines
     */
    public void generateLines(Line[] lines) {
        this.lines = new Line[lines.length];
        for (int i = 0; i < lines.length; i++) {
            this.lines[i] = new Line(this.transformX(lines[i].getStartX()), this.transformY(lines[i].getStartY()), this.transformX(lines[i].getEndX()), this.transformY(lines[i].getEndY()));
            this.lines[i].setStroke(Color.BLUE);
        }
    }

    /**
     * Helper method to generate all stars of the asterism graphically and logically.
     * @param starLocs
     */
    public void generateStars(Point2D[] starLocs) {
        this.stars = new Ellipse[starLocs.length];
        for (int i = 0; i < starLocs.length; i++) {
            this.stars[i] = new Ellipse(this.transformX(starLocs[i].getX()), this.transformY(starLocs[i].getY()), 1, 1);
            this.stars[i].setFill(Color.WHITE);
        }
    }

    /**
     * Helper method to transform the Azimuth to coordinates defined in Constants.
     *
     * @param initX initial aziumth in degrees
     * @return transformed x coordinate
     */
    public double transformX(double initX) {
        return initX / Constants.HOR_FOV * Constants.APP_WIDTH + Constants.NCP_X;
    }

    /**
     * Helper method to transform the Altitude to coordinates defined in Constants.
     *
     * @param initY initial altitude in degrees
     * @return transformed y coordinate
     */
    public double transformY(double initY) {
        return Constants.APP_HEIGHT - initY / Constants.VERT_FOV * Constants.APP_HEIGHT;
    }

    /**
     * Sets the position of the label to the average positions of all of the stars in the asterism. This is to
     * keep the label vaguely centered upon its respective asterism.
     */
    public void updateLabelPos() {
        double avgX = 0;
        double avgY = 0;

        for (Ellipse star : this.stars) {
            avgX += star.getCenterX();
            avgY += star.getCenterY();
        }
        avgX /= this.stars.length;
        avgY /= this.stars.length;

        this.name.setX(avgX);
        this.name.setY(avgY);
    }

    /**
     * Getters
     */

    public Ellipse[] getStars() {
        return this.stars;
    }

    public Line[] getLines() {
        return this.lines;
    }

    public Text getName() {
        return this.name;
    }
}
