package entities;

import Environment.Constants;
import Graphics.GraphicalObject;
import Logic.Point;

/**
 * Stores the information for graphical objects to be draw on screen.
 * @author Joshua_Eddy
 */
public class Entity {

	private Integer color;
	private GraphicalObject object;
	private HitBox hitBox;
	private Point position = new Point(Constants.DEFAULT_POSITION);

	/**
	 * Sets the X value of the Entity.
	 * 
	 * @param x The new X value for the Entity.
	 */
	public void setX(int x) {
		position.x = x;
	}

	/**
	 * Sets the Y value of the Entity.
	 * @param y The new Y value for the Entity.
	 */
	public void setY(int y) {
		position.y = y;
	}

	/**
	 * Retrieves the X coordinate of the Entity.
	 * @return The current X coordinate of the Entity.
	 */
	public int getX() {
		return position.x;
	}

	/**
	 * Retrieves the Y coordinate of the Entity.
	 * @return The current Y coordinate of the Entity.
	 */
	public int getY() {
		return position.y;
	}

	/**
	 * Sets the RGB colour of the Entity.
	 * @param color The new RGB colour of the Entity.
	 */
	public void setColor(Integer color) {
		this.color = color;
	}

	/**
	 * Retrieves the RGB colour of the Entity.
	 * @return The current RGB colour of the Entity.
	 */
	public Integer getColor() {
		return color;
	}

	/**
	 * Initialises the GraphicalObject stored in the Entity.
	 * @param width The width of the GraphicalObject.
	 * @param height The height of the GraphicalObject. 
	 * @param color The RGB colour of the GraphicalObject.
	 */
	public void setGraphicalObject(int width, int height, Integer color) {
		object = new GraphicalObject(width, height, color);
		hitBox = new HitBox(object);
	}

	/**
	 * Initialises the GraphicalObject stored in the Entity from a collection of Entities.
	 * @param width width The width of the GraphicalObject.
	 * @param height The height of the GraphicalObject. 
	 * @param entities The collection of entities that will make up this entity.
	 */
	public void setGraphicalObject(int width, int height, Entity[] entities) {
		object = new GraphicalObject(width, height, entities);
		hitBox = new HitBox(object);
	}

	/**
	 * Retrieves the GraphicalObject from the Entity.
	 * @return The GraphicalObject stored in the Entity.
	 */
	public GraphicalObject getGraphicalObject() {
		return object;
	}

	/**
	 * Returns the HitBox of the Entity.
	 * @return The HitBox of the Entity.
	 */
	public HitBox getHitBox(){
		return hitBox;
	}
}
