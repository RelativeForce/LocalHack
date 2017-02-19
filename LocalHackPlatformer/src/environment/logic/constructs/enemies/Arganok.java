package environment.logic.constructs.enemies;

import environment.Constants;
import environment.Main;
import environment.logic.Direction;
import environment.logic.Point;
import environment.logic.constructs.Construct;
import environment.logic.entities.Sprite;

/**
 * 
 * An <code>Enemy</code> that chases the <code>Player</code> when the player
 * enters a circle area around the <code>Arganok</code>. This enemy uses the a
 * predetermined sprite that can be found in the working directory as
 * <code>Arganok.png</code>. This is also a concrete subclass of
 * <code>Construct</code>.
 * 
 * @version 1.1
 * @author Joshua_Eddy
 * 
 * @see environment.logic.constructs.Construct
 * @see environment.logic.constructs.enemies.Enemy
 *
 */
public class Arganok extends Construct implements Enemy {

	// Instance Fields
	// -------------------------------------------------------------------------------------

	/**
	 * The <code>Point</code> that denotes the left hovering boundary of the
	 * Arganok.
	 * 
	 * @see environment.logic.Point
	 */
	private Point startHover;

	/**
	 * The <code>Point</code> that denotes the right hovering boundary of the
	 * Arganok.
	 * 
	 * @see environment.logic.Point
	 */
	private Point endHover;

	/**
	 * The <code>Direction</code> that denotes the which direction that the
	 * Arganok is currently moving between the <code>startHover</code> and
	 * <code>endHover</code>.
	 * 
	 * @see #startHover
	 * @see #endHover
	 * @see environment.logic.Direction
	 */
	private Direction hoverDirection;

	/**
	 * This denotes the <code>int</code> radius around the Arganok that it will
	 * begin to move towards the player.
	 * 
	 * @see #getMove()
	 */
	private int trackingRadius;

	/**
	 * Holds the number of moves the Arganok has made since the Arganok last
	 * updated its sprite frame.
	 */
	private int movesMade;

	// Constructor
	// -------------------------------------------------------------------------------------

	/**
	 * Constructs an new Arganok <code>Enemy</code> that is also a concrete
	 * subclass of <code>Construct</code>.
	 * 
	 * @param inital
	 *            <code>Point</code> that denotes the initial position on the
	 *            screen of the Arganok
	 * @param trackingRadius
	 *            <code>int</code> that denotes the radius around the Arganok
	 *            that the Arganok will peruse the player.
	 * 
	 * @see environment.logic.entities.Sprite
	 * @see environment.logic.Point
	 * @see environment.logic.constructs.Construct
	 * @see environment.logic.Direction
	 * @see #setHoverBounds()
	 */
	public Arganok(Point inital, int trackingRadius) {
		// Constructs the construct super class
		super(inital.x, inital.y, new Sprite(Constants.ARGANOK_SPRITE_DETAILS, inital.x, inital.y));

		this.trackingRadius = trackingRadius;
		this.movesMade = 0;
		this.hoverDirection = Direction.RIGHT;

		setHoverBounds();

	}

	// Public Methods
	// -------------------------------------------------------------------------------------

	@Override
	public void getMove() {

		// If the Arganok has moved outside its hover bounds then the bounds
		// must be reset.
		if (getX() > endHover.x || getX() < startHover.x) {
			setHoverBounds();
		}

		// The position of the centre of the Arganok sprite
		int x = getX() - (getSprite().getEntity().getGraphicalObject().getWidth() / 2);
		int y = getY() - (getSprite().getEntity().getGraphicalObject().getHeight() / 2);
		Point arganokPosition = new Point(x, y);

		// The position of the centre of the Player sprite
		int playerX = Main.player.getEntity().getX() - (Main.player.getEntity().getGraphicalObject().getWidth() / 2);
		int playerY = Main.player.getEntity().getY() - (Main.player.getEntity().getGraphicalObject().getHeight() / 2);
		Point playerPosition = new Point(playerX, playerY);

		// If the distance to the player from the Arganok is less than or equal
		// to the tracking radius of the Arganok then the Arganok should seek
		// out the Player but if it is not then the Arganok should just hover
		// between the hover start and end points
		if (distanceToPlayer(playerPosition, arganokPosition) <= trackingRadius) {
			seek(playerPosition, arganokPosition);
		} else {
			hover();
		}

		// If the Arganok has made the interval number of moves then the sprite
		// should be changed to its next frame.
		if (movesMade == 0) {
			getSprite().nextFrame();
		}
		movesMade = (movesMade++) % 6;

	}

