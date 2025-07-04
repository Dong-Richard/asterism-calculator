# Asterism Calculator README

The purpose of the program is to display the major asterisms in the night sky in the exact, accurate positions in real
life at any given time and day, as seen from Providence, RI. Since I only chose to display the brightest and most
recognizable asterisms, it is likely that the asterisms you can indentify from Providence are the exact ones shown
in the program. Since the clock is accurate to both motions of the earth (revolution and orbit), it is likely that
if you walk outside at the exact time displayed by the program that the night sky in real life would look the exact
same as the one portrayed in the program.

**Disclaimer**: The program does not show the day/night cycle, brightness of stars, or the locations of planets, although
they are very noticeable to an observer.
The day/night cycle is difficult to model due to the complications with the changing lengths of day/night, and how
light "spills" through the atmosphere during sunrise and sunset.
I chose deliberately not to include the brightness of stars, since these asterisms are all bright and you will likely be
able to see all of the stars anyway (other than the little dipper stars, which are relatively dim. I chose only to
include it due to the significance of including the north star). Additionally, the javafx app is so small that stars
close together with large magnitudes would likely overlap (since brighter stars appear larger in the night sky).
The planets are not included due to the difficulty in mapping their path across the ecliptic and
modeling retrograde motion.

## How to Run:
Simply click run from App.java on IntelliJ. 

## Overview

This program has 6 classes which interact in the following ways:

- App contains a PaneOragnizer
- PaneOrganizer contains a BorderPane, which is associated with a Pane (Pane with all of the shapes) and Vbox
(Pane with control and instructions)Sin
- Cartoon, the main logic class, is associated with the shape Pane, and control VBox. It also contains an instance
of NightSky, the main composite shape class, a TimeLine to control automatic movement, a Label to display the time,
a BigDecimal to store the precise time, and an integer to store the multiplier by which the speed of the sky
rotation occurs.
- NightSky, the main composite shape class, contains multiple Asterisms
- Asterism, the sub composite shape class, contains multiple Ellipses(stars) and multiple Lines, as well as a String.
- Constants contains all of the raw data for positions of the stars at Oct 19 12:00:00 AM according to Stellarium.

### Design Choices:

 **I. MATHEMATICAL MODELS:**

- DEFINITION AND TRANSFORMATIONS OF COORDINATE SYSTEMS:
    - Choosing the Altitude-Azimuth Coordinate System: Although the altaz system is egocentric, there is no point
    in using more accurate coordinates (like the RA/Dec. System) since my program does not have the functionality
    to change locations on the earth, the program only needs to show the locations of stars as seen from the
    latitude of Providence, RI. Therefore, I chose to use Alt-Az.
    - The Altitude-Azimuth system describes points on the Celestial dome with two values: Altitude and Azimuth,
    both in units of degrees, with 90 degrees altitude being set to the point directly overhead, the zenith, and
    decreases to 0 degrees at the horizon. Azimuth is defined with 0 degrees being directly north, and increases
    West to East until it circles back 360 degrees. In terms of spherical coordinates, Altitude is synonymous to
    phi and Azimuth to theta.
    - When representing a spherical surface on a 2D surface, we have to take its cross-sectional surface, which
    is a circle. Therefore, we can convert the spherical coordinates into polar coordinates, and then into
    rectangular. (NOTE: There will be shape distortions when projecting a 3d surface onto a 2d one).
    - In the Constants class, SCALE_FACTOR is a measure of graphical units per degree, and is used to convert
    degrees to graphical units.
    - In the Asterism class, I calculate a radius from the center of the circle in the calculateR method (line 78).
    The degrees to the center can be calculated with 90.0(the degree value of the zenith) minus the degree value
    of the star. Then, we multiply it by the scale factor to determine its graphical distance to the center.
    - In the Asterism class, I then calculate the X and Y of stars using trigonometry in the transformX(line 89) and
    transformY(line 100) methods. We can draw a right triangle with the r being the line from the zenith to the
    star, and theta being the azimuth of the star. From this triangle, we can see that the Y value of the star's
    position is rcos(theta) and the X value being rsin(theta).
    - Using these definitions, and the fact that the NCP and Zenith both lie on the line where theta = 0, the Y
    value of each of these points are the same as for a star at 90 degrees Altitude (Zenith) and 41.8 degrees
    Altitude (NCP at Providence).
    - Lines are Java Lines with the endpoints being the points of the two stars that the line connects. The same
    transformation is applied on both ends of the line.

