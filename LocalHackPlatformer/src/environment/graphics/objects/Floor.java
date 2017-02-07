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
	 * Constructs a new <code>Floor</code> graphi
	 * @param width <code>int</code> width of the floor in pixels.
	 * @param height <code>int</code> height of the floor in pixels.
	 * @param color <code>Integer</code> colour of the border surrounding the boxes inside the floor.
	 * @param boxWidth <code>int</code> width of the boxes inside the floor. The '<i>width of box</i>' % '<i>width of floor</i>' <strong>must</strong> equal zero.
	 * @param boxHeight <code>int</code> height of the boxes inside the floor. The '<i>height of box</i>' % '<i>height of floor</i>' <strong>must</strong> equal zero.
	 * @param boxColor <code>Integer</code> colour of the boxes inside the floor.
	 * @see GraphicalObject
	 */
	public Floor(int width, int height, Integer color, int boxWidth, int boxHeight, Integer boxColor) {

		this.pixels = new Integer[width][height];
		this.width = width;
		this.height = height;

		addBackground();
		addBoxes(boxWidth, boxHeight, boxColor);

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
