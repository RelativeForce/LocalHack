package directions;

/**
 * Handles all miscellaneous functions related to directions.
 * 
 * @author Joshua_Eddy
 *
 */
public class DirectionHandler {

	/**
	 * Inverts a direction that is passed as a parameter and returns the inverse of that direction.
	 * @param direction The direction to be inverted.
	 * @return The inverse of the direction passed as a parameter.
	 */
	public static Direction invert(Direction direction) {

		switch (direction) {
		case UP:
			return Direction.DOWN;
		case DOWN:
			return Direction.UP;
		case LEFT:
			return Direction.RIGHT;
		case RIGHT:
			return Direction.LEFT;
		default:
			return null;
		}

	}

}
