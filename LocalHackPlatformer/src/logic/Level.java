package logic;

import java.io.File;
import java.util.ArrayList;

import entities.Entity;
import environment.Constants;
import environment.LevelLoader;

/**
 * Stores all the objects and details associated with a level.
 * @author Joshua_Eddy
 *
 */
public class Level {
	
	/**
	 * Stores all the component entities of the level. Player collision is normal with items in this ArrayList.
	 */
	public static ArrayList<Entity> components;
	
	/**
	 * Stores all the enemy entities of the level. Player collision with items in this ArrayList causes level restart.
	 */
	public static ArrayList<Entity> enemies;
	
	/**
	 * Stores all the objective entities of the level. Player collision with items in this ArrayList cause custom events.
	 */
	public static ArrayList<Entity> objectives;
	
	/**
	 * Stores the maximum length of the current level.
	 */
	public static int Length;
	
	/**
	 * Stores the X position of the start of the current level in relation to the left side of the screen.
	 */
	public static int StartX;
	
	public static Point StartPosition;

	/**
	 * Constructs a new Level object.
	 * @param levelNumber The number of the level that is to be loaded from computer memory.
	 */
	public Level(int levelNumber){
		
		components = new ArrayList<Entity>();
		enemies = new ArrayList<Entity>();
		objectives = new ArrayList<Entity>();
		StartX = 0;
		StartPosition = Constants.DEFAULT_POSITION;
		loadLevel(levelNumber);
		
	}
	
	private void loadLevel(int levelNumber) {

		File currentDirectory = new File(System.getProperty("user.dir"));
		LevelLoader levelloader = new LevelLoader(currentDirectory.getPath());
		levelloader.getLevel(levelNumber, "levelDetails");
		components.addAll(levelloader.getLevel(levelNumber, "component"));
		enemies.addAll(levelloader.getLevel(levelNumber, "enemy"));
		objectives.addAll(levelloader.getLevel(levelNumber, "objective"));

	}
	
	/**
	 * Moves the level by a specified to give the illusion of the level scrolling.
	 * @param changeInX The amount the level should scroll in pixels.
	 */
	public void moveLevel(int changeInX){
		
		move(components, changeInX);
		move(enemies, changeInX);
		move(objectives, changeInX);
		StartX += changeInX;
		
	}
	
	private void move(ArrayList<Entity> entities, int changeInX){
		
		for(Entity entity : entities){
			entity.setX(entity.getX() + changeInX);;
		}
		
	}
	
}
