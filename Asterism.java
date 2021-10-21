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
     * @param lines    Definitions for all of the lines connecting those stars
     * @param name     Name of the asterism
     */
    public Asterism(Point2D[] starLocs, Line[] lines, String name) {
        this.generateLines(lines);
        this.generateStars(starLocs);
        this.name = new Text(name);
        this.name.setFill(Color.SKYBLUE);
        this.setLabelPos();
    }

    /**
     * Helper method to generate all Lines of the asterism graphically and logically.
     *
     * @param lines
     */
    public void generateLines(Line[] lines) {
        this.lines = new Line[lines.length];
        for (int i = 0; i < lines.length; i++) {
            double r1 = this.calculateR(lines[i].getStartY());
            double r2 = this.calculateR(lines[i].getEndY());
            this.lines[i] = new Line(this.transformX(lines[i].getStartX(), r1),
                    this.transformY(lines[i].getStartX(), r1),
                    this.transformX(lines[i].getEndX(), r2),
                    this.transformY(lines[i].getEndX(), r2));
            this.lines[i].setStroke(Color.BLUE);
        }
    }

    /**
     * Helper method to generate all stars of the asterism graphically and logically.
     *
     * @param starLocs
     */
    public void generateStars(Point2D[] starLocs) {
        this.stars = new Ellipse[starLocs.length];
        for (int i = 0; i < starLocs.length; i++) {
            double r = this.calculateR(starLocs[i].getY());
            this.stars[i] = new Ellipse(this.transformX(starLocs[i].getX(), r),
                    this.transformY(starLocs[i].getX(), r)
                    , 1, 1);
            this.stars[i].setFill(Color.WHITE);
        }
    }

    /**
     * Helper method to determine the distance R from the zenith.
     *
     * @param Altitude Altitude of the point
     * @return
     */

    public double calculateR(double Altitude) {
        return (90.0 - Altitude) * Constants.SCALE_FACTOR;
    }

    /**
     * Helper method to transform a point's X value given R and Theta
     *
     * @param theta Azimuth, in angles
     * @param R     Radius from zenith
     * @return X value in rectangular coordinates
     */
    public double transformX(double theta, double R) {
        return R * Math.sin(theta * Math.PI / 180.0) + Constants.ZENITH_X;
    }

    /**
     * Helper method to transform a point's Y value given R theta
     *
     * @param theta Azimuth, in angles
     * @param R     Radius from zenith
     * @return Y value in rectangular coordinates
     */
    public double transformY(double theta, double R) {
        return Constants.ZENITH_Y + R * Math.cos(theta * Math.PI / 180.0);
    }

    /**
     * Sets the position of the label to the average positions of all of the stars in the asterism. This is to
     * keep the label vaguely centered upon its respective asterism.
     */
    public void setLabelPos() {
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
