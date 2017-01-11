package graphics.objects;

import java.awt.Color;

/**
 * Describes a door that will start another level when the player intercepts it.
 * 
 * @author Joshua_Eddy
 *
 */
public class Door extends GraphicalObject {


	/**
	 * Constructs a new Door GraphicalObject.
	 * @param details
	 * 	 *        0. int width
	 *            1. int height 
	 *            2. Integer doorColor 
	 */
	public Door(Object[] details) {

		pixels = new Integer[(int)details[0]][(int)details[1]];
		width = (int)details[0];
		height = (int)details[1];

		addBackground();
		addDoor((Integer)details[2]);
		addDoorHandle((3 * width) / 4, height / 2);

	}

	private void addBackground() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				pixels[j][i] = Color.BLACK.getRGB();
			}
		}
	}

	private void addDoor(Integer color) {
		for (int i = 1; i < height - 1; i++) {
			for (int j = 1; j < width - 1; j++) {
				pixels[j][i] = color;
			}
		}
	}

	private void addDoorHandle(int x, int y) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				pixels[x + j][y + i] = Color.BLACK.getRGB();
			}
		}
	}
}
