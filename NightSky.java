package cartoon;

import javafx.geometry.Point2D;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

/**
 * The top-level composite shape class. Models the entire night sky as an array of asterisms.
 */
public class NightSky {

    private final Asterism[] asterisms; //the night sky contains all of the asterisms

    /**
     * Constructs the night sky, adds all appropriate asterisms.
     */
    public NightSky() {
        this.asterisms = new Asterism[Constants.NUM_ASTERISMS];

        this.asterisms[0] = new Asterism(Constants.UMISTARS, Constants.UMILINES, "Little Dipper");
        this.asterisms[1] = new Asterism(Constants.UMASTARS, Constants.UMALINES, "Big Dipper");
        this.asterisms[2] = new Asterism(Constants.SUMMERTRIANGLESTARS, Constants.SUMMERTRIANGLELINES, "Summer Triangle");
        this.asterisms[3] = new Asterism(Constants.CASSTARS, Constants.CASLINES, "Cassiopeia's W");
        this.asterisms[4] = new Asterism(Constants.SPRINGTRIANGLESTARS, Constants.SPRINGTRIANGLELINES, "Spring Triangle");
        this.asterisms[5] = new Asterism(Constants.GREATSQUARESTARS, Constants.GREATSQUARELINES, "Great Square of Pegasus");
        this.asterisms[6] = new Asterism(Constants.WINTERHEXAGONSTARS, Constants.WINTERHEXAGONLINES, "Winter Hexagon");
        this.asterisms[7] = new Asterism(Constants.ORIONSTARS, Constants.ORIONLINES, "Orion's Belt and Shield");
    }


    /**
     * For each asterism, each star will be rotated about the North Celestial Pole by the degrees specified. For this,
     * we use the Geometric Rotation Formula, to change the actual positions of each of the stars (since the label's
     * position is dependent on the stars positions).
     * <p>
     * For the lines, we simply perform the same rotation on its independant starting and ending points.
     *
     * @param rotationRadians radians by which the object is rotated, clockwise (initially set to a negative value in
     *                       constants due to Earth rotating on its axis counterclockwise).
     */
    public void rotate(double rotationRadians) {

        for (Asterism asterism : this.asterisms) {
            for (Ellipse star : asterism.getStars()) {
                Point2D rotatedPoint = rotatePoint(star.getCenterX(), star.getCenterY(), rotationRadians);
                star.setCenterX(rotatedPoint.getX());
                star.setCenterY(rotatedPoint.getY());
            }
            for (Line line : asterism.getLines()) {
                Point2D rotatedStart = rotatePoint(line.getStartX(),line.getStartY(),rotationRadians);
                Point2D rotatedEnd = rotatePoint(line.getEndX(),line.getEndY(),rotationRadians);
                line.setStartX(rotatedStart.getX());
                line.setStartY(rotatedStart.getY());
                line.setEndX(rotatedEnd.getX());
                line.setEndY(rotatedEnd.getY());
            }
            asterism.setLabelPos();
        }
    }

    /**
     * Helper Method for rotate to rotate a specific point around a specific point of rotation by the specified degrees.
     *
     * @param initialX The original X value of the point to be rotated
     * @param initialY The original Y value of the point to be rotated
     * @param rotationRadians The amount of radians the point is to be rotated.
     * @return
     */
    private Point2D rotatePoint(double initialX, double initialY, double rotationRadians) {
        double tempX = initialX - Constants.NCP_X;
        double tempY = initialY - Constants.NCP_Y;
        return new Point2D(tempX * Math.cos(rotationRadians) - tempY * Math.sin(rotationRadians) + Constants.NCP_X,
                tempX * Math.sin(rotationRadians) + tempY * Math.cos(rotationRadians) + Constants.NCP_Y);
    }

    /**
     * Getter for the array of Asterisms.
     *
     * @return
     */
    public Asterism[] getAsterisms() {
        return this.asterisms;
    }
}
