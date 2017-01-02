package Logic;

import entities.Entity;

/**
 * Contains the Logic for detecting hits between Entities.
 * @author Joshua_Eddy
 *
 */
public class HitDetection {

	/**
	 * Checks if entity1 will collide with entity2 before it reaches the location of nextEntity1.
	 * @param entity1 The initial position of the first Entity.
	 * @param nextEntity1 The final position of the first Entity.
	 * @param entity2 The current position of the second Entity.
	 * @return Whether entity1 has collided with entity2.
	 */
	public static boolean detectHit(Entity entity1, Entity nextEntity1, Entity entity2) {

		if (checkX(entity1, nextEntity1, entity2) && checkY(entity1, nextEntity1, entity2)) {
			return true;
		}

		return false;
	}

	private static boolean checkX(Entity entity1, Entity nextEntity1, Entity entity2) {

		Integer[][] obj1XPerimeter = entity1.getHitBox().getXPerimeter();
		Integer[][] obj2XPerimeter = entity2.getHitBox().getXPerimeter();

		int initalX;
		int finalX;

		if (entity1.getX() > nextEntity1.getX()) {
			initalX = nextEntity1.getX();
			finalX = entity1.getX();
		} else {
			initalX = entity1.getX();
			finalX = nextEntity1.getX();
		}

		int obj2x = entity2.getX();

		for (int x = initalX; x <= finalX; x++) {
			for (int rowObj1 = 0; rowObj1 < entity1.getGraphicalObject().height; rowObj1++) {
				for (int rowObj2 = 0; rowObj2 < entity2.getGraphicalObject().height; rowObj2++) {
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

	private static boolean checkY(Entity entity1, Entity nextEntity1, Entity entity2) {

		Integer[][] obj1YPerimeter = entity1.getHitBox().getYPerimeter();
		Integer[][] obj2YPerimeter = entity2.getHitBox().getYPerimeter();

		int initalY;
		int finalY;
		if (entity1.getY() > nextEntity1.getY()) {
			initalY = nextEntity1.getY();
			finalY = entity1.getY();
		} else {
			initalY = entity1.getY();
			finalY = nextEntity1.getY();
		}

		int obj2Y = entity2.getY();

		for (int y = initalY; y <= finalY; y++) {
			for (int colObj1 = 0; colObj1 < entity1.getGraphicalObject().width; colObj1++) {
				for (int colObj2 = 0; colObj2 < entity2.getGraphicalObject().width; colObj2++) {
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

}
