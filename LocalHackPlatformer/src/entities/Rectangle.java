package entities;

public class Rectangle extends Entity {

	public Rectangle(int x, int y, int width, int height) {

		if (x >= 0) {
			setX(x);
		} else {
			setX(0);
		}
		if (y >= 0) {
			setY(y);
		} else {
			setY(0);
		}

		
		
		/*
		 * setImage(width, height);
		 * setHitBox(width, height);
		 * 
		 */

	}

	public void changeSize(int width, int height) {

		// redefine image

	}

}
