package logic.enemy;

import java.awt.Color;
import directions.*;
import entities.Entity;
import entities.EntityType;
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

		// eD = entity Details
		Object[] eD = new Object[5];
		eD[0] = inital.x;
		eD[1] = inital.y;
		eD[2] = 20;
		eD[3] = 20;
		eD[4] = Color.GREEN.getRGB();

		gruntEntity = new Entity(EntityType.RECTANGLE, eD);
		direction = Direction.LEFT;
		xSpeed = 1;
	}

	@Override
	public void move() {

		int width = gruntEntity.getGraphicalObject().getWidth();
		int x = gruntEntity.getX();
		int y = gruntEntity.getY();

		if (currentSupport == null) {
			getSupport(x, y);
		}

		int nextX;
		Entity checkSupport;

		// eD = entity Details
		Object[] eD = new Object[5];
		eD[2] = 20;
		eD[3] = 20;
		eD[4] = 0xffff0000;
		eD[1] = y + 5;
		if (direction == Direction.RIGHT) {
			nextX = x + xSpeed;
			eD[0] = nextX + width;

		} else {
			nextX = x - xSpeed;
			eD[0] = nextX - width;
		}

		checkSupport = new Entity(EntityType.RECTANGLE, eD);

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

		// eD = entity Details
		Object[] eD = new Object[5];
		eD[0] = x;
		eD[1] = y + 5;
		eD[2] = 20;
		eD[3] = 20;
		eD[4] = 0xffff0000;

		Entity checkSupport = new Entity(EntityType.RECTANGLE, eD);
		for (Entity component : Main.level.getComponents()) {

			if (HitDetection.detectHit(gruntEntity, checkSupport, component)) {
				currentSupport = component;
			}

		}
	}

	private boolean rebound(int nextX, int y) {

		// eD = entity Details
		Object[] eD = new Object[5];
		eD[0] = nextX;
		eD[1] = y;
		eD[2] = 20;
		eD[3] = 20;
		eD[4] = 0xffff0000;

		Entity checkForWall = new Entity(EntityType.RECTANGLE, eD);

		for (Entity component : Main.level.getComponents()) {

			if (HitDetection.detectHit(gruntEntity, checkForWall, component)) {
				return true;
			}

		}
		return false;
	}
}
