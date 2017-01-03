package entities;

import java.awt.Color;

/**
 * Describes a door that will start another level when the player intercepts it.
 * @author Joshua_Eddy
 *
 */
public class Door extends Entity{
	
	/**
	 * Constructs a new Door Entity.
	 * @param x The X coordinate of the door.
	 * @param y The Y coordinate of the door.
	 * @param width The width of the door.
	 * @param height The height of the door.
	 * @param doorColor The RGB colour of the door.
	 */
	public Door(int x, int y, int width, int height, Integer doorColor){
		
		if (x >= 0) {
			setX(x);
		} else {
			setX(0);
		}
		if (y >= 0) {
			setY(y);
		} else {
			setY(0);
		}
		
		Rectangle border = new Rectangle(x, y, width, height, Color.black.getRGB());
		Rectangle door = new Rectangle(1, 1, width - 2, height - 2, doorColor);
		Rectangle handle = new Rectangle(((3 * width) / 4),(height / 2), 4, 4, Color.BLACK.getRGB());
		setColor(border.getColor());
		
		Entity[] entities = new Entity[3];
		entities[0] = border;
		entities[1] = door;
		entities[2] = handle;
		
		setGraphicalObject(width, height, entities);
		
	}
	
}
