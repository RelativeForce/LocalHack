package environment.logic.constructs.objectives;

import java.awt.Color;

import environment.Main;
import environment.logic.Level;
import environment.logic.constructs.Construct;
import environment.logic.entities.Entity;
import environment.logic.entities.Sprite;

/**
 * When this object is intercepted by the player, the game will change level.
 * @author Joshua_Eddy
 *
 */
public class LevelChange extends Construct implements Objective{
	
	private int levelLink;
	

	/**
	 * Constructs a LevelChange that when intercepted by the player will change the current level.
	 * @param door The door that will change the level when intercepted by the player.
	 * @param levelLink The level that this door links to.
	 */
	public LevelChange(int x, int y, Entity door, int levelLink){
		super(door.getX(), door.getY(), new Sprite(door, door.getX(), door.getY()));
		this.levelLink = levelLink;
	}
	
	/**
	 * Returns the level that this door links to.
	 * @return The level number that this door links to.
	 */
	public int getLevelLink(){
		return levelLink;
	}
	
	@Override
	public void action() {
		
		Main.transitionScreen.setColor(Color.CYAN.getRGB());
		Main.transitionScreen.isActive = true;
		Level.currentLevel = levelLink;
		
	}

}
