package environment.graphics.objects;

/**
 * <h3>Function:</h3>
 * A <code>GraphicalObject</code> that denotes a rectangle on the screen. 
 * 
 * <h3>Super-class:</h3> GraphicalObject
 * 
 * @author Joshua_Eddy
 * @see GraphicalObject
 *
 */
public class Rectangle extends GraphicalObject {

	/**
	 * Constructs a new <code>Rectangle</code> graphical object.
	 * @param details <code>Object[]<code> which is indexed as follows: </br>
	 *            0. <code>int</code> width of the <code>Rectangle</code>.</br>
	 *            1. <code>int</code> height of the <code>Rectangle</code>.</br>
	 *            2. <code>Integer</code> colour of the <code>Rectangle</code>.
	 * @see GraphicalObject
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
