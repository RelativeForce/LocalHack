package logic.enemy;

import java.io.File;
import directions.*;
import entities.Entity;
import entities.EntityType;
import entities.Sprite;
import environment.Constants;
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

	private Sprite gruntSprite;
	private int xSpeed;
	private Direction direction;
	private Entity currentSupport;
	private int movesMade;

	/**
	 * Constructs a new Grunt object.
	 * 
	 * @param inital
	 *            The initial position of the Grunt object.
	 */
	public Grunt(Point inital) {

		File currentDirectory = new File(System.getProperty("user.dir"));
		gruntSprite = new Sprite(currentDirectory.getPath() + "\\" + Constants.gruntFileName, 20, 25, inital.x,
				inital.y);

		direction = Direction.LEFT;
		xSpeed = 1;
		movesMade = 0;
	}

	@Override
	public void move() {

		int width = gruntSprite.getEntity().getGraphicalObject().getWidth();
		int x = gruntSprite.getX();
		int y = gruntSprite.getY();

		if (currentSupport == null) {
			getSupport(x, y);
		}

		int nextX;
		Entity checkSupport;

		// eD = entity Details
		Object[] eD = new Object[5];
		eD[2] = width;
		eD[3] = gruntSprite.getEntity().getGraphicalObject().getHeight();
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

		boolean hitRightBoundry = nextX + width >= Level.StartX + Level.Length;
		boolean hitLeftBoundry = nextX <= Level.StartX;
		boolean isSupported = HitDetection.detectHit(checkSupport, checkSupport, currentSupport);

		if (rebound(nextX, y) || hitRightBoundry || hitLeftBoundry || !isSupported) {
			direction = direction.getOppositeDirection();
		}

		if (direction == Direction.RIGHT) {
			gruntSprite.setX(gruntSprite.getX() + xSpeed);
		} else {
			gruntSprite.setX(gruntSprite.getX() - xSpeed);
		}
		
		if(movesMade % 5 == 0){
			gruntSprite.nextFrame();
		}
		
		movesMade++;

	}

	@Override
	public Entity getEntity() {

		return gruntSprite.getEntity();
	}

	private void getSupport(int x, int y) {

		// eD = entity Details
		Object[] eD = new Object[5];
		eD[0] = x;
		eD[1] = y + 5;
		eD[2] = gruntSprite.getEntity().getGraphicalObject().getWidth();
		eD[3] = gruntSprite.getEntity().getGraphicalObject().getHeight();
		eD[4] = 0xffff0000;

		Entity checkSupport = new Entity(EntityType.RECTANGLE, eD);
		for (Entity component : Main.level.getComponents()) {

			if (HitDetection.detectHit(gruntSprite.getEntity(), checkSupport, component)) {
				currentSupport = component;
			}

		}
	}

	private boolean rebound(int nextX, int y) {

		// eD = entity Details
		Object[] eD = new Object[5];
		eD[0] = nextX;
		eD[1] = y;
		eD[2] = gruntSprite.getEntity().getGraphicalObject().getWidth();
		eD[3] = gruntSprite.getEntity().getGraphicalObject().getHeight();
		eD[4] = 0xffff0000;

		Entity checkForWall = new Entity(EntityType.RECTANGLE, eD);

		for (Entity component : Main.level.getComponents()) {

			if (HitDetection.detectHit(gruntSprite.getEntity(), checkForWall, component)) {
				return true;
			}

		}
		return false;
	}

	@Override
	public void setX(int x) {
		gruntSprite.setX(x);

	}

	@Override
	public void setY(int y) {
		gruntSprite.setX(y);

	}
}
