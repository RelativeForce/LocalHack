package logic.player;

import java.awt.Color;
import java.util.ArrayList;

import entities.Entity;
import entities.Objective;
import entities.Rectangle;
import environment.Constants;
import environment.Main;
import logic.HitDetection;
import logic.Level;

/**
 * Contains all the logic for the Player.
 * @author Joshua_Eddy
 */
public class Player {

	private Entity playerEntity;
	private double ySpeed;
	private int xSpeed;
	
	/**
	 * The player's state.
	 */
	public boolean isDead;

	/**
	 * Constructs a new Player object.
	 * @param x The X coordinate of the Player.
	 * @param y The Y coordinate of the Player.
	 * @param width The width of the Player Entity.
	 * @param height The height of the Player Entity.
	 */
	public Player(int x, int y, int width, int height) {

		playerEntity = new Rectangle(x, y, width, height, Color.RED.getRGB());
		ySpeed = 0;
		xSpeed = 0;
		isDead = false;

	}

	/**
	 * Simulates the effects of gravity on the Player.
	 */
	public void gravity() {

		int x = playerEntity.getX();
		int y = playerEntity.getY();

		ySpeed = ySpeed + Constants.GRAVITY;
		int nextX = x + xSpeed;
		int nextY = y + (int) ySpeed;

		
		if (checkCollision(nextX, nextY, Level.components) == null) {
			playerEntity.setY(y + (int) ySpeed);
			playerEntity.setX(x + xSpeed);
		} else {
			ySpeed = 0;
			xSpeed = 0;
		}

	}

	/**
	 * Moves the player a specified X distance.
	 * @param changeInX The change in X coordinate for the Player Entity.
	 */
	public void move(int changeInX) {

		int x = playerEntity.getX();
		int y = playerEntity.getY();

		int nextX = x + changeInX;

		if (checkCollision(nextX, y, Level.components) == null && nextX >= 0
				&& nextX <= Constants.WINDOW_WIDTH - playerEntity.getGraphicalObject().width) {

			if (nextX < Constants.WINDOW_PADDING && Level.StartX < 0) {
				Main.level.moveLevel(-changeInX);

			} else if (nextX > Constants.WINDOW_WIDTH - Constants.WINDOW_PADDING
					&& Level.StartX > -Level.Length + Constants.WINDOW_WIDTH) {
				Main.level.moveLevel(-changeInX);

			} else {

				playerEntity.setX(nextX);
			}

			xSpeed = changeInX / Constants.FRICTION;
		}
	}

	/**
	 * Simulates the player jumping.
	 */
	public void jump() {

		int x = playerEntity.getX();
		int y = playerEntity.getY();
		
		gravity();
		if (ySpeed == 0 && checkCollision(x, y + 2, Level.components) != null) {
			ySpeed = -Constants.JUMP_HEIGHT;
		}

	}

	/**
	 * Checks if the player has achieved death criteria.
	 */
	public void checkForDeath() {

		int x = playerEntity.getX();
		int y = playerEntity.getY();

		ySpeed = ySpeed + Constants.GRAVITY;
		
		if(y > Constants.WINDOW_HEIGHT){
			isDead = true;
		}

		
		if (checkCollision(x + xSpeed, y + (int) ySpeed, Level.enemies) != null) {
			playerEntity = new Rectangle(x, y, playerEntity.getGraphicalObject().width,
					playerEntity.getGraphicalObject().height, Color.WHITE.getRGB());
			isDead = true;
			
		}
		
		if(isDead){
			Main.transitionScreen.setColor(Color.RED.getRGB());
			Main.transitionScreen.isActive = true;
		}

	}

	/**
	 * Checks if the player has intercepted an objective and if so, performs that objectives action.
	 */
	public void checkObjectves(){
		
		int x = playerEntity.getX();
		int y = playerEntity.getY();

		ySpeed = ySpeed + Constants.GRAVITY;
		
		Objective objective = (Objective)checkCollision(x + xSpeed, y + (int) ySpeed, Level.objectives);
		if (!isDead &&  objective != null) {
			objective.action();
		}
	}
	
	private Entity checkCollision(int nextX, int nextY, ArrayList<Entity> list) {

		Entity nextPlayerEntity = new Rectangle(nextX, nextY, playerEntity.getGraphicalObject().width,
				playerEntity.getGraphicalObject().height, 0xffff0000);

		for (Entity component : list) {
			if (HitDetection.detectHit(playerEntity, nextPlayerEntity, component)) {
				return component;
			}
		}
		return null;
	}
	
	/**
	 * Retrieves the Entity assigned to the Player.
	 * @return The Entity assigned to the Player.
	 */
	public Entity getEntity() {
		return playerEntity;
	}

}
