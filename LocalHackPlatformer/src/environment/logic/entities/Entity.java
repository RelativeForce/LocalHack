package environment.logic.entities;

import environment.Constants;
import environment.graphics.objects.Door;
import environment.graphics.objects.Floor;
import environment.graphics.objects.GraphicalObject;
import environment.graphics.objects.Rectangle;
import environment.graphics.objects.SpriteFrame;
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
	private Point position = new Point(Constants.DEFAULT_POSITION);

	private Entity(int x, int y, GraphicalObject object) {

		setX(x);
		setY(y);
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
	public static Entity Rectangle(int x, int y, int width, int height, Integer color) {

		Object[] eSD = new Object[3];

		eSD[0] = width;
		eSD[1] = height;
		eSD[2] = color;

		GraphicalObject object = new Rectangle(eSD);

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
	public static Entity Floor(int x, int y, int width, int height, Integer borderColor, int boxWidth, int boxHeight,
			Integer boxColor) {

		Object[] eSD = new Object[6];

		eSD[0] = width;
		eSD[1] = height;
		eSD[2] = borderColor;
		eSD[3] = boxWidth;
		eSD[4] = boxHeight;
		eSD[5] = boxColor;

		GraphicalObject object = new Floor(eSD);

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
	public static Entity Door(int x, int y, int width, int height, Integer color) {

		Object[] eSD = new Object[3];

		eSD[0] = width;
		eSD[1] = height;
		eSD[2] = color;

		GraphicalObject object = new Door(eSD);

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
	public static Entity SpriteFrame(int x, int y, Integer[][] pixels) {

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
	 * @return <code>boolean</code> of if the the object is identical to <code>this</code>.
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

}
