package entities;

import environment.Constants;
import graphics.objects.Door;
import graphics.objects.Floor;
import graphics.objects.GraphicalObject;
import graphics.objects.Rectangle;
import graphics.objects.SpriteFrame;
import logic.Point;

/**
 * Stores the information for graphical objects to be draw on screen.
 * 
 * @author Joshua_Eddy
 */
public class Entity {

	private GraphicalObject object;
	private EntityType type;
	private HitBox hitBox;
	private Point position = new Point(Constants.DEFAULT_POSITION);

	private Entity(EntityType type, Object[] details) {

		int x = (int) details[0];
		int y = (int) details[1];

		setX(x);
		setY(y);

		// eSD = entity Specific Details
		Object[] eSD;

		switch (type) {
		case RECTANGLE:

			eSD = new Object[3];

			eSD[0] = (int) details[2];
			eSD[1] = (int) details[3];
			eSD[2] = (Integer) details[4];

			object = new Rectangle(eSD);
			break;
		case DOOR:

			eSD = new Object[3];

			eSD[0] = (int) details[2];
			eSD[1] = (int) details[3];
			eSD[2] = (Integer) details[4];

			object = new Door(eSD);
			break;
		case FLOOR:

			eSD = new Object[6];

			eSD[0] = (int) details[2];
			eSD[1] = (int) details[3];
			eSD[2] = (Integer) details[4];
			eSD[3] = (int) details[5];
			eSD[4] = (int) details[6];
			eSD[5] = (Integer) details[7];

			object = new Floor(eSD);
			break;
		case SPRITEFRAME:

			Integer[][] pixels = (Integer[][]) details[2];

			object = new SpriteFrame(pixels);
			break;
		default:
			System.out.println("Invalid EntityType");
			break;
		}

		if (object != null) {
			hitBox = new HitBox(object);
		}
	}
	
	public static Entity Rectangle(int x, int y, int width, int height, Integer color){
		
		Object[] eSD = new Object[5];
		eSD[0] = x;
		eSD[1] = y;
		eSD[2] = width;
		eSD[3] = height;
		eSD[4] = color;
		
		return new Entity(EntityType.RECTANGLE, eSD);
	}
	
	public static Entity Floor(int x, int y, int width, int height, Integer borderColor, int boxWidth, int boxHeight, Integer boxColor){
		
		Object[] eSD = new Object[8];
		eSD[0] = x;
		eSD[1] = y;
		eSD[2] = width;
		eSD[3] = height;
		eSD[4] = borderColor;
		eSD[5] = boxWidth;
		eSD[6] = boxHeight;
		eSD[7] = boxColor;
		
		
		return new Entity(EntityType.FLOOR, eSD);
	}
	
	public static Entity Door(int x, int y, int width, int height, Integer color){
		
		Object[] eSD = new Object[5];
		eSD[0] = x;
		eSD[1] = y;
		eSD[2] = width;
		eSD[3] = height;
		eSD[4] = color;
		
		return new Entity(EntityType.DOOR, eSD);
		
	}
	
	public static Entity SpriteFrame(int x, int y, Integer[][] pixels){
		
		Object[] eSD = new Object[3];
		eSD[0] = x;
		eSD[1] = y;
		eSD[2] = pixels;
		
		return new Entity(EntityType.SPRITEFRAME, eSD);
		
	}

	/**
	 * Sets the X value of the Entity.
	 * 
	 * @param x
	 *            The new X value for the Entity.
	 */
	public void setX(int x) {
		position.x = x;
	}

	/**
	 * Sets the Y value of the Entity.
	 * 
	 * @param y
	 *            The new Y value for the Entity.
	 */
	public void setY(int y) {
		position.y = y;
	}

	/**
	 * Retrieves the X coordinate of the Entity.
	 * 
	 * @return The current X coordinate of the Entity.
	 */
	public int getX() {
		return position.x;
	}

	/**
	 * Retrieves the Y coordinate of the Entity.
	 * 
	 * @return The current Y coordinate of the Entity.
	 */
	public int getY() {
		return position.y;
	}

	/**
	 * Retrieves the GraphicalObject from the Entity.
	 * 
	 * @return The GraphicalObject stored in the Entity.
	 */
	public GraphicalObject getGraphicalObject() {
		return object;
	}

	/**
	 * Returns the HitBox of the Entity.
	 * 
	 * @return The HitBox of the Entity.
	 */
	public HitBox getHitBox() {
		return hitBox;
	}

	/**
	 * Retrieves the Type of this instance of an Entity.
	 * 
	 * @return The Type of this instance of an Entity.
	 */
	public EntityType getType() {
		return type;
	}
}
