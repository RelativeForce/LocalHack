package environment.logic.constructs.weapons;

import java.util.ArrayList;

import environment.logic.constructs.Construct;
import environment.logic.entities.Sprite;

public class FlameThrower extends Weapon{

	private ArrayList <Projectile> fuel;
	
	
	public FlameThrower(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		
		fuel = new ArrayList <Projectile>();
		
	}
	
	public void fire(){
		
	}
	
	
	
}
