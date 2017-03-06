package environment.graphics.objects;

import java.awt.Color;

/**
 * The Floor object is type of <code>GraphicalObject</code>. It consists of a
 * set of squares bordered by a single colour.
 * 
 * <h3>Super-class:</h3> GraphicalObject
 * 
 * @author Joshua_Eddy
 * 
 * @see GraphicalObject
 *
 */
public class Floor extends GraphicalObject {

	/**
	 * Constructs a new <code>Floor</code> graphical object.
	 * 
	 * @param width
	 *            <code>int</code> width of the floor in pixels.
	 * @param height
	 *            <code>int</code> height of the floor in pixels.
	 * @param color
	 *            <code>Integer</code> colour of the border surrounding the
	 *            boxes inside the floor.
	 * @param boxWidth
	 *            <code>int</code> width of the boxes inside the floor. The
	 *            '<i>width of box</i>' % '<i>width of floor</i>'
	 *            <strong>must</strong> equal zero.
	 * @param boxHeight
	 *            <code>int</code> height of the boxes inside the floor. The
	 *            '<i>height of box</i>' % '<i>height of floor</i>'
	 *            <strong>must</strong> equal zero.
	 * @param boxColor
	 *            <code>Integer</code> colour of the boxes inside the floor.
	 * @see GraphicalObject
	 */
	public Floor(int width, int height, Integer color, int boxWidth, int boxHeight, Integer boxColor) {

		super(width, height);
		// Assigns the basic fields of the GraphicalObject from the parameters.
		this.pixels = new Integer[width][height];

		// Creates a box on screen that will serve as the black border for the
		// finished floor.
		addSolidBackground(Color.BLACK.getRGB());

		addBoxes(boxWidth, boxHeight, boxColor);

	}

	/**
	 * Adds the boxes to the floor.
	 * 
	 * @param boxWidth
	 *            <code>int</code> width of each box inside the floor.
	 * @param boxHeight
	 *            <code>int</code> height of each box inside the floor.
	 * @param color
	 *            <code>Integer</code> colour of each box inside the floor.
	 * 
	 * @see #addBox(int, int, Integer)
	 * 
	 */
	private void addBoxes(int boxWidth, int boxHeight, Integer color) {

		// Calculates the number of boxes in both the x and y directions.
		int xLayers = getWidth() / boxWidth;
		int yLayers = getHeight() / boxHeight;

		// Iterates through rows of boxes.
		for (int yLayer = 0; yLayer < yLayers; yLayer++) {

			// Iterates through each box in that row.
			for (int xLayer = 0; xLayer < xLayers; xLayer++) {

				// Calculates the position that the box should have in relation
				// to the position of the floor.
				int boxX = (xLayer * 25) + 1;
				int boxY = (yLayer * 25) + 1;

				addBox(boxX, boxY, color);
			}
		}

	}

	/**
	 * Adds a box with a specific position in relation to the floors position to
	 * the floor.
	 * 
	 * @param boxX
	 *            <code>int</code> x position of the box to be added.
	 * @param boxY
	 *            <code>int</code> y position of the box to be added.
	 * @param color
	 *            <code>Integer</code> colour of the box to be added.
	 * @see #addBoxes(int, int, Integer)
	 */
	private void addBox(int boxX, int boxY, Integer color) {

		// Iterates through each row of pixels for the box.
		for (int y = 0; y < 23; y++) {

			// Iterates through each pixel in the row and sets its colour to the
			// parameter.
			for (int x = 0; x < 23; x++) {
				pixels[boxX + x][boxY + y] = color;
			}
		}

	}

}
