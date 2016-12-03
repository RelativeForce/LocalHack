package entities;

import Graphics.*;

public class Entity {

	private int x;
	private int y;
	private GraphicalObject object;

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

	public void setGraphicalObject(int width, int height) {
		object = new GraphicalObject(width, height);
		object.setColor(8000);
	}

	public GraphicalObject getGraphicalObject() {
		return object;
	}

}
