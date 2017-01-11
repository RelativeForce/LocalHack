package graphics.objects;

import logic.Point;

/**
 * A single position of a sprite.
 * @author Joshua_Eddy
 *
 */
public class SpriteFrame extends GraphicalObject {

	/**
	 * Constructs a new instance of a SpriteFrame.
	 * @param details
	 *            0. int width 
	 *            1. int height 
	 *            2. String directory
	 *            3. Point position
	 */
	public SpriteFrame(Object[] details) {

		pixels = new Integer[(int) details[0]][(int) details[1]];
		width = (int) details[0];
		height = (int) details[1];
		String directory = (String) details[2];
		Point positionInFile = (Point)details[3];
		
		
	}

}
