package Environment;

import entities.*;
import Graphics.*;
import Logic.*;
import java.io.File;
import java.util.ArrayList;

public class Main {

	// Static
	public static boolean run;
	public static Player player;
	public static ArrayList<Entity> components;
	public static ArrayList<Entity> enemies;

	// Private
	private static Thread logicThread;
	private static Thread displayThread;
	private static Display display;
	private static Screen screen;

	public static void main(String[] args) {

		components = new ArrayList<Entity>();
		enemies = new ArrayList<Entity>();
		run = true;
		display = new Display(1000, 500, "Platformer");
		screen = new Screen(1000, 500);

		for (int i = 0; i < 3; i++) {
			display.render(screen);
		}

		player = new Player(20, 20, 20, 20);

		loadLevel(1);

		// Thread initialisation
		logicThread();
		displayThread();
		logicThread.start();
		displayThread.start();

	}

	private static void logicThread() {

		logicThread = new Thread() {

			@Override
			public void run() {

				while (run) {
					try {
						sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
					player.gravity();
					player.checkForDeath();
				}
			}
		};

	}

	private static void displayThread() {

		displayThread = new Thread() {

			@Override
			public void run() {

				while (run) {

					try {
						sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}

					ArrayList<Entity> entities = new ArrayList<Entity>();
					entities.addAll(components);
					entities.addAll(enemies);
					entities.add(player.getEntity());

					for (Entity entity : entities) {
						screen.addGraphicalObject(entity.getGraphicalObject(), entity.getX(), entity.getY());
					}
					display.render(screen);
					screen.clear();
				}

			}
		};

	}

	private static void loadLevel(int levelNumber) {

		File currentDirectory = new File(System.getProperty("user.dir"));
		LevelLoader levelloader = new LevelLoader(currentDirectory.getPath());
		components.addAll(levelloader.getLevel(levelNumber, "component"));
		enemies.addAll(levelloader.getLevel(levelNumber, "enemy"));

	}
}
