package Environment;

import entities.*;
import Graphics.*;
import Logic.*;
import java.util.ArrayList;

public class Main {

	// Static
	public static boolean run;
	public static Player player;
	public static Level level;
	public static DeathScreen deathScreen;

	// Private
	private static Thread logicThread;
	private static Thread displayThread;
	private static Display display;
	private static Screen screen;
	
	private static int currentLevelNumber;

	public static void main(String[] args) {

		run = true;

		display = new Display(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, "Platformer");
		screen = new Screen(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

		for (int i = 0; i < 3; i++) {
			display.render(screen);
		}

		
		currentLevelNumber = 1;
		deathScreen = new DeathScreen();
		start();

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
					logic();
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
					display();
				}
			}
		};
	}

	private static void logic() {

		display.handleKeys();
		player.gravity();
		player.checkForDeath();

	}

	private static void display() {
		
		ArrayList<Entity> entities = new ArrayList<Entity>();

		entities.addAll(Level.components);
		entities.addAll(Level.enemies);
		entities.add(player.getEntity());

		for (Entity entity : entities) {
			screen.addGraphicalObject(entity.getGraphicalObject(), entity.getX(), entity.getY());
		}
		
		if (player.isDead) {
			deathScreen.increment();
		}
		
		if(deathScreen.getEntity().getGraphicalObject().height > 0 && !player.isDead){
			deathScreen.decrement();
		}
		screen.addGraphicalObject(deathScreen.getEntity().getGraphicalObject(), deathScreen.getEntity().getX(),
				deathScreen.getEntity().getY());
		
		display.render(screen);
		screen.clear();
	}

	public static void start() {
		
		player = new Player(20, 20, 20, 20);

		level = new Level(currentLevelNumber);

	}
	
}
