package Logic;

import java.awt.Color;
import entities.Entity;
import entities.Rectangle;

public class Grunt implements Enemy{
	
	private Entity gruntEntity;
	

	public Grunt(Point initalPosition){
		
		gruntEntity = new Rectangle(0 , 0, 20, 20, Color.GREEN.getRGB());
		
	}
	
	
	@Override
	public Point move() {

		// David This is where you write your stuff
		//self.note ( will need : collison values for all entities,players movement commands(with negative)
		
		return null;
	}


	@Override
	public Entity getEntity() {
		
		return gruntEntity;
	}
	
}
