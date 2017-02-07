package environment.logic.constructs;

import environment.logic.entities.Sprite;

/**
 * An abstract class that denotes aggregates a sprite and stores its own
 * position.
 * 
 * @author Joshua_Eddy
 *
 */
public abstract class Construct {

	private int x;
	private int y;
	private Sprite sprite;

	/**
	 * Constructs a new Construct.
	 * 
	 * @param x
	 *            The <code>int</code> x coordinate of the construct.
	 * @param y
	 *            The <code>int</code> y coordinate of the construct.
	 * @param sprite
	 *            The <code>Sprite</code> that graphically represents the
	 *            construct.
	 */
	public Construct(int x, int y, Sprite sprite) {

		this.setX(x);
		this.setY(y);
		this.sprite = sprite;

	}

	/**
	 * Sets the x coordinate of the construct.
	 * 
	 * @param x
	 *            The new <code>int</code> x coordinate of the construct.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Retrieves the x coordinate of the construct.
	 * 
	 * @return The <code>int</code> x coordinate of the construct.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Retrieves the y coordinate of the construct.
	 * 
	 * @return The <code>int</code> y coordinate of the construct.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y coordinate of the construct.
	 * 
	 * @param y
	 *            The new <code>int</code> y coordinate of the construct.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Retrieves the sprite that graphically represents the construct.
	 * 
	 * @return The <code>Sprite</code> that graphically represents the
	 *         construct.
	 */
	public Sprite getSprite() {
		sprite.setX(getX());
		sprite.setY(getY());
		return sprite;
	}

	/**
	 * Moves the construct in a specified direction.
	 * 
	 * @param changeInX
	 *            The <code>int</code> change in the x coordinate.
	 * @param changeInY
	 *            The <code>int</code> change in the y coordinate.
	 */
	public void move(int changeInX, int changeInY) {
		setX(getX() + changeInX);
		setY(getY() + changeInY);
	}

	@Override
	public boolean equals(Object o) {

		if (o instanceof Construct) {

			Construct otherConstruct = (Construct) o;

			if (otherConstruct.x == this.x && otherConstruct.y == this.y) {

				if (otherConstruct.sprite.equals(this.sprite)) {
					return true;
				}

			}

		}

		return false;
	}

}
