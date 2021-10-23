package cartoon;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;


public class Constants {
    public static final int APP_WIDTH = 700;
    public static final int CARTOON_PANE_HEIGHT = 527;
    public static final int APP_HEIGHT = 600;

    public static final String QUIT_BUTTON = "Quit";
    public static final String INTRODUCTION_STRING = "Welcome to the Planetarium! This program shows the exact" +
            " locations of the brightest\n Asterisms at the exact time and date displayed, as seen from Providence, " +
            "Rhode Island.";
    public static final String INSTRUCTION_STRING = "Instructions: Space to pause, Right arrow key to " +
            "increase speed forward in time, Left arrow key to increase speed backwards in time";

    public static final double VERT_FOV = 180;
    public static final double SCALE_FACTOR = CARTOON_PANE_HEIGHT / VERT_FOV;

    public static final double ONE_RADIAN_CCW = -1 * (Math.PI / 180.0); //negative value to rotate counter clockwise
    public static final double KEYFRAME_DURATION = .05;
    public static final long DAY_LENGTH_MILLIS = 86400000L;

    public static final double[] MULTIPLIERS = {-16, -8, -4, -2, -1, -.5, -.125, -.05, 0, .05, .125, .5, 1, 2, 4, 8, 16};
    public static final int INITIAL_MULTIPLIER_INDEX = 11;

    public static final int NUM_ASTERISMS = 8;

    /**
     * COORDINATE DEFINITIONS:
     * I will be using Alt-Az coordinates to describe the locations of stars. I would like the app frame to show
     * the entire celestial Dome. Since the NCP in Providence, Rhode Island is at approximately 0/40 az./alt,
     * I will arbitrarily decide 0 degrees azimuth to be in the horizontal center of the app window, and 90 degrees
     * altitude to be at the bottom of the app window.
     *
     * Alt-Az Coordinates of Stars is given at approximately an initial time of 12 am in mid-October.
     * Data was collected from the free software *Stellarium*
     *
     * Transformations will be performed in the Asterism Class.
     */


    /**
     * THE FOLLOWING ARE INTIIAL COORDINATE DEFINITIONS FOR THE STARS IN EACH ASTERISM OR CONSTELLATION.
     */

    public static final double ZENITH_ALTITUDE = 90.0;

    //define zenith
    public static final double ZENITH_X = APP_WIDTH / 2;
    public static final double ZENITH_Y = CARTOON_PANE_HEIGHT - ZENITH_ALTITUDE * SCALE_FACTOR;

    //define Celestial Pole Coordinates
    public static final double NCP_X = APP_WIDTH / 2;
    public static final double NCP_Y = CARTOON_PANE_HEIGHT - 41.8 * SCALE_FACTOR;

    //define Little Dipper
    public static final Point2D POLARIS = new Point2D(0, 42);
    public static final Point2D YILDUN = new Point2D(356, 40);
    public static final Point2D EUMI = new Point2D(352, 37);
    public static final Point2D ZUMI = new Point2D(351, 32);
    public static final Point2D HUMI = new Point2D(347, 31);
    public static final Point2D PHERKAD = new Point2D(349, 26);
    public static final Point2D KOCHAB = new Point2D(352, 27);

    public static final Line PO_YI = new Line(0, 42, 356, 40);
    public static final Line YI_EU = new Line(356, 40, 352, 37);
    public static final Line EU_ZU = new Line(352, 37, 351, 32);
    public static final Line ZU_HU = new Line(351, 32, 347, 31);
    public static final Line HU_PH = new Line(347, 31, 349, 26);
    public static final Line PH_KO = new Line(349, 26, 352, 27);
    public static final Line KO_ZU = new Line(352, 27, 351, 32);

    public static final Point2D[] UMISTARS = {POLARIS, YILDUN, EUMI, ZUMI, HUMI, PHERKAD, KOCHAB};
    public static final Line[] UMILINES = {PO_YI, YI_EU, EU_ZU, ZU_HU, HU_PH, PH_KO, KO_ZU};

    //define Big Dipper
    public static final Point2D ALKAID = new Point2D(353, 2);
    public static final Point2D MIZAR = new Point2D(357, 7);
    public static final Point2D ALIOTH = new Point2D(2, 8);
    public static final Point2D MEGREZ = new Point2D(7, 9);
    public static final Point2D PHECDA = new Point2D(11, 6);
    public static final Point2D DUBHE = new Point2D(14, 16);
    public static final Point2D MERAK = new Point2D(15, 10);

    public static final Line AL_MI = new Line(353, 2, 357, 7);
    public static final Line MI_AL = new Line(357, 7, 2, 8);
    public static final Line AL_MEG = new Line(2, 8, 7, 9);
    public static final Line MEG_PH = new Line(7, 9, 11, 6);
    public static final Line PH_MER = new Line(11, 6, 15, 10);
    public static final Line MER_DU = new Line(15, 10, 14, 16);
    public static final Line DU_MEG = new Line(14, 16, 7, 9);

    public static final Point2D[] UMASTARS = {ALKAID, MIZAR, ALIOTH, MEGREZ, PHECDA, DUBHE, MERAK};
    public static final Line[] UMALINES = {AL_MI, MI_AL, AL_MEG, MEG_PH, PH_MER, MER_DU, DU_MEG};

    //define Summer Triangle
    public static final Point2D VEGA = new Point2D(304, 20);
    public static final Point2D ALTAIR = new Point2D(269, 14);
    public static final Point2D DENEB = new Point2D(297, 43);

