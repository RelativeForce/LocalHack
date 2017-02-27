package environment.logic.constructs.players;

import environment.logic.Drawable;
import environment.logic.entities.Entity;

/**
 * The interface for implementing new playable characters
 * @author Joshua_Eddy
 *
 */
public interface Player extends Drawable{

	/**
	 * The player's state.
	 */
	public void gravity();

	/**
	 * Moves the player a specified X distance.
	 * 
	 * @param changeInX
	 *            The change in X coordinate for the Player Entity.
	 * @param changeInY
	 *            Unused
	 */
	public void move(int changeInX, int changeInY);

	/**
	 * Simulates the player jumping.
	 */
	public void jump();

	
	/**
	 * Checks if the player has achieved death criteria.
	 */
	public void checkForDeath();

	/**
	 * Checks if the player has intercepted an objective and if so, performs
	 * that objectives action.
	 */
	public void checkObjectives();

	/**
	 * Retrieves the Entity assigned to the Player.
	 * 
	 * @return The Entity assigned to the Player.
	 */
	public Entity getEntity();

	/**
	 * Gets the current state of the player.
	 * 
	 * @return <code>boolean</code> whether the player is alive or dead. Where
	 *         <code>true</code> means alive and <code>false</code> means dead.
	 */
	public boolean isAlive();

}
