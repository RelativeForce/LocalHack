package environment.logic.constructs.players;

import java.awt.Color;
import environment.Constants;
import environment.Main;
import environment.logic.Level;
import environment.logic.Point;
import environment.logic.constructs.Construct;
import environment.logic.constructs.objectives.Objective;
import environment.logic.entities.Entity;
import environment.logic.sprites.Sprite;

/**
 * A default player.
 * 
 * @author Joshua_Eddy
 */
public class DefaultPlayer extends Construct implements Player {

	private double ySpeed;
	private int xSpeed;
	private int movesMade;
	private boolean isDead;
	
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
	public DefaultPlayer(int x, int y, int width, int height) {

		super(x, y, new Sprite(DefaultPlayer.class, x, y));

		movesMade = 0;
		ySpeed = 0;
		xSpeed = 0;
		isDead = false;

	}

	@Override
	public void gravity() {

		int x = getX();
		int y = getY();

		ySpeed = ySpeed + Constants.GRAVITY;
		int nextX = x + xSpeed;
		int nextY = y + (int) ySpeed;

		if (Main.level.getLevelCollision(this, new Point(nextX, nextY)) == null) {
			setY(nextY);
			setX(nextX);
		} else {
			ySpeed = 0;
			xSpeed = 0;
		}

	}

	@Override
	public void move(int changeInX, int changeInY) {

		int x = getX();
		int y = getY();

		if (changeInX >= 0) {
			getSprite().revert();
		} else {
			getSprite().invert();
		}

		int nextX = x + changeInX;
		
		if (Main.level.getLevelCollision(this, new Point(nextX, y)) == null && nextX >= 0
				&& nextX <= Constants.WINDOW_WIDTH - getSprite().getEntity().getGraphicalObject().getWidth()) {

			if (nextX < Constants.WINDOW_PADDING && Level.StartX < 0) {
				Main.level.moveLevel(-changeInX);

			} else if (nextX > Constants.WINDOW_WIDTH - Constants.WINDOW_PADDING
					&& Level.StartX > -Level.Length + Constants.WINDOW_WIDTH) {
				Main.level.moveLevel(-changeInX);

			} else {

				setX(nextX);
			}

			xSpeed = changeInX / Constants.FRICTION;

			if (movesMade % 5 == 0) {
				getSprite().nextFrame();
			}

			movesMade++;
		}
	}

	@Override
	public void jump() {

		int x = getX();
		int y = getY();

		gravity();
		
		if (ySpeed == 0 && Main.level.getLevelCollision(this, new Point(x, y+1)) != null) {
			ySpeed = -Constants.JUMP_HEIGHT;
		}

	}

	@Override
	public void checkForDeath() {

		int x = getX();
		int y = getY();

		ySpeed = ySpeed + Constants.GRAVITY;

		if (y > Constants.WINDOW_HEIGHT) {
			isDead = true;
		}
		
		if (Main.level.checkForEnemyCollision(this, new Point(x + xSpeed, y + (int) ySpeed)) != null) {

			isDead = true;

		}

		if (isDead) {
			Main.transitionScreen.setColor(Color.RED.getRGB());
			Main.transitionScreen.isActive = true;
		}

	}

	@Override
	public void checkObjectives() {

		int x = getX();
		int y = getY();

		ySpeed = ySpeed + Constants.GRAVITY;

		Construct objective = Main.level.checkForObjectiveCollision(this, new Point(x + xSpeed, y + (int) ySpeed));
		
		if (!isDead && objective != null && objective instanceof Objective) {
			((Objective) objective).action();
		}
	}

	@Override
	public Entity getEntity() {
		return getSprite().getEntity();
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}


}
