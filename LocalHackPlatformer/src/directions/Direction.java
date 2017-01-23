package directions;

/**
 * Directions that can be passed and compared.
 * @author Joshua_Eddy
 */
public enum Direction {

	UP,
	DOWN,
	LEFT,
	RIGHT;
	
	private Direction opposite;

    static {
        UP.opposite = DOWN;
        DOWN.opposite = UP;
        RIGHT.opposite = LEFT;
        LEFT.opposite = RIGHT;
    }

    /**
     * @return The opposite <code>Direction</code> to <code>this</code>.
     */
    public Direction getOppositeDirection() {
        return opposite;
    }
	
}