package environment.logic;

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
     * Retrieves the opposite direction to <code>this</code>.
     * @return The opposite <code>enum</code> direction.
     */
    public Direction getOppositeDirection() {
        return opposite;
    }
	
}