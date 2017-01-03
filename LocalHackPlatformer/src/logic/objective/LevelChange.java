package logic.objective;

import java.awt.Color;
import entities.Door;
import entities.Entity;
import environment.Main;

/**
 * When this object is intercepted by the player, the game will change level.
 * @author Joshua_Eddy
 *
 */
public class LevelChange implements Objective{
	
	private int levelLink;
	private Entity entity;
	
	/**
	 * Constructs a LevelChange that when intercepted by the player will change the current level.
	 * @param levelLink the level that this door links to.
	 */
	public LevelChange(int x, int y, int width, int height, Integer doorColor, int levelLink){
		this.levelLink = levelLink;
		entity = new Door(x, y, width, height, doorColor);
	}
	
	/**
	 * Returns the level that this door links to.
	 * @return The level number that this door links to.
	 */
	public int getLevelLink(){
		return levelLink;
	}
	
	@Override
	public Entity getEntity(){
		return entity;
	}
	
	@Override
	public void action() {
		
		Main.transitionScreen.setColor(Color.CYAN.getRGB());
		Main.transitionScreen.isActive = true;
		Main.levelNumber = levelLink;
		
	}

}
