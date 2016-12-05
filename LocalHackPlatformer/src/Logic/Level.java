package Logic;

import java.io.File;
import java.util.ArrayList;

import Environment.LevelLoader;
import entities.Entity;

public class Level {
	
	public static ArrayList<Entity> components;
	public static ArrayList<Entity> enemies;

	public Level(){
		
		components = new ArrayList<Entity>();
		enemies = new ArrayList<Entity>();
		
		loadLevel(1);
		
	}
	
	public static void loadLevel(int levelNumber) {

		File currentDirectory = new File(System.getProperty("user.dir"));
		LevelLoader levelloader = new LevelLoader(currentDirectory.getPath());
		components.addAll(levelloader.getLevel(levelNumber, "component"));
		enemies.addAll(levelloader.getLevel(levelNumber, "enemy"));

	}
	
	
}
