package Logic;

import java.io.File;
import java.util.ArrayList;

import Environment.LevelLoader;
import entities.Entity;

public class Level {
	
	public static ArrayList<Entity> components;
	public static ArrayList<Entity> enemies;
	public static int Length;
	public static int StartX;

	public Level(int levelNumber){
		
		components = new ArrayList<Entity>();
		enemies = new ArrayList<Entity>();
		StartX = 0;
		loadLevel(levelNumber);
		
	}
	
	public void loadLevel(int levelNumber) {

		File currentDirectory = new File(System.getProperty("user.dir"));
		LevelLoader levelloader = new LevelLoader(currentDirectory.getPath());
		levelloader.getLevel(levelNumber, "levelDetails");
		components.addAll(levelloader.getLevel(levelNumber, "component"));
		enemies.addAll(levelloader.getLevel(levelNumber, "enemy"));
		

	}
	
	public void moveLevel(int changeInX){
		
		move(components, changeInX);
		move(enemies, changeInX);
		StartX += changeInX;
		
	}
	
	private void move(ArrayList<Entity> entities, int changeInX){
		
		for(Entity entity : entities){
			entity.setX(entity.getX() + changeInX);;
		}
		
	}
	
}
