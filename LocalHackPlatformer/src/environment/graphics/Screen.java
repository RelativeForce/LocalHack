package environment.graphics;

import java.awt.Color;

import environment.graphics.objects.GraphicalObject;

/**
 * An object that denotes the array of pixels that are displayed on the JFrame.
 * 
 * @author Joshua_Eddy, John_Berg
 * 
 * @see Display
 *
 */
public class Screen {

	private int width;
	private int height;
	private int[][] pixels;

	/**
	 * Creates a Screen object that will be displayed.
	 * 
	 * @param width
	 *            The width in pixels of the screen.
	 * @param height
	 *            the height in pixels of the screen.
	 */
	public Screen(int width, int height) {

		pixels = new int[width][height];

		this.width = width;
		this.height = height;
	}

	/**
	 * Returns the pixel array to be Displayed.
	 * 
	 * @return The <code>int[]</code> pixel array to be Displayed by <code>Display</code>.
	 * 
	 * @see Display
	 */
	public int[] getPixels() {

		int c = 0;
		int[] p = new int[width * height];

		for (int i = 0; i < height; i++) {

			for (int j = 0; j < width; j++) {

				p[c++] = pixels[j][i];
			}
		}

		return p;
	}

	/**
	 * Adds a <code>GraphicalObject</code> to the <code>Screen</code>.
	 * 
	 * @param graphicalObject The <code>GraphicalObject</code> to be added to the <code>Screen</code>.
	 * @param x The <code>int</code> x coordinate of the <code>GraphicalObject</code>.
	 * @param y The <code>int</code> y coordinate of the <code>GraphicalObject</code>.
	 */
	public void addGraphicalObject(GraphicalObject graphicalObject, int x, int y) {

		for (int i = 0; i < graphicalObject.getHeight(); i++) {

			if (0 <= y + i && y + i < height) {

				for (int j = 0; j < graphicalObject.getWidth(); j++) {

					if (0 <= x + j && x + j < width) {

						pixels[j + x][i + y] = graphicalObject.getPixels()[j][i] != null ? graphicalObject.getPixels()[j][i]
								: pixels[j + x][i + y];

					}
				}
			}
		}
	}

	/**
	 * Fills the screen with a set colour.
	 * 
	 * @param color  The <code>int</code> RGB colour that the pixel array will be filled with.
	 */
	public void setColor(int color) {

		for (int i = 0; i < height; i++) {

			for (int j = 0; j < width; j++) {

				pixels[j][i] = color;
			}
		}
	}

	/**
	 * Clears the Screen leaving it black.
	 */
	public void clear() {

		for (int i = 0; i < height; i++) {

			for (int j = 0; j < width; j++) {

				pixels[j][i] = 	Color.BLACK.getRGB();
			}
		}
	}
}