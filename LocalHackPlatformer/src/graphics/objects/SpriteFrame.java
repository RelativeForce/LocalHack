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

	/**
	 * 
	 * @return
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

}
