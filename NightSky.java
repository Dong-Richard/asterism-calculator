package cartoon;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;


public class NightSky {

    private final Asterism[] asterisms; //the night sky contains all of the asterisms

    /**
     * Constructs the night sky, adds all appropriate asterisms.
     */
    public NightSky() {
        this.asterisms = new Asterism[Constants.NUM_ASTERISMS];
        this.addAsterisms();
    }

    /**
     * Grabs the initial positions of all of the asterisms, and keeps them in NightSky's asterism array, to be accessed
     * by Cartoon. We simply create a reference rather than a new object here because we want the changes to each
     * individual star component to be able to be made through this class.
     */
    public void addAsterisms() {
        Asterism[] initialPositions = this.allAsterisms();
        for (int i = 0; i < initialPositions.length; i++) {
            this.asterisms[i] = initialPositions[i];
        }
    }

    /**
     * Helper method for addAsterisms to add all of the asterisms into a single array.
     *
     * @return All asterisms defined
     */
    public Asterism[] allAsterisms() {
        Asterism[] asterisms = new Asterism[Constants.NUM_ASTERISMS];

        asterisms[0] = new Asterism(Constants.UMISTARS, Constants.UMILINES, "Little Dipper");
        asterisms[1] = new Asterism(Constants.UMASTARS, Constants.UMALINES, "Big Dipper");
        asterisms[2] = new Asterism(Constants.SUMMERTRIANGLESTARS, Constants.SUMMERTRIANGLELINES, "Summer Triangle");
        asterisms[3] = new Asterism(Constants.CASSTARS, Constants.CASLINES, "Cassiopeia's W");
        asterisms[4] = new Asterism(Constants.SPRINGTRIANGLESTARS, Constants.SPRINGTRIANGLELINES, "Spring Triangle");

        return asterisms;
    }

    /**
     * For each asterism, each star will be rotated about the North Celestial Pole by the degrees specified. For this,
     * we use the Geometric Rotation Formula, to change the actual positions of each of the stars (since the label's
     * position is dependent on the stars positions).
     *
     * For the lines, since we do not need to change the actual positions of the lines, we can simply apply a rotation
     * transformation on each line to transform their coordinate system rather than translating the line.
     *
     * @param rotationDegree radians by which the object is rotated, clockwise.
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
            asterism.updateLabelPos();
        }
    }

    /**
     * Getter for the array of Asterisms.
     * @return
     */
    public Asterism[] getAsterisms() {
        return this.asterisms;
    }
}
