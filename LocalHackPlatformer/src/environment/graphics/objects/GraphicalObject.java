package environment.graphics.objects;

/**
 * <h3>Function:</h3>
 * Encapsulates the behaviours of an visual object.
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
	 * @return The <code>Integer[][]</code> pixel array that is used to display the <code>GraphicalObject</code>.
	 */
	public Integer[][] getPixels() {

		return pixels;
	}
	
	/**
	 * Retrieves the width of the <code>GrapghicalObject</code>.
	 * 
	 * @return The <code>int</code> width of the <code>GrapghicalObject</code>.
	 */
	public int getWidth(){
		
		return width;
	}
	
	/**
	 * Inverts the pixels to give a mirror image.
	 * @return <code>Integer[][]</code> that denotes the mirror pixels of the <code>GraphicalObject</code>s pixels.
	 */
	public Integer[][] getInvertedPixels() {
		
		Integer[][] invertedPixels = new Integer[width][height];
		
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
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
	public int getHeight(){
		
		return height;
	}
}
