package resources;

import java.io.File;
import graphics.objects.SpriteFrame;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Reads an image file and builds SpriteFrames from it.
 * @author Joshua_Eddy, John_Berg
 *
 */
public class SpriteSheetReader {

	private File[] directory;

	/**
	 * Constructs a new SpriteSheetReader.
	 * @param directoryPath The directory path containing the sprite sheets.
	 */
	public SpriteSheetReader(String directoryPath) {
		directory = ResourceHandler.getDirectory(directoryPath);
	}

	/**
	 * 
	 * @param fileName The name of the image file.
	 * @param x The x position of the SpriteFrame in the file.
	 * @param y The y position of the SpriteFrame in the file.
	 * @return A SpriteFrame object read from the image file. 
	 */
	public SpriteFrame getSpriteFrame(String fileName, int x, int y) {

		int width = 100;
		int height = 100;

		BufferedImage image = null;
		File file = null;

		try {
			file = ResourceHandler.getFile(fileName, directory);
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			image = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (image == null) {
			return null;
		}
		
		
		// TODO use image to create int[][] for image processing
		return null;

	}

}