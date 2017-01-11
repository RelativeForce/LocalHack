package graphics.objects;

/**
 * Rectangle is a type of Entity. 
 * @author Joshua_Eddy
 *
 */
public class Rectangle extends GraphicalObject {

	/**
	 * Constructs a new solid Rectangle GraphicalObject.
	 * @param details
	 * 	 *        0. int width
	 *            1. int height 
	 *            2. Integer color 
	 */
	public Rectangle(Object[] details) {

		pixels = new Integer[(int)details[0]][(int)details[1]];
		width = (int)details[0];
		height = (int)details[1];
		setColor((Integer)details[2]);

	}
	
	private void setColor(Integer color) {

		for (int i = 0; i < height; i++) {

			for (int j = 0; j < width; j++) {

				pixels[j][i] = color;
			}
		}
	}

}
