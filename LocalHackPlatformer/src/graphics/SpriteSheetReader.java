package graphics;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import graphics.objects.SpriteFrame;

/**
 * The SpriteSheetReader class is intended for extracting spritesheets from image files,
 * SpriteSheetReader has one public method which fetches the spritesheet from a target 
 * path.
 * 
 * @author John
 *
 */
public final class SpriteSheetReader {
	
	private final int width;
	private final int height;
	private final File file;
	private final BufferedImage image;
	
	private SpriteSheetReader(final String path, final int width, final int height) {
		
		this.width = width;
		this.height = height;
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
	private int getMaxWidth(){
		
		return image.getWidth();
	}
	private int getMaxHeight(){
		
		return image.getHeight();
	}
	private SpriteFrame getSprite(final int xOffset, final int yOffset){
		
		for(int y = 0; y < height; ++y){
			
			for(int x = 0; x < width; ++x){
				
				image.getRGB(x + xOffset, y + yOffset);
			}
		}
		
		return null;
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
		
		SpriteSheetReader spriteSheet = new SpriteSheetReader(path, width, height);
		LinkedList<SpriteFrame> sprites = new LinkedList<SpriteFrame>();
		
		
		for(int i = 0; i < spriteSheet.getMaxHeight(); i += height){
			
			for(int j = 0; j < spriteSheet.getMaxWidth(); j += width){
				
				sprites.add(spriteSheet.getSprite(j, i));
			}
		}
		
		return sprites.toArray(new SpriteFrame[sprites.size()]);
	}
}