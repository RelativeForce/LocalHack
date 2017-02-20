package environment.logic.constructs.weapons;

import environment.Constants;
import environment.logic.constructs.Construct;
import environment.logic.entities.Sprite;

public class Projectile extends Construct{

	private double Xvelocity;
	private double Yvelocity;
	private double DROP = 0.1;
	private double AIR_RESISTANCE = 0.4;
	
	
	public Projectile(int x, int y) {		
		super(x, y, new Sprite(Constants.GRUNT_SPRITE_DETAILS, x, y));
		Xvelocity = 4;
		Yvelocity = -5;
		
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	
	public void updatePosition(){
		this.x += Xvelocity;
		this.y += Yvelocity;
		
		if(Xvelocity < 0){
			Xvelocity += AIR_RESISTANCE;
		}else{
			Xvelocity += -(AIR_RESISTANCE);
		}
		
		if(Yvelocity < 0){
			Yvelocity += -(DROP);
		}else{
			Yvelocity += DROP;
		}
		
		
	}
	
}
