package environment.logic.constructs.enemies;

import java.io.File;
import environment.Constants;
import environment.Main;
import environment.logic.Direction;
import environment.logic.Level;
import environment.logic.Point;
import environment.logic.constructs.Construct;
import environment.logic.entities.Entity;
import environment.logic.entities.Sprite;

/**
 * A Grunt is a type of Enemy that moves along the floor until it hits reaches
 * the end of the floor. When it reaches the end of the floor it turns back.
 * 
 * @author Joshua_Eddy
 */
public class Grunt extends Construct implements Enemy {

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

		super(inital.x, inital.y,
				new Sprite(new File(System.getProperty("user.dir")).getPath() + "\\" + Constants.GRUNT_FILENAME, 20, 25,
						inital.x, inital.y));

		direction = Direction.LEFT;
		xSpeed = 1;
		movesMade = 0;
	}

	@Override
	public void getMove() {

		int width = getSprite().getEntity().getGraphicalObject().getWidth();
		int height = getSprite().getEntity().getGraphicalObject().getHeight();
		int x = getX();
		int y = getY();

		if (currentSupport == null) {
			getSupport(x, y);
		}

		int nextX;
		Entity checkSupport;

		if (direction == Direction.RIGHT) {

			nextX = x + xSpeed;
			checkSupport = Entity.newRectangle(nextX + width, y + 5, width, height, 0xffff0000);

		} else {

			nextX = x - xSpeed;
			checkSupport = Entity.newRectangle(nextX - width, y + 5, width, height, 0xffff0000);

		}

		boolean hitRightBoundry = nextX + width >= Level.StartX + Level.Length;
		boolean hitLeftBoundry = nextX <= Level.StartX;
		boolean isSupported = Entity.detectHit(checkSupport, new Point(checkSupport.getX(), checkSupport.getY()),
				currentSupport);

		if (rebound(nextX, y) || hitRightBoundry || hitLeftBoundry || !isSupported) {
			direction = direction.getOppositeDirection();
		}

		if (direction == Direction.RIGHT) {
			setX(x + xSpeed);
			getSprite().invert();
		} else {
			setX(x - xSpeed);
			getSprite().revert();
		}

		if (movesMade % 5 == 0) {
			getSprite().nextFrame();
		}

		movesMade++;

	}

	private void getSupport(int x, int y) {

		currentSupport = Main.level.getLevelCollision(this, new Point(x, y + 5)).getSprite().getEntity();

	}

	private boolean rebound(int nextX, int y) {

		return (Main.level.getLevelCollision(this, new Point(nextX, y))) != null;
	}

}
