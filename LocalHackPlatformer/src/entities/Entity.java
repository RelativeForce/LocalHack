package entities;

import Graphics.*;

public class Entity {

	private int x;
	private int y;
	private GraphicalObject object;

	public void setX(int x) {
		
			this.x = x;
		
	}

	public void setY(int y) {
		
			this.y = y;
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setGraphicalObject(int width, int height, Integer color) {
		object = new GraphicalObject(width, height, color);
	}

	public GraphicalObject getGraphicalObject() {
		return object;
	}

}
