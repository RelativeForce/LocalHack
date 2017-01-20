package logic.enemy;

import entities.Entity;

/**
 * The interface of the Enemy type.
 * @author Joshua_Eddy
 *
 */
public interface Enemy {
	
	/**
	 * Moves the enemy to its next location.
	 */
	public void move();

	/**
	 * Retrieves the Entity assigned to the Enemy.
	 * @return The Entity assigned in the Enemy.
	 */
	public Entity getEntity();
	
	
	public void setX(int x);
	
	public void setY(int y);
}
