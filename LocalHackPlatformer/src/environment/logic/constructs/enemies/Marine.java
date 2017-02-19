package environment.logic.constructs.enemies;

import environment.logic.Point;

/**
 * A marine is a second edition grunt featuring the basic concepts of a grunt
 * but implementing a range attack and slightly altered movement pattern.
 * 
 *
 * @author David_Wightman
 *
 */

public class Marine extends Grunt implements Enemy {

	private int Ammunition;
	private int level;

	public Marine(int ammocount, int level, Point initial) {
		super(initial);
		Ammunition = ammocount;
		this.level = level;

	}
	
	@Override
	public void getMove(){
		
		
		
	}

	public void Fire() {

	}
}
