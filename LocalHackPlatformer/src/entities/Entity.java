package entities;

import Graphics.*;

public class Entity {

	private int x;
	private int y;
	private Integer color;
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

	public void setColor(Integer color){
		this.color = color;
	}
	
	public Integer getColor(){
		return color;
	}
	
	public void setGraphicalObject(int width, int height, Integer color) {
		object = new GraphicalObject(width, height, color);
	}
	public void setGraphicalObject(int width, int height, Entity[] entities){
		object = new GraphicalObject(width, height, entities);
	}

	public GraphicalObject getGraphicalObject() {
		return object;
	}

}
