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
	 * public void setImage(int width, int height){
	 * 		image = new Image(width, height); 
	 * }
	 * public void setHitBox(int width, int height){
	 * 		hitBox = new hitBox(width, height);
	 * }
	 * 
	 * public Image getImage(){ 
	 * 		if(image != null){
	 * 			return image; 
	 * 		}else{
	 * 			return null;
	 * 		}
	 * 
	 * public HitBox getHitBox(){ return hitBox; }
	 */

}