	/**
	 * Sets the <code>int</code> x coordinate of the Arganok while also moving
	 * the region in which the Arganok hovers.
	 * 
	 * @see environment.logic.Point
	 */
	@Override
	public void setX(int x) {

		// Retrieve the difference between he current x value and the new x
		// value.
		int changeInX = getX() - x;

		// If the hover boundaries have been initialised then change then change
		// the positions x values of the boundaries to account for the sprite
		// being moved.
		if (this.endHover != null && this.startHover != null) {

			int endHoverX = endHover.x;
			int endHoverY = endHover.y;
			endHover = new Point(endHoverX + changeInX, endHoverY);

			int startHoverX = startHover.x;
			int startHoverY = startHover.y;
			startHover = new Point(startHoverX + changeInX, startHoverY);

		}

		super.setX(x);
	}

	// Private Methods
	// -------------------------------------------------------------------------------------

	/**
	 * Causes the Arganok to move left or right between the start hover and end
	 * hover points respectively. This gives the appearance of the Arganok
	 * hovering on screen.
	 * 
	 * @see #getMove()
	 * @see #startHover
	 * @see #endHover
	 * @see #hoverDirection
	 * @see environment.logic.Direction
	 * @see environment.logic.Point
	 */
	private void hover() {

		// Retrieve the next x position of the hovering Arganok based on its
		// current direction of travel.
		int nextX = getNextX();

		// If the next x position is beyond the boundaries of the hover then the
		// direction the Arganok is travelling must be reversed.
		if (nextX >= endHover.x || nextX <= startHover.x) {
			hoverDirection = hoverDirection.getOppositeDirection();

		}
		// The direction of the sprite is re-evaluated based on the direction
		// the Arganok is travelling.
		changeSpriteDirection(hoverDirection);

		setX(getNextX());

	}

	/**
	 * Retrieves the next x position of the Arganok, based on the current
	 * <code>hoverDirection</code>.
	 * 
	 * @return <code>int<code> next x value.
	 * 
	 * @see #hoverDirection
	 * @see #hover()
	 * @see environment.logic.Direction
	 */
	private int getNextX() {
		// If the direction of hover is right then add one too the current x
		// position but if the direction is left then subtract one.
		return getX() + ((hoverDirection == Direction.RIGHT) ? 1 : 0) + ((hoverDirection == Direction.LEFT) ? (-1) : 0);
	}

	/**
	 * Initialises the hover bounds based on the Arganok's current position.
	 */
	private void setHoverBounds() {

		// Initialise startHover as a point 100 pixels to the left and
		// endHover as a point 100 pixels to the right.
		startHover = new Point(getX() - 100, getY());
		endHover = new Point(getX() + 100, getY());

	}

	/**
	 * Changes the direction of where the Arganok's sprite is facing based on
	 * the direction specified.
	 * 
	 * @param direction
	 *            <code>Direction</code> That the Arganok should be facing.
	 * 
	 * @see environment.logic.Direction
	 * @see environment.logic.entities.Sprite
	 */
	private void changeSpriteDirection(Direction direction) {

		// Sprites by default face to the right, therefore if the parameter
		// direction is LEFT the sprite must be inverted in the x direction but
		// if the direction is RIGHT then the sprite's direction must be
		// reverted to its original direction.
		if (direction == Direction.LEFT) {
			getSprite().invert();
		} else if (direction == Direction.RIGHT) {
			getSprite().revert();
		}
	}

