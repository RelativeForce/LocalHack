package environment.graphics.objects;
/**
 * <h3>Function:</h3>
 * A single position of a sprite that can also be represented as a <code>Integer[][]</code> of pixels.
 * 
 * <h3>Super-class:</h3> GraphicalObject
 * 
 * @author Joshua_Eddy
 * 
 * @see GraphicalObject
 *
 */
public class SpriteFrame extends GraphicalObject {

	/**
	 * Constructs a new <code>SpriteFrame</code> graphical object.
	 * 
	 * @param pixels The <code>Integer[][]</code> that denotes the colour of each pixel of the SpriteFrame.
	 */
	public SpriteFrame(Integer[][] pixels) {

		this.pixels = pixels;
		this.width = pixels.length;
		this.height = pixels[0].length;

	}

}
