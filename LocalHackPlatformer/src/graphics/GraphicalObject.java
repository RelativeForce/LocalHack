package graphics;

import entities.Entity;

/**
 * Encapsulates the behaviours of an object the will be displayed on the Screen.
 * 
 * @author Joshua_Eddy, John_Berg
 *
 */
public class GraphicalObject {

	/**
	 * The width in pixels of the graphical object.
	 */
	public final int width;

	/**
	 * The height in pixels of the graphical object.
	 */
	public final int height;

	private Integer[][] pixels;

	/**
	 * Creates a GraphicalObject of solid rectangle colour that can be
	 * displayed.
	 * 
	 * @param width
	 *            The width of the rectangle in pixels.
	 * @param height
	 *            The height of the rectangle in pixels.
	 * @param color
	 *            The RGB colour of the rectangle.
	 */
	public GraphicalObject(int width, int height, Integer color) {

		pixels = new Integer[width][height];

		this.width = width;
		this.height = height;
		setColor(color);
	}

	/**
	 * Creates an GraphicalObject from a collection of entities that can be
	 * displayed.
	 * 
	 * @param width
	 *            The maximum width of the GraphicalObject width.
	 * @param height
	 *            The maximum height of the GrpahicalObject height.
	 * @param entitys
	 *            The collection entities that will make up a complex
	 *            GraphicalObject.
	 */
	public GraphicalObject(int width, int height, Entity[] entitys) {

		pixels = new Integer[width][height];
		this.width = width;
		this.height = height;
		complexObject(entitys);

	}

	private void complexObject(Entity[] entities) {

		boolean first = true;
		for (Entity entity : entities) {
			if (first) {
				setColor(entity.getColor());
				first = false;
			} else {
				setColor(entity);
			}
		}

	}

	private void setColor(Entity entity) {

		for (int i = 0; i < entity.getGraphicalObject().height; i++) {
			for (int j = 0; j < entity.getGraphicalObject().width; j++) {
				pixels[entity.getX() + j][entity.getY() + i] = entity.getColor();
			}
		}
	}

	/**
	 * Sets the colour of the GraphicalObject to one colour.
	 * 
	 * @param color
	 *            The RGB colour that the Graphical object will be set to.
	 */
	public void setColor(Integer color) {

		for (int i = 0; i < height; i++) {

			for (int j = 0; j < width; j++) {

				pixels[j][i] = color;
			}
		}
	}

	/**
	 * Sets the RGB colour the of a specified pixel to specified RGB colour.
	 * 
	 * @param color
	 *            The new RGB colour of the specified pixel.
	 * @param x
	 *            The x position of the pixel relative to the corner of the
	 *            GraphicalObject.
	 * @param y
	 *            The y position of the pixel relative to the corner of the
	 *            GraphicalObject.
	 */
	public void setColor(Integer color, int x, int y) {

		pixels[x][y] = color;
	}

	/**
	 * Retrieves the pixel array of the GraphicalObject.
	 * 
	 * @return The pixel array that is used to display the GraphicalObject.
	 */
	public Integer[][] getPixels() {

		return pixels;
	}
}
