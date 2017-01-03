package logic;

import java.awt.Color;
import entities.Entity;
import entities.Rectangle;

/**
 * A Grunt is a type of Enemy that...
 * 
 * @author Joshua_Eddy
 */
public class Grunt implements Enemy {

	private Entity gruntEntity;

	/**
	 * Constructs a new Grunt object.
	 * 
	 * @param initalPosition The initial position of the Grunt object.
	 */
	public Grunt(Point initalPosition) {
		gruntEntity = new Rectangle(0, 0, 20, 20, Color.GREEN.getRGB());
	}

	@Override
	public Point move() {

		// David This is where you write your stuff
		// self.note ( will need : collison values for all entities,players
		// movement commands(with negative)

		return null;
	}

	@Override
	public Entity getEntity() {

		return gruntEntity;
	}

}
