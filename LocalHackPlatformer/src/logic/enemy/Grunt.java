package logic.enemy;

import java.awt.Color;
import directions.*;
import entities.Entity;
import entities.Rectangle;
import environment.Main;
import logic.HitDetection;
import logic.Level;
import logic.Point;

/**
 * A Grunt is a type of Enemy that moves along the floor until it hits reaches
 * the end of the floor. When it reaches the end of the floor it turns back.
 * 
 * @author Joshua_Eddy
 */
public class Grunt implements Enemy {

	private Entity gruntEntity;
	private int xSpeed;
	private Direction direction;
	private Entity currentSupport;

	/**
	 * Constructs a new Grunt object.
	 * 
	 * @param inital
	 *            The initial position of the Grunt object.
	 */
	public Grunt(Point inital) {
		gruntEntity = new Rectangle(inital.x, inital.y, 20, 20, Color.GREEN.getRGB());
		direction = Direction.LEFT;
		xSpeed = 1;
	}

	@Override
	public void move() {

		int width = gruntEntity.getGraphicalObject().width;
		int x = gruntEntity.getX();
		int y = gruntEntity.getY();

		if (currentSupport == null) {
			getSupport(x, y);
		}

		int nextX;
		Entity checkSupport;

		if (direction == Direction.RIGHT) {
			nextX = x + xSpeed;
			checkSupport = new Rectangle(nextX + width, y + 5, 20, 20, 0xffff0000);
		} else {
			nextX = x - xSpeed;
			checkSupport = new Rectangle(nextX - width, y + 5, 20, 20, 0xffff0000);
		}

		boolean hitRightBoundry = x + width >= Level.StartX + Level.Length;
		boolean hitLeftBoundry = x <= Level.StartX;
		boolean isSupported = HitDetection.detectHit(checkSupport, checkSupport, currentSupport);

		if (rebound(nextX, y) || hitRightBoundry || hitLeftBoundry || !isSupported) {
			direction = DirectionHandler.invert(direction);
		}

		if (direction == Direction.RIGHT) {
			gruntEntity.setX(gruntEntity.getX() + xSpeed);
		} else {
			gruntEntity.setX(gruntEntity.getX() - xSpeed);
		}

	}

	@Override
	public Entity getEntity() {

		return gruntEntity;
	}

	private void getSupport(int x, int y) {

		Entity checkSupport = new Rectangle(x, y + 5, 20, 20, 0xffff0000);
		for (Entity component : Main.level.getComponents()) {

			if (HitDetection.detectHit(gruntEntity, checkSupport, component)) {
				currentSupport = component;
			}

		}
	}

	private boolean rebound(int nextX, int y) {

		Entity checkForWall = new Rectangle(nextX, y, 20, 20, 0xffff0000);

		for (Entity component : Main.level.getComponents()) {

			if (HitDetection.detectHit(gruntEntity, checkForWall, component)) {
				return true;
			}

		}
		return false;
	}
}