- CALCULATIONS FOR THE SPEED OF TIME PASSING:
    - In the Cartoon class, the updateCartoon() method (line 215) contains the logic for determining the amount of
    time that passes.
    - Because a day passes every time the sky has rotated 2pi radians, we need to find how many radians have passed
    during the tick, then find out what percentage of a full day's rotation that is, and then multiply that by
    the length of a day to find out how much time has passed per tick.
    - Radians per tick is the constant ONE_RADIAN_CCW multiplied by the scale factor for speed of the rotation.
    - Because the Earth orbits the sun once every 365 days, the stars appear to "shift" by a set amount every day,
    just by a little bit, since the actual frame of stars that we can see changes as Earth moves into a different
    part of space relative to the galactic reference frame. This means that the constellations will be just a tiny
    bit farther forward at 12 AM Oct 20 than at 12AM Oct 19.
    - Effectively, the total "time shift" is a full day, since when Earth completes a full orbit, the sky should
    look approximately the same (Ie. 12 AM Oct 20 2021 should be the same as 12 AM Oct 20 2022). This means that
    every day, the "shift" is daylegnth/365. Since we already have the fraction of the day that passes
    per tick, we can divide this ratio daylength/365 by that fraction, and get the amount of time we have to
    subtract per tick, so that it would eventually make up daylegnth/365 amount of shift per day.

- MOVEMENT OF OBJECTS THROUGH THE NIGHT SKY:
    - Since the stars travel in a perfect circle around the North Celestial Pole, we can simply keep r constant
    and modify the star's theta value. Since the speed of rotation is determined by a set number of radians per
    tick, we can simply translate the star's location by that radian value about the North Celestial Pole through
    the geometric rotation formula.
    - The reason we actually have to update the positions of the stars instead of adding a new rotation
    transformation is because the name label is set to the average position of all of the stars, and the rotation
    transformation simple changes the Node's coordinate system through the change of basis formula from Linear
    Algebra.
    - We use the same rotation formula for lines rather than using a Rotation Class since the rotation classes will
    quickly pile up, slowing down the program due to increasing the amount of memory needed to store the
    transformations in the internal collection.

 **II. MODULARITY OF THE PROGRAM**:

- The transformation of the locations of the stars and lines are done in the Asterism class rather than in the
Constants class, so that when I go in to modify or add new stars, I can simply put in the raw data in AltAz, and
the program will convert them into the correct coordinates for me in the Asterism class upon creation of the
asterism.
- Rotate is placed in the NightSky class since the entire night sky rotates together, so every element in every
asterism is rotated by the same amount, so when a new Asterism is added, I can simply add it to the NightSky and
it will rotate flawlessly.
- The NCP and Zenith are both defined in terms of the size of the App and the Apparent FOV, so the window can be
resized without issue, and the apparent FOV can be decreased without issue.
- Positions of the stars are defined relative to the NCP and Zenith, so the NCP's latitude can be changed without
issue. This also means the the App window can be changed without affecting the position of the stars.

 **III. MISCELLANEOUS OTHER DESIGN CHOICES**

- In the Constants class, I define ZENITH_ALTITUDE as 90.0 because the zenith must be 90 degrees altitude
regardless of the window size or the apparent field of view.
- THE WINDOW CANNOT BE RESIZED DURING RUNTIME. If you click "fullscreen" on the window the dimensions will be
messed up since the code does not dynamically resize the graphical elements. To get a bigger window you have to
change the app size in Constants.
- The speed is determined by an array of multipliers applied to the default speed of one radian per tick. This is
because I want the app to be able to run both forwards in time, and backwards in time in a varying number of
rates. I consider this method of simply moving the index head a more elegant solution than using a seperate
instance variable to keep track of speed, and flipping the sign after it crosses a certain threshold manually.
- In the onKeyPressed method (line 243), I do not allow the speed to be changed while the program is paused to
try to minimize user error.
- NORTH was arbitrarily decided to be on the bottom of the circle. Since I want the program to be aligned with
North towards the front, and the NCP is closest to the bottom of the screen, I defined North to be the bottom
of the screen, as the "forward direction". The location of the "S", "W", and "E" characters are behind, to the left,
and to the right of the observer, respectively. These points may not all be visible if the user decides to change
the apparent FOV of the program to be smaller than the full celestial dome.
