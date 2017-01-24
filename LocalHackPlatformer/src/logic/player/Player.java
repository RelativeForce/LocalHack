package logic.player;

import java.awt.Color;
import java.util.ArrayList;
import entities.Entity;
import environment.Constants;
import environment.Main;
import logic.HitDetection;
import logic.Level;
import logic.Point;
import logic.enemy.Enemy;
import logic.objective.Objective;

/**
 * Contains all the logic for the Player.
 * 
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
	 * 
	 * @param x
	 *            The X coordinate of the Player.
	 * @param y
	 *            The Y coordinate of the Player.
	 * @param width
	 *            The width of the Player Entity.
	 * @param height
	 *            The height of the Player Entity.
	 */
	public Player(int x, int y, int width, int height) {

		playerEntity = Entity.Rectangle(x, y, width, height, Color.RED.getRGB());
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

		if (!checkCollision(nextX, nextY, Main.level.getComponents())) {
			playerEntity.setY(y + (int) ySpeed);
			playerEntity.setX(x + xSpeed);
		} else {
			ySpeed = 0;
			xSpeed = 0;
		}

	}

	/**
	 * Moves the player a specified X distance.
	 * 
	 * @param changeInX
	 *            The change in X coordinate for the Player Entity.
	 */
	public void move(int changeInX) {

		int x = playerEntity.getX();
		int y = playerEntity.getY();

		int nextX = x + changeInX;

		if (!checkCollision(nextX, y, Main.level.getComponents()) && nextX >= 0
				&& nextX <= Constants.WINDOW_WIDTH - playerEntity.getGraphicalObject().getWidth()) {

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
		if (ySpeed == 0 && checkCollision(x, y + 2, Main.level.getComponents())) {
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

		if (y > Constants.WINDOW_HEIGHT) {
			isDead = true;
		}

		if (checkEnemyCollision(x + xSpeed, y + (int) ySpeed, Main.level.getEnemies())) {

			isDead = true;
			playerEntity = Entity.Rectangle(x + xSpeed, y + (int) ySpeed, playerEntity.getGraphicalObject().getWidth(),
					playerEntity.getGraphicalObject().getHeight(), Color.WHITE.getRGB());

		}

		if (isDead) {
			Main.transitionScreen.setColor(Color.RED.getRGB());
			Main.transitionScreen.isActive = true;
		}

	}

	/**
	 * Checks if the player has intercepted an objective and if so, performs
	 * that objectives action.
	 */
	public void checkObjectves() {

		int x = playerEntity.getX();
		int y = playerEntity.getY();

		ySpeed = ySpeed + Constants.GRAVITY;

		Objective objective = checkObjectiveCollision(x + xSpeed, y + (int) ySpeed, Main.level.getObjectives());
		if (!isDead && objective != null) {

			playerEntity = Entity.Rectangle(x + xSpeed, y + (int) ySpeed, playerEntity.getGraphicalObject().getWidth(),
					playerEntity.getGraphicalObject().getHeight(), Color.RED.getRGB());

			objective.action();
		}
	}

	/**
	 * Retrieves the Entity assigned to the Player.
	 * 
	 * @return The Entity assigned to the Player.
	 */
	public Entity getEntity() {
		return playerEntity;
	}

	private boolean checkCollision(int nextX, int nextY, ArrayList<Entity> list) {

		return HitDetection.getObstruction(playerEntity, new Point(nextX, nextY),
				list.toArray(new Entity[list.size()])) != null;

	}

	private boolean checkEnemyCollision(int nextX, int nextY, ArrayList<Enemy> list) {

		Entity[] array = new Entity[list.size()];

		for (int i = 0; i < list.size(); ++i) {

			array[i] = list.get(i).getEntity();

		}

		return HitDetection.getObstruction(playerEntity, new Point(nextX, nextY), array) != null;
	}

	private Objective checkObjectiveCollision(int nextX, int nextY, ArrayList<Objective> list) {

		for (Objective objective : list) {
			if (HitDetection.detectHit(playerEntity, new Point(nextX, nextY), objective.getEntity())) {
				return objective;
			}
		}
		return null;

	}

}
