package cartoon;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

public class Constants {
    public static final int APP_WIDTH = 1600;
    public static final int APP_HEIGHT = 800;

    public static final double HOR_FOV = 250;
    public static final double VERT_FOV = 125;

    public static final double ROTATION_DEGREE = -1 * (Math.PI / 180.0);
    public static final double KEYFRAME_DURATION = .05;
    public static final long DAY_LEGNTH = 86400000L;

    public static final double[] MULTIPLIERS = {-16, -8, -4, -2, -1, -.5, -.125, -.05, 0, .05, .125, .5, 1, 2, 4, 8, 16};
    public static final int INITIAL_MULTIPLIER = 12;

    public static final int NUM_ASTERISMS = 2;

    /**
     * COORDINATE DEFINITIONS:
     * I will be using Alt-Az coordinates to describe the locations of stars. I would like the app frame to show
     * an apparent field of view of 90 degrees altitude and 180 degrees azimuth. Since the NCP in Providence, Rhode
     * Island is at approximately 0/40 az./alt, I will arbitrarily decide 0 degrees azimuth to be in the horizontal
     * center of the app window, and 0 degrees altitude to be at the bottom of the app window.
     *
     * Alt-Az Coordinates of Stars is given at approximately an initial time of 12 am in mid-October.
     * Data was collected from the free software *Stellarium*
     *
     * Transformations will be performed in the Constellation Class.
     */


    /**
     * THE FOLLOWING ARE INTIIAL COORDINATE DEFINITIONS FOR THE STARS IN EACH ASTERISM OR CONSTELLATION.
     */

    //define Celestial Pole Coordinates
    public static final double NCP_X = APP_WIDTH / 2;
    public static final double NCP_Y = APP_HEIGHT - 40.0 / VERT_FOV * APP_HEIGHT;

    //define Little Dipper
    public static final Point2D POLARIS = new Point2D(0, 42);
    public static final Point2D YILDUN = new Point2D(-5, 40);
    public static final Point2D EUMI = new Point2D(-9, 36);
    public static final Point2D ZUMI = new Point2D(-10, 31);
    public static final Point2D HUMI = new Point2D(-13, 31);
    public static final Point2D PHERKAD = new Point2D(-12, 26);
    public static final Point2D KOCHAB = new Point2D(-8, 27);

    public static final Line PO_YI = new Line(0, 42, -5, 40);
    public static final Line YI_EU = new Line(-5, 40, -9, 36);
    public static final Line EU_ZU = new Line(-9, 36, -10, 31);
    public static final Line ZU_HU = new Line(-10, 31, -13, 31);
    public static final Line HU_PH = new Line(-13, 31, -12, 26);
    public static final Line PH_KO = new Line(-12, 26, -8, 27);
    public static final Line KO_ZU = new Line(-8, 27, -10, 31);

    public static final Point2D[] UMISTARS = {POLARIS, YILDUN, EUMI, ZUMI, HUMI, PHERKAD, KOCHAB};
    public static final Line[] UMILINES = {PO_YI, YI_EU, EU_ZU, ZU_HU, HU_PH, PH_KO, KO_ZU};

    //define Big Dipper
    public static final Point2D ALKAID = new Point2D(-7, 2);
    public static final Point2D MIZAR = new Point2D(-3, 7);
    public static final Point2D ALIOTH = new Point2D(1.5, 8);
    public static final Point2D MEGREZ = new Point2D(7, 9);
    public static final Point2D PHECDA = new Point2D(10.5, 7);
    public static final Point2D DUBHE = new Point2D(14, 16);
    public static final Point2D MERAK = new Point2D(17, 11.5);

    public static final Line AL_MI = new Line(-7, 2, -3, 7);
    public static final Line MI_AL = new Line(-3, 7, 1.5, 8);
    public static final Line AL_MEG = new Line(1.5, 8, 7, 9);
    public static final Line MEG_PH = new Line(7, 9, 10.5, 7);
    public static final Line PH_MER = new Line(10.5, 7, 17, 11.5);
    public static final Line MER_DU = new Line(17, 11.5, 14, 16);
    public static final Line DU_MEG = new Line(14, 16, 7, 9);

    public static final Point2D[] UMASTARS = {ALKAID, MIZAR, ALIOTH, MEGREZ, PHECDA, DUBHE, MERAK};
    public static final Line[] UMALINES = {AL_MI, MI_AL, AL_MEG, MEG_PH, PH_MER, MER_DU, DU_MEG};
}
