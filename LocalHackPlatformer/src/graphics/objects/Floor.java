package graphics.objects;

import java.awt.Color;

/**
 * The Floor object is type of GraphicalObject. It consists of a set of squares bordered
 * by a single colour.
 * 
 * @author Joshua_Eddy
 *
 */
public class Floor extends GraphicalObject {

	/**
	 * Constructs a new Floor GrapghicalObject.
	 * @param details
	 *            0. int width
	 *            1. int height 
	 *            2. Integer borderColor 
	 *            3. int boxWidth
	 *            4. int boxHeight
	 *            5. Integer boxColor
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
