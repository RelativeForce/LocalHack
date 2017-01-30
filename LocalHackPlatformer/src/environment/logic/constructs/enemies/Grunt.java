package environment.logic.constructs.enemies;

import java.io.File;
import java.util.ArrayList;

import environment.Constants;
import environment.Main;
import environment.logic.Direction;
import environment.logic.HitDetection;
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
		int x = getSprite().getX();
		int y = getSprite().getY();

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
			getSprite().setX(getSprite().getX() + xSpeed);
			getSprite().invert();
		} else {
			getSprite().setX(getSprite().getX() - xSpeed);
			getSprite().revert();
		}

		if (movesMade % 5 == 0) {
			getSprite().nextFrame();
		}

		movesMade++;

	}

	private void getSupport(int x, int y) {

		currentSupport = HitDetection.getObstruction(getSprite().getEntity(), new Point(x, y + 5),
				toArray(Main.level.getConstructs()));

	}

	private boolean rebound(int nextX, int y) {

		return (HitDetection.getObstruction(getSprite().getEntity(), new Point(nextX, y),
				toArray(Main.level.getConstructs()))) != null;
	}

	private Entity[] toArray(ArrayList<Construct> list) {

		Entity[] entities = new Entity[list.size()];

		for (int i = 0; i < list.size(); i++) {
			entities[i] = list.get(i).getSprite().getEntity();
		}

		return entities;
	}

}
