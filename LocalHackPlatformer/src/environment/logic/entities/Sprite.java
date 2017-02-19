package environment.logic.entities;

import java.util.ArrayList;
import environment.graphics.objects.SpriteFrame;
import environment.logic.SpriteDetails;
import resources.SpriteSheetReader;

/**
 * Encapsulates the behaviour of a sprite which is denoted by a collection of
 * <code>SpriteFrame</code> <code>Entity</code>s.
 * 
 * @author Joshua_Eddy
 * 
 * @see environment.logic.entities.Entity
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
	 * Constructs a new <code>Sprite</code>.<br>
	 * If the <code>path</code> is invalid an IOException will be thrown.
	 * 
	 * @param details
	 *            <code>SpriteDetails</code> All of the information required to
	 *            find and read the sprite sheet.
	 * @param x
	 *            The <code>int</code> x of the sprite on the
	 *            <code>Screen</code>.
	 * @param y
	 *            The <code>int</code> y of the sprite on the
	 *            <code>Screen</code>.
	 * 
	 * @see environment.logic.entities.Entity
	 * @see environment.graphics.objects.GraphicalObject
	 * @see environment.graphics.objects.SpriteFrame
	 */
	public Sprite(SpriteDetails details, int x, int y) {

		setX(x);
		setY(y);
		currentFrame = 0;

		frames = new ArrayList<Entity>();

		for (SpriteFrame frameSprite : SpriteSheetReader.getSprites(details.getPath(), details.getWidth(),
				details.getHeight())) {

			frames.add(Entity.newSpriteFrame(x, y, frameSprite.getPixels()));

		}

		initaliseInvertedSpriteFrames();

	}

	/**
	 * Constructs a new <code>Sprite</code>.<br>
	 * 
	 * @param frames
	 *            The collection of <code>Entity</code>s that denote the
	 *            sprite's frames.
	 * @param x
	 *            The <code>int</code> x of the sprite on the
	 *            <code>Screen</code>.
	 * @param y
	 *            The <code>int</code> y of the sprite on the
	 *            <code>Screen</code>.
	 * 
	 * @see environment.logic.entities.Entity
	 * @see environment.graphics.objects.GraphicalObject
	 * @see environment.graphics.objects.SpriteFrame
	 */
	public Sprite(ArrayList<Entity> frames, int x, int y) {

		this.frames = frames;
		setX(x);
		setY(y);
		currentFrame = 0;

		initaliseInvertedSpriteFrames();

	}

	/**
	 * Constructs a new <code>Sprite</code>.<br>
	 * 
	 * @param frame
	 *            The single <code>Entity</code> that denotes the sprite.
	 * @param x
	 *            The <code>int</code> x of the sprite on the
	 *            <code>Screen</code>.
	 * @param y
	 *            The <code>int</code> y of the sprite on the
	 *            <code>Screen</code>.
	 * @see environment.logic.entities.Entity
	 * @see environment.graphics.objects.GraphicalObject
	 * @see environment.graphics.objects.SpriteFrame
	 */
	public Sprite(Entity frame, int x, int y) {

		frames = new ArrayList<Entity>();
		frames.add(frame);

		setX(x);
		setY(y);
		currentFrame = 0;

		initaliseInvertedSpriteFrames();

	}

	private void initaliseInvertedSpriteFrames() {

		invertedFrames = new ArrayList<Entity>();
		inverted = false;

		for (Entity frame : frames) {
			invertedFrames.add(Entity.newSpriteFrame(x, y, frame.getGraphicalObject().getInvertedPixels()));
		}

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

	public void invert() {
		inverted = true;
	}

	public void revert() {
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

	@Override
	public boolean equals(Object o) {

		if (o instanceof Sprite) {

			Sprite otherSprite = (Sprite) o;

			if (otherSprite.x == this.x && otherSprite.y == this.y) {

				if (otherSprite.frames.size() == this.frames.size()) {

					for (int index = 0; index < this.frames.size(); index++) {

						if (!otherSprite.frames.get(index).equals(this.frames.get(index))) {

							return false;

						}

					}

					return true;

				}

			}

		}

		return false;
	}

}
