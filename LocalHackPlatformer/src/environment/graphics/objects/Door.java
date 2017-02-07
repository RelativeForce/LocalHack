package environment.graphics.objects;

import java.awt.Color;

/**
 * <h3>Function:</h3>
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
	 * @param width <code>int</code> width of the Door in pixels.
	 * @param height <code>int</code> height of the Door in pixels.
	 * @param color <code>Integer</code> colour of the Door.
	 * 
	 * @see GraphicalObject
	 */
	public Door(int width, int height, Integer color) {

		this.pixels = new Integer[width][height];
		this.width = width;
		this.height = height;

		addBackground();
		addDoor(color);
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
