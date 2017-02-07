package environment.logic.entities;

import environment.graphics.objects.*;
import environment.logic.Point;

/**
 * Encapsulates the behaviour of a <code>GraphicalObject<code> that will be
 * displayed on the <code>Screen<code>.
 * 
 * @author Joshua_Eddy
 * @see GraphicalObject
 * @see Screen
 * @see HitBox
 * @see Point
 */
public class Entity {

	private GraphicalObject object;
	private HitBox hitBox;
	private Point position;

	private Entity(int x, int y, GraphicalObject object) {

		position = new Point(x, y);
		this.object = object;
		this.hitBox = new HitBox(object);

	}

	/**
	 * Creates a Rectangle <code>Entity</code> with the details specified as
	 * <code>parameters</code>.
	 * 
	 * @param x
	 *            The <code>int</code> x coordinate of the <code>Entity</code>.
	 * @param y
	 *            The <code>int</code> y coordinate of the <code>Entity</code>.
	 * @param width
	 *            The <code>int</code> width of the <code>Entity</code>.
	 * @param height
	 *            The <code>int</code> height of the <code>Entity</code>.
	 * @param color
	 *            The <code>Integer</code> colour of the <code>Entity</code>.
	 * @return If any of the <code>parameters</code> are invalid, this function
	 *         will return <code>null</code>. Otherwise a <code>Entity</code> of
	 *         type Rectangle with the specified details.
	 */
	public static Entity newRectangle(int x, int y, int width, int height, Integer color) {

		GraphicalObject object = new Rectangle(width, height, color);

		return new Entity(x, y, object);
	}

	/**
	 * Creates a Floor <code>Entity</code> with the details specified as
	 * <code>parameters</code>.
	 * 
	 * @param x
	 *            The <code>int</code> x coordinate of the <code>Entity</code>.
	 * @param y
	 *            The <code>int</code> y coordinate of the <code>Entity</code>.
	 * @param width
	 *            The <code>int</code> width of the <code>Entity</code>.
	 * @param height
	 *            The <code>int</code> height of the <code>Entity</code>.
	 * @param borderColor
	 *            The <code>Integer</code> colour of the space between the boxes
	 *            of the Floor.
	 * @param boxWidth
	 *            The <code>int</code> width of the boxes that are contained
	 *            within the <code>Entity</code>.
	 * @param boxHeight
	 *            The <code>int</code> height of the boxes that are contained
	 *            within the <code>Entity</code>
	 * @param boxColor
	 *            The <code>Integer</code> colour of the squares of the Floor.
	 * @return If any of the <code>parameters</code> are invalid, this function
	 *         will return <code>null</code>. Otherwise a <code>Entity</code> of
	 *         type Floor with the specified details.
	 */
	public static Entity newFloor(int x, int y, int width, int height, Integer borderColor, int boxWidth, int boxHeight,
			Integer boxColor) {

		GraphicalObject object = new Floor(width, height, borderColor, boxWidth, boxHeight, boxColor);

		return new Entity(x, y, object);
	}

	/**
	 * Creates a Door <code>Entity</code> with the details specified as
	 * <code>parameters</code>.
	 * 
	 * @param x
	 *            The <code>int</code> x coordinate of the <code>Entity</code>.
	 * @param y
	 *            The <code>int</code> y coordinate of the <code>Entity</code>.
	 * @param width
	 *            The <code>int</code> width of the <code>Entity</code>.
	 * @param height
	 *            The <code>int</code> height of the <code>Entity</code>.
	 * @param color
	 *            The <code>Integer</code> colour of the <code>Entity</code>.
	 * @return If any of the <code>parameters</code> are invalid, this function
	 *         will return <code>null</code>. Otherwise a <code>Entity</code> of
	 *         type Door with the specified details.
	 */
	public static Entity newDoor(int x, int y, int width, int height, Integer color) {

		GraphicalObject object = new Door(width, height, color);

		return new Entity(x, y, object);

	}

