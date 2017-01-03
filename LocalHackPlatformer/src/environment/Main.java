package environment;

import entities.*;
import graphics.*;
import logic.*;
import logic.player.Player;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Stores the Game logic and fundamental functions.
 * 
 * @author Joshua_Eddy
 */
public class Main {

	/**
	 * Store whether game threads are running, set to false to kill the game
	 * threads.
	 */
	public static boolean run;

	/**
	 * Stores the current Player object.
	 */
	public static Player player;

	/**
	 * Stores the current Level object.
	 */
	public static Level level;

	/**
	 * Stores the current DeathScreen object.
	 */
	public static TransitionScreen transitionScreen;
	
	/**
	 * The level that will be loaded upon Screen transition.
	 */
	public static int levelNumber;

	// Private
	private static Thread logicThread;
	private static Thread displayThread;
	private static Display display;
	private static Screen screen;
	

	/**
	 * Starts the game.
	 * 
	 * @param args
	 *            Starting string arguments.
	 */
	public static void main(String[] args) {

		run = true;

		display = new Display(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, "Platformer");
		screen = new Screen(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

		for (int i = 0; i < 3; i++) {
			display.render(screen);
		}

		levelNumber = 1;
		transitionScreen = new TransitionScreen(Color.RED.getRGB());
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

		if (transitionScreen.isFullScreen) {
			start();
		}

		display.handleKeys();
		player.gravity();
		player.checkForDeath();
		player.checkObjectves();

	}

	private static void display() {

		ArrayList<Entity> entities = new ArrayList<Entity>();

		entities.addAll(Level.components);
		entities.addAll(Level.enemies);
		entities.addAll(Level.objectives);
		entities.add(player.getEntity());

		for (Entity entity : entities) {
			screen.addGraphicalObject(entity.getGraphicalObject(), entity.getX(), entity.getY());
		}

		if (transitionScreen.isActive) {
			if (!transitionScreen.isFullScreen && transitionScreen.willIncrement) {
				transitionScreen.increment();
			} else if(!transitionScreen.willIncrement) {
				transitionScreen.decrement();
			}

			screen.addGraphicalObject(transitionScreen.getEntity().getGraphicalObject(),
					transitionScreen.getEntity().getX(), transitionScreen.getEntity().getY());
		}

		display.render(screen);
		screen.clear();
	}

	/**
	 * Starts the level and initialises the player.
	 * 
	 */
	public static void start() {
	
		level = new Level(levelNumber);
		player = new Player(Level.StartPosition.x, Level.StartPosition.y, 20, 20);
		
	}

}
