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
		int height = gruntSprite.getEntity().getGraphicalObject().getHeight();
		int x = gruntSprite.getX();
		int y = gruntSprite.getY();

		if (currentSupport == null) {
			getSupport(x, y);
		}

		int nextX;
		Entity checkSupport;

		if (direction == Direction.RIGHT) {

			nextX = x + xSpeed;
			checkSupport = Entity.Rectangle(nextX + width, y + 5, width, height, 0xffff0000);

		} else {
			
			nextX = x - xSpeed;
			checkSupport = Entity.Rectangle(nextX - width, y + 5, width, height, 0xffff0000);
			
		}

		boolean hitRightBoundry = nextX + width >= Level.StartX + Level.Length;
		boolean hitLeftBoundry = nextX <= Level.StartX;
		boolean isSupported = HitDetection.detectHit(checkSupport, new Point(checkSupport.getX(), checkSupport.getY()),
				currentSupport);

		if (rebound(nextX, y) || hitRightBoundry || hitLeftBoundry || !isSupported) {
			direction = direction.getOppositeDirection();
		}

		if (direction == Direction.RIGHT) {
			gruntSprite.setX(gruntSprite.getX() + xSpeed);
		} else {
			gruntSprite.setX(gruntSprite.getX() - xSpeed);
		}

		if (movesMade % 5 == 0) {
			gruntSprite.nextFrame();
		}

		movesMade++;

	}

	@Override
	public Entity getEntity() {

		return gruntSprite.getEntity();
	}

	private void getSupport(int x, int y) {

		currentSupport = HitDetection.getObstruction(gruntSprite.getEntity(), new Point(x, y + 5),
				Main.level.getComponents().toArray(new Entity[Main.level.getComponents().size()]));

	}

	private boolean rebound(int nextX, int y) {

		return (HitDetection.getObstruction(gruntSprite.getEntity(), new Point(nextX, y),
				Main.level.getComponents().toArray(new Entity[Main.level.getComponents().size()]))) != null;
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
