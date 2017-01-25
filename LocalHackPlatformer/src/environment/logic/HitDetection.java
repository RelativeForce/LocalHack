package environment.logic;

import environment.logic.entities.Entity;

/**
 * Library for hit detection between objects of type <code>Entity</code>.
 * 
 * @author Joshua_Eddy
 *
 */
public class HitDetection {

	/**
	 * Checks if a specified <code>Entity</code> will collide with another
	 * specified <code>Entity</code> before it reaches a specified
	 * <code>Point</code> from the <strong>first</strong> <code>Entity</code>.
	 * 
	 * @param entity1
	 *            The first <code>Entity</code>.
	 * @param finalPosition
	 *            The <code>Point</code> that denotes the final position of the
	 *            first <code>Entity</code>.
	 * @param entity2
	 *            The second <code>Entity</code>.
	 * @return <code>true</code> if a collision is detected between the two
	 *         specified <code>Entity</code>s and <code>false</code> if not.
	 */
	public static boolean detectHit(Entity entity1, Point finalPosition,
			Entity entity2) {

		if (checkX(entity1, finalPosition.x, entity2)
				&& checkY(entity1, finalPosition.y, entity2)) {
			return true;
		}

		return false;
	}

	private static boolean checkX(Entity entity1, int nextX, Entity entity2) {

		Integer[][] obj1XPerimeter = entity1.getHitBox().getHorizontalBoundaries();
		Integer[][] obj2XPerimeter = entity2.getHitBox().getHorizontalBoundaries();

		int initalX;
		int finalX;

		if (entity1.getX() > nextX) {
			initalX = nextX;
			finalX = entity1.getX();
		} else {
			initalX = entity1.getX();
			finalX = nextX;
		}

		int obj2x = entity2.getX();

		for (int x = initalX; x <= finalX; x++) {
			for (int rowObj1 = 0; rowObj1 < entity1.getGraphicalObject()
					.getHeight(); rowObj1++) {
				for (int rowObj2 = 0; rowObj2 < entity2.getGraphicalObject()
						.getHeight(); rowObj2++) {
					boolean condition1 = (x + obj1XPerimeter[rowObj1][0]) <= (obj2x + obj2XPerimeter[rowObj2][1]);
					boolean condition2 = (x + obj1XPerimeter[rowObj1][1]) >= (obj2x + obj2XPerimeter[rowObj2][0]);
					if (condition1 && condition2) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private static boolean checkY(Entity entity1, int nextY, Entity entity2) {

		Integer[][] obj1YPerimeter = entity1.getHitBox().getVerticalBoundaries();
		Integer[][] obj2YPerimeter = entity2.getHitBox().getVerticalBoundaries();

		int initalY;
		int finalY;
		if (entity1.getY() > nextY) {
			initalY = nextY;
			finalY = entity1.getY();
		} else {
			initalY = entity1.getY();
			finalY = nextY;
		}

		int obj2Y = entity2.getY();

		for (int y = initalY; y <= finalY; y++) {
			for (int colObj1 = 0; colObj1 < entity1.getGraphicalObject()
					.getWidth(); colObj1++) {
				for (int colObj2 = 0; colObj2 < entity2.getGraphicalObject()
						.getWidth(); colObj2++) {
					boolean condition1 = (y + obj1YPerimeter[colObj1][0]) <= (obj2Y + obj2YPerimeter[colObj2][1]);
					boolean condition2 = (y + obj1YPerimeter[colObj1][1]) >= (obj2Y + obj2YPerimeter[colObj2][0]);

					if (condition1 && condition2) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Retrieves the element from a collection of type <code>Entity</code> that
	 * a specified <code>Entity</code> will collide with on its path to a final
	 * position.
	 * 
	 * If entity does not collide with any elements in entities the function
	 * will return </code>null</code>.
	 * 
	 * Otherwise the function will return the <strong>first</strong> element in
	 * the collection that the specified <code>Entity</code> collides with.
	 * 
	 * @param entity
	 *            A <code>Entity</code> that will collide with an element from
	 *            entities .
	 * @param finalPosition
	 *            The <code>Point</code> that denotes the final position that
	 *            entity will have.
	 * @param entities
	 *            The collection of entities that entity may collide with.
	 * @return The <strong>first</strong> element from a collection of type
	 *         <code>Entity</code> that the specified specified
	 *         <code>Entity</code> collided with, if any.
	 */
	public static Entity getObstruction(Entity entity, Point finalPosition,
			Entity[] entities) {

		for (Entity element : entities) {

			if (detectHit(entity, finalPosition, element)) {

				return element;

			}

		}

		return null;
	}

}