	/**
	 * Creates a SpriteFrame <code>Entity</code> with the details specified as
	 * <code>parameters</code>.
	 * 
	 * @param x
	 *            The <code>int</code> x coordinate of the <code>Entity</code>.
	 * @param y
	 *            The <code>int</code> y coordinate of the <code>Entity</code>.
	 * @param pixels
	 *            The <code>Integer[][]</code> that denotes the pixels of the
	 *            SpriteFrame.
	 * @return If any of the <code>parameters</code> are invalid, this function
	 *         will return <code>null</code>. Otherwise a <code>Entity</code> of
	 *         type SpriteFrame with the specified details.
	 */
	public static Entity newSpriteFrame(int x, int y, Integer[][] pixels) {

		GraphicalObject object = new SpriteFrame(pixels);

		return new Entity(x, y, object);

	}

	/**
	 * Sets the x coordinate value of the <code>Entity<code>.
	 * 
	 * @param x The <code>int</code> x value for the <code>Entity<code>.
	 */
	public void setX(int x) {
		position.x = x;
	}

	/**
	 * Sets the y coordinate value of the <code>Entity<code>.
	 * 
	 * @param y The <code>int</code> y value for the <code>Entity<code>.
	 */
	public void setY(int y) {
		position.y = y;
	}

	/**
	 * Retrieves the x coordinate of the Entity.
	 * 
	 * @return The current <code>int</code> x coordinate of the
	 *         <code>Entity</code>.
	 */
	public int getX() {
		return position.x;
	}

	/**
	 * Retrieves the y coordinate of the <code>Entity<code>.
	 * 
	 * @return The current <code>int<code> y coordinate of the
	 *         <code>Entity<code>.
	 */
	public int getY() {
		return position.y;
	}

	/**
	 * Retrieves the <code>GraphicalObject<code> that denotes the
	 * <code>Entity<code>.
	 * 
	 * @return The <code>GraphicalObject<code> of the <code>Entity<code>.
	 * 
	 * @see GraphicalObject
	 */
	public GraphicalObject getGraphicalObject() {
		return object;
	}

	/**
	 * Retrieves the <code>HitBox<code> of the <code>Entity<code>.
	 * 
	 * @return The <code>HitBox<code> of the <code>Entity<code>.
	 * 
	 * @see HitBox
	 */
	public HitBox getHitBox() {
		return hitBox;
	}

	/**
	 * Checks if another object is a duplicate of <code>this</code>.
	 * 
	 * @param o
	 *            The
	 *            <code>Object<code> that is to be checked against <code>this</code>.
	 * @return <code>boolean</code> of if the the object is identical to
	 *         <code>this</code>.
	 * 
	 * @see Entity
	 */
	@Override
	public boolean equals(Object o) {

		if (o instanceof Entity) {

			Entity otherEntity = (Entity) o;

			if (otherEntity.getX() == this.getX() && otherEntity.getY() == this.getY()
					&& otherEntity.hitBox.equals(this.hitBox) && otherEntity.object.equals(this.object)) {
				return true;
			}

		}

		return false;
	}

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
	public static boolean detectHit(Entity entity1, Point finalPosition, Entity entity2) {

		if (!entity1.equals(entity2)) {
			if (checkX(entity1, finalPosition.x, entity2) && checkY(entity1, finalPosition.y, entity2)) {
				return true;
			}
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
			for (int rowObj1 = 0; rowObj1 < entity1.getGraphicalObject().getHeight(); rowObj1++) {
				for (int rowObj2 = 0; rowObj2 < entity2.getGraphicalObject().getHeight(); rowObj2++) {
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
			for (int colObj1 = 0; colObj1 < entity1.getGraphicalObject().getWidth(); colObj1++) {
				for (int colObj2 = 0; colObj2 < entity2.getGraphicalObject().getWidth(); colObj2++) {
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
