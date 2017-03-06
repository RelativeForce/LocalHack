package environment.graphics.objects;

/**
 * <h3>Function:</h3> A <code>GraphicalObject</code> that denotes a rectangle on
 * the screen.
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
	 * 
	 * @param width
	 *            <code>int</code> width of the <code>Rectangle</code>.
	 * @param height
	 *            <code>int</code> height of the <code>Rectangle</code>.
	 * @param color
	 *            <code>Integer</code> colour of the <code>Rectangle</code>.
	 * 
	 * @see GraphicalObject
	 * @see #addSolidBackground(Integer)
	 */
	public Rectangle(int width, int height, Integer color) {

		super(width, height);
		// Assigns the basic details of the Rectangle from the parameters.
		this.pixels = new Integer[width][height];
		
		addSolidBackground(color);

	}

}
