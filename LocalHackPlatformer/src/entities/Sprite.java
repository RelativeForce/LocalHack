package entities;

import java.util.ArrayList;
import graphics.objects.SpriteFrame;
import resources.SpriteSheetReader;

public class Sprite {

	private ArrayList<Entity> frames;
	private ArrayList<Entity> invertedFrames;
	private int currentFrame;
	private boolean inverted;
	private int x;
	private int y;

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
