package logic.enemy;

import entities.Entity;

/**
 * The interface of the Enemy type.
 * @author Joshua_Eddy
 *
 */
public interface Enemy {
	
	/**
	 * Retrieves the next position of the Enemy.
	 * @return The next position of the Enemy.
	 */
	public void move();

	/**
	 * Retrieves the Entity assigned to the Enemy.
	 * @return The Entity assigned in the Enemy.
	 */
	public Entity getEntity();
	
}
