package Logic;

import java.awt.Color;
import java.util.ArrayList;

import Environment.Constants;
import Environment.Main;
import entities.Entity;
import entities.Rectangle;

public class Player {

	private Entity playerEntity;
	private int ySpeed;
	private int xSpeed;
	public boolean isDead;

	public Player(int x, int y, int width, int height) {

		playerEntity = new Rectangle(x, y, width, height, Color.RED.getRGB());
		ySpeed = 0;
		xSpeed = 0;
		isDead = false;

	}

	public void gravity() {

		int x = playerEntity.getX();
		int y = playerEntity.getY();

		ySpeed = ySpeed + Constants.GRAVITY;
		int nextX = x + xSpeed;
		int nextY = y + ySpeed;

		if (!checkCollision(nextX, nextY, Level.components)) {
			playerEntity.setY(y + ySpeed);
			playerEntity.setX(x + xSpeed);
		} else {

			ySpeed = 0;
			xSpeed = 0;
		}

	}

	public void move(int changeInX) {

		int x = playerEntity.getX();
		int y = playerEntity.getY();

		int nextX = x + changeInX;

		if (!checkCollision(nextX, y, Level.components) && nextX >= 0
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
		} else {

		/*	
			boolean initalIsGreater;
			if (x <= nextX) {
				initalIsGreater = false;
			} else {
				initalIsGreater = true;
			}

			boolean hasCollided = false;
			
			int xInc; 
			if (initalIsGreater) {
				xInc = -1;
			} else {
				xInc = 1;
			}

			while (initalIsGreater ? (x >= nextX) : (x <= nextX) && !hasCollided) {

				if (!checkCollision(x + xInc, y, Level.components)) {
					x += xInc;
				} else {
					hasCollided = true;
				}

			}
			playerEntity.setX(x);
*/
		}

	}

	public void jump() {

		gravity();
		if (ySpeed == 0) {
			ySpeed = -Constants.JUMP_HEIGHT;
		}

	}

	public void checkForDeath() {

		int x = playerEntity.getX();
		int y = playerEntity.getY();

		ySpeed = ySpeed + Constants.GRAVITY;

		if (checkCollision(x + xSpeed, y + ySpeed, Level.enemies)) {
			playerEntity = new Rectangle(x, y, playerEntity.getGraphicalObject().width,
					playerEntity.getGraphicalObject().height, Color.WHITE.getRGB());
			isDead = true;
		}

	}

	private boolean checkCollision(int nextX, int nextY, ArrayList<Entity> list) {

		Entity nextPlayerEntity = new Rectangle(nextX, nextY, playerEntity.getGraphicalObject().width,
				playerEntity.getGraphicalObject().height, 0xffff0000);

		for (Entity component : list) {
			if (HitDetection.detectHit(playerEntity, nextPlayerEntity, component)) {
				return true;
			}
		}
		return false;
	}

	public Entity getEntity() {
		return playerEntity;
	}

}
