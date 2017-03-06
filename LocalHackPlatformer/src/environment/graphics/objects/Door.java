package environment.graphics.objects;

import java.awt.Color;

/**
 * A <code>GraphicalObject</code> that denotes a Door on the screen.
 * 
 * <h3>Super-class:</h3> GraphicalObject
 * 
 * @author Joshua_Eddy
 * 
 * @see GraphicalObject
 *
 */
public class Door extends GraphicalObject {

	/**
	 * Constructs a new <code>Door</code> graphical object.
	 * 
	 * @param width
	 *            <code>int</code> width of the Door in pixels.
	 * @param height
	 *            <code>int</code> height of the Door in pixels.
	 * @param color
	 *            <code>Integer</code> colour of the Door.
	 * 
	 * @see GraphicalObject
	 * @see #addBackground()
	 * @see #addDoor(Integer)
	 * @see #addDoorHandle(int, int)
	 */
	public Door(int width, int height, Integer color) {

		super(width, height);
		// Assigns the dimensions of the door to the instance fields
		this.pixels = new Integer[width][height];

		// Creates a box on screen that will serve as the black border for the
		// finished door
		addSolidBackground(Color.BLACK.getRGB());

		// Creates the door with the specified colour.
		addDoor(color);

		// Positions the door handle three quarters of the width of the door to
		// the right and half way up the door.
		addDoorHandle((3 * width) / 4, height / 2);

	}

	/**
	 * Changes the pixels that are not part of the border to the desired colour
	 * of the door.
	 * 
	 * @param color
	 *            <code>Integer</code> colour that denotes the desired colour of
	 *            the door.
	 */
	private void addDoor(Integer color) {
		// Iterates through each row of the door's pixels except the first and
		// the last row.
		for (int yCoordinate = 1; yCoordinate < getHeight() - 1; yCoordinate++) {

			// Iterates trough each pixel in the row except the first and last
			// changing their colour to the desired colour of the door.
			for (int xCoordinate = 1; xCoordinate < getWidth() - 1; xCoordinate++) {
				pixels[xCoordinate][yCoordinate] = color;
			}
		}
	}

	/**
	 * Adds the door handle to the door which is 4x4.
	 * 
	 * @param handleX
	 *            <code>int</code> x position of the handle of the door.
	 * @param handleY
	 *            <code>int</code> y position of the handle of the door.
	 */
	private void addDoorHandle(int handleX, int handleY) {
		// Iterates through each row of the handle.
		for (int yCoordinate = 0; yCoordinate < 4; yCoordinate++) {

			// Iterates through each pixel in the row and assigns it the colour
			// of black.
			for (int xCoordinate = 0; xCoordinate < 4; xCoordinate++) {
				pixels[handleX + xCoordinate][handleY + yCoordinate] = Color.BLACK.getRGB();
			}
		}
	}
}
