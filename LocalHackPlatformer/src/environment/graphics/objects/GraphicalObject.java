package environment.graphics.objects;

/**
 * <h3>Function:</h3> Encapsulates the behaviours of an visual object on screen.
 * 
 * <h3>Sub-class(es):</h3>
 * <ul>
 * <li>{@link Floor}</li>
 * <li>{@link Door}</li>
 * <li>{@link Rectangle}</li>
 * <li>{@link SpriteFrame}</li>
 * </ul>
 * 
 * @author Joshua_Eddy, John_Berg
 * 
 * @see Floor
 * @see Door
 * @see Rectangle
 * @see SpriteFrame
 *
 */
public abstract class GraphicalObject {

	/**
	 * The width of {@link #pixels}.
	 */
	private int width;

	/**
	 * The height of {@link #pixels}. 
	 */
	private int height;

	/**
	 * Stores the pixels that visually represent the <code>GraphicalObject</code>.
	 * 
	 * @see #width
	 * @see #height
	 */
	protected Integer[][] pixels;

	protected GraphicalObject(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Retrieves the pixel array of the <code>GraphicalObject</code>.
	 * 
	 * @return The <code>Integer[][]</code> pixel array that is used to display
	 *         the <code>GraphicalObject</code>.
	 */
 	public Integer[][] getPixels() {
		return pixels;
	}

	/**
	 * Retrieves the width of the <code>GrapghicalObject</code>.
	 * 
	 * @return The <code>int</code> width of the <code>GrapghicalObject</code>.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Inverts the pixels to give a mirror image.
	 * 
	 * @return <code>Integer[][]</code> that denotes the mirror pixels of the
	 *         <code>GraphicalObject</code>s pixels.
	 */
	public Integer[][] getInvertedPixels() {

		Integer[][] invertedPixels = new Integer[width][height];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				invertedPixels[j][i] = this.pixels[width - j - 1][i];
			}
		}

		return invertedPixels;
	}

	/**
	 * Retrieves the height of the <code>GrapghicalObject</code>.
	 * 
	 * @return The <code>int</code> height of the <code>GrapghicalObject</code>.
	 */
	public int getHeight() {

		return height;
	}

	/**
	 * Fills all the <code>GraphicalObject</code> pixels with one specified
	 * colour.
	 * 
	 * @param color
	 *            <code>Integer</code> colour that will be the solid colour of
	 *            the <code>GraphicalObject</code>.
	 * @see #pixels
	 */
	protected void addSolidBackground(Integer color) {

		// Iterates through each row of the door's pixels
		for (int yCoordinate = 0; yCoordinate < height; yCoordinate++) {

			// Iterates through each pixel in the row and assigns it the colour
			// black.
			for (int xCoordinate = 0; xCoordinate < width; xCoordinate++) {
				pixels[xCoordinate][yCoordinate] = color;
			}
		}
	}

	/**
	 * Checks the equality between a <code>this</code> and an <code>Object</code>.
	 * 
	 * @param o
	 *            <code>Object</code> who's equality to <code>this</code> will
	 *            be determined.
	 * 
	 * @see GraphicalObject
	 * @see #checkPixels(Integer[][])
	 */
	@Override
	public boolean equals(Object o) {

		// Check if the parameter is a GraphicalObject
		if (o instanceof GraphicalObject) {

			GraphicalObject otherGraphicalObject = (GraphicalObject) o;

			// If the dimensions of both arrays of pixels are the same then
			// check if the pixels in both arrays are the same.
			if (otherGraphicalObject.pixels.length == this.pixels.length
					&& otherGraphicalObject.pixels[0].length == this.pixels[0].length) {

				if (checkPixels(otherGraphicalObject.pixels)) {
					// If all the pixels are the same the two graphical objects
					// are identical.
					return true;
				}
			}
		}
		// Otherwise the two graphical objects are not equal.
		return false;
	}

	/**
	 * Checks if all the elements in the parameter <code>Integer[][]</code> are
	 * the same as {@link #pixels}. This method is only executed by
	 * {@link #equals(Object)} in which both the dimensions of the two arrays
	 * are check to be equal.
	 * 
	 * @param otherPixels
	 *            <code>Integer[][]</code> that will be checked against
	 *            <code>this</code>.
	 * @return <code>boolean<code> status of equality.
	 * 
	 * @see #pixels
	 * @see #equals(Object)
	 */
	private boolean checkPixels(Integer[][] otherPixels) {

		// Iterates through each row of pixels.
		for (int x = 0; x < pixels.length; x++) {

			// Iterates through each pixel in that row and if any pixels are not
			// equal then the two arrays are not identical.
			for (int y = 0; y < pixels[0].length; y++) {

				if (otherPixels[x][y] != pixels[x][y]) {
					return false;
				}
			}
		}
		// Otherwise the arrays must be equal.
		return true;
	}
}
