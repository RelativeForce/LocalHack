package environment.logic.constructs.enemies;

import java.io.File;

/**
 * A marine is a second edition grunt featuring the basic concepts of a grunt
 * but implementing a range attack and slightly altered movement pattern.
 * 
 *
 * @author Wightman
 *
 */

public class Marine extends Grunt implements Enemy {

	private int Ammunition;
	private int level;

	public Marine(int ammocount, int level,Point initial) {
		super(initial);
		Ammunition = ammocount;
		this.level = level;

	}

	public void Fire() {

	}
}
