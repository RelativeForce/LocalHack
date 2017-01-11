package entities;

import graphics.objects.GraphicalObject;

/**
 * Two two dimensional Integer arrays that denote the HitBox of an Entity.
 * @author Joshua_Eddy
 *
 */
public class HitBox {

	private Integer[][] yPerimeter;
	private Integer[][] xPerimeter;
	
	/**
	 * Constructs a new HitBox object.
	 * @param object The GraphicalObject from which the HitBox is derived.
	 */
	public HitBox(GraphicalObject object){
		
		setYPerimeter(object);
		setXPerimeter(object);
		
	}
	
	private void setXPerimeter(GraphicalObject object) {

		xPerimeter = new Integer[object.getHeight()][2];
		Integer[][] pixels = object.getPixels();
		int width = object.getWidth();
		int height = object.getHeight();

		for (int rowNum = 0; rowNum < height; rowNum++) {
			for (int x = 0; x < width; x++) {

				if ((pixels[x][rowNum] != null && x == 0)) {
					xPerimeter[rowNum][0] = x;
					break;
				}
				if (x > 0) {
					if (pixels[x - 1][rowNum] == null) {
						xPerimeter[rowNum][0] = x;
						break;
					}
				}

			}
		}

		for (int rowNum = 0; rowNum < height; rowNum++) {
			for (int x = width - 1; x >= 0; x--) {
				if (pixels[x][rowNum] != null && x == (width - 1)) {
					xPerimeter[rowNum][1] = x;
					break;
				}
				if (x < width - 1) {
					if (pixels[x + 1][rowNum] == null) {
						xPerimeter[rowNum][1] = x;
						break;
					}
				}
			}
		}

	}

	private void setYPerimeter(GraphicalObject object) {

		yPerimeter = new Integer[object.getWidth()][2];
		int width = object.getWidth();
		int height = object.getHeight();
		Integer[][] pixels = object.getPixels();

		for (int colNum = 0; colNum < width; colNum++) {
			for (int y = 0; y < height; y++) {

				if ((pixels != null && y == 0)) {
					yPerimeter[colNum][0] = y;
					break;
				}
				if (y > 0) {
					if (pixels[colNum][y + 1] == null) {
						yPerimeter[colNum][0] = y;
						break;
					}
				}
			}
		}
		for (int colNum = 0; colNum < width; colNum++) {
			for (int y = height - 1; y >= 0; y--) {
				if (pixels[colNum][y] != null && y == (height - 1)) {
					yPerimeter[colNum][1] = y;
					break;
				}
				if (y < height - 1) {
					if (pixels[colNum][y + 1] == null) {
						yPerimeter[colNum][1] = y;
						break;
					}
				}
			}
		}

	}

	/**
	 * Returns the X parameter pixel array of the HitBox.
	 * @return The X parameter pixel array of the HitBox.
	 */
	public Integer[][] getXPerimeter(){
		return xPerimeter;
	}
	
	/**
	 * Returns the Y parameter pixel array of the HitBox.
	 * @return The X parameter pixel array of the HitBox.
	 */
	public Integer[][] getYPerimeter(){
		return yPerimeter;
	}
	
}
