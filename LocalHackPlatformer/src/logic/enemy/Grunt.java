package logic.enemy;

import java.awt.Color;
import entities.Entity;
import entities.Rectangle;
import logic.Point;

/**
 * A Grunt is a type of Enemy that moves along the floor until it hits reaches
 * the end of the floor. When it reaches the end of the floor it turns back.
 * 
 * @author Joshua_Eddy, David_Whiteman
 */
public class Grunt implements Enemy {

	private Entity gruntEntity;

	/**
	 * Constructs a new Grunt object.
	 * 
	 * @param initalPosition
	 *            The initial position of the Grunt object.
	 */
	public Grunt(Point inital) {
		gruntEntity = new Rectangle(inital.x, inital.y, 20, 20, Color.GREEN.getRGB());
	}

	@Override
	public Point move() {

		// David This is where you write your stuff
		// self.note ( will need : collision values for all entities,players
		// movement commands(with negative)

		return null;
	}

	@Override
	public Entity getEntity() {

		return gruntEntity;
	}

}
