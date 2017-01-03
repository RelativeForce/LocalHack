package logic;

import java.io.File;
import java.util.ArrayList;

import entities.Entity;
import environment.Constants;
import environment.LevelLoader;
import logic.enemy.Enemy;
import logic.objective.Objective;

/**
 * Stores all the objects and details associated with a level.
 * @author Joshua_Eddy
 *
 */
public class Level {
	
	/**
	 * Stores the maximum length of the current level.
	 */
	public static int Length;
	
	/**
	 * Stores the X position of the start of the current level in relation to the left side of the screen.
	 */
	public static int StartX;
	
	/**
	 * Stores the position the player will start at when the level is played.
	 */
	public static Point StartPosition;

	private ArrayList<Entity> components;

	private ArrayList<Enemy> enemies;
	
	private ArrayList<Objective> objectives;
	
	/**
	 * Constructs a new Level object.
	 * @param levelNumber The number of the level that is to be loaded from computer memory.
	 */
	public Level(int levelNumber){
		
		components = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();
		objectives = new ArrayList<Objective>();
		StartX = 0;
		StartPosition = Constants.DEFAULT_POSITION;
		loadLevel(levelNumber);
		
	}
	
	/**
	 * Moves the level by a specified to give the illusion of the level scrolling.
	 * @param changeInX The amount the level should scroll in pixels.
	 */
	public void moveLevel(int changeInX){
		
		moveComponents(changeInX);
		moveEnemies(changeInX);
		moveObjectives(changeInX);
		StartX += changeInX;
		
	}
	
	/**
	 * Returns an ArrayList of all the Entities in the Level.
	 * @return ArrayList of all the Entities in the Level.
	 */
	public ArrayList<Entity> getAll(){
		
		ArrayList<Entity> entities = new ArrayList<Entity>();
		for(Entity entity: components){
			entities.add(entity);
		}
		for(Enemy enemy: enemies){
			entities.add(enemy.getEntity());
		}
		for(Objective objective: objectives){
			entities.add(objective.getEntity());
		}
		
		return entities;
	}
	
	/**
	 * Returns an ArrayList of all the Enemy Entities in the Level.
	 * @return ArrayList of all the Enemy Entities in the Level.
	 */
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	
	/**
	 * Returns an ArrayList of all the Component Entities in the Level.
	 * @return ArrayList of all the Component Entities in the Level.
	 */
	public ArrayList<Entity> getComponents(){
		return components;
	}
	
	/**
	 * Returns an ArrayList of all the Objectives in the Level.
	 * @return ArrayList of all the Objectives in the Level.
	 */
	public ArrayList<Objective> getObjectives(){
		return objectives;
	}
	
	private void loadLevel(int levelNumber) {

		File currentDirectory = new File(System.getProperty("user.dir"));
		LevelLoader levelloader = new LevelLoader(currentDirectory.getPath());
		levelloader.getLevelDetails(levelNumber);
		components.addAll(levelloader.getComponents(levelNumber));
		enemies.addAll(levelloader.getEnemies(levelNumber));
		objectives.addAll(levelloader.getObjectives(levelNumber));

	}
	
	private void moveComponents(int changeInX){
		
		for(Entity entity : components){
			entity.setX(entity.getX() + changeInX);;
		}
		
	}
	
	private void moveEnemies(int changeInX){
		for(Enemy enemy : enemies){
			enemy.getEntity().setX(enemy.getEntity().getX() + changeInX);
		}
	}
	
	private void moveObjectives(int changeInX){
		
		for(Objective objective : objectives){
			objective.getEntity().setX(objective.getEntity().getX() + changeInX);;
		}
	}
	
}
