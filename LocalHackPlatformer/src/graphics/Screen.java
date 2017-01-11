package graphics;

import graphics.objects.GraphicalObject;

/**
 * An object that denotes the array of pixels that are displayed on the JFrame.
 * 
 * @author Joshua_Eddy, John_Berg
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
	 * @return The pixel array to be Displayed.
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
	 * Adds a GraphicalObject to the Screen.
	 * 
	 * @param go
	 *            The GraphicalObject to be added to the Screen.
	 * @param x
	 *            The x coordinate of the GraphicalObject relative to the top
	 *            left of the Screen.
	 * @param y
	 *            The y coordinate of the GraphicalObject relative to the top
	 *            left of the Screen.
	 */
	public void addGraphicalObject(GraphicalObject go, int x, int y) {

		for (int i = 0; i < go.getHeight(); i++) {

			if (0 <= y + i && y + i < height) {

				for (int j = 0; j < go.getWidth(); j++) {

					if (0 <= x + j && x + j < width) {

						pixels[j + x][i + y] = go.getPixels()[j][i] != null ? go.getPixels()[j][i]
								: pixels[j + x][i + y];

					}
				}
			}
		}
	}

	/**
	 * Fills the screen with a set colour.
	 * 
	 * @param color
	 *            The RGB colour that the pixel array will be filled with.
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

				pixels[j][i] = 0;
			}
		}
	}
}