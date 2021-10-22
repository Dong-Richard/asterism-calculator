package cartoon;

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
     * For the lines, since we do not need to change the actual positions of the lines, we can simply apply a rotation
     * transformation on each line to transform their coordinate system rather than translating the line.
     *
     * @param rotationDegree radians by which the object is rotated, clockwise (initially set to a negative value in
     *                       constants due to Earth rotating on its axis counterclockwise).
     */
    public void rotate(double rotationDegree) {

        Rotate rotate = new Rotate(rotationDegree * 180 / Math.PI, Constants.NCP_X, Constants.NCP_Y);

        for (Asterism asterism : this.asterisms) {
            for (Ellipse star : asterism.getStars()) {
                double tempX = star.getCenterX() - Constants.NCP_X;
                double tempY = star.getCenterY() - Constants.NCP_Y;
                star.setCenterX(tempX * Math.cos(rotationDegree) - tempY * Math.sin(rotationDegree) + Constants.NCP_X);
                star.setCenterY(tempX * Math.sin(rotationDegree) + tempY * Math.cos(rotationDegree) + Constants.NCP_Y);
            }
            for (Line line : asterism.getLines()) {
                line.getTransforms().add(rotate);
            }
            asterism.setLabelPos();
        }
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
