package graphics.objects;
/**
 * A single position of a sprite.
 * 
 * @author Joshua_Eddy
 *
 */
public class SpriteFrame extends GraphicalObject {

	/**
	 * Constructs a new instance of a SpriteFrame.
	 * 
	 * @param pixels The 2d array of Integers that make up a SpriteFrame.
	 */
	public SpriteFrame(Integer[][] pixels) {

		this.pixels = pixels;
		this.width = pixels.length;
		this.height = pixels[0].length;

	}

}
