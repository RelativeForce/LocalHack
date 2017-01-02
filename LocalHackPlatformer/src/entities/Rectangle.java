package entities;

/**
 * Rectangle is a type of Entity. 
 * @author Joshua_Eddy
 *
 */
public class Rectangle extends Entity {

	/**
	 * Constructs a Rectangle object.
	 * @param x The X coordinate of the Rectangle.
	 * @param y The Y coordinate of the Rectangle.
	 * @param width The width of the Rectangle in pixels.
	 * @param height The height of the Rectangle in pixels.
	 * @param color The RGB colour of the Rectangle.
	 */
	public Rectangle(int x, int y, int width, int height, Integer color) {

		if (x >= 0) {
			setX(x);
		} else {
			setX(0);
		}
		if (y >= 0) {
			setY(y);
		} else {
			setY(0);
		}
		setColor(color);
		setGraphicalObject(width, height, color);

	}

	/**
	 * Changes the size and colour of the Rectangle.
	 * @param width The new width of the Rectangle.
	 * @param height The new height of the Rectangle.
	 * @param color The new RGB colour of the Rectangle.
	 */
	public void changeSize(int width, int height, Integer color) {

		setGraphicalObject(width, height, color);

	}

}
