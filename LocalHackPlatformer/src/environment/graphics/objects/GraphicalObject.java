package environment.graphics.objects;

/**
 * <h3>Function:</h3> Encapsulates the behaviours of an visual object.
 * 
 * <h3>Sub-class(es):</h3>
 * <ul>
 * <li>Floor</li>
 * <li>Door</li>
 * <li>Rectangle</li>
 * <li>SpriteFrame</li>
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

	protected int width;

	protected int height;

	protected Integer[][] pixels;

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
	 * @see GraphicalObject
	 */
	@Override
	public boolean equals(Object o) {

		if (o instanceof GraphicalObject) {

			GraphicalObject otherGraphicalObject = (GraphicalObject) o;

			if (otherGraphicalObject.pixels.length == this.pixels.length
					&& otherGraphicalObject.pixels[0].length == this.pixels[0].length) {

				if (checkPixels(otherGraphicalObject.pixels)) {
					return true;
				}
			}

		}

		return false;
	}

	private boolean checkPixels(Integer[][] otherPixels) {

		for (int x = 0; x < pixels.length; x++) {
			
			for (int y = 0; y < pixels[0].length; y++) {
				
				if (otherPixels[x][y] != pixels[x][y]) {
					
					return false;
				}
			}
		}

		return true;
	}
}
