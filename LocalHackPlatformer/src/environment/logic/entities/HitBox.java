package environment.logic.entities;

import environment.graphics.objects.GraphicalObject;

/**
 * Denotes the outer boundaries of the region of pixels given by a <code>GraphicalObject<code>.
 * 
 * @author Joshua_Eddy
 *
 * @see Entity
 * @see environment.graphics.objects.GraphicalObject
 *
 */
public class HitBox {

	private Integer[][] verticalBoundaries;
	private Integer[][] horizontalBoundaries;

	/**
	 * Constructs a new <code>HitBox<code> object.
	 * 
	 * @param object The <code>GraphicalObject<code> from which the <code>HitBox<code> is derived.
	 * 
	 * @see environment.graphics.objects.GraphicalObject
	 */
	public HitBox(GraphicalObject object) {

		setVerticalBoundaries(object);
		setHorizontalBoundaries(object);

	}

	private void setHorizontalBoundaries(GraphicalObject object) {

		horizontalBoundaries = new Integer[object.getHeight()][2];
		Integer[][] pixels = object.getPixels();
		int width = object.getWidth();
		int height = object.getHeight();

		for (int rowNum = 0; rowNum < height; rowNum++) {
			for (int x = 0; x < width; x++) {

				if ((pixels[x][rowNum] != null && x == 0)) {
					horizontalBoundaries[rowNum][0] = x;
					break;
				}
				if (x > 0) {
					if (pixels[x - 1][rowNum] == null) {
						horizontalBoundaries[rowNum][0] = x;
						break;
					}
				}

			}
		}

		for (int rowNum = 0; rowNum < height; rowNum++) {
			for (int x = width - 1; x >= 0; x--) {
				if (pixels[x][rowNum] != null && x == (width - 1)) {
					horizontalBoundaries[rowNum][1] = x;
					break;
				}
				if (x < width - 1) {
					if (pixels[x + 1][rowNum] == null) {
						horizontalBoundaries[rowNum][1] = x;
						break;
					}
				}
			}
		}

	}

	private void setVerticalBoundaries(GraphicalObject object) {

		verticalBoundaries = new Integer[object.getWidth()][2];
		int width = object.getWidth();
		int height = object.getHeight();
		Integer[][] pixels = object.getPixels();

		for (int colNum = 0; colNum < width; colNum++) {
			for (int y = 0; y < height; y++) {

				if ((pixels != null && y == 0)) {
					verticalBoundaries[colNum][0] = y;
					break;
				}
				if (y > 0) {
					if (pixels[colNum][y + 1] == null) {
						verticalBoundaries[colNum][0] = y;
						break;
					}
				}
			}
		}
		for (int colNum = 0; colNum < width; colNum++) {
			for (int y = height - 1; y >= 0; y--) {
				if (pixels[colNum][y] != null && y == (height - 1)) {
					verticalBoundaries[colNum][1] = y;
					break;
				}
				if (y < height - 1) {
					if (pixels[colNum][y + 1] == null) {
						verticalBoundaries[colNum][1] = y;
						break;
					}
				}
			}
		}

	}

	/**
	 * Retrieves the right and left boundary for each row of pixels.</br>
	 * The row on which the boundaries lie is indexed by <code>Integer<strong>[]</strong>[]</code>.</br>
	 * The left boundary is given by <code>Integer[]<strong>[0]</strong></code>.</br>
	 * The right boundary is given by <code>Integer[]<strong>[1]</strong></code>.</br>
	 * 
	 * @return The <code>Integer[][]<code> denoting the horizontal boundaries of the <code>HitBox</code>.
	 * 
	 * @see environment.graphics.objects.GraphicalObject
	 * @see Entity
	 */
	public Integer[][] getHorizontalBoundaries() {
		return horizontalBoundaries;
	}

	/**
	 * Retrieves the upper and lower boundary for each column of pixels.</br>
	 * The column on which the boundaries lie is indexed by <code>Integer<strong>[]</strong>[]</code>.</br>
	 * The upper boundary is given by <code>Integer[]<strong>[0]</strong></code>.</br>
	 * The lower boundary is given by <code>Integer[]<strong>[1]</strong></code>.</br>
	 * 
	 * @return The <code>Integer[][]<code> denoting the vertical boundaries of the <code>HitBox</code>.
	 * 
	 * @see environment.graphics.objects.GraphicalObject
	 * @see Entity
	 */
	public Integer[][] getVerticalBoundaries() {
		return verticalBoundaries;
	}

}
