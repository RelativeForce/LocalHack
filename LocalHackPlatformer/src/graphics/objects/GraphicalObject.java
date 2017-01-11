package graphics.objects;

/**
 * Encapsulates the behaviours of an object the will be displayed on the Screen.
 * 
 * @author Joshua_Eddy, John_Berg
 *
 */
public class GraphicalObject {

	protected int width;

	protected  int height;

	protected Integer[][] pixels;

	/**
	 * Sets the 2D array of pixels.
	 * @param pixels The pixel array that will be displayed on the screen.
	 */
	public void setPixels(Integer[][] pixels){
		this.width = pixels.length;
		this.height = pixels[0].length;
		this.pixels = pixels;
	}

	/**
	 * Retrieves the pixel array of the GraphicalObject.
	 * 
	 * @return The pixel array that is used to display the GraphicalObject.
	 */
	public Integer[][] getPixels() {

		return pixels;
	}
	
	/**
	 * Retrieves the width of the GrapghicalObject.
	 * @return The width of the GrapghicalObject.
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * Retrieves the height of the GrapghicalObject.
	 * @return The height of the GrapghicalObject.
	 */
	public int getHeight(){
		return height;
	}
}
