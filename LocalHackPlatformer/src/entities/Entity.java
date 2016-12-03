package entities;

public class Entity {

	private int x;
	private int y;
	// private Image image;
	// private HitBox hitBox;

	public void setX(int x) {
		if (x >= 0) {
			this.x = x;
		}
	}

	public void setY(int y) {
		if (y >= 0) {
			this.y = y;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	/*
	 * public Image getImage(){ return image; }
	 * 
	 * public HitBox getHitBox(){ return hitBox; }
	 */

}