    public static final Line VE_AL = new Line(304, 20, 269, 14);
    public static final Line AL_DE = new Line(269, 14, 297, 43);
    public static final Line DE_VE = new Line(297, 43, 304, 20);

    public static final Point2D[] SUMMERTRIANGLESTARS = {VEGA, ALTAIR, DENEB};
    public static final Line[] SUMMERTRIANGLELINES = {VE_AL, AL_DE, DE_VE};

    //defin Cassiopeia
    public static final Point2D CAPH = new Point2D(338, 70);
    public static final Point2D SHEDAR = new Point2D(347, 74);
    public static final Point2D NAVI = new Point2D(357, 70);
    public static final Point2D RUCHBAH = new Point2D(8, 71);
    public static final Point2D SEGIN = new Point2D(14, 66);

    public static final Line CA_SH = new Line(338, 70, 347, 74);
    public static final Line SH_NA = new Line(347, 74, 357, 70);
    public static final Line NA_RU = new Line(357, 70, 8, 71);
    public static final Line RU_SE = new Line(8, 71, 14, 66);

    public static final Point2D[] CASSTARS = {CAPH, SHEDAR, NAVI, RUCHBAH, SEGIN};
    public static final Line[] CASLINES = {CA_SH, SH_NA, NA_RU, RU_SE};

    //define Spring Triangle
    public static final Point2D ARCTURUS = new Point2D(341, -26);
    public static final Point2D SPICA = new Point2D(350, -59);
    public static final Point2D DENEBOLA = new Point2D(21, -31);

    public static final Line AR_SP = new Line(341, -26, 350, -59);
    public static final Line SP_DE = new Line(350, -59, 21, -31);
    public static final Line DE_AR = new Line(21, -31, 341, -26);

    public static final Point2D[] SPRINGTRIANGLESTARS = {ARCTURUS, SPICA, DENEBOLA};
    public static final Line[] SPRINGTRIANGLELINES = {AR_SP, SP_DE, DE_AR};

    //define Great Square
    public static final Point2D ALPHERATZ = new Point2D(227, 73);
    public static final Point2D SCHEAT = new Point2D(251, 62);
    public static final Point2D MARKAB = new Point2D(233, 53);
    public static final Point2D ALGENIB = new Point2D(207, 61);

    public static final Line ALPH_SC = new Line(227, 73, 251, 62);
    public static final Line SC_MA = new Line(251, 62, 233, 53);
    public static final Line MA_ALGE = new Line(233, 53, 207, 61);
    public static final Line ALGE_ALPH = new Line(207, 61, 227, 73);

    public static final Point2D[] GREATSQUARESTARS = {ALPHERATZ, SCHEAT, MARKAB, ALGENIB};
    public static final Line[] GREATSQUARELINES = {ALPH_SC, SC_MA, MA_ALGE, ALGE_ALPH};

    //define Winter Hexagon
    public static final Point2D CAPELLA = new Point2D(62, 46);
    public static final Point2D POLLUX = new Point2D(62, 11);
    public static final Point2D PROCYON = new Point2D(80, -2);
    public static final Point2D SIRIUS = new Point2D(106, -8);
    public static final Point2D RIGEL = new Point2D(115, 14);
    public static final Point2D ALDEBARAN = new Point2D(103, 38);

    public static final Line CA_PO = new Line(62, 46, 62, 11);
    public static final Line PO_PR = new Line(62, 11, 80, -2);
    public static final Line PR_SI = new Line(80, -2, 106, -8);
    public static final Line SI_RI = new Line(106, -8, 115, 14);
    public static final Line RI_AL = new Line(115, 14, 103, 38);
    public static final Line AL_CA = new Line(103, 38, 62, 46);

    public static final Point2D[] WINTERHEXAGONSTARS = {CAPELLA, POLLUX, PROCYON, SIRIUS, RIGEL, ALDEBARAN};
    public static final Line[] WINTERHEXAGONLINES = {CA_PO, PO_PR, PR_SI, SI_RI, RI_AL, AL_CA};

    //define Orion
    public static final Point2D BETELGEUSE = new Point2D(96, 18);
    public static final Point2D BELLATRIX = new Point2D(102, 23);
    public static final Point2D SAIPH = new Point2D(110, 8);
    public static final Point2D ALNITAK = new Point2D(106, 14);
    public static final Point2D ALNILAM = new Point2D(106, 16);
    public static final Point2D MINTAKA = new Point2D(106, 17);

    public static final Line BET_ALNIT = new Line(96, 18, 106, 14);
    public static final Line ALNIT_SA = new Line(106, 14, 110, 8);
    public static final Line SA_RI = new Line(110, 8, 115, 14);
    public static final Line RI_MI = new Line(115, 14, 106, 17);
    public static final Line MI_BEL = new Line(106, 17, 102, 23);
    public static final Line ALNIT_ALNIL = new Line(106, 14, 106, 16);
    public static final Line ALNIL_MI = new Line(106, 16, 106, 17);
    public static final Line BEL_BET = new Line(102, 23, 96, 18);

    public static final Point2D[] ORIONSTARS = {BETELGEUSE, BELLATRIX, RIGEL, SAIPH, ALNILAM, ALNITAK, MINTAKA};
    public static final Line[] ORIONLINES = {BET_ALNIT, ALNIT_SA, SA_RI, RI_MI, MI_BEL, ALNIT_ALNIL, ALNIL_MI, BEL_BET};


}
