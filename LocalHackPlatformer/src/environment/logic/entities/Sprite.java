package environment.logic.entities;

import java.util.ArrayList;

import environment.graphics.objects.SpriteFrame;
import resources.SpriteSheetReader;

/**
 * Encapsulates the behaviour of a sprite which is denoted by a collection of <code>SpriteFrame</code> <code>Entity</code>s.
 * 
 * @author Joshua_Eddy
 * 
 * @see Entity
 * @see environment.graphics.objects.SpriteFrame
 *
 */
public class Sprite {

	private ArrayList<Entity> frames;
	private ArrayList<Entity> invertedFrames;
	private int currentFrame;
	private boolean inverted;
	private int x;
	private int y;

	/**
	 * Constructs a new <code>Sprite</code>.</br>
	 * If the <code>path</code> is invalid an IOException will be thrown.
	 * 
	 * @param path The <code>String</code> file path of the sprite image file.
	 * @param width The <code>int</code> width of the sprites in the image file in pixels.
	 * @param height The <code>int</code> height of the sprites in the image file in pixels.
	 * @param x The <code>int</code> x  of the sprite on the <code>Screen</code>.
	 * @param y The <code>int</code> y  of the sprite on the <code>Screen</code>.
	 * 
	 * @see Entity
	 * @see environment.graphics.objects.GraphicalObject
	 * @see environment.graphics.objects.SpriteFrame
	 */
	public Sprite(String path, int width, int height, int x, int y) {

		setX(x);
		setY(y);

		inverted = false;
		frames = new ArrayList<Entity>();
		invertedFrames = new ArrayList<Entity>();

		for (SpriteFrame frameSprite : SpriteSheetReader.getSprites(path, width, height)) {

			frames.add(Entity.SpriteFrame(x, y, frameSprite.getPixels()));
			invertedFrames.add(Entity.SpriteFrame(x, y, frameSprite.getInvertedPixels()));

		}

		currentFrame = 0;
	}

	public void nextFrame() {
		currentFrame = (currentFrame + 1) % frames.size();
	}

	public Entity getEntity() {
		Entity entity = inverted ? invertedFrames.get(currentFrame) : frames.get(currentFrame);
		entity.setX(x);
		entity.setY(y);
		return entity;
	}
	
	public void invert(){
		inverted = true;
	}
	
	public void revert(){
		inverted = false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
