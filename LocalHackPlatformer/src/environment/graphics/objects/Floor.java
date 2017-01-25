package environment.graphics.objects;

import java.awt.Color;

/**
 * <h3>Function:</h3>
 * The Floor object is type of <code>GraphicalObject</code>. It consists of a set of squares bordered
 * by a single colour.
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
	 * @param details <code>Object[]<code> which is indexed as follows: </br>
	 *            0. <code>int</code> width of the floor in pixels.</br>
	 *            1. <code>int</code> height of the floor in pixels.</br>
	 *            2. <code>Integer</code> colour of the border surrounding the boxes inside the floor.</br>
	 *            3. <code>int</code> width of the boxes inside the floor. The '<i>width of box</i>' % '<i>width of floor</i>' <strong>must</strong> equal zero.</br>
	 *            4. <code>int</code> height of the boxes inside the floor. The '<i>height of box</i>' % '<i>height of floor</i>' <strong>must</strong> equal zero.</br>
	 *            5. <code>Integer</code> colour of the boxes inside the floor.
	 * @see GraphicalObject
	 */
	public Floor(Object[] details) {

		pixels = new Integer[(int) details[0]][(int) details[1]];
		width = (int) details[0];
		height = (int) details[1];

		addBackground();
		addBoxes((int) details[3], (int) details[4], (Integer) details[5]);

	}

	private void addBoxes(int boxWidth, int boxHeight, Integer color) {

		int xLayers = width / boxWidth;
		int yLayers = height / boxHeight;

		for (int i = 0; i < yLayers; i++) {
			for (int j = 0; j < xLayers; j++) {
				int boxX = (j * 25) + 1;
				int boxY = (i * 25) + 1;
				addBox(boxX, boxY, color);
			}
		}

	}

	private void addBox(int x, int y, Integer color) {

		for (int i = 0; i < 23; i++) {
			for (int j = 0; j < 23; j++) {
				pixels[x + j][y + i] = color;
			}
		}

	}

	private void addBackground() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				pixels[j][i] = Color.BLACK.getRGB();
			}
		}
	}

}
