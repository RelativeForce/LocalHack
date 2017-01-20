package graphics.objects;

/**
 * A single position of a sprite.
 * @author Joshua_Eddy
 *
 */
public class SpriteFrame extends GraphicalObject {

	/**
	 * Constructs a new instance of a SpriteFrame.
	 * @param pixels The 2d array of pixels that will denote the pixels of the graphical object.
	 */
	public SpriteFrame(Integer[][] pixels) {

		this.pixels = pixels;
		this.width = pixels.length;
		this.height = pixels[0].length;
		
	}

}
