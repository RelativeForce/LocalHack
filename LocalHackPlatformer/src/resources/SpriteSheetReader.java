package resources;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.HashMap;
import environment.Constants;
import environment.graphics.objects.SpriteFrame;
import environment.logic.constructs.enemies.Arganok;
import environment.logic.constructs.enemies.Grunt;
import environment.logic.constructs.players.DefaultPlayer;

/**
 * The SpriteSheetReader class is intended for extracting sprite sheets from
 * image files, SpriteSheetReader has one public method which fetches the sprite
 * sheet from a target path.
 * 
 * @author John_Berg
 *
 */
public final class SpriteSheetReader {

	private static HashMap<Class<?>, SpriteDetails> SPRITE_DETAILS;

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

	private BufferedImage getImage() {

		try {

			return ImageIO.read(file);

		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}
	}

	private int getMaxWidth() {

		return image.getWidth();
	}

	private int getMaxHeight() {

		return image.getHeight();
	}

	private SpriteFrame getSprite(final int x_Offset, final int y_Offset) {

		final Integer[][] sprite = new Integer[width][height];

		for (int y = 0; y < height; ++y) {

			for (int x = 0; x < width; ++x) {

				int currentPixel = image.getRGB(x + x_Offset, y + y_Offset);
				if (currentPixel == Constants.TRANSPARENT_COLOR.intValue()) {
					sprite[x][y] = null;
				} else {
					sprite[x][y] = currentPixel;
				}
			}
		}

		return new SpriteFrame(sprite);
	}

	/**
	 * Read a sprite sheet from a given path where each sprite is a fixed width
	 * and height. Each sprite in the sprite sheet must be of equal size.
	 * 
	 * @param path
	 *            The path to the sprite sheet.
	 * @param width
	 *            Sprite width.
	 * @param height
	 *            Sprite height.
	 * @return The sprites contained within the target sprite sheet.
	 * 
	 * @see environment.graphics.objects.SpriteFrame
	 */
	public static final SpriteFrame[] getSprites(Class<?> type) {
	
		String path = SPRITE_DETAILS.get(type).getPath();
		int width = SPRITE_DETAILS.get(type).getWidth();
		int height = SPRITE_DETAILS.get(type).getHeight();
		
		final SpriteSheetReader spriteSheet = new SpriteSheetReader(path, width, height);
		final LinkedList<SpriteFrame> sprites = new LinkedList<SpriteFrame>();

		for (int i = 0; i < spriteSheet.getMaxHeight(); i += height) {

			for (int j = 0; j < spriteSheet.getMaxWidth(); j += width) {

				sprites.add(spriteSheet.getSprite(j, i));
			}
		}

		return sprites.toArray(new SpriteFrame[sprites.size()]);
	}

	/**
	 * Initialises the details of each sprite type.
	 */
	public static void initaliseSpriteDetails() {
		
		SPRITE_DETAILS = new HashMap<Class<?>, SpriteDetails>();

		// The filename of the image containing the grunt sprite frames.

		SpriteDetails grunt = new SpriteDetails("Grunt.png", 20, 25);

		// The filename of the image containing the player sprite frames.

		SpriteDetails defaultPlayer = new SpriteDetails("Mario.png", 16, 32);

		// The filename of the image containing the Arganok sprite frames.

		SpriteDetails arganok = new SpriteDetails("Arganok.png", 42, 42);

		SPRITE_DETAILS.put(Grunt.class, grunt);
		SPRITE_DETAILS.put(Arganok.class, arganok);
		SPRITE_DETAILS.put(DefaultPlayer.class, defaultPlayer);

	}

	private static class SpriteDetails {

		private String path;
		private int width;
		private int height;

		public SpriteDetails(String relativePath, int width, int height) {

			path = new File(System.getProperty("user.dir")).getPath() + File.separatorChar + relativePath;
			this.height = height;
			this.width = width;
		}

		public String getPath() {
			return path;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

	}
}