	/**
	 * This causes the Arganok to appear to chase the player on screen.
	 * 
	 * @param playerPosition
	 *            <code>Point</code> that represents the Players position on
	 *            screen.
	 * @param arganokPosition
	 *            <code>Point</code> that represents this Arganok's position on
	 *            screen.
	 * 
	 * @see environment.logic.Point
	 * @see #seekInX(Point, Point)
	 * @see #seekInY(Point, Point)
	 * @see #setHoverBounds()
	 */
	private void seek(Point playerPosition, Point arganokPosition) {

		// Moves the arganok in the X and Y direction closer to the Players
		// Position.
		seekInX(playerPosition, arganokPosition);
		seekInY(playerPosition, arganokPosition);

		// Resets the hover boundaries so that is the player evades the Arganok
		// by leaving the tracking radius the Arganok will begin hovering form
		// its current position.
		setHoverBounds();

	}

	/**
	 * Moves the Arganok 2% of the distance between the Arganok and the Player,
	 * closer to the Player in the X direction.
	 * 
	 * @param playerPosition
	 *            <code>Point</code> that represents the Players position on
	 *            screen.
	 * @param arganokPosition
	 *            <code>Point</code> that represents this Arganok's position on
	 *            screen.
	 * @see environment.logic.Point
	 */
	private void seekInX(Point playerPosition, Point arganokPosition) {

		// Gets 2% of the absolute difference between players X position and the
		// Arganok's X position.
		double changeInX = (((double) (Math.abs(playerPosition.x - arganokPosition.x))) / 50);

		// If 2% of that distance is greater than zero but less than 1 then the
		// chnageInX is rounded up to 1. Otherwise just convert the double to an
		// int
		if (changeInX > 0 && changeInX < 1) {
			changeInX = 1;
		} else {
			changeInX = ((int) changeInX);
		}

		// If the Arganok is to the left of the player then make the Sprite face
		// towards the player (right) and move the Arganok right. The opposite
		// is
		// performed if the Arganok is right of the player.
		if (playerPosition.x > arganokPosition.x) {
			getSprite().revert();
			setX(getX() + (int) changeInX);

		} else {
			getSprite().invert();
			setX(getX() - (int) changeInX);
		}
	}

	/**
	 * Moves the Arganok 2% of the distance between the Arganok and the Player,
	 * closer to the Player in the Y direction.
	 * 
	 * @param playerPosition
	 *            <code>Point</code> that represents the Players position on
	 *            screen.
	 * @param arganokPosition
	 *            <code>Point</code> that represents this Arganok's position on
	 *            screen.
	 * @see environment.logic.Point
	 */
	private void seekInY(Point playerPosition, Point arganokPosition) {

		// Gets 2% of the absolute difference between players Y position and the
		// Arganok's Y position.
		double changeInY = (((double) (Math.abs(playerPosition.y - arganokPosition.y))) / 50);
		
		// If 2% of that distance is greater than zero but less than 1 then the
		// chnageInY is rounded up to 1. Otherwise just convert the double to an
		// int
		if (changeInY > 0 && changeInY < 1) {
			changeInY = 1;
		} else {
			changeInY = ((int) changeInY);
		}

		// If the Arganok is below of the player then move the Arganok upwards,
		// otherwise move the Arganok downwards.
		if (playerPosition.y > arganokPosition.y) {
			setY(getY() + (int) changeInY);

		} else {
			setY(getY() - (int) changeInY);
		}
	}

	private double distanceToPlayer(Point playerPosition, Point arganokPosition) {

		int xDistance = Math.abs(playerPosition.x - arganokPosition.x);
		int yDistance = Math.abs(playerPosition.y - arganokPosition.y);

		double resultantDistance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));

		return resultantDistance;
	}

}
