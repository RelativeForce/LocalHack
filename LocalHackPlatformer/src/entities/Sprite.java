package entities;

import java.util.ArrayList;
import graphics.objects.SpriteFrame;
import resources.SpriteSheetReader;

public class Sprite {

	private ArrayList<Entity> frames;
	private int currentFrame;
	private int x;
	private int y;

	public Sprite(String path, int width, int height, int x, int y) {

		setX(x);
		setY(y);

		frames = new ArrayList<Entity>();

		for (SpriteFrame frameSprite : SpriteSheetReader.getSprites(path, width, height)) {

			Object[] eSD = { x, y, frameSprite.getPixels() };

			frames.add(new Entity(EntityType.SPRITEFRAME, eSD));
		}

		currentFrame = 0;
	}

	public void nextFrame() {
		currentFrame = currentFrame++ % frames.size();
	}

	public Entity getEntity() {
		Entity entity = frames.get(currentFrame);
		entity.setX(x);
		entity.setY(y);
		return entity;
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
