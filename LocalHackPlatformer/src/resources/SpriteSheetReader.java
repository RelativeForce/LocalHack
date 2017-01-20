package resources;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import graphics.objects.SpriteFrame;

public final class SpriteSheetReader {
	
	private final File file;
	private final BufferedImage image;
	
	private SpriteSheetReader(final String path) {
		
		file = new File(path);
		image = getImage();
	}
	private BufferedImage getImage(){
		
		try{
			
			return ImageIO.read(file);
			
		}catch(IOException e){
			
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Read a spritesheet from a given path where each sprite is a fixed width and height.
	 * 
	 * @param path The path to the spritesheet.
	 * @param width Sprite width.
	 * @param height Sprite height.
	 * @return The sprites contained within the target spritesheet.
	 * @See SpriteFrame
	 */
	public static final SpriteFrame[] getSprites(final String path, final int width, final int height){
		
		SpriteSheetReader spriteSheet = new SpriteSheetReader(path);
		
		
		return null;
	}
